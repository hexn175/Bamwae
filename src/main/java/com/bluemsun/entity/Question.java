package com.bluemsun.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("tb_question")
@Data
public class Question {

    private int id;
    //题目
    private String stem;
    //选项A
    private String optionA;
    //选项B
    private String optionB;
    //选项C
    private String optionC;
    //选项D
    private String optionD;
    //图片
    private String optionPicture;
    //正确选项
    private String optionCorrect;
    //解析
    private String optionAnalysis;
    //题目类型：0单选 1多选 2判断
    private Integer status;

}
