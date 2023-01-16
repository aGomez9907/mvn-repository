package com.solvd.laba.connections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Vector;


public class ConnectionPool {
        private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

        protected int size;
        protected Vector<Connection> connections;

        public ConnectionPool(int size) {
            this.size = size;
            connections = new Vector<>(size) {
            };
        }

        private void connect(Connection connection) {
            connections.add(connection);
        }

        public void disconnect(Connection connection) {
            connections.remove(connection);
        }

        public synchronized Connection getConnection() {

            Connection connection;

            if (connections.size() < size) {
                connection = new Connection("Thread #" + (connections.size() + 1));
                connect(connection);
                return connection;

            } else {
                LOGGER.info("No free slots, connection added to queue");
                int count = 20;
                while (count-- > 0) {
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }


                    if (connections.size() < size) {
                        connection = new Connection("Thread #" + (connections.size() + 1));
                        disconnect(connection);
                        return connection;
                    }
                }
                throw new RuntimeException("Error. No connections available.");

            }
        }
    }


