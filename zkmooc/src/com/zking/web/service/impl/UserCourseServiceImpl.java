package com.zking.web.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.zking.web.entity.user.UserCourse;
import com.zking.web.factory.DAOFactory;
import com.zking.web.service.IUserCourseService;

public class UserCourseServiceImpl implements IUserCourseService {

	@Override
	public List<UserCourse> selectUserCourses(String uuid) throws SQLException {
		return DAOFactory.getUserCourseDAO()
				.selectUserCourses(uuid);
	}

	@Override
	public boolean saveUserCourse(UserCourse ucourse) throws SQLException {
		return DAOFactory.getUserCourseDAO()
				.saveUserCourse(ucourse);
	}

	@Override
	public UserCourse selectByCuidUuid(String cuid, String uuid)
			throws SQLException {
		return DAOFactory.getUserCourseDAO()
				.selectByCuidUuid(cuid, uuid);
	}

	@Override
	public boolean deleteByCuidUuid(String cuid, String uuid)
			throws SQLException {
		return DAOFactory.getUserCourseDAO()
				.deleteByCuidUuid(cuid, uuid);
	}

}
