package com.yunhou.openapi.model.oauth;

import java.util.Date;

import com.yunhou.openapi.model.RecordStatus;
import com.yunhou.openapi.model.security.RatelimtResource;
import com.yunhou.openapi.request.config.OauthLimitRequest;

/**
 * 
 * 访问频率实体类<br/>
 * 
 * @author 何冰(hebing@bubugao.com)
 * @date: 2015年6月1日 下午4:03:10
 * @version 1.0
 * @since JDK 1.7
 */
public class OauthLimit {

    private long id;
    private RatelimtResource resource;// 来源
    private String method;// 方法
    private int limitCount;// 限制次数
    private OauthlimitTime limitTime;// 限制次数 时间单位
    private String level;// 应用等级

    private Date createTime = new Date();// 创建时间
    private Date lastModify = new Date();// 最后修改时间
    private RecordStatus recordStatus;// 字段状态

    public OauthLimit() {

    }

    public OauthLimit(OauthLimitRequest req) {
        this.id = req.getId();
        this.resource = req.getResource();
        this.method = req.getMethods();
        this.limitCount = req.getLimitCount();
        this.limitTime = req.getLimitTime();
        this.level = req.getLevel();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RatelimtResource getResource() {
        return resource;
    }

    public void setResource(RatelimtResource resource) {
        this.resource = resource;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getLimitCount() {
        return limitCount;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setLimitCount(int limitCount) {
        this.limitCount = limitCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModify() {
        return lastModify;
    }

    public void setLastModify(Date lastModify) {
        this.lastModify = lastModify;
    }

    public RecordStatus getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(RecordStatus recordStatus) {
        this.recordStatus = recordStatus;
    }

    public OauthlimitTime getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(OauthlimitTime limitTime) {
        this.limitTime = limitTime;
    }

}
