/**
 * Copyright &copy; 2017 <a href="https://github.com/topojuly/commonsite">topojuly</a> All rights reserved.
 */
package com.topojuly.commonsite.modules.sys.dao;

import java.util.List;

import com.topojuly.commonsite.common.persistence.CrudDao;
import com.topojuly.commonsite.common.persistence.annotation.MyBatisDao;
import com.topojuly.commonsite.modules.sys.entity.Dict;

/**
 * 字典DAO接口
 * @author topojuly
 * @version 2017-05-16
 */
@MyBatisDao
public interface DictDao extends CrudDao<Dict> {

	public List<String> findTypeList(Dict dict);
	
}
