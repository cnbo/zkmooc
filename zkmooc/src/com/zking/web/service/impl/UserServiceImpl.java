package com.zking.web.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.zking.web.entity.user.User;
import com.zking.web.factory.DAOFactory;
import com.zking.web.service.IUserService;

/**
 * UserService类
 * @author 胡博
 *
 */
public class UserServiceImpl implements IUserService {
	/**
	 * 添加一条user记录
	 * @param 所要添加的user
	 * @return 添加成功返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean saveUser(User user) throws SQLException {
		return DAOFactory.getUserDAO().saveUser(user);
	}

	/**
	 * 根据uuid删除一条user记录，这个方法暂且用不到，嘻嘻。。。。。
	 * @param 所要删除User的uuid
	 * @return 删除成功返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean deleteUserByUuid(String uuid) throws SQLException {
		return DAOFactory.getUserDAO().deleteUserByUuid(uuid);
	}

	/**
	 * 更新一条user记录
	 * @param 所要更新的user
	 * @return 更新成功返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean updateUser(User user) throws SQLException {
		return DAOFactory.getUserDAO().updateUser(user);
	}

	/**
	 * 根据uphone查询user
	 * @param 所要查询user的uphone
	 * @return 对应的user
	 * @throws SQLException
	 */
	public User selectUserByUphone(String uphone) throws SQLException {
		return DAOFactory.getUserDAO().selectUserByUphone(uphone);
	}

	/**
	 * 根据umai查询user
	 * @param 所要查询user的umail
	 * @return 对应的user
	 * @throws SQLException
	 */
	public User selectUserByUmail(String umail) throws SQLException {
		return DAOFactory.getUserDAO().selectUserByUmail(umail);
	}

	/**
	 * 根据unickName查询user
	 * @param 对应user的unickName
	 * @return 对应的User
	 * @throws SQLException
	 */
	public User selectUserByUnickName(String unickName) throws SQLException {
		return DAOFactory.getUserDAO().selectUserByUnickName(unickName);
	}

	/**
	 * 查询出所有user，好像并不会用到此方法，只能怪我手贱，哈哈哈。。。。。。
	 * @return
	 * @throws SQLException
	 */
	public List<User> selectUsers() throws SQLException {
		return DAOFactory.getUserDAO().selectUsers();
	}

}
