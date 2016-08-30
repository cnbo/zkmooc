package com.zking.web.dao;

import java.sql.SQLException;
import java.util.List;

import com.zking.web.entity.user.User;

/**
 * 用户DAO接口
 * @author 胡博
 *
 */
public interface IUserDAO {
	/**
	 * 保存用户
	 * @param user
	 * @return 如果添加成功则返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean saveUser(User user) throws SQLException; 
	
	/**
	 * 更新用户
	 * @param user
	 * @return 如果更新成功返回true,否则返回false
	 * @throws SQLException
	 */
	public boolean updateUser(User user) throws SQLException; 
	
	/**
	 * 根据用户的uuid删除用户
	 * @param uuid
	 * @return 如果删除成功返回true,否则返回false
	 * @throws SQLException
	 */
	public boolean deleteUserByUuid(String uuid) throws SQLException; 
	
	/**
	 * 根据用户手机号查询用户
	 * @param uphone
	 * @return 对应的user
	 * @throws SQLException
	 */
	public User selectUserByUphone(String uphone) throws SQLException; 
	
	/**
	 * 根据用户邮箱查询用户
	 * @param umail
	 * @return 对应的User
	 * @throws SQLException
	 */
	public User selectUserByUmail(String umail) throws SQLException; 
	
	/**
	 * 根据用户名查询用户
	 * @param umail
	 * @return 对应的user
	 * @throws SQLException
	 */
	public User selectUserByUnickName(String unickName) throws SQLException; 
	
	/**
	 * 查询所有用户
	 * @return 对应的user的List集合
	 * @throws SQLException
	 */
	public List<User> selectUsers() throws SQLException; 
}
