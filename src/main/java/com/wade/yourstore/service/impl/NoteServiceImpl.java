package com.wade.yourstore.service.impl;

import com.wade.yourstore.entity.Note;
import com.wade.yourstore.mapper.NoteMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wade
 * @since 2021-02-19
 */
@Service
public class NoteServiceImpl extends ServiceImpl<NoteMapper, Note>{


    public void  saveByCmd(List<String> lines){
        Note note = new Note();
        AtomicReference<String> content= new AtomicReference<>("");
        lines.forEach(line->{
            content.set(content.get()+line+"\n");
        });
        note.setContent(content.get());
        note.setTime(LocalDateTime.now());
        note.setFormat("cmd");
        this.getBaseMapper().insert(note);
    }
}
