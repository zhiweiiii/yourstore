package com.wade.yourstore.shell;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class InputJavaShell {




        public static void main(String[] args) throws IOException {
            OutputStream os = null;
            DataOutputStream dos = null;
            try {


                //使用Scanner读取控制台的输入，并发送到服务端

                Scanner sc = new Scanner(System.in);
                String str="";
                while(sc.hasNextLine()){
                    String next=sc.nextLine();
                    if (next.equals("")){
                        break;
                    }
                    str =str+ next+"\n";
                }

                Socket s = new Socket("127.0.0.1", 9555);
                os = s.getOutputStream();
                dos = new DataOutputStream(os);

                dos.writeUTF(str);

            } catch (Exception e) {

                e.printStackTrace();
            }finally {
                dos.close();
                os.close();
            }
        }
    }
