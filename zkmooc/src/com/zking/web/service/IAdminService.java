package com.zking.web.service;

import com.zking.web.entity.admin.Admin;
import com.zking.web.exception.DAOException;

/**
 * admin的service接口
 * @author 胡博
 *
 */
public interface IAdminService {
	/**
	 * 根据账号aacount得到一条admin记录
	 * @param 所要查询admin记录的账号
	 * @return admin
	 * @throws DAOException
	 */
	public Admin getAdmin(String aacount) throws DAOException;
}
