/**
 * Copyright &copy; 2017 <a href="https://github.com/topojuly/commonsite">topojuly</a> All rights reserved.
 */
package com.topojuly.commonsite.modules.sys.dao;

import com.topojuly.commonsite.common.persistence.CrudDao;
import com.topojuly.commonsite.common.persistence.annotation.MyBatisDao;
import com.topojuly.commonsite.modules.sys.entity.Log;

/**
 * 日志DAO接口
 * @author topojuly
 * @version 2017-05-16
 */
@MyBatisDao
public interface LogDao extends CrudDao<Log> {

}
