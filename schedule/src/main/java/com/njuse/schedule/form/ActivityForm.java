package com.njuse.schedule.form;

import lombok.Data;

@Data
public class ActivityForm {

    private int id;
    private int userId;
    private int isAuthor;
    private String title;
    private Long createTime;
    private Long startTime;
    private Long endTime;
    private String content;
    private String posterUrl;
    private int isFinished;
    private int isPublic;

}
