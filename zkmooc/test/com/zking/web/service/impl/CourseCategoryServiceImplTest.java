package com.zking.web.service.impl;

import java.sql.SQLException;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.zking.web.entity.course.CourseCategory;
import com.zking.web.factory.ServiceFactory;

public class CourseCategoryServiceImplTest {

	@Test
	public void testSelectCourseCategoryByKeyPage() {
		List<CourseCategory> categories =
				ServiceFactory.getCourseCategoryService()
				.selectCourseCategoryByKeyPage("s", 1, 2);
		
		Assert.assertTrue(categories.size() > 0);
	}
	
	@Test
	public void testSelectCountByKey() throws SQLException {
		int count =
				ServiceFactory.getCourseCategoryService()
				.selectCountByKey("s");
		
		Assert.assertTrue(count > 0);
	}

}
