/**
 * Copyright &copy; 2017 <a href="https://github.com/topojuly/commonsite">topojuly</a> All rights reserved.
 */
package com.topojuly.commonsite.modules.gen.dao;

import com.topojuly.commonsite.common.persistence.CrudDao;
import com.topojuly.commonsite.common.persistence.annotation.MyBatisDao;
import com.topojuly.commonsite.modules.gen.entity.GenTemplate;

/**
 * 代码模板DAO接口
 * @author topojuly
 * @version 2017-10-15
 */
@MyBatisDao
public interface GenTemplateDao extends CrudDao<GenTemplate> {
	
}
