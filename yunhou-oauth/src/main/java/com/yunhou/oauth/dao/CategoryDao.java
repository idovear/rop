package com.yunhou.oauth.dao;

import java.util.List;

import com.yunhou.oauth.model.Category;

/**
 * 云猴网类别dao层
 * 
 * @author hebing<hebing@bubugao.com>
 * @version 1.0
 * @date: 2015年04月23日 13:44
 * @since JDK 1.7
 */
public interface CategoryDao {

    List<Category> selectAll();

    Category select(String catId);

    void insertList(List<Category> categories);

    void insert(Category category);

    void update(Category category);
}
