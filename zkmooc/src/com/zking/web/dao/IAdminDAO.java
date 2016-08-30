package com.zking.web.dao;

import java.io.IOException;

import com.zking.web.entity.admin.Admin;
import com.zking.web.exception.DAOException;

/**
 * 操作admin表规范
 * @author 胡博
 *
 */
public interface IAdminDAO {
	/**
	 * 根据管理员账号查询管理员
	 * @param acount 所要查询管理员的账号
	 * @return 管理员
	 * @throws SQLException 
	 * @throws IOException
	 */
	public Admin selectAdminByAcount(String aacount) throws DAOException;
}
