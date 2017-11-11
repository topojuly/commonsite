/**
 * Copyright &copy; 2017 <a href="https://github.com/topojuly/commonsite">topojuly</a> All rights reserved.
 */
package com.topojuly.commonsite.modules.sys.dao;

import com.topojuly.commonsite.common.persistence.TreeDao;
import com.topojuly.commonsite.common.persistence.annotation.MyBatisDao;
import com.topojuly.commonsite.modules.sys.entity.Area;

/**
 * 区域DAO接口
 * @author topojuly
 * @version 2017-05-16
 */
@MyBatisDao
public interface AreaDao extends TreeDao<Area> {
	
}
