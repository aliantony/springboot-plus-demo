package com.example.demo.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.SequenceInputStream;
 
/**
 * SequenceInputStream 表示其他输入流的逻辑串联。
 * 它从输入流的有序集合开始，并从第一个输入流开始读取，直到到达文件末尾；
 * 接着从第二个输入流读取，依次类推，直到到达包含的最后一个输入流的文件末尾为止。
 * 
 * @author 半步疯子
 *
 */
public class SequenceInputStreamDemo01 {
	
	// SequenceInputStream(InputStream s1, InputStream s2) 
	
	public static void main(String[] args) throws IOException {
		InputStream s1 = new FileInputStream("src/main/java/com/example/demo/io/SequenceInputStreamDemo01.java");
		InputStream s2 = new FileInputStream("src/main/java/com/example/demo/io/SequenceInputStreamDemo02.java");
		
		SequenceInputStream sis = new SequenceInputStream(s1, s2);
		
		InputStreamReader isr = new InputStreamReader(sis);
		BufferedReader br = new BufferedReader(isr);
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("F://CopySequence.java"));
		
		String line = null;
		while((line = br.readLine()) != null) {
		// while(br.ready()) {  /* 为什么合并流之后，不能使用ready方法？结果只有S1 */
			// line = br.readLine();
			bw.write(line);
			bw.newLine();
		}
		bw.flush();
		s1.close();
		s2.close();
		br.close();
		bw.close();
		
		/*
		BufferedReader br = new BufferedReader(
				new InputStreamReader(
						new SequenceInputStream(
								new FileInputStream("src/stream/sequence/SequenceInputStreamDemo01.java"), 
								new FileInputStream("src/special/RandomAccessFileDemo02.java")
								)
						)
				);
		BufferedWriter bw = new BufferedWriter(new FileWriter("CopySequence.java"));
		br.close();
		bw.close();
		*/
	}
}
