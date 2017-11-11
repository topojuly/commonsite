/**
 * There are <a href="https://github.com/topojuly/commonsite">topojuly</a> code generation
 */
package com.topojuly.commonsite.modules.oa.dao;

import com.topojuly.commonsite.common.persistence.CrudDao;
import com.topojuly.commonsite.common.persistence.annotation.MyBatisDao;
import com.topojuly.commonsite.modules.oa.entity.Leave;

/**
 * 请假DAO接口
 * @author liuj
 * @version 2017-8-23
 */
@MyBatisDao
public interface LeaveDao extends CrudDao<Leave> {
	
	/**
	 * 更新流程实例ID
	 * @param leave
	 * @return
	 */
	public int updateProcessInstanceId(Leave leave);
	
	/**
	 * 更新实际开始结束时间
	 * @param leave
	 * @return
	 */
	public int updateRealityTime(Leave leave);
	
}
