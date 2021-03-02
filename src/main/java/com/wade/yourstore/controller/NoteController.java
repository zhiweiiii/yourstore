package com.wade.yourstore.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wade.yourstore.entity.Note;
import com.wade.yourstore.service.impl.NoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class NoteController {

    @Autowired
    NoteServiceImpl noteService;

    @PostMapping()
    public void test(){
        Scanner s= new Scanner(System.in);//定义一输入s
        System.out.println("输入：");
//判断是否有输入的值，nextLine为接收字符串，next()为接收单词遇到空格就停止
        if(s.hasNextLine()){
            String str= s.nextLine();
            System.out.println("输出的结果为：" +str);}
        System.out.println("结束");
        s.close();
    }

    @PostMapping("/getAll")
    public Page getAllNotePage(@RequestBody Page page){
        return noteService.getBaseMapper().selectPage(page,new QueryWrapper<Note>().orderByDesc("time"));
    }

    @PostMapping("update")
    public Note update(@RequestBody Note note){
        noteService.updateById(note);
        return note;
    }
    @PostMapping("delete")
    public Note delete(@RequestBody Note note){
        noteService.removeById(note.getId());
        return note;

    }
}
