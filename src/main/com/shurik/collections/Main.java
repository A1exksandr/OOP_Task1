package com.shurik.collections;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Демонстрация MyHashMap ===");
        demoMyHashMap();

        System.out.println("\n\n=== Демонстрация HashMultiMap ===");
        demoHashMultiMap();
    }

    public static void demoMyHashMap() {
        MyHashMap<String, String> map = new MyHashMap<>(4);

        map.put("Воронеж", "Город в России");
        map.put("Глобус", "Модель Земли");
        map.put("Пушкин", "Русский поэт");
        map.put("Брат", "Тетрадь по русскому");

        System.out.println("Воронеж: " + map.get("Воронеж"));
        System.out.println("Глобус: " + map.get("Глобус"));
        System.out.println("Брат: " + map.get("Брат"));

        map.remove("Глобус");
        System.out.println("Глобус после удаления: " + map.get("Глобус"));
        System.out.println("Размер: " + map.size());
    }

    public static void demoHashMultiMap() {
        HashMultiMap<String, String> multiMap = new HashMultiMap<>();

        multiMap.put("Брат", "Тетрадь по русскому");
        multiMap.put("Брат", "Тетрадь по математике");
        multiMap.put("Брат", "Тетрадь по истории");
        multiMap.put("Пушкин", "Я помню чудное мгновенье...");

        System.out.println("Брат: " + multiMap.get("Брат"));
        System.out.println("Пушкин: " + multiMap.get("Пушкин"));

        multiMap.remove("Брат", "Тетрадь по математике");
        System.out.println("Брат после удаления математики: " + multiMap.get("Брат"));

        System.out.println("Общий размер: " + multiMap.size());
    }
}