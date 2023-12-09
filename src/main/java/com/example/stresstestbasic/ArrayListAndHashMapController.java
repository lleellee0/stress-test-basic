package com.example.stresstestbasic;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ArrayListAndHashMapController {

    private static final int N = 10_000_000; // 요소 개수

    private final List<Integer> arrayList = new ArrayList<>();
    private final Map<Integer, String> hashMap = new HashMap<>();

    public ArrayListAndHashMapController() {
        // ArrayList 초기화
        for (int i = 0; i < N; i++) {
            arrayList.add(i);
        }

        // HashMap 초기화
        for (int i = 0; i < N; i++) {
            hashMap.put(i, "Value" + i);
        }
    }

    // 시간복잡도 : O(n)
    @GetMapping("/arraylist")
    public String arrayListPerformance(@RequestParam Integer target) {
        long startTime = System.currentTimeMillis();

        Integer result = arrayList.stream()
                .filter(value -> value.equals(target))
                .findFirst()
                .orElse(null);

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        return "ArrayList Lookup Time: " + elapsedTime + " ms, Founded: " + result;
    }

    // 시간복잡도 : O(1)
    @GetMapping("/hashmap")
    public String hashMapPerformance(@RequestParam Integer target) {
        long startTime = System.currentTimeMillis();

        String value = hashMap.get(target);

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        return "HashMap Lookup Time: " + elapsedTime + " ms, Founded: " + value;
    }

}
