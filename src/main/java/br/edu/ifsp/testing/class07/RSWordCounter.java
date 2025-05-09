package br.edu.ifsp.testing.class07;

import java.util.HashMap;
import java.util.LinkedList;

import static java.lang.Character.isLetter;

public class RSWordCounter {
    public int count(String str) {
        int words = 0;
        char last = ' ';
        for (int i = 0; i < str.length(); i++) {
            if (!isLetter(str.charAt(i)) && (last == 's' || last == 'r')) {
                words++;
            }
            last = str.charAt(i);
        }
        if (last == 'r' || last == 's') {
            words++;
        }
        return words;
    }
}

