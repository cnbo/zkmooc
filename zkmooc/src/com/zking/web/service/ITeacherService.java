package com.zking.web.service;

import java.sql.SQLException;
import java.util.List;

import com.zking.web.entity.teacher.Teacher;

/**
 * TeacherService的接口类
 * @author 胡博
 *
 */
public interface ITeacherService {
	/**
	 * 添加一条teacher记录
	 * @param 所要添加的teacher
	 * @return 添加成功返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean saveTeacher(Teacher teacher) throws SQLException;
	
	/**
	 * 根据tuid删除一条teacher记录
	 * @param 所要删除教师的tuid
	 * @return 删除成功返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean deleteTeacher(String tuid) throws SQLException;
	
	/**
	 * 修改一条teacher记录
	 * @param 所要修改的teacher
	 * @return 修改成功返回true,否则返回false
	 * @throws SQLException
	 */
	public boolean updateTeacher(Teacher teacher) throws SQLException;
	
	/**
	 * 根据tuid查询一条teacher记录
	 * @param 所要查询teacher的tuid
	 * @return 对应的teacher
	 * @throws SQLException
	 */
	public Teacher selectByTuid(String tuid) throws SQLException;
	
	/**
	 * 根据tacount查询一条teacher记录
	 * @param 所要查询teacher的tacount
	 * @return 对应的teacher
	 * @throws SQLException
	 */
	public Teacher selectByTacount(String tacount) throws SQLException;
	
	/**
	 * 查询出所有的teacher
	 * @return 返回对应的list集合
	 * @throws SQLException
	 */
	public List<Teacher> selectTeachers() throws SQLException;
	
	/**
	 * 查询指定页的teacher
	 * @param page指定页
	 * @param count所要查询的最大记录条数
	 * @return 所要查询的teacher集合
	 * @throws SQLException
	 */
	public List<Teacher> selectTeachersByPage(int page, int count) throws SQLException;
	
	/**
	 * 查询总共有多少条教师记录
	 * @return 返回教师的总人数
	 * @throws SQLException
	 */
	public int selectTeacherCount() throws SQLException;
	
	/**
	 * 根据姓名查找教师，教师的可能会重名，所以返回的结果是个list集合
	 * @param tname
	 * @return 对应的教师集合
	 * @throws SQLException
	 */
	public List<Teacher> selectTeachersByName(String tname) throws SQLException;
	
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
