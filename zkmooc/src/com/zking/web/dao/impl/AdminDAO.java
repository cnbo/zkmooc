package com.zking.web.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zking.web.dao.IAdminDAO;
import com.zking.web.entity.admin.Admin;
import com.zking.web.exception.DAOException;
import com.zking.web.sql.AdminSql;
import com.zking.web.util.JdbcUtil;

/**
 * 
 * @author 胡博
 *
 */
public class AdminDAO extends BaseDAO<Admin> implements IAdminDAO {

	
	public Admin selectAdminByAcount(String aacount) throws DAOException {
		return get(AdminSql.SELECT_ADMIN, new Object[]{aacount});
	}

	@Override
	protected Admin getEntity(ResultSet rs) throws DAOException {
		Admin admin = new Admin();
		try {
			admin.setAcount(rs.getString("aacount"));
			admin.setApass(rs.getString("apass"));
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("admin对象转换失败");
		}
		
		return admin;
	}

}
