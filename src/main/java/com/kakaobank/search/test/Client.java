package com.kakaobank.search.test;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket(InetAddress.getByName("localhost"), 12345);
        InputStream in = socket.getInputStream();
        int c;
        while ((c = in.read()) != -1) {
            System.out.write(c);
        }
        System.out.flush();

        // should now be in CLOSE_WAIT
        Thread.sleep(Integer.MAX_VALUE);
    }
}