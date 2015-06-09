package com.yunhou.openapi.model.oauth;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.bubugao.framework.util.UtilJson;
import com.yunhou.openapi.model.RecordStatus;
import com.yunhou.openapi.request.config.OauthLevelConfigRequest;

/**
 * 
 * 应用等级配置<br/>
 * 
 * @author 何冰(hebing@bubugao.com)
 * @date: 2015年6月8日 上午10:09:24
 * @version 1.0
 * @since JDK 1.7
 */
public class OauthLevelConfig {

    private long id;
    private String level;// 等级
    private String desc;// 等级描述
    private String methodsJson;// 可访问的方法列表 json字符串
    private long expireToken;// 秒 token过期时间

    private Date createTime = new Date();// 创建时间
    private Date lastModify = new Date();// 最后修改时间
    private RecordStatus recordStatus;// 字段状态

    private List<String> serviceMethods;// 可访问的方法列表

    public OauthLevelConfig() {

    }

    public OauthLevelConfig(OauthLevelConfigRequest req) {
        this.id = req.getId();
        this.level = req.getLevel();
        this.desc = req.getDesc();
        if (StringUtils.isNotBlank(req.getMethods())) {
            String[] parts = req.getMethods().split(",");
            List<String> methods = new ArrayList<String>();
            for (String part : parts) {
                if (StringUtils.isNotBlank(part)) {
                    methods.add(part);
                }
            }
            setServiceMethods(methods);
        }
        this.expireToken = req.getExpireTokenTime();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<String> getServiceMethods() {
        return serviceMethods;
    }

    public void setServiceMethods(List<String> serviceMethods) {
        this.serviceMethods = serviceMethods;
        if (serviceMethods == null || serviceMethods.size() == 0) {
            this.methodsJson = null;
        } else {
            this.methodsJson = UtilJson.writeValueAsString(serviceMethods);
        }
    }

    public void setMethodsJson(String methodsJson) {
        this.methodsJson = methodsJson;
        if (StringUtils.isNotBlank(methodsJson)) {
            this.serviceMethods = UtilJson.readListValue(methodsJson, String.class);
        } else {
            this.serviceMethods = null;
        }
    }

    public long getExpireToken() {
        return expireToken;
    }

    public String getMethodsJson() {
        return methodsJson;
    }

    public void setExpireToken(long expireToken) {
        this.expireToken = expireToken;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
