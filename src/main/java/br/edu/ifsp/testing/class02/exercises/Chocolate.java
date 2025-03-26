package br.edu.ifsp.testing.class02.exercises;

import java.util.Scanner;

public class Chocolate {
    public int calculateTotalOfChocolates(double n, double c, int m){
        if (n < 0 || c <= 0 || m <= 1) throw new IllegalArgumentException();
        if (c > n){
            return 0;
        } else if (c == n) {
            return 1;
        } else {
            int chocolates = (int) (n / c);
            int embalagens = chocolates;
            int chocolatesTrocados;
            while (embalagens >= m){
                chocolatesTrocados = embalagens / m;
                chocolates += chocolatesTrocados;
                embalagens = embalagens % m + chocolatesTrocados;
            }
            return chocolates;
        }
    }

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int c = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.close();
        final int result = new Chocolate().calculateTotalOfChocolates(n, c, m);
        System.out.println(result);
    }
}


