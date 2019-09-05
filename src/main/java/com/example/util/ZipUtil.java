package com.example.util;

import org.springframework.util.ClassUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.Pipe;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.concurrent.CompletableFuture;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @program demo1
 * @description 文件压缩工具类
 * @author wangqian
 * created on 2019-09-05
 * @version  1.0.0
 */
public class ZipUtil {

    private final static String ZIP_FILE = "D:/test.zip";

    private final static File JPG_FILE = new File(ClassUtils.getDefaultClassLoader().getResource("static/99.jpg").getFile());

    private final static String FILE_NAME = "99.jpg";
    private static final String SUFFIX_FILE = ".jpg";

    /**
     * 压缩20M花费48秒
     */
    public static void zipFileNoBuffer() {
        System.out.println("方法开始运行");
        File zipFile = new File(ZIP_FILE);
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile))) {
            //开始时间
            long beginTime = System.currentTimeMillis();

            for (int i = 0; i < 30; i++) {
                try (InputStream input = new FileInputStream(JPG_FILE)) {
                    zipOut.putNextEntry(new ZipEntry(FILE_NAME + i));
                    int temp = 0;
                    while ((temp = input.read()) != -1) {
                        zipOut.write(temp);
                    }
                }
            }
            printInfo(beginTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 优化后只需1秒多
     */
    public static void zipFileBuffer() {
        System.out.println("方法开始运行");
        File zipFile = new File(ZIP_FILE);
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(zipOut)) {
            //开始时间
            long beginTime = System.currentTimeMillis();
            for (int i = 0; i < 30; i++) {
                try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(JPG_FILE))) {
                    zipOut.putNextEntry(new ZipEntry(FILE_NAME + i));
                    int temp = 0;
                    while ((temp = bufferedInputStream.read()) != -1) {
                        bufferedOutputStream.write(temp);
                    }
                }
            }
            printInfo(beginTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * nio读写
     */
    public static void zipFileChannel() {
        System.out.println("方法开始运行");
        //开始时间
        long beginTime = System.currentTimeMillis();
        File zipFile = new File(ZIP_FILE);
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
             WritableByteChannel writableByteChannel = Channels.newChannel(zipOut)) {
            for (int i = 0; i < 30; i++) {
                try (FileChannel fileChannel = new FileInputStream(JPG_FILE).getChannel()) {
                    zipOut.putNextEntry(new ZipEntry(i + SUFFIX_FILE));
                    fileChannel.transferTo(0, JPG_FILE.length(), writableByteChannel);
                }
            }
            printInfo(beginTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Version 4 使用Map映射文件
    public static void zipFileMap() {
        System.out.println("方法开始运行");
        //开始时间
        long beginTime = System.currentTimeMillis();
        File zipFile = new File(ZIP_FILE);
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
             WritableByteChannel writableByteChannel = Channels.newChannel(zipOut)) {
            for (int i = 0; i < 30; i++) {

                zipOut.putNextEntry(new ZipEntry(i + SUFFIX_FILE));

                //内存中的映射文件
                MappedByteBuffer mappedByteBuffer = new RandomAccessFile(JPG_FILE.getPath(), "r").getChannel()
                        .map(FileChannel.MapMode.READ_ONLY, 0, JPG_FILE.length());

                writableByteChannel.write(mappedByteBuffer);
            }
            printInfo(beginTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Version 5 使用Pip
    public static void zipFilePip() {
        long beginTime = System.currentTimeMillis();
        try(WritableByteChannel out = Channels.newChannel(new FileOutputStream(ZIP_FILE))) {
            Pipe pipe = Pipe.open();
            //异步任务
            CompletableFuture.runAsync(()->runTask(pipe));

            //获取读通道
            ReadableByteChannel readableByteChannel = pipe.source();
            ByteBuffer buffer = ByteBuffer.allocate(((int) JPG_FILE.length())*10);
            while (readableByteChannel.read(buffer)>= 0) {
                buffer.flip();
                out.write(buffer);
                buffer.clear();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        printInfo(beginTime);

    }

    //异步任务
    public static void runTask(Pipe pipe) {
        try(ZipOutputStream zos = new ZipOutputStream(Channels.newOutputStream(pipe.sink()));
            WritableByteChannel out = Channels.newChannel(zos)) {
            System.out.println("方法开始运行");
            for (int i = 0; i < 30; i++) {
                zos.putNextEntry(new ZipEntry(i+SUFFIX_FILE));
                FileChannel jpgChannel = new FileInputStream(new File(JPG_FILE.getPath())).getChannel();
                jpgChannel.transferTo(0, JPG_FILE.length(), out);
                jpgChannel.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void printInfo(long beginTime) {
        System.out.println("方法耗时:" + (System.currentTimeMillis() - beginTime));
    }

    public static void main(String[] args) {
        //zipFileNoBuffer();
        //zipFileBuffer();
        //zipFileChannel();
        //zipFileMap();
        zipFilePip();
    }
}
