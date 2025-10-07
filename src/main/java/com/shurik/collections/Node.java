package com.shurik.collections;

// Этот класс описывает один элемент, хранящийся в нашей HashMap
class Node<K, V> {
    final K key; // Ключ (например, "Брат"). final - потому что ключ не должен меняться
    V value; // Значение (например, "Тетрадь по русскому")
    Node<K, V> next; // Ссылка на следующий узел в цепочке (для коллизий)

    // Конструктор для создания новой "вещи"
    public Node(K key, V value, Node<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
}