package com.zking.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.zking.web.dao.IUserDAO;
import com.zking.web.entity.user.User;
import com.zking.web.exception.DAOException;
import com.zking.web.sql.UserSql;
import com.zking.web.util.JdbcUtil;

/**
 * 对user表的dao操作
 * @author 胡博
 *
 */
public class UserDAO extends BaseDAO<User> implements IUserDAO {
	/**
	 * 保存用户
	 * @param user
	 * @return 如果添加成功则返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean saveUser(User user) throws SQLException {
		return add(UserSql.INSERT, 
				new Object[]{user.getUuid(), user.getUname(), user.getUpass(), user.getUsex(),
							user.getUage(), user.getUnickName(), user.getUphone(),
							user.getUmail(), user.getUimage(), user.getUprofession(),
							user.getAddress()});
	}

	/**
	 * 更新用户
	 * @param user
	 * @return 如果更新成功返回true,否则返回false
	 * @throws SQLException
	 */
	public boolean updateUser(User user) throws SQLException {
		return update(UserSql.UPDATE_BY_UUID, 
				new Object[]{user.getUname(), user.getUpass(), user.getUsex(),
							user.getUage(), user.getUnickName(), user.getUphone(),
							user.getUimage(), user.getUprofession(), user.getAddress(),
							user.getUuid()});
	}

	/**
	 * 根据用户的uuid删除用户
	 * @param uuid
	 * @return 如果删除成功返回true,否则返回false
	 * @throws SQLException
	 */
	public boolean deleteUserByUuid(String uuid) throws SQLException {
		return delete(UserSql.DELETE_BY_UUID, 
				new Object[]{uuid});
	}

	/**
	 * 根据用户手机号查询用户
	 * @param uphone
	 * @return 对应的user
	 * @throws SQLException
	 */
	public User selectUserByUphone(String uphone) throws SQLException {
		return get(UserSql.SELECT_BY_UPHONE, 
				new Object[]{uphone});
		
	}

	/**
	 * 根据用户邮箱查询用户
	 * @param umail
	 * @return 对应的User
	 * @throws SQLException
	 */
	public User selectUserByUmail(String umail) throws SQLException {
		return get(UserSql.SELECT_BY_UMAIL, 
				new Object[]{umail});
	}

	/**
	 * 根据用户名查询用户
	 * @param umail
	 * @return 对应的user
	 * @throws SQLException
	 */
	public User selectUserByUnickName(String unickName) throws SQLException {
		return get(UserSql.SELECT_BY_UNICKNAME, 
				new Object[]{unickName});
	}

	/**
	 * 查询所有用户
	 * @return 对应的user的List集合
	 * @throws SQLException
	 */
	public List<User> selectUsers() throws SQLException {
		return getAll(UserSql.SELECT_USER_ALL, 
				new Object[]{});
	}

	@Override
	protected User getEntity(ResultSet rs) throws DAOException {
		User user = new User();
		
		try {
			user.setUuid(rs.getString("uuid"));
			user.setUname(rs.getString("uname"));
			user.setUnickName(rs.getString("unickname"));
			user.setUphone(rs.getString("uphone"));
			user.setUmail(rs.getString("umail"));
			user.setUsex(rs.getString("usex"));
			user.setUpass(rs.getString("upass"));
			user.setAddress(rs.getString("address"));
			user.setUimage(rs.getString("uimage"));
			user.setUage(rs.getInt("uage"));
			user.setUprofession(rs.getString("uprofession"));
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("实体对象转换失败");
		}
		
		return user;
	}

}
