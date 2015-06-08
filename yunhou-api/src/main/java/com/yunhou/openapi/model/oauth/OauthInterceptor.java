package com.yunhou.openapi.model.oauth;

import java.util.Date;

import com.yunhou.openapi.model.RecordStatus;
import com.yunhou.openapi.model.security.RatelimtResource;
import com.yunhou.openapi.request.config.OauthInterceptorRequest;

/**
 * 
 * 应用访问拦截<br/>
 * 
 * @author 何冰(hebing@bubugao.com)
 * @date: 2015年6月2日 下午2:36:57
 * @version 1.0
 * @since JDK 1.7
 */
public class OauthInterceptor {

    private long id;
    private RatelimtResource type;// 拦截类型 ip,应用..
    private String resource;// 拦截匹配字符串

    private Date createTime = new Date();// 创建时间
    private Date lastModify = new Date();// 最后修改时间
    private RecordStatus recordStatus;// 字段状态
    
    public OauthInterceptor(){
        
    }

    public OauthInterceptor(OauthInterceptorRequest req) {
        this.id = req.getId();
        this.type = req.getType();
        this.resource = req.getResource();
    }

    public RatelimtResource getType() {
        return type;
    }

    public void setType(RatelimtResource type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
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

}
