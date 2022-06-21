package com.kakaobank.search.test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try {
            Socket socket = new ServerSocket(12345).accept();
            OutputStream out = socket.getOutputStream();

            out.write("Hello World\n".getBytes());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}