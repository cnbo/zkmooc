package com.zking.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.zking.web.dao.ITeacherDAO;
import com.zking.web.entity.teacher.Teacher;
import com.zking.web.exception.DAOException;
import com.zking.web.sql.TeacherSql;
import com.zking.web.util.JdbcUtil;

/**
 * teacher表的dao操作
 * @author 胡博
 *
 */
public class TeacherDAO extends BaseDAO<Teacher> implements ITeacherDAO {
	
	/**
	 * 添加一条teacher记录
	 * @param 所要添加的teacher
	 * @return	如果添加成功返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean saveTeacher(Teacher teacher) throws SQLException {
		return add(TeacherSql.INSERT_TEACHER, 
				new Object[]{teacher.getTuid(), teacher.getTname(), teacher.getTage(),
							teacher.getTsex(), teacher.getTacount(), teacher.getTphone(),
							teacher.getTmail(), teacher.getTimage(), teacher.getTinfo(),
							teacher.getTpass()});
	}

	/**
	 * 更新一条teacher记录
	 * @param 所要更新的teacher
	 * @return 如果更新成功返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean updateTeacher(Teacher teacher) throws SQLException {
		return update(TeacherSql.UPDATE_BY_TUID, 
				new Object[]{teacher.getTname(), teacher.getTage(), teacher.getTsex(),
							teacher.getTphone(), teacher.getTimage(), teacher.getTinfo(),
							teacher.getTpass(), teacher.getTuid()});
	}

	/**
	 * 根据tuid删除一条teacher记录
	 * @param 所要删除teacher的tuid
	 * @return 如果删除成功返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean deleteTeacher(String tuid) throws SQLException {
		return delete(TeacherSql.DELETE_BY_TUID, 
				new Object[]{tuid});
	}

	/**
	 * 根据tuid查找一条teacher记录
	 * @param 所要查找eacher的tuid
	 * @return 对应的teacher
	 * @throws SQLException
	 */
	public Teacher selectByTuid(String tuid) throws SQLException {
		return get(TeacherSql.SELECT_BY_TUID, 
				new Object[]{tuid});
	}

	/**
	 * 根据账号查找一条teacher记录
	 * @param 所要查找teacher的tacount
	 * @return 对应的teacher
	 * @throws SQLException
	 */
	public Teacher selectByTacount(String tacount) throws SQLException {
		return get(TeacherSql.SELECT_BY_TACOUNT, 
				new Object[]{tacount});
	}
	
	/**
	 * 查找所有teacher的记录
	 * @return 返回一个teacher的List集合
	 * @throws SQLException
	 */
	public List<Teacher> selectTeachers() throws SQLException {
		return getAll(TeacherSql.SELECT_TEACHER_ALL, 
				new Object[]{});
	}
	
	/**
	 * 得到teacher表中记录的条数
	 * @return teacher表中记录的条数
	 * @throws SQLException
	 */
	public int selectTeacherCount() throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(TeacherSql.SELECT_COUNT_OF_TEACHER);
		ResultSet rs = pstmt.executeQuery();
		
		int count = 0;
		
		if (rs.next()) {
			count = rs.getInt(1);
		}
		
		JdbcUtil.close(conn, pstmt, rs);
		
		return count;
	}

	/**
	 * 根据tname查找teacher，因为教师的姓名可能有相同的，所以返回一个list集合
	 * @param 所要查找teacher的tname
	 * @return 返回一个teacher的List集合
	 * @throws SQLException
	 */
	public List<Teacher> selectTeachersByName(String tname) throws SQLException {
		return getAll(TeacherSql.SELECT_ALL_BY_TNAME, 
				new Object[]{tname});
	}

	/**
	 * 查询指定页的teacher
	 * @param page所要查询的页码，每页最多显示的记录数量
	 * @return	对应的teacher的list集合
	 * @throws SQLException
	 */
	public List<Teacher> selectTeachersByPage(int page, int count) throws SQLException {
		int start = 0;
		if (page != 1) {
			start = (page - 1) * count;
		}
		return getAll(TeacherSql.SELECT_BY_PAGE, 
				new Object[]{start, count});
	}
	/**
	 * 根据教师姓名的关键字模糊分页查询
	 * @param key教师姓名的关键字
	 * @param page指定查询的页码
	 * @param count最多需要查询记录的数量
	 * @return
	 * @throws SQLException
	 */
	public List<Teacher> selectTeacherByKeyPage(String key, int page, int count) 
			throws SQLException {
		int start = 0;
		if (page != 1) {
			start = (page - 1) * count;
		}
		
		return getAll(TeacherSql.SELECT_TEACHER_BY_NAME_KEY_PAGE, 
				new Object[]{"%"+key+"%", start, count});
	}

	/**
	 * 根据教师姓名的关键字查询相关记录的条数
	 * @param key教师姓名的关键字
	 * @return 对应的记录条数
	 * @throws SQLException
	 */
	public int selectCountByNameKey(String key) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(
				TeacherSql.SELECT_COUNT_BY_NAME_KEY);
		pstmt.setString(1, "%"+key+"%");
		ResultSet rs = pstmt.executeQuery();
		
		int count = 0;
		
		if (rs.next()) {
			count = rs.getInt(1);
		}
		
		JdbcUtil.close(conn, pstmt, rs);
		
		return count;
	}
	
	/**
	 * 根据教师编号的关键字模糊分页查询
	 * @param key教师编号的关键字
	 * @param page指定查询的页码
	 * @param count最多需要查询记录的数量
	 * @return
	 * @throws SQLException
	 */
	public List<Teacher> selectTeacherByAcountKeyPage(String key, int page, int count)
			throws SQLException {
		return getAll(TeacherSql.SELECT_TEACHER_BY_ACOUNT_KEY_PAGE, 
				new Object[]{"%"+key+"%", (page-1)*count, count});
	}
	
	/**
	 * 根据教师编号的关键字查询相关记录的条数
	 * @param key教师编号的关键字
	 * @return 对应的记录条数
	 * @throws SQLException
	 */
	public int selectCountByAcountKey(String key) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(
				TeacherSql.SELECT_COUNT_BY_ACOUNT_KEY);
		pstmt.setString(1, "%"+key+"%");
		ResultSet rs = pstmt.executeQuery();
		
		int count = 0;
		
		if (rs.next()) {
			count = rs.getInt(1);
		}
		
		JdbcUtil.close(conn, pstmt, rs);
		
		return count;
	}
	
	@Override
	protected Teacher getEntity(ResultSet rs) throws DAOException {
		Teacher teacher = new Teacher();
		
		try {
			teacher.setTuid(rs.getString("tuid"));
			teacher.setTacount(rs.getString("tacount"));
			teacher.setTpass(rs.getString("tpass"));
			teacher.setTname(rs.getString("tname"));
			teacher.setTsex(rs.getString("tsex"));
			teacher.setTage(rs.getInt("tage"));
			teacher.setTphone(rs.getString("tphone"));
			teacher.setTimage(rs.getString("timage"));
			teacher.setTinfo(rs.getString("tinfo"));
			teacher.setTmail(rs.getString("tmail"));
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("实体对象转换失败");
		}
		
		return teacher;
	}

}
