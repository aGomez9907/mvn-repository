package com.solvd.laba.connections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class ConnectionTask {
    private static final int FREE_POOL_TIMEOUT = 500;
    private static final int EXECUTE_QUERY_TIMEOUT = 250;
    private static Logger LOGGER = LogManager.getLogger(ConnectionTask.class);
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    public static void main(String[] args) {
        Runnable r = () -> {
            Thread currentThread = Thread.currentThread();
            String currentThreadName = currentThread.getName();

            try {
                Thread.sleep(500);
                while (!CONNECTION_POOL.hasFreeConnections()) {
                    LOGGER.info(currentThreadName + " in queue");
                    Thread.sleep(FREE_POOL_TIMEOUT);
                }
                createConnectionUsingThreadName(currentThreadName);
            } catch (InterruptedException e) {
                LOGGER.info(e.getMessage());
            }
        };
        run(r);
    }

    public static void createConnectionUsingThreadName(String threadName) throws InterruptedException {
        Connection connection = CONNECTION_POOL.connect();
        connection.pinging(threadName);
        connection.auth(threadName);
        connection.info(threadName);
        connection.executeQuery(threadName);
        connection.close(threadName);

        Thread.sleep(EXECUTE_QUERY_TIMEOUT);

        CONNECTION_POOL.disconnect(connection);
    }

    public static void run(Runnable r) {
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            threads.add(new Thread(r, "thread " + i));
            threads.get(i).start();
        }
    }
}