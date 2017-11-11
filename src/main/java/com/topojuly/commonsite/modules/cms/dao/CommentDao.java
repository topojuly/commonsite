/**
 * Copyright &copy; 2017 <a href="https://github.com/topojuly/commonsite">topojuly</a> All rights reserved.
 */
package com.topojuly.commonsite.modules.cms.dao;

import com.topojuly.commonsite.common.persistence.CrudDao;
import com.topojuly.commonsite.common.persistence.annotation.MyBatisDao;
import com.topojuly.commonsite.modules.cms.entity.Comment;

/**
 * 评论DAO接口
 * @author topojuly
 * @version 2017-8-23
 */
@MyBatisDao
public interface CommentDao extends CrudDao<Comment> {

}
