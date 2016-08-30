package com.zking.web.service.impl;

import java.sql.SQLException;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.zking.web.entity.course.Course;
import com.zking.web.factory.ServiceFactory;

public class CourseServiceImplTest {

	@Test
	public void testSelectCoursesByKeyword() throws SQLException {
		String keyword = "入";
		List<Course> courses =
				ServiceFactory.getCourseService().selectCoursesByKeyword(keyword);
		
		Assert.assertTrue(courses.size() > 0);
	}

	@Test
	public void testSelectCountByKeyTuid() throws SQLException {
		int count =
				ServiceFactory.getCourseService().selectCountByKeyTuid("j",
						"de51ccc7b4e8445c9b5df01ab4b07d97");
		
		Assert.assertTrue(count > 0);
	}
	
	@Test
	public void testSelectCourseByKeyTuidPage() throws SQLException {
		List<Course> courses =
				ServiceFactory.getCourseService()
				.selectCourseByKeyTuidPage("j",
						"de51ccc7b4e8445c9b5df01ab4b07d97", 1, 2);
		
		Assert.assertTrue(courses.size() > 0);
	}
}
