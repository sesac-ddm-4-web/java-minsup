package lesson3;

import java.util.Arrays;

public class copyArray {
    public static void main(String[] args) {
        int[] prices = {1000, 2000, 3000};
        int[] copy = Arrays.copyOf(prices, prices.length);
        copy[0] += 500;

        System.out.println("원본: " + Arrays.toString(prices));
        System.out.println("복사본: " + Arrays.toString(copy));
    }
}
