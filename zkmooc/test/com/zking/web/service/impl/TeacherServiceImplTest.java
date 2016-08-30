package com.zking.web.service.impl;

import java.sql.SQLException;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.zking.web.entity.teacher.Teacher;
import com.zking.web.factory.ServiceFactory;

public class TeacherServiceImplTest {

	@Test
	public void testSelectTeacherByKeyPage() throws SQLException {
		List<Teacher> teachers =
			ServiceFactory.getTeacherService()
			.selectTeacherByKeyPage("b", 1, 2);
		
		Assert.assertTrue(teachers.size() > 0);
	}
	
	@Test
	public void testSelectCountByNameKey() throws SQLException {
		int count = 
				ServiceFactory.getTeacherService()
				.selectCountByNameKey("b");
		
		Assert.assertTrue(count > 0);
	}
	
	@Test
	public void testSelectTeacherByAcountKeyPage() throws SQLException {
		List<Teacher> teachers =
				ServiceFactory.getTeacherService()
				.selectTeacherByAcountKeyPage("t1", 0, 2);
		
		Assert.assertTrue(teachers.size() == 1);
	}

	@Test
	public void testSelectCountByAcountKey() throws SQLException {
		int count = 
				ServiceFactory.getTeacherService()
				.selectCountByAcountKey("t");
		
		Assert.assertTrue(count > 0);
	}
}
