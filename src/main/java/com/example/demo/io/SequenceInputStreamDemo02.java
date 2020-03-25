package com.example.demo.io;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Vector;
 
/**
 * SequenceInputStream 表示其他输入流的逻辑串联。
 * 它从输入流的有序集合开始，并从第一个输入流开始读取，直到到达文件末尾；
 * 接着从第二个输入流读取，依次类推，直到到达包含的最后一个输入流的文件末尾为止。
 * 
 * @author 半步疯子
 *
 */
public class SequenceInputStreamDemo02 {
	
	// SequenceInputStream(Enumeration<? extends InputStream> e)
	// 通过枚举类：进行多个流的合并
	public static void main(String[] args) throws IOException {
		InputStream s1 = new FileInputStream("src/main/java/com/example/demo/io/SequenceInputStreamDemo02.java");
		InputStream s2 = new FileInputStream("src/main/java/com/example/demo/io/SequenceInputStreamDemo01.java");
		InputStream s3 = new FileInputStream("src/main/java/com/example/demo/io/SequenceInputStreamDemo02.java");
		
		Vector<InputStream> v = new Vector<InputStream>();
		v.addElement(s1);
		v.addElement(s2);
		v.addElement(s3);
		SequenceInputStream sis = new SequenceInputStream(v.elements());
		
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("F://copySequence02.java"));
		
		byte[] buf = new byte[1024];
		int len = buf.length;
		while((len = sis.read(buf, 0, len)) != -1) {
			bos.write(buf, 0, len);
		}
		
		sis.close();
		bos.close();
	}
}