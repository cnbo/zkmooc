package com.zking.web.service.impl;

import com.zking.web.entity.admin.Admin;
import com.zking.web.exception.DAOException;
import com.zking.web.factory.DAOFactory;
import com.zking.web.service.IAdminService;

/**
 * IAdminService的实现类
 * @author 胡博
 *
 */
public class AdminServiceImpl implements IAdminService {

	/**
	 * 根据账号aacount查询管理员admin
	 */
	public Admin getAdmin(String aacount) throws DAOException {
		return DAOFactory.getAdminDAO().selectAdminByAcount(aacount);
	}

}
