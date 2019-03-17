package com.hash.memoryleakdemo;

import org.junit.Test;

/**
 * Created by HashWaney on 2019/3/2.
 */

public class test {
    @Test
    public void main() {

        fun(23);
    }

    private void fun(int i) {
        int sum = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int j = 0; j < i; j++) {
            sum += 1;
            if (sum % 3 == 0) {
                stringBuilder.append("-" + "A" + "-");
            } else if (sum % 3 == 0 && sum % 5 == 0) {
                stringBuilder.append("-" + "B" + "-");
            } else if (sum % 7 == 0) {
                stringBuilder.append("-" + "AD" + "-");
            } else {
                stringBuilder.append("-" + sum + "-");
            }

        }
        System.out.print(stringBuilder);
    }

    @Test
    public void huiwen() {
        int n = 1211;
        String s = String.valueOf(n);
        char[] chars = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            chars[i] = c;
        }
        for (int i = 0; i < chars.length; i++) {
            System.err.println("chars:" + chars[i]);
        }

        boolean isReal = false;

        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] == chars[chars.length - 1 - i]) {
                isReal = true;
            } else {
                isReal = false;
            }
        }
        System.err.println("当前数是回文数吗:" + isReal);

    }

    @Test
    public void fun3() {
        int i = 1, j = 0;
        while (j < 1000) {
            j = i * i;
            i++;
            System.err.println("j:" + j);
        }


    }


}
