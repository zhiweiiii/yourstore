package com.wade.yourstore.shell;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class InputJavaShell {




        public static void main(String[] args) {
            try {
                Socket s = new Socket("127.0.0.1", 9555);
                OutputStream os = s.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                //使用Scanner读取控制台的输入，并发送到服务端

                Scanner sc = new Scanner(System.in);

                String str = sc.nextLine();
                dos.writeUTF(str);

                dos.close();
                s.close();
            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
