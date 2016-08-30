package com.zking.web.service.impl;

import com.zking.web.entity.admin.Admin;
import com.zking.web.exception.DAOException;
import com.zking.web.factory.DAOFactory;
import com.zking.web.service.IAdminService;

/**
 * IAdminService��ʵ����
 * @author ����
 *
 */
public class AdminServiceImpl implements IAdminService {

	/**
	 * �����˺�aacount��ѯ����Աadmin
	 */
	public Admin getAdmin(String aacount) throws DAOException {
		return DAOFactory.getAdminDAO().selectAdminByAcount(aacount);
	}

}
