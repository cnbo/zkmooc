package com.zking.web.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.zking.web.entity.user.User;
import com.zking.web.factory.DAOFactory;
import com.zking.web.service.IUserService;

/**
 * UserService��
 * @author ����
 *
 */
public class UserServiceImpl implements IUserService {
	/**
	 * ���һ��user��¼
	 * @param ��Ҫ��ӵ�user
	 * @return ��ӳɹ�����true�����򷵻�false
	 * @throws SQLException
	 */
	public boolean saveUser(User user) throws SQLException {
		return DAOFactory.getUserDAO().saveUser(user);
	}

	/**
	 * ����uuidɾ��һ��user��¼��������������ò�������������������
	 * @param ��Ҫɾ��User��uuid
	 * @return ɾ���ɹ�����true�����򷵻�false
	 * @throws SQLException
	 */
	public boolean deleteUserByUuid(String uuid) throws SQLException {
		return DAOFactory.getUserDAO().deleteUserByUuid(uuid);
	}

	/**
	 * ����һ��user��¼
	 * @param ��Ҫ���µ�user
	 * @return ���³ɹ�����true�����򷵻�false
	 * @throws SQLException
	 */
	public boolean updateUser(User user) throws SQLException {
		return DAOFactory.getUserDAO().updateUser(user);
	}

	/**
	 * ����uphone��ѯuser
	 * @param ��Ҫ��ѯuser��uphone
	 * @return ��Ӧ��user
	 * @throws SQLException
	 */
	public User selectUserByUphone(String uphone) throws SQLException {
		return DAOFactory.getUserDAO().selectUserByUphone(uphone);
	}

	/**
	 * ����umai��ѯuser
	 * @param ��Ҫ��ѯuser��umail
	 * @return ��Ӧ��user
	 * @throws SQLException
	 */
	public User selectUserByUmail(String umail) throws SQLException {
		return DAOFactory.getUserDAO().selectUserByUmail(umail);
	}

	/**
	 * ����unickName��ѯuser
	 * @param ��Ӧuser��unickName
	 * @return ��Ӧ��User
	 * @throws SQLException
	 */
	public User selectUserByUnickName(String unickName) throws SQLException {
		return DAOFactory.getUserDAO().selectUserByUnickName(unickName);
	}

	/**
	 * ��ѯ������user�����񲢲����õ��˷�����ֻ�ܹ����ּ���������������������
	 * @return
	 * @throws SQLException
	 */
	public List<User> selectUsers() throws SQLException {
		return DAOFactory.getUserDAO().selectUsers();
	}

}
