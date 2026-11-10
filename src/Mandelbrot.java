public class Mandelbrot
{
    private static class Mandelbrot_Complex
    {
        double re;
        double im;
    }

    private static final int MAXITERS = 32;
    private static final int ESCAPE = 2;

    static Mandelbrot_Complex cadd(Mandelbrot_Complex c1, Mandelbrot_Complex c2)
    {
        Mandelbrot_Complex cadd;
        Mandelbrot_Complex sum = new Mandelbrot_Complex();

        sum.re = c1.re + c2.re;
        sum.im = c1.im + c2.im;
        cadd = sum;

        return cadd;
    }

    static Mandelbrot_Complex csub(Mandelbrot_Complex c1, Mandelbrot_Complex c2)
    {
        Mandelbrot_Complex csub;
        Mandelbrot_Complex diff = new Mandelbrot_Complex();

        diff.re = c1.re - c2.re;
        diff.im = c1.im - c2.im;
        csub = diff;

        return csub;
    }

    static Mandelbrot_Complex cmul(Mandelbrot_Complex c1, Mandelbrot_Complex c2)
    {
        Mandelbrot_Complex cmul;
        Mandelbrot_Complex prod = new Mandelbrot_Complex();

        prod.re = c1.re*c2.re - c1.im*c2.im;
        prod.im = c1.re*c2.im + c1.im*c2.re;
        cmul = prod;

        return cmul;
    }

    static Mandelbrot_Complex cdiv(Mandelbrot_Complex c1, Mandelbrot_Complex c2)
    {
        Mandelbrot_Complex cdiv;
        Mandelbrot_Complex quo = new Mandelbrot_Complex();
        double divisor;

        divisor = c2.re*c2.re + c2.im*c2.im;
        quo.re = (c1.re*c2.re + c1.im*c2.im)/divisor;
        quo.im = (c1.im*c2.re - c1.re*c2.im)/divisor;
        cdiv = quo;

        return cdiv;
    }

    static double cmodulus(Mandelbrot_Complex c)
    {
        double cmodulus;

        cmodulus = Stdlib.sqrt(c.re*c.re + c.im*c.im);

        return cmodulus;
    }

    static void print(Mandelbrot_Complex c)
    {

        System.out.printf("(%f, %f)\n", c.re, c.im);
    }

    static void mandelbrot()
    {
        final double DELTA = 0.01;
        final double XMIN = -2.25;
        final double YMIN = -2.25;
        final double YMAX = -YMIN;
        int row;
        int col;
        double x;
        double y;
        Mandelbrot_Complex c = new Mandelbrot_Complex();
        Mandelbrot_Complex z = new Mandelbrot_Complex();
        Mandelbrot_Complex zz = new Mandelbrot_Complex();
        double modulus;
        int iters;
        int m;

        row = 122;
        do
        {
            col = 45;
            do
            {
                x = XMIN + col*DELTA;
                y = YMAX - row*DELTA;
                c.re = x;
                c.im = y;
                z.re = 0;
                z.im = 0;
                iters = 0;
                do
                {
                    zz = cmul(z, z);
                    z = cadd(zz, c);
                    modulus = cmodulus(z);
                    iters = iters + 1;
                }
                while (!((modulus > ESCAPE) || (iters > MAXITERS)));
                if (modulus >= ESCAPE)
                    System.out.printf(" ");
                else 
                {
                    m = Stdlib.trunc((100*modulus)/ESCAPE);
                    m = m%10;
                    System.out.printf("%1d", m);
                }
                col = col + 2;
            }
            while (!(col > 270));
            System.out.println();
            row = row + 3;
        }
        while (!(row > 316));
    }

    public static void main(String[] args)
    {
        java.time.Instant _start = java.time.Instant.now();
        System.out.printf("The Mandelbrot Set\n");
        System.out.println();
        mandelbrot();

        java.time.Instant _end = java.time.Instant.now();
        long _elapsed = java.time.Duration.between(_start, _end).toMillis();
        System.out.printf("\n[%,d milliseconds execution time.]\n", _elapsed);
    }
}
