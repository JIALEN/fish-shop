package com.alen.base;

import java.util.Date;

import lombok.Data;

/**
 *
 *
 *
 * @description:BaseDo
 *
 * @author alen
 * @create 2019-09-13 17:27
 **/
@Data
public class BaseDo {
	/**
	 * 注册时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 *
	 */
	private Date updateTime;
	/**
	 * id
	 */
	private Long id;

	/**
	 * 是否可用 0可用 1不可用
	 */
	private Long isAvailability;
}
