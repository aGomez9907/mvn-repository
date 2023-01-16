package com.solvd.laba.connections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Connection {
    protected final String name;
    protected boolean isAvailable;
    private static final Logger LOGGER = LogManager.getLogger(Connection.class);


    public Connection(String name) {
        this.name = name;
        LOGGER.info("Connection " + name + " was created");
    }

    void connect() {
        LOGGER.info("Connecting " + name);
        isAvailable = false;
    }

    void disconnect() {
        LOGGER.info("Disconnecting " + name);
        isAvailable = true;
    }
}


