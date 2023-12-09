package dat.peter.utils;

public class MathUtils {

    public static long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }

    private static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
}