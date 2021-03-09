package com.wade.yourstore.conf;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wade.yourstore.entity.KeyInfo;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.event.KeyEvent;
import java.io.*;
import java.util.List;
import java.util.Scanner;

public class MyKeyListener implements NativeKeyListener {

    private List<KeyInfo> keyInfoList;

    public MyKeyListener(List<KeyInfo> keyInfoList) {
        super();
        this.keyInfoList=keyInfoList;
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        //按下前
        keyInfoList.forEach(keyInfo -> {
            if (keyInfo.getIsUsing()){
                if ((keyInfo.getPrefixKey()==nativeKeyEvent.getModifiers()) && (nativeKeyEvent.getKeyCode()==keyInfo.getKeyWord())){
                    if (StringUtils.isBlank(keyInfo.getContent())){
                        keyInfo.setContent("cmd /c start cmd");
                    }
                    try {
                        Process p=Runtime.getRuntime().exec("cmd /c start run.bat");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        //按下后


    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {


    }
}
