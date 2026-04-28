public class Memory {
    private int instanceData = 100;
    private static int staticData = 200;

    class InnerClass {
        void show() {
            System.out.println("Inner: instanceData = " + instanceData);  // ?
            System.out.println("Inner: staticData = " + staticData);      // ?
        }
    }

    static class StaticNested {
        void show() {
            // System.out.println("Static: instanceData = " + instanceData); // ?
            System.out.println("Static: staticData = " + staticData);     // ?
        }
    }

    public static void main(String[] args) {
        Memory memory = new Memory();

        // Нестатический — нужен экземпляр
        InnerClass inner = memory.new InnerClass();
        inner.show();

        // Статический — экземпляр не нужен
        StaticNested nested = new StaticNested();
        nested.show();
    }
}

