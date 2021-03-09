package com.wade.yourstore.conf;


import com.wade.yourstore.controller.NoteController;
import com.wade.yourstore.service.impl.NoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class FileWatchedAdapter {

    @Autowired
    NoteController noteController;


    public void onCreated(WatchEvent<Path> watchEvent) throws IOException {
        Path fileName = Paths.get("out/" + watchEvent.context().getFileName());
        try {
            List<String> lines = Files.readAllLines(fileName);
            AtomicReference<String> content = new AtomicReference<>("");
            lines.forEach(line -> {
                content.set(content.get() + line + "\n");
            });
            noteController.create(content.get());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Files.delete(fileName);
        }


    }


    public void onDeleted(WatchEvent<Path> watchEvent) {
//        Path fileName = watchEvent.context();
//        System.out.println(String.format("文件【%s】被删除，时间：%s", fileName, now()));
    }


    public void onModified(WatchEvent<Path> watchEvent) throws IOException {

//        System.out.println(String.format("文件【%s】被修改，时间：%s", fileName, now()));
    }


    public void onOverflowed(WatchEvent<Path> watchEvent) {
        Path fileName = watchEvent.context();
        System.out.println(String.format("文件【%s】被丢弃，时间：%s", fileName, now()));
    }

    private String now(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        return dateFormat.format(Calendar.getInstance().getTime());
    }
}
