import java.util.ArrayList;

public class VarDemo {

    static class Example {
    }

    public static void main(String[] args) {

        var a = 42;
        System.out.println(a + " -> " + ((Object)a).getClass().getSimpleName());
        // (Object)a превращает число в объект, чтобы вызвать getClass()
        //getClass() метод, кот. возвращает точный класс объекта
        //getSimpleName() метод, который возврашает простое имя класса
        var s = "Java";
        System.out.println(s + " -> " + ((Object)s).getClass().getSimpleName());

        var list = new ArrayList<String>();
        list.add("one");
        list.add("two");
        System.out.println(list + " -> " + ((Object)list).getClass().getSimpleName());

        var ar = new int[]{1, 2, 3};
        System.out.println(java.util.Arrays.toString(ar) + " -> " + ((Object)ar).getClass().getSimpleName());

        var b = new Example();
        System.out.println(b + " -> " + ((Object)b).getClass().getSimpleName());
    }
}
        // 1. var без инициализации
        // var x; нельзя использовать var без инициализации
        // 2. var как параметр метода
        // void test(var param) {} нельзя использовать var для параметров
        // 3. var как поле класса
        // class Test {
        //       var field =10; нельзя использовать для полей класса
        // }
        // 4. var с null
         // var data = null; невозможно определить тип по null
