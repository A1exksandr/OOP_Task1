package com.shurik.collections;

public class MyHashMap<K, V> {
    private Node<K, V>[] buckets; // Массив "полок" (корзин). Каждая полка - голова связного списка.
    private int capacity; // Количество "полок" (размер массива)
    private int size; // Общее количество "вещей" в шкафу

    // Конструктор. Создаем шкаф с заданным количеством полок.
    @SuppressWarnings("unchecked")
    public MyHashMap(int capacity) {
        this.capacity = capacity;
        this.buckets = (Node<K, V>[]) new Node[capacity]; // Создаем массив
        this.size = 0;
    }

    // Наша "хеш-функция". Определяет, на какую полку положить вещь.
    // Аналог вашего "преобразуем буквы в цифры и делим на кол-во ячеек".
    private int getBucketIndex(K key) {
        if (key == null) {
            return 0; // Для ключа null обычно отводят первую ячейку.
        }
        // Вычисляем хеш-код ключа. У каждого объекта в Java есть метод hashCode().
        // Берем модуль от хеш-кода по размеру массива, чтобы индекс был в нужных границах.
        return Math.abs(key.hashCode()) % capacity;
    }

    public void put(K key, V value) {
        // 1. Определяем номер полки
        int bucketIndex = getBucketIndex(key);
        Node<K, V> head = buckets[bucketIndex]; // Голова списка на этой полке

        // 2. Проверяем, не лежит ли уже вещь с таким же ключом на этой полке?
        Node<K, V> current = head;
        while (current != null) {
            if (current.key.equals(key)) { // Если ключ совпал...
                current.value = value; // ...ПЕРЕЗАПИСЫВАЕМ значение (брат принес новую тетрадь вместо старой)
                return; // и выходим
            }
            current = current.next;
        }

        // 3. Если ключ не нашелся, значит, это новый ключ.
        // Создаем новую "вещь" и кладем ее в НАЧАЛО списка на своей полке.
        // Старая голова списка становится "следующей" для новой вещи.
        Node<K, V> newNode = new Node<>(key, value, head);
        buckets[bucketIndex] = newNode; // Теперь новыая Node - глава списка на этой полке
        size++; // Увеличиваем счетчик вещей в шкафу

        // 4. (Позже) Здесь можно добавить логику "расширения шкафа",
        // если вещей стало слишком много по сравнению с количеством полок.
    }

    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        Node<K, V> current = buckets[bucketIndex];

        // Идем по цепочке на полке
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value; // Нашли - возвращаем значение
            }
            current = current.next;
        }
        return null; // Не нашли - возвращаем null
    }

    public V remove(K key) {
        int bucketIndex = getBucketIndex(key);
        Node<K, V> head = buckets[bucketIndex];

        // Специальный случай: если удаляемый элемент - первый в списке
        if (head != null && head.key.equals(key)) {
            buckets[bucketIndex] = head.next; // Просто "перевешиваем" табличку на следующую вещь
            size--;
            return head.value;
        }

        // Если элемент не первый, ищем его
        Node<K, V> current = head;
        Node<K, V> prev = null;

        while (current != null) {
            if (current.key.equals(key)) {
                // Нашли! Перекидываем ссылку предыдущего элемента на следующий за current.
                prev.next = current.next;
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null; // Не нашли что удалять
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsKey(K key) {
        int bucketIndex = getBucketIndex(key);
        Node<K, V> current = buckets[bucketIndex];

        while (current != null) {
            if (current.key.equals(key)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
}
