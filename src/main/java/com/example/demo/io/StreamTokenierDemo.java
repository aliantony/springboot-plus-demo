package com.example.demo.io;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;

/**
 * @program demo1
 * @description 输入流分解
 * @author wangqian
 * created on 2020-03-25
 * @version  1.0.0
 */
public class StreamTokenierDemo {
    public static void main(String[] args) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(new StringReader("Mary had 1 little lamb..."));

        while(tokenizer.nextToken() != StreamTokenizer.TT_EOF){

            if(tokenizer.ttype == StreamTokenizer.TT_WORD) {

                System.out.println(tokenizer.sval);
            } else if(tokenizer.ttype == StreamTokenizer.TT_NUMBER) {

                System.out.println(tokenizer.nval);

            } else if(tokenizer.ttype == StreamTokenizer.TT_EOL) {

                System.out.println();

            }

        }
    }
}
