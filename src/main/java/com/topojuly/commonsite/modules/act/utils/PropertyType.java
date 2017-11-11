/**
 * Copyright &copy; 2017 <a href="https://github.com/topojuly/commonsite">topojuly</a> All rights reserved.
 */
package com.topojuly.commonsite.modules.act.utils;

import java.util.Date;

/**
 * 属性数据类型
 * @author topojuly
 * @version 2017-11-03
 */
public enum PropertyType {
	
	S(String.class), 
	I(Integer.class), 
	L(Long.class), 
	F(Float.class), 
	N(Double.class),
	D(Date.class), 
	SD(java.sql.Date.class), 
	B(Boolean.class);

	private Class<?> clazz;

	private PropertyType(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Class<?> getValue() {
		return clazz;
	}
}