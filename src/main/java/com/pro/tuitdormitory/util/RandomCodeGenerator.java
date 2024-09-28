package com.pro.tuitdormitory.util;

import java.util.UUID;

public class RandomCodeGenerator {
    public static String generateReferralCode() {
        return UUID.randomUUID().toString().substring(0, 8); // Generate 8-character code
    }
}
