/**
 * Copyright &copy; 2017 <a href="https://github.com/topojuly/commonsite">topojuly</a> All rights reserved.
 */
package com.topojuly.commonsite.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topojuly.commonsite.common.service.CrudService;
import com.topojuly.commonsite.common.utils.CacheUtils;
import com.topojuly.commonsite.modules.sys.dao.DictDao;
import com.topojuly.commonsite.modules.sys.entity.Dict;
import com.topojuly.commonsite.modules.sys.utils.DictUtils;

/**
 * 字典Service
 * @author topojuly
 * @version 2017-05-16
 */
@Service
@Transactional(readOnly = true)
public class DictService extends CrudService<DictDao, Dict> {
	
	/**
	 * 查询字段类型列表
	 * @return
	 */
	public List<String> findTypeList(){
		return dao.findTypeList(new Dict());
	}

	@Transactional(readOnly = false)
	public void save(Dict dict) {
		super.save(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

	@Transactional(readOnly = false)
	public void delete(Dict dict) {
		super.delete(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

}
