public class Stdlib
{
    public static int    abs(int arg)    { return Math.abs(arg); }
    public static double abs(double arg) { return Math.abs(arg); }
    
    public static int    sqr(int arg)    { return arg*arg; }
    public static double sqr(double arg) { return arg*arg; }
    
    public static int trunc(double arg) { return (int) arg;}
    public static int round(double arg) { return (int) Math.round(arg); }
    
    public static char chr(int arg)  { return (char) arg; }    
    public static int  ord(char arg) { return (int)  arg; }
    
    public static int pred(int arg) { return --arg; }
    public static int succ(int arg) { return ++arg; }
    
    public static double arctan(double arg) { return Math.atan(arg); }
    public static double cos   (double arg) { return Math.cos(arg);  }
    public static double exp   (double arg) { return Math.exp(arg);  }
    public static double ln    (double arg) { return Math.log(arg);  }
    public static double sin   (double arg) { return Math.sin(arg);  }
    public static double sqrt  (double arg) { return Math.sqrt(arg); }
}
