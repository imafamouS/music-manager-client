package com.infamous.data;

import com.infamous.data.connector.Connector;
import com.infamous.service.Service;

public abstract class DataRepository<S extends Service> implements Repository {

	protected Connector connector;
	protected S service;

	public DataRepository() {

	}

	public DataRepository(Connector connector, S service) {
		this.connector = connector;
		this.service = service;
	}

	public Connector getConnector() {
		return connector;
	}

	public void setConnector(Connector connector) {
		this.connector = connector;
	}

	public Service getService() {
		return service;
	}

	public void setService(S service) {
		this.service = service;
	}

}
