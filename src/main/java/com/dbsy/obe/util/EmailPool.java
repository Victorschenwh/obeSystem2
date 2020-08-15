package com.dbsy.obe.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailPool {
    private static ExecutorService execute = Executors.newFixedThreadPool(4);

    public static ExecutorService get() {
        return execute;
    }

    public static void close() {
        execute.shutdown();
    }
}