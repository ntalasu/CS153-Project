public class Hilbert
{
    private static final int SIZE = 5;
    private static enum why {SINGULARMATRIX, ZEROROW, NOCONVERGENCE};
    private static int ps[];
    private static double h[][];
    private static double hinv[][];
    private static double z[][];
    private static int i;
    private static int j;

    static
    {
        ps = new int[5];
        h = new double[5][5];
        hinv = new double[5][5];
        z = new double[5][5];
    }

    static void singular(why w)
    {

        switch (w)
        {
            case SINGULARMATRIX: 
                System.out.printf("Singular matrix in decompose.\n");
                break;
            case ZEROROW: 
                System.out.printf("Matrix with zero row in decompose.\n");
                break;
            case NOCONVERGENCE: 
                System.out.printf("No convergence in improve.\n");
                break;
        }
    }

    static double absvalue(double x)
    {
        double absvalue;

        if (x < 0)
            x = -x;
        absvalue = x;

        return absvalue;
    }

    static void decompose(int n, double a[][], double lu[][])
    {
        double scales[] = new double[5];
        int i;
        int j;
        int k;
        int pivotindex;
        double normrow;
        double pivot;
        double size;
        double biggest;
        double mult;

        for (i = 1; i <= n; ++i) 
        {
            ps[(i)-1] = i;
            normrow = 0;
            for (j = 1; j <= n; ++j) 
            {
                lu[(i)-1][(j)-1] = a[(i)-1][(j)-1];
                if (normrow < absvalue(lu[(i)-1][(j)-1]))
                    normrow = absvalue(lu[(i)-1][(j)-1]);
            }
            if (normrow != 0)
                scales[(i)-1] = 1/normrow;
            else 
            {
                scales[(i)-1] = 0;
                singular(why.ZEROROW);
            }
        }
        for (k = 1; k <= n - 1; ++k) 
        {
            pivotindex = 0;
            biggest = 0;
            for (i = k; i <= n; ++i) 
            {
                size = absvalue(lu[(ps[(i)-1])-1][(k)-1])*scales[(ps[(i)-1])-1];
                if (biggest < size)
                {
                    biggest = size;
                    pivotindex = i;
                }
            }
            if (biggest == 0)
                singular(why.SINGULARMATRIX);
            else 
            {
                if (pivotindex != k)
                {
                    j = ps[(k)-1];
                    ps[(k)-1] = ps[(pivotindex)-1];
                    ps[(pivotindex)-1] = j;
                }
                pivot = lu[(ps[(k)-1])-1][(k)-1];
                for (i = k + 1; i <= n; ++i) 
                {
                    mult = lu[(ps[(i)-1])-1][(k)-1]/pivot;
                    lu[(ps[(i)-1])-1][(k)-1] = mult;
                    if (mult != 0)
                    {
                        for (j = k + 1; j <= n; ++j) 
                        {
                            lu[(ps[(i)-1])-1][(j)-1] = lu[(ps[(i)-1])-1][(j)-1] - mult*lu[(ps[(k)-1])-1][(j)-1];
                        }
                    }
                }
            }
        }
        if (lu[(ps[(n)-1])-1][(n)-1] == 0)
            singular(why.SINGULARMATRIX);
    }

    static void solve(int n, double lu[][], double b[], double x[])
    {
        int i;
        int j;
        double dot;

        for (i = 1; i <= n; ++i) 
        {
            dot = 0;
            for (j = 1; j <= i - 1; ++j) 
            {
                dot = dot + lu[(ps[(i)-1])-1][(j)-1]*x[(j)-1];
            }
            x[(i)-1] = b[(ps[(i)-1])-1] - dot;
        }
        for (i = n; i >= 1; --i) 
        {
            dot = 0;
            for (j = i + 1; j <= n; ++j) 
            {
                dot = dot + lu[(ps[(i)-1])-1][(j)-1]*x[(j)-1];
            }
            x[(i)-1] = (x[(i)-1] - dot)/lu[(ps[(i)-1])-1][(i)-1];
        }
    }

    static void invert(int n, double a[][], double ainv[][])
    {
        double lu[][] = new double[5][5];
        double b[] = new double[5];
        double x[] = new double[5];
        int i;
        int j;

        decompose(n, a, lu);
        for (j = 1; j <= n; ++j) 
        {
            for (i = 1; i <= n; ++i) 
            {
                if (i == j)
                    b[(i)-1] = 1;
                else 
                    b[(i)-1] = 0;
            }
            solve(n, lu, b, x);
            for (i = 1; i <= n; ++i) 
                ainv[(i)-1][(j)-1] = x[(i)-1];
        }
    }

    static void multiply(int n, double a[][], double b[][], double p[][])
    {
        int i;
        int j;
        int k;
        double sum;

        sum = 0.0;
        for (i = 1; i <= n; ++i) 
        {
            for (j = 1; j <= n; ++j) 
            {
                sum = 0.0;
                for (k = 1; k <= n; ++k) 
                {
                    sum = sum + a[(i)-1][(k)-1]*b[(k)-1][(j)-1];
                }
                p[(i)-1][(j)-1] = sum;
            }
        }
    }

    static void printmatrix(double m[][])
    {
        int i;
        int j;

        System.out.println();
        for (i = 1; i <= SIZE; ++i) 
        {
            for (j = 1; j <= SIZE; ++j) 
                System.out.printf("%15.6f", m[(i)-1][(j)-1]);
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args)
    {
        java.time.Instant _start = java.time.Instant.now();
        for (i = 1; i <= SIZE; ++i) 
        {
            for (j = 1; j <= SIZE; ++j) 
            {
                h[(i)-1][(j)-1] = 1.0/(i + j - 1);
            }
        }
        System.out.println();
        System.out.printf("Hilbert matrix:\n");
        printmatrix(h);
        invert(SIZE, h, hinv);
        System.out.printf("Hilbert matrix inverted:\n");
        printmatrix(hinv);
        multiply(SIZE, h, hinv, z);
        System.out.printf("Hilbert matrix multiplied by its inverse:\n");
        printmatrix(z);
        invert(SIZE, hinv, h);
        System.out.printf("Inverse matrix inverted:\n");
        printmatrix(h);

        java.time.Instant _end = java.time.Instant.now();
        long _elapsed = java.time.Duration.between(_start, _end).toMillis();
        System.out.printf("\n[%,d milliseconds execution time.]\n", _elapsed);
    }
}
