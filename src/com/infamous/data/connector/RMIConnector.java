package com.infamous.data.connector;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import com.infamous.logging.Log;

public class RMIConnector implements IRMIConnector {

	public static final String URL;

	static {
		URL = String.format(PATTERN_RMI_URL, IRMIConnector.HOST, IRMIConnector.HOST, IRMIConnector.PORT,
				IRMIConnector.NAME_CONNECTOR);
	}

	private JMXConnector connector;
	private MBeanServerConnection connection;

	private static volatile RMIConnector sInstance;

	private RMIConnector() {
		init();
	}

	public static synchronized RMIConnector getInstance() {
		Log.i("Get instance RMIConnector");
		if (sInstance == null) {
			synchronized (RMIConnector.class) {
				if (sInstance == null) {
					sInstance = new RMIConnector();
				}
			}
		}

		return sInstance;
	}

	@Override
	public void open() {

		try {
			if (connector == null || connection == null) {
				init();
			}
			if (connector != null) {
				connection = connector.getMBeanServerConnection();
			}
			Log.i("Open connection rmi successfully");

		} catch (Exception ex) {
			Log.i("Failure to open connection rmi");

			ex.printStackTrace();
			Log.e(ex.getMessage(), ex);
		}

	}

	@Override
	public void close() {
		Log.d("Close connection rmi");

		try {
			if (connector != null) {
				connector.close();
			}
			Log.i("Close connection rmi successfully");

		} catch (Exception ex) {
			Log.i("Failure to close connection rmi");

			ex.printStackTrace();
			Log.e(ex.getMessage(), ex);
		}
	}

	@Override
	public <T> T getBean(Class<T> clazz, String name, boolean isBroadcast) {
		Log.i("Get MBean " + name);

		try {
			ObjectName mbeanName = new ObjectName(name);
			return MBeanServerInvocationHandler.newProxyInstance(connection, mbeanName, clazz, isBroadcast);
		} catch (Exception ex) {
			Log.i("Failure to get bean " + name);

			ex.printStackTrace();
			Log.e(ex.getMessage(), ex);
		}
		return null;

	}

	public MBeanServerConnection getConnection() {
		return connection;
	}

	private void init() {

		try {
			JMXServiceURL url = new JMXServiceURL(URL);
			connector = JMXConnectorFactory.connect(url);

		} catch (MalformedURLException ex) {
			ex.printStackTrace();
			Log.e(ex.getMessage(), ex);

		} catch (IOException ex) {
			ex.printStackTrace();
			Log.e(ex.getMessage(), ex);
		}
	}

}
