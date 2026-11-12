public class SquareRootProcedure
{
    private static int i;
    private static int row;
    private static double number;

    static void headers()
    {
        int i;

        System.out.printf("%60s\n", "Square Root Table");
        System.out.println();
        System.out.printf("     ");
        for (i = 0; i <= 9; ++i) 
            System.out.printf("        .%1d", i);
        System.out.println();
    }

    static double sqroot(double x)
    {
        double sqroot;
        double root;
        double diff;
        double prev;

        root = x;
        prev = root;
        do
        {
            root = (x/root + root)/2;
            diff = Stdlib.abs(prev - root);
            prev = root;
        }
        while (!(diff < 1.0E-6));
        sqroot = root;

        return sqroot;
    }

    public static void main(String[] args)
    {
        java.time.Instant _start = java.time.Instant.now();
        headers();
        for (row = 1; row <= 25; ++row) 
        {
            System.out.printf("%5d", row);
            for (i = 0; i <= 9; ++i) 
            {
                number = row + ((double) i)/10;
                System.out.printf("%10.6f", sqroot(number));
            }
            System.out.println();
        }

        java.time.Instant _end = java.time.Instant.now();
        long _elapsed = java.time.Duration.between(_start, _end).toMillis();
        System.out.printf("\n[%,d milliseconds execution time.]\n", _elapsed);
    }
}
