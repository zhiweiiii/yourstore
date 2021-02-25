package com.wade.yourstore.conf;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;
import com.wade.yourstore.entity.KeyInfo;
import com.wade.yourstore.entity.Note;
import com.wade.yourstore.service.impl.KeyInfoServiceImpl;
import com.wade.yourstore.service.impl.NoteServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class RegisterKey implements ServletContextAware {

    @Autowired
    KeyInfoServiceImpl keyInfoService;
    @Autowired
    JavaSocketConnect javaSocketConnect;


    private void registerKey(){

        List<KeyInfo> keyInfoList=keyInfoService.getBaseMapper().selectList(new QueryWrapper<>());
        JIntellitype jIntellitype=JIntellitype.getInstance();
        keyInfoList.forEach(keyInfo -> {
            if (keyInfo.getIsUsing()) {
                jIntellitype.registerHotKey(keyInfo.getFlag(), keyInfo.getPrefixKey(), keyInfo.getKeyWord());
            }
        });
        Map<Integer,KeyInfo> flagMap = keyInfoList.stream().filter(item->{
            if (!item.getIsUsing())
                return false;
            return true;
        }).collect(Collectors.toMap(KeyInfo::getFlag, Function.identity(), (key1, key2) -> key2));
        //添加监听
        //实现HotkeyListener
        jIntellitype.addHotKeyListener(aIdentifier -> {
            KeyInfo keyInfo=flagMap.get(aIdentifier);
            if (keyInfo==null) {
                return;
            }
            if (StringUtils.isBlank(keyInfo.getContent())){
                keyInfo.setContent("C:/Windows/System32/cmd.exe /k start cmd");
            }
            try {
                Runtime.getRuntime().exec(keyInfo.getContent());
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }


    @Override
    public void setServletContext(ServletContext servletContext) {
        registerKey();
        javaSocketConnect.listenerJavaInput();
    }


}
