package com.zking.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.zking.web.dao.IUserCourseDAO;
import com.zking.web.entity.course.Course;
import com.zking.web.entity.user.User;
import com.zking.web.entity.user.UserCourse;
import com.zking.web.exception.DAOException;
import com.zking.web.sql.CourseCategorySql;
import com.zking.web.sql.UserCourseSql;
import com.zking.web.util.JdbcUtil;

/**
 * 
 * @author 胡博
 *
 */
public class UserCourseDAO extends BaseDAO<UserCourse> implements IUserCourseDAO {
	/**
	 * 根据uuid查询usercourse
	 * @param uuid
	 * @return 返回对应的usercourse
	 * @throws SQLException
	 */
	public List<UserCourse> selectUserCourses(String uuid) throws SQLException {
		return getAll(UserCourseSql.SELECT_BY_UUID, 
				new Object[]{uuid});
	}

	/**
	 * 添加一条usercourse记录
	 * @param ucoure
	 * @return 添加成功返回true,否则返回false
	 * @throws SQLException
	 */
	public boolean saveUserCourse(UserCourse ucourse) throws SQLException {
		return add(UserCourseSql.INSERT, 
				new Object[]{ucourse.getCuid(), ucourse.getUcuid(), ucourse.getUuid()});
	}

	
	@Override
	protected UserCourse getEntity(ResultSet rs) throws DAOException {
		UserCourse uCourse = new UserCourse();
		
		try {
			uCourse.setUcuid(rs.getString("ucuid"));
			uCourse.setCuid(rs.getString("cuid"));
			uCourse.setUuid(rs.getString("uuid"));
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("实体对象转换失败");
		}
		
		return uCourse;
	}

	@Override
	public UserCourse selectByCuidUuid(String cuid, String uuid)
			throws SQLException {
		return get(UserCourseSql.SELECT_BY_CUID_UUID, 
				new Object[]{cuid, uuid});
	}

	@Override
	public boolean deleteByCuidUuid(String cuid, String uuid)
			throws SQLException {
		return delete(UserCourseSql.DELETE_BY_CUID_UUID, 
				new Object[]{cuid, uuid});
	}


	
}
