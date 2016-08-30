package com.zking.web.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.zking.web.entity.teacher.Teacher;
import com.zking.web.factory.DAOFactory;
import com.zking.web.service.ITeacherService;

/**
 * TeacherService��
 * @author ��
 *
 */
public class TeacherServiceImpl implements ITeacherService {

	/**
	 * ���һ��teacher��¼
	 * @param ��Ҫ��ӵ�teacher
	 * @return ��ӳɹ�����true�����򷵻�false
	 * @throws SQLException
	 */
	public boolean saveTeacher(Teacher teacher) throws SQLException {
		return DAOFactory.getTeacherDAO().saveTeacher(teacher);
	}

	/**
	 * ���tuidɾ��һ��teacher��¼
	 * @param ��Ҫɾ���ʦ��tuid
	 * @return ɾ��ɹ�����true�����򷵻�false
	 * @throws SQLException
	 */
	public boolean deleteTeacher(String tuid) throws SQLException {
		return DAOFactory.getTeacherDAO().deleteTeacher(tuid);
	}

	/**
	 * �޸�һ��teacher��¼
	 * @param ��Ҫ�޸ĵ�teacher
	 * @return �޸ĳɹ�����true,���򷵻�false
	 * @throws SQLException
	 */
	public boolean updateTeacher(Teacher teacher) throws SQLException {
		return DAOFactory.getTeacherDAO().updateTeacher(teacher);
	}

	/**
	 * ���tuid��ѯһ��teacher��¼
	 * @param ��Ҫ��ѯteacher��tuid
	 * @return ��Ӧ��teacher
	 * @throws SQLException
	 */
	public Teacher selectByTuid(String tuid) throws SQLException {
		return DAOFactory.getTeacherDAO().selectByTuid(tuid);
	}

	/**
	 * ���tacount��ѯһ��teacher��¼
	 * @param ��Ҫ��ѯteacher��tacount
	 * @return ��Ӧ��teacher
	 * @throws SQLException
	 */
	public Teacher selectByTacount(String tacount) throws SQLException {
		return DAOFactory.getTeacherDAO().selectByTacount(tacount);
	}

	/**
	 * ��ѯ�����е�teacher
	 * @return ���ض�Ӧ��list����
	 * @throws SQLException
	 */
	public List<Teacher> selectTeachers() throws SQLException {
		return DAOFactory.getTeacherDAO().selectTeachers();
	}

	/**
	 * ��ѯָ��ҳ��teacher
	 * @param pageָ��ҳ
	 * @param count��Ҫ��ѯ������¼����
	 * @return ��Ҫ��ѯ��teacher����
	 * @throws SQLException
	 */
	public List<Teacher> selectTeachersByPage(int page, int count) throws SQLException {
		return DAOFactory.getTeacherDAO().selectTeachersByPage(page, count);
	}

	/**
	 * ��ѯ�ܹ��ж�������ʦ��¼
	 * @return ���ؽ�ʦ��������
	 * @throws SQLException
	 */
	public int selectTeacherCount() throws SQLException {
		return DAOFactory.getTeacherDAO().selectTeacherCount();
	}

	/**
	 * ���������ҽ�ʦ����ʦ�Ŀ��ܻ��������Է��صĽ���Ǹ�list����
	 * @param tname
	 * @return ��Ӧ�Ľ�ʦ����
	 * @throws SQLException
	 */
	public List<Teacher> selectTeachersByName(String tname) throws SQLException {
		return DAOFactory.getTeacherDAO().selectTeachersByName(tname);
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
		return DAOFactory.getTeacherDAO().selectTeacherByKeyPage(key, page, count);
	}
	
	/**
	 * 根据教师姓名的关键字查询相关记录的条数
	 * @param key教师姓名的关键字
	 * @return 对应的记录条数
	 * @throws SQLException
	 */
	public int selectCountByNameKey(String key) throws SQLException {
		return DAOFactory.getTeacherDAO().selectCountByNameKey(key);
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
		return DAOFactory.getTeacherDAO().selectTeacherByAcountKeyPage(key, page, count);
	}
	
	/**
	 * 根据教师编号的关键字查询相关记录的条数
	 * @param key教师编号的关键字
	 * @return 对应的记录条数
	 * @throws SQLException
	 */
	public int selectCountByAcountKey(String key) throws SQLException {
		return DAOFactory.getTeacherDAO().selectCountByAcountKey(key);
	}
}
