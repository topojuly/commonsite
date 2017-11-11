/**
 * Copyright &copy; 2017 <a href="https://github.com/topojuly/commonsite">topojuly</a> All rights reserved.
 */
package com.topojuly.commonsite.modules.cms.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topojuly.commonsite.common.service.CrudService;
import com.topojuly.commonsite.modules.cms.dao.ArticleDataDao;
import com.topojuly.commonsite.modules.cms.entity.ArticleData;

/**
 * 站点Service
 * @author topojuly
 * @version 2017-01-15
 */
@Service
@Transactional(readOnly = true)
public class ArticleDataService extends CrudService<ArticleDataDao, ArticleData> {

}
