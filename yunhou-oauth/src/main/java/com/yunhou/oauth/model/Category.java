/**
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: bubugao </p>
 */
package com.yunhou.oauth.model;

import java.util.Date;

/**
 * 商品类别实体类
 * 
 * @author hebing<hebing@bubugao.com>
 * @version 1.0
 * @date: 2015年04月23日 11:52
 * @since JDK 1.7
 */
public class Category {
    private String catId = "";// 类别ID
    private String catName = "";// 类名称
    private String url = "";
    private String parentCatId = "";// 父类ID
    private Date createTime = new Date();// 创建时间
    private Date lastModify = new Date();// 最后修改时间

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getParentCatId() {
        return parentCatId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setParentCatId(String parentCatId) {
        this.parentCatId = parentCatId;
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

}
