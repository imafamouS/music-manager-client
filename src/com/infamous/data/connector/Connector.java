package com.infamous.data.connector;

import java.io.IOException;

public interface Connector {

	void open() throws IOException;

	void close() throws IOException;

}
