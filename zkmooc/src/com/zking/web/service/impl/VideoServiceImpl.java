package com.zking.web.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.zking.web.entity.course.Video;
import com.zking.web.factory.DAOFactory;
import com.zking.web.service.IVideoService;

/**
 * VideoService的实现类
 * @author 胡博
 *
 */
public class VideoServiceImpl implements IVideoService {
	/**
	 * 添加一条video记录
	 * @param 所要添加的video
	 * @return 如果添加成功返回true,否则返回false
	 * @throws SQLException
	 */
	public boolean saveVideo(Video video) throws SQLException {
		return DAOFactory.getVideoDAO().saveVideo(video);
	}

	/**
	 * 更新一条video记录
	 * @param 所要更新的video
	 * @return 如果更新成功返回true,否则返回false
	 * @throws SQLException
	 */
	public boolean updateVideo(Video video) throws SQLException {
		return DAOFactory.getVideoDAO().updateVideo(video);
	}

	/**
	 * 根据vuid删除一条video记录
	 * @param 所要删除video的vuid
	 * @return 删除成功返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean deleteVideo(String vuid) throws SQLException {
		return DAOFactory.getVideoDAO().deleteVideo(vuid);
	}

	/**
	 * 根据cuid查询video的list集合
	 * @param cuid
	 * @return	返回cuid对应得video的list集合
	 * @throws SQLException
	 */
	public List<Video> selectVideos(String cuid) throws SQLException {
		return DAOFactory.getVideoDAO().selectVideos(cuid);
	}

	/**
	 * 根据vuid查询video
	 * @param vuid
	 * @return 对应得video
	 * @throws SQLException
	 */
	public Video selectVideoByVuid(String vuid) throws SQLException {
		return DAOFactory.getVideoDAO().selectVideoByVuid(vuid);
	}

	/**
	 * 根据vname查询video
	 * @param vname
	 * @return 相对应得video
	 * @throws SQLException
	 */
	public Video selectVideoByVname(String vname) throws SQLException {
		return DAOFactory.getVideoDAO().selectVideoByVname(vname);
	}

	/**
	 * 根据cuid获取视频名称的List集合
	 * @param cuid
	 * @return 对应得List集合
	 * @throws SQLException
	 */
	public List<String> selectVnames(String cuid) throws SQLException {
		return DAOFactory.getVideoDAO().selectVnames(cuid);
	}

	/**
	 * 获取又少条video记录
	 * @return video记录的数量
	 * @throws SQLException
	 */
	public int selectVideoCount() throws SQLException {
		return DAOFactory.getVideoDAO().selectVideoCount();
	}

	/**
	 * 获取指定页page的video的list集合，并制定每页的数量
	 * @param 指定的页码page,每页记录的数量count
	 * @return 对应的video集合
	 * @throws SQLException
	 */
	public List<Video> selectVideosByPage(int page, int count)
			throws SQLException {
		return DAOFactory.getVideoDAO().selectVideosByPage(page, count);
	}
	
	/**
	 * 查询指定教师旗下有多少视频
	 * @param tuid
	 * @return 对应的视频数量
	 * @throws SQLException
	 */
	public int selectCountOfVideoByTuid(String tuid) throws SQLException {
		return DAOFactory.getVideoDAO().selectCountOfVideoByTuid(tuid);
	}

	/**
	 * 对指定教师旗下的视频进行分页查询
	 * @param tuid
	 * @param page
	 * @param count
	 * @return
	 * @throws SQLException
	 */
	public List<Video> selectVideoByTuidPage(String tuid, int page, int count) 
			throws SQLException {
		return DAOFactory.getVideoDAO().selectVideoByTuidPage(tuid, page, count);
	}

	/**
	 * 删除指定课程下的相关视频
	 * @param cuid所有删除课程的id
	 * @return 删除成功返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean deleteVideoByCuid(String cuid) throws SQLException {
		return DAOFactory.getVideoDAO().deleteVideoByCuid(cuid);
	}
	
	/**
	 * 根据视频名的关键字分页查询指定教师的视频
	 * @param key视频名的关键字
	 * @param tuid教师Id
	 * @param page指定显示页码
	 * @param count每页做多能显示的记录条数
	 * @return 对应的课程集合
	 * @throws SQLException
	 */
	public List<Video> selectVideoByTuidKeyPage(String key, String tuid, int page, int count)
			throws SQLException {
		return DAOFactory.getVideoDAO().selectVideoByTuidKeyPage(key, tuid, page, count);
	}
	
	/**
	 * 根据视频名的关键字分页查询指定教师的视频数量
	 * @param key视频名的关键字
	 * @param tuid教师Id
	 * @return 对应视频的数量
	 * @throws SQLException
	 */
	public int selectCountByTuidKey(String key, String tuid)
			throws SQLException {
		return DAOFactory.getVideoDAO().selectCountByTuidKey(key, tuid);
	}
}
