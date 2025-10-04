

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Демонстрация MyHashMap (обычная HashMap) ===");
        demoMyHashMap();

        System.out.println("\n\n=== Демонстрация HashMultiMap (многозначная Map) ===");
        demoHashMultiMap();
    }

    public static void demoMyHashMap() {
        // 1. Создаем нашу самодельную HashMap
        MyHashMap<String, String> myMap = new MyHashMap<>(4); // Создаем шкаф на 4 полки

        // 2. Добавляем значения (кладем вещи в шкаф)
        System.out.println("Добавляем элементы...");
        myMap.put("Воронеж", "Город в России");
        myMap.put("Глобус", "Модель Земли");
        myMap.put("Пушкин", "Русский поэт");
        myMap.put("Брат", "Тетрадь по русскому");

        // 3. Демонстрируем коллизию (два ключа попадают на одну полку)
        // Это случится, если хеши "Брат" и "ДругойКлюч" дадут одинаковый остаток от деления на 4
        System.out.println("Создаем коллизию...");
        myMap.put("Брат", "Тетрадь по математике"); // Это ЗАМЕНИТ значение, т.к. ключ тот же
        // myMap.put("ДругойКлюч", "Какое-то значение"); // Раскомментируйте, если хотите проверить коллизию с другим ключом

        // 4. Показываем результаты
        System.out.println("\nРезультаты:");
        System.out.println("Воронеж: " + myMap.get("Воронеж"));
        System.out.println("Глобус: " + myMap.get("Глобус"));
        System.out.println("Пушкин: " + myMap.get("Пушkin")); // Опечатка специально, чтобы показать null
        System.out.println("Брат: " + myMap.get("Брат")); // Покажет "Тетрадь по математике" (последнее значение)

        // 5. Демонстрируем удаление
        System.out.println("\nУдаляем 'Глобус'...");
        myMap.remove("Глобус");
        System.out.println("Глобус после удаления: " + myMap.get("Глобус")); // null

        // 6. Показываем размер
        System.out.println("Размер map: " + myMap.size()); // Должно быть 3
    }

    public static void demoHashMultiMap() {
        // 1. Создаем нашу многозначную карту
        HashMultiMap<String, String> multiMap = new HashMultiMap<>();

        // 2. Добавляем несколько значений для ОДНОГО ключа
        System.out.println("Добавляем тетради для брата...");
        multiMap.put("Брат", "Тетрадь по русскому");
        multiMap.put("Брат", "Тетрадь по математике");
        multiMap.put("Брат", "Тетрадь по истории");

        // 3. Добавляем значения для других ключей
        System.out.println("Добавляем стихи Пушкина...");
        multiMap.put("Пушкин", "Я помню чудное мгновенье...");
        multiMap.put("Пушкин", "У лукоморья дуб зеленый...");

        multiMap.put("Воронеж", "Город");

        // 4. Получаем и выводим ВСЕ значения для ключей
        System.out.println("\nСодержимое multiMap:");
        System.out.println("Брат: " + multiMap.get("Брат")); // [Тетрадь по русскому, Тетрадь по математике, Тетрадь по истории]
        System.out.println("Пушкин: " + multiMap.get("Пушкин"));
        System.out.println("Воронеж: " + multiMap.get("Воронеж"));
        System.out.println("Несуществующий: " + multiMap.get("Несуществующий")); // Пустой список

        // 5. Демонстрируем удаление КОНКРЕТНОГО значения
        System.out.println("\nУдаляем у брата тетрадь по математике...");
        multiMap.remove("Брат", "Тетрадь по математике");
        System.out.println("Брат после удаления: " + multiMap.get("Брат"));

        // 6. Демонстрируем удаление ВСЕХ значений по ключу
        System.out.println("\nУдаляем все стихи Пушкина...");
        List<String> removedPoems = multiMap.removeAll("Пушкин");
        System.out.println("Удаленные стихи: " + removedPoems);
        System.out.println("Пушкин после удаления: " + multiMap.get("Пушkin")); // Пустой список

        // 7. Показываем общий размер (общее количество всех значений)
        System.out.println("\nОбщее количество значений: " + multiMap.size()); // Должно быть 3 (2 тетради + "Город")
        System.out.println("Есть ли ключ 'Брат'? " + multiMap.containsKey("Брат")); // true
        System.out.println("Есть ли ключ 'Пушкин'? " + multiMap.containsKey("Пушкин")); // false
    }
}