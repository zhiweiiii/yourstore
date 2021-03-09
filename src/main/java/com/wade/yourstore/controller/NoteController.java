package com.wade.yourstore.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wade.yourstore.dto.SearchDTO;
import com.wade.yourstore.entity.Note;
import com.wade.yourstore.service.impl.NoteServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wade
 * @since 2021-02-19
 */
@ResponseBody
@Controller
@RequestMapping("/api/yourstore/note")
@Slf4j
public class NoteController {

    @Autowired
    NoteServiceImpl noteService;

    @PostMapping("/getAll")
    public Page getAllNotePage(@RequestBody SearchDTO<String> searchDTO){
        String searchStr="%"+searchDTO.getSearchData()+"%";
        return noteService.getBaseMapper().selectPage(searchDTO,new QueryWrapper<Note>()
                .or().like("title",searchStr).or().like("content",searchStr).orderByDesc("time"));
    }
    @GetMapping("/createByCmd")
    public String create(@RequestParam String content){
        Note note=new Note();
        note.setFormat("cmd");
        note.setTime(LocalDateTime.now());
        note.setContent(content);
        noteService.saveOrUpdate(note);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日hh点mm分ss秒");
        return note.getTime().toString()+"创建成功";
    }

    @PostMapping("update")
    public Note update(@RequestBody Note note){
        if (note.getId()==null){
            note.setFormat("html");
            note.setTime(LocalDateTime.now());
        }
        noteService.saveOrUpdate(note);
        return note;
    }
    @PostMapping("delete")
    public Note delete(@RequestBody Note note){
        noteService.removeById(note.getId());
        return note;

    }
}
