package test;

public class getPrimeAbove {
    int func(int number) {
        int i;
        for (i = 2; i * 2 <= number; i *= 2) ;

        i*=1.5;

        while (!isPrime(i)) i++;
        return i;
    }

    boolean isPrime(int n) {
        if (n <= 1) return false;
        else if (n <= 3) return true;
        else if (n % 2 == 0 || n % 3 == 0) return false;
        int i = 5;
        while (i * i <= n) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
            i += 6;
        }
        return true;
    }

    public static void main(String[] args) {
        getPrimeAbove g = new getPrimeAbove();
        System.out.println(g.func(667));
    }
}

