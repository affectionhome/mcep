package net.mcep.upms.server;

import net.mcep.upms.utils.FastDFSClient;
import org.junit.Test;

import java.io.*;
import java.net.URL;

/**
 * 方法名称 : net.mcep.upms.server.FastDFSTest
 * 作   者 : admin
 * 创建时间 : 2018/1/11 16:45
 * 方法描述 : FastDFS测试
 * <p>
 * 修改作者 :
 * 修改时间 :
 * 修改描述 :
 */

public class FastDFSTest {

    @Test
    public void testDownload() throws IOException {
        //InputStream inputStream = FastDFSClient.downloadFile("group1/M00/00/00/wKgK2FpXP02AaXv5AATgRpIjW2M370.png");
        InputStream inputStream = FastDFSClient.downloadFile("group1/M00/00/00/wKgK2Fph0XOARUWFAAAoz1aJe406764.js");

        byte[] buff = new byte[1024];
        int index = 0;
        FileOutputStream downloadFile = new FileOutputStream("F:/ggggggg.js");
        while ((index = inputStream.read(buff)) != -1) {
            downloadFile.write(buff, 0, index);
            downloadFile.flush();
        }
        downloadFile.close();
        inputStream.close();

    }

    @Test
    public void testUpload(){
        String fileId = FastDFSClient.uploadFile(new File("F:/123.png"),"123.png");
        System.out.println(fileId);
    }

    @Test
    public void testJsoup() throws IOException {
        //File file = new File("http://www.kanshuge.la/files/article/html/150/150887/index.html");
        //InputStream inputStream = new FileInputStream(file);
        URL url = new URL("http://www.kanshuge.la/files/article/html/150/150887/index.html");
        InputStream inputStream = url.openStream();


        byte[] buff = new byte[1024];
        int index = 0;
        FileOutputStream downloadFile = new FileOutputStream("F:/123_456.txt");
        while ((index = inputStream.read(buff)) != -1) {
            downloadFile.write(buff, 0, index);
            downloadFile.flush();
        }
        downloadFile.close();
        inputStream.close();
    }


}
