package com.zmxrlid.backend.common;

import java.util.ArrayList;
import java.util.List;

public class StringToList {
    public static List<Integer> parseStringToList(String input) {
        List<Integer> numbers = new ArrayList<>();
        try {
            // 去除方括号并按逗号分割
            String cleanInput = input.replaceAll("[\\[\\]]", "");
            String[] stringNumbers = cleanInput.split(",");
            for (String str : stringNumbers) {
                numbers.add(Integer.parseInt(str.trim()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return numbers;
    }
}
