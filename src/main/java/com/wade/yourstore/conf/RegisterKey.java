package com.wade.yourstore.conf;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wade.yourstore.entity.KeyInfo;

import com.wade.yourstore.service.impl.KeyInfoServiceImpl;

import lombok.SneakyThrows;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import javax.servlet.ServletContext;
import java.util.List;


@Component
public class RegisterKey implements ServletContextAware{

    @Autowired
    KeyInfoServiceImpl keyInfoService;
    @Autowired
    JavaSocketConnect javaSocketConnect;

    private void registerKeyBy() throws NativeHookException, NoSuchFieldException, IllegalAccessException {
        List<KeyInfo> keyInfoList=keyInfoService.getBaseMapper().selectList(new QueryWrapper<>());
        //注册监听
        GlobalScreen.registerNativeHook();
        NativeKeyListener listener = new MyKeyListener(keyInfoList);
        GlobalScreen.addNativeKeyListener(listener);
    }


    @SneakyThrows
    @Override
    public void setServletContext(ServletContext servletContext) {
        registerKeyBy();
        javaSocketConnect.listenerJavaInput();
    }



}
