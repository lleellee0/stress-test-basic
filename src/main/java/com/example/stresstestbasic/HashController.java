package com.example.stresstestbasic;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class HashController {

    private final Map<String, String> cashHashResult = new ConcurrentHashMap<>();

    @GetMapping("/no-cache-hash-string")
    public String noCacheHashString(@RequestParam String input) {
        return calculateHash(input);
    }

    @GetMapping("/cached-hash-string")
    public String cachedHashString(@RequestParam String input) {
        if (cashHashResult.containsKey(input)) {
            return cashHashResult.get(input);
        }

        String hashedResult = calculateHash(input);
        cashHashResult.put(input, hashedResult);
        return hashedResult;
    }

    private String calculateHash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            for (int i = 0; i < 50000; i++) {
                byte[] bytes = input.getBytes();
                byte[] hashedBytes = md.digest(bytes);
                input = bytesToHex(hashedBytes);
                md.reset();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return input;
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte aByte : bytes) {
            result.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }

}
