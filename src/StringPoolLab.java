public class StringPoolLab {
    public static void main(String[] args) {
        String s1 = "Hello";
        String s2 = "Hello";
        String s3 = new String("Hello");
        String s4 = new String("Hello");
        String s5 = s3.intern();
        String s6 = "Hel" + "lo";
        String half = "Hel";
        String s7 = half + "lo";

        StringBuilder sb = new StringBuilder("Hello");


        System.out.println("s1 == s2 " + (s1 == s2));
        System.out.println("s1.equals(s2) " + s1.equals(s2));

        System.out.println("s1 == s3 " + (s1 == s3));
        System.out.println("s1.equals(s3) " + s1.equals(s3));

        System.out.println("s3 == s4 " + (s3 == s4));
        System.out.println("s3.equals(s4) " + s3.equals(s4));

        System.out.println("s1 == s5 " + (s1 == s5));
        System.out.println("s1.equals(s5) " + s1.equals(s5));

        System.out.println("s1 == s6 " + (s1 == s6));
        System.out.println("s1.equals(s6) " + s1.equals(s6));

        System.out.println("s1 == s7 " + (s1 == s7));
        System.out.println("s1.equals(s7) " + s1.equals(s7));

        //System.out.println(s1 == sb);
        System.out.println("s1.equals(sb) " + s1.equals(sb));
        System.out.println("s1.equals(sb.toString()) " + s1.equals(sb.toString()));
    }
}

