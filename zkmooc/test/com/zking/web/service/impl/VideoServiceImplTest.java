package com.zking.web.service.impl;

import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.zking.web.entity.course.Video;
import com.zking.web.factory.ServiceFactory;

public class VideoServiceImplTest {

	@Test
	public void testSelectVideoByTuidKeyPage() throws SQLException {
		List<Video> videos =
			ServiceFactory.getVideoService()
			.selectVideoByTuidKeyPage("a", 
					"10f7eb8d71fc438ab96e0bad90cd557b", 1, 2);
		
		Assert.assertTrue(videos.size() > 0);
	}

	@Test
	public void testSelectCountByTuidKey() throws SQLException {
		int count =
			ServiceFactory.getVideoService()
			.selectCountByTuidKey("a", 
					"10f7eb8d71fc438ab96e0bad90cd557b");
		
		Assert.assertTrue(count > 0);
	}

}
