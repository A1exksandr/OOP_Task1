package com.shurik.collections;

import java.util.*;

public class HashMultiMap<K, V> {
    // Внутри нашей MultiMap работает обычный HashMap.
    // Ключом для него является наш ключ (K).
    // А значением - не одно V, а целый список (ArrayList) значений.
    private Map<K, List<V>> map = new HashMap<>();
    private int size = 0; // Общее количество всех значений

    // Метод добавления значения для ключа
    public void put(K key, V value) {
        // 1. Пытаемся получить коробку (список) для нашего ключа
        List<V> values = map.get(key);

        // 2. Если коробки для такого ключа еще нет...
        if (values == null) {
            // ...создаем новую пустую коробку (список)
            values = new ArrayList<>();
            // и кладем эту коробку на нужную полку внутреннего HashMap
            map.put(key, values);
        }

        // 3. Кладем наше значение (тетрадку) в коробку (список)
        values.add(value);
        size++; // Увеличиваем общий счетчик значений
    }

    // Метод для получения всех значений по ключу
    // Если ключа нет, возвращаем пустой список (а не null!)
    public List<V> get(K key) {
        // Получаем коробку по ключу
        List<V> values = map.get(key);
        // Если коробки нет, возвращаем пустой список. Это лучше, чем null.
        if (values == null) {
            return Collections.emptyList();
        }
        // Если коробка есть, возвращаем ее содержимое
        return values;
    }

    // Метод для удаления КОНКРЕТНОГО значения у ключа
    // (Достаем одну тетрадку из коробки)
    public boolean remove(K key, V value) {
        List<V> values = map.get(key);
        if (values != null) {
            // Пытаемся удалить значение из списка
            boolean wasRemoved = values.remove(value);
            if (wasRemoved) {
                size--; // Уменьшаем общий счетчик, если удалили
                // Если коробка опустела, можно убрать ее совсем
                if (values.isEmpty()) {
                    map.remove(key);
                }
            }
            return wasRemoved;
        }
        return false; // Нечего было удалять
    }

    // Метод для удаления ВСЕХ значений по ключу
    // (Выкидываем всю коробку с этикеткой "Брат")
    public List<V> removeAll(K key) {
        List<V> values = map.remove(key); // Удаляет и возвращает коробку
        if (values != null) {
            int count = values.size();
            size -= count; // Уменьшаем общий счетчик на размер коробки
            return values;
        }
        return Collections.emptyList();
    }

    public int size() {
        return size; // Возвращает общее количество значений (тетрадок)
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public boolean containsKey(K key) {
        return map.containsKey(key);
    }
}