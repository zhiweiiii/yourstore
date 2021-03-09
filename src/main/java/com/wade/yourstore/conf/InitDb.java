package com.wade.yourstore.conf;

import com.wade.yourstore.entity.KeyInfo;
import com.wade.yourstore.service.impl.KeyInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitDb {

    @Autowired
    KeyInfoServiceImpl keyInfoService;

    public void  initDate(){
        if (keyInfoService.getById(1L)==null) {
            KeyInfo keyInfo = new KeyInfo();
            keyInfo.setId(1L);
            keyInfo.setPrefixKey(2);
            keyInfo.setKeyWord(19);
            keyInfo.setContent("cmd /c start run.bat");
            keyInfo.setIsUsing(true);
            keyInfoService.save(keyInfo);
        }
        if (keyInfoService.getById(2L)==null) {
            KeyInfo keyInfo2 = new KeyInfo();
            keyInfo2.setId(2L);
            keyInfo2.setPrefixKey(2);
            keyInfo2.setKeyWord(16);
            keyInfo2.setContent("cmd /c start run.bat");
            keyInfo2.setIsUsing(true);
            keyInfoService.save(keyInfo2);
        }

    }

}
