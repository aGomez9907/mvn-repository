package com.solvd.laba.connections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.*;

public class ConnectionTask {
    private static final int EXECUTE_QUERY_TIMEOUT = 2500;
    private static Logger LOGGER = LogManager.getLogger(ConnectionTask.class);

    private static ConnectionPool connectionPool = new ConnectionPool(5);
    public static void main(String[] args) {

        final int MAX_THREADS = 7;

        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(MAX_THREADS);

        try (ThreadPoolExecutor ex = new ThreadPoolExecutor(MAX_THREADS, MAX_THREADS, 4, TimeUnit.SECONDS, queue)) {
            for (int i = 0; i < MAX_THREADS; i++) {
                Runnable connection = () -> {
                    try {
                        Connection connection1 = connectionPool.getConnection();
                        connection1.connect();
                        try {
                            Thread.sleep(EXECUTE_QUERY_TIMEOUT);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        connection1.disconnect();
                        connectionPool.disconnect(connection1);
                    } catch (RuntimeException e) {
                        LOGGER.error(e.getMessage());
                    }
                };
                ex.execute(connection);
            }

        }
        LOGGER.info("Process completed. Finished all threads");
    }
}






