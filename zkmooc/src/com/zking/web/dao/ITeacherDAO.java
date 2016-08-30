package com.zking.web.dao;

import java.sql.SQLException;
import java.util.List;

import com.zking.web.entity.teacher.Teacher;

/**
 * teacher表的dao操作
 * @author 胡博
 *
 */
public interface ITeacherDAO {
	/**
	 * 添加一条teacher记录
	 * @param 所要添加的teacher
	 * @return	如果添加成功返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean saveTeacher(Teacher teacher) throws SQLException;
	
	/**
	 * 更新一条teacher记录
	 * @param 所要更新的teacher
	 * @return 如果更新成功返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean updateTeacher(Teacher teacher) throws SQLException;
	
	/**
	 * 根据tuid删除一条teacher记录
	 * @param 所要删除teacher的tuid
	 * @return 如果删除成功返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean deleteTeacher(String tuid) throws SQLException;
	
	/**
	 * 根据tuid查找一条teacher记录
	 * @param 所要查找eacher的tuid
	 * @return 对应的teacher
	 * @throws SQLException
	 */
	public Teacher selectByTuid(String tuid) throws SQLException;
	
	/**
	 * 根据账号查找一条teacher记录
	 * @param 所要查找teacher的tacount
	 * @return 对应的teacher
	 * @throws SQLException
	 */
	public Teacher selectByTacount(String tacount) throws SQLException;
	
	/**
	 * 查找所有teacher的记录
	 * @return 返回一个teacher的List集合
	 * @throws SQLException
	 */
	public List<Teacher> selectTeachers() throws SQLException;
	
	/**
	 * 根据tname查找teacher，因为教师的姓名可能有相同的，所以返回一个list集合
	 * @param 所要查找teacher的tname
	 * @return 返回一个teacher的List集合
	 * @throws SQLException
	 */
	public List<Teacher> selectTeachersByName(String tname) throws SQLException;
		
	/**
	 * 得到teacher表中记录的条数
	 * @return teacher表中记录的条数
	 * @throws SQLException
	 */
	public int selectTeacherCount() throws SQLException;
	
	/**
	 * 查询指定页的teacher
	 * @param page所要查询的页码，每页最多显示的记录数量
	 * @return	对应的teacher的list集合
	 * @throws SQLException
	 */
	public List<Teacher> selectTeachersByPage(int page, int count) throws SQLException;
	
	/**
	 * 根据教师姓名的关键字模糊分页查询
	 * @param key教师姓名的关键字
	 * @param page指定查询的页码
	 * @param count最多需要查询记录的数量
	 * @return
	 * @throws SQLException
	 */
	public List<Teacher> selectTeacherByKeyPage(String key, int page, int count)
			throws SQLException;
	
	/**
	 * 根据教师姓名的关键字查询相关记录的条数
	 * @param key教师姓名的关键字
	 * @return 对应的记录条数
	 * @throws SQLException
	 */
	public int selectCountByNameKey(String key) throws SQLException;
	
	/**
	 * 根据教师编号的关键字模糊分页查询
	 * @param key教师编号的关键字
	 * @param page指定查询的页码
	 * @param count最多需要查询记录的数量
	 * @return
	 * @throws SQLException
	 */
	public List<Teacher> selectTeacherByAcountKeyPage(String key, int page, int count)
			throws SQLException;
	
	/**
	 * 根据教师编号的关键字查询相关记录的条数
	 * @param key教师编号的关键字
	 * @return 对应的记录条数
	 * @throws SQLException
	 */
	public int selectCountByAcountKey(String key) throws SQLException;
}
