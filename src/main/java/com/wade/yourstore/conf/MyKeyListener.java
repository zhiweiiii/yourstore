package com.wade.yourstore.conf;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wade.yourstore.entity.KeyInfo;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

public class MyKeyListener implements NativeKeyListener {

    private List<KeyInfo> keyInfoList;

    public MyKeyListener(List<KeyInfo> keyInfoList) {
        super();
        this.keyInfoList=keyInfoList;
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        System.out.println(nativeKeyEvent.getKeyCode());
        //按下前
        keyInfoList.forEach(keyInfo -> {
            if (keyInfo.getIsUsing()){
                if ((nativeKeyEvent.getModifiers() & keyInfo.getPrefixKey()) >0 && (nativeKeyEvent.getKeyCode()==keyInfo.getKeyWord())){
                    if (StringUtils.isBlank(keyInfo.getContent())){
                        keyInfo.setContent("C:/Windows/System32/cmd.exe /k start cmd");
                    }
                    try {
                        Runtime.getRuntime().exec(keyInfo.getContent());
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
