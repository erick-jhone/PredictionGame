package com.erick.prova1_erick_jhone_rodrigues_da_silva.utils;

import java.util.HashMap;
import java.util.Map;

public class CalculatorValueUtil {

    Map<String, Integer> map = new HashMap<>();

    public CalculatorValueUtil(){
        map = new HashMap<>();
        map.put("a", 10);
        map.put("e", 20);
        map.put("i", 30);
        map.put("o", 40);
        map.put("u", 50);
        map.put("b", 1);
        map.put("c", 2);
        map.put("d", 3);
        map.put("f", 4);
        map.put("g", 5);
        map.put("h", 6);
        map.put("j", 7);
        map.put("k", 8);
        map.put("l", 9);
        map.put("m", 10);
        map.put("n", 11);
        map.put("p", 12);
        map.put("q", 13);
        map.put("r", 14);
        map.put("s", 15);
        map.put("t", 16);
        map.put("v", 17);
        map.put("w", 18);
        map.put("x", 19);
        map.put("y", 20);
        map.put("z", 21);
    }

    public int calculateVogais(String palavra) {
        int valorTotalVogais = 0;
        for (char letra : palavra.toLowerCase().toCharArray()) {
            if (String.valueOf(letra).matches("[aeiou]")) {
                valorTotalVogais += map.getOrDefault(String.valueOf(letra), 0);
            }
        }
        return valorTotalVogais;
    }

    public int calculateConsoantes(String palavra) {
        int valorTotalConsoantes = 0;
        for (char letra : palavra.toLowerCase().toCharArray()) {
            if (String.valueOf(letra).matches("[bcdfghjklmnpqrstvwxyz]")) {
                valorTotalConsoantes += map.getOrDefault(String.valueOf(letra), 0);
            }
        }
        return valorTotalConsoantes;
    }
}
