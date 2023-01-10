package com.solvd.laba.connections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Connection {


    private static Logger LOGGER = LogManager.getLogger(Connection.class);

    public void pinging(String message) {
        LOGGER.info("pinging " + message);
    }

    public void auth(String message) {
        LOGGER.info("auth " + message);
    }

    public void info(String message) {
        LOGGER.info("info " + message);
    }

    public void executeQuery(String message) {
        LOGGER.info("executeQuery " + message);
    }

    public void close(String message) {
        LOGGER.info("close " + message);
    }
}

