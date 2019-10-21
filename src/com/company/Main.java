package com.company;

import java.io.*;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        try {
            Socket server = new Socket ("localhost",12345);
            System.out.println("Соединение с сервером выполнено");
            DataInputStream in = new DataInputStream (server.getInputStream());
            DataOutputStream out = new DataOutputStream (server.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while(!server.isOutputShutdown()) {
                System.out.print("Введите свой текст для обработки: ");
                String client_str = br.readLine();
                if (client_str.equalsIgnoreCase("stop")) {
                    out.writeUTF(client_str);
                    out.flush();
                    break;
                }
                out.writeUTF(client_str);
                out.flush();
                System.out.println("Отослали сроку на сервер");
                String str_from_server = in.readUTF();
                System.out.println("Результат обработки строки:");
                System.out.println(str_from_server);
            }
            System.out.println("Соединение c сервером разорвано");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
