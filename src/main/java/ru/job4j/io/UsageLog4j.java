package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b = 1;
        short s = 20000;
        int i = 123456;
        long l = 12345678900L;
        float f = 0.00222F;
        double d = 0.123123;
        char ch = 80;
        boolean tr = true;

        LOG.debug("{}, {}, {}, {}, {}, {}, {}, {}", b, s, i, l, f, d, ch, tr);
    }
}