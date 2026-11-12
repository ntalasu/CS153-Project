public class CopyTest
{
    private static class CopyTest_Person
    {
        String name;
        int age;
    }

    private static CopyTest_Person p1;
    private static CopyTest_Person p2;

    static
    {
        p1 = new CopyTest_Person();
        p2 = new CopyTest_Person();
    }

    static void print(CopyTest_Person p)
    {

        System.out.printf("  name:%s\n", p.name);
        System.out.printf("   age:%d\n", p.age);
        System.out.println();
    }

    public static void main(String[] args)
    {
        java.time.Instant _start = java.time.Instant.now();
        p1.name = "John";
        p1.age = 22;
        print(p1);
        p2 = p1;
        p2.name = "Mary";
        p2.age = 20;
        print(p2);
        print(p1);

        java.time.Instant _end = java.time.Instant.now();
        long _elapsed = java.time.Duration.between(_start, _end).toMillis();
        System.out.printf("\n[%,d milliseconds execution time.]\n", _elapsed);
    }
}
