package com.njuse.schedule.vo;

import com.njuse.schedule.entity.ActivityEntity;
import lombok.Data;

@Data
public class ActivityVO {

    private int id;
    private int userId;
    private int isAuthor;
    private String title;
    private Long createTime;
    private Long startTime;
    private Long endTime;
    private String content;
    private String poster_url;
    private int isFinished;
    private int isPublic;
    private String author;
    private String department;


    public ActivityVO(ActivityEntity activityEntity){
        if(activityEntity!=null){
            this.id = activityEntity.getId();
            this.userId = activityEntity.getUserId();
            this.isAuthor =activityEntity.getIsAuthor();
            this.title = activityEntity.getTitle();
            this.createTime = activityEntity.getCreateTime();
            this.startTime = activityEntity.getStartTime();
            this.endTime = activityEntity.getEndTime();
            this.content = activityEntity.getContent();
            this.poster_url = activityEntity.getPosterUrl();
            this.isFinished = activityEntity.getIsFinished();
            this.isPublic = activityEntity.getIsPublic();
        }
    }
}
