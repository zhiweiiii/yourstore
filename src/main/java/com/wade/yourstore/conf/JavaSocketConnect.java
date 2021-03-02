package com.wade.yourstore.conf;

import com.wade.yourstore.entity.Note;
import com.wade.yourstore.service.impl.NoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

@Component
public class JavaSocketConnect {

    @Autowired
    NoteServiceImpl noteService;

    @Async
    public void listenerJavaInput() {
        try {
            ServerSocket server = new ServerSocket(9555);
            while (true) {
                //等待请求
                try {
                    Socket socket = server.accept();
                    BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String allLine = "";
                    String line;
                    boolean first = true;
                    while ((line = is.readLine()) != null) {
                        if (first)
                            line = line.substring(2).trim();
                        allLine += " " + line;
                    }
                    Note note = new Note();
                    note.setContent(allLine);
                    note.setTime(LocalDateTime.now());
                    note.setFormat("cmd");
                    noteService.getBaseMapper().insert(note);
                    System.out.println("received frome client:" + line);
                    //创建PrintWriter，用于发送数据
                    PrintWriter pw = new PrintWriter(socket.getOutputStream());
                    pw.println("this data is from server");
                    pw.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
