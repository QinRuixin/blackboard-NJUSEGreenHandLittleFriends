package com.njuse.schedule.entity;

import lombok.Data;

@Data
public class ActivityEntity {

    private Integer id;
    private Integer userId;
    private int isAuthor;
    private String title;
    private long createTime;
    private long startTime;
    private long endTime;
    private String posterUrl;
    private String content;
    private int isFinished;
    private int isPublic;

}
