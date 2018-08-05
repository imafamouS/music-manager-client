package com.infamous.data.connector;

public interface IRMIConnector extends Connector {

	
	public static final String HOST = "localhost";
	public static final String PORT = "1099";
	public static final String NAME_CONNECTOR = "connector";
	public static final String PATTERN_RMI_URL = "service:jmx:rmi://%s/jndi/rmi://%s:%s/%s";


	<T>  T getBean(Class<T> clazz, String name, boolean isBroadcast);
	
}
