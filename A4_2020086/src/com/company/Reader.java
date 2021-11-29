//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    public Reader() {
    }

    static void init(InputStream input) {
        reader = new BufferedReader(new InputStreamReader(input));
        tokenizer = new StringTokenizer("");
    }

    static String next() throws IOException {
        while(!tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }

        return tokenizer.nextToken();
    }

    static int nextint() throws IOException {
        return Integer.parseInt(next());
    }

    static long nextlong() throws IOException {
        return Long.parseLong(next());
    }

    static double nextdouble() throws IOException {
        return Double.parseDouble(next());
    }

    static String nextLine() throws IOException {
        return reader.readLine();
    }
}
