package com.zking.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.zking.web.dao.IVideoDAO;
import com.zking.web.entity.course.Course;
import com.zking.web.entity.course.Video;
import com.zking.web.exception.DAOException;
import com.zking.web.sql.VideoSql;
import com.zking.web.util.JdbcUtil;

/**
 * video表的dao
 * @author 胡博
 *
 */
public class VideoDAO extends BaseDAO<Video> implements IVideoDAO {
	
	/**
	 * 添加一条video记录
	 * @param 所要添加的video
	 * @return 如果添加成功返回true,否则返回false
	 * @throws SQLException
	 */
	public boolean saveVideo(Video video) throws SQLException {
		return add(VideoSql.INSERT, 
				new Object[]{video.getVuid(), video.getCuid(), 
							video.getVname(), video.getVpath()});
	}

	/**
	 * 更新一条video记录
	 * @param 所要更新的video
	 * @return
	 * @throws SQLException
	 */
	public boolean updateVideo(Video video) throws SQLException {
		return update(VideoSql.UPDATE_BY_VUID, 
				new Object[]{video.getVname(), video.getVpath(), video.getCuid(), video.getVuid()});
	}

	/**
	 * 根据cuid查询video的list集合
	 * @param cuid
	 * @return	返回cuid对应得video的list集合
	 * @throws SQLException
	 */
	public List<Video> selectVideos(String cuid) throws SQLException {
		return getAll(VideoSql.SELECT_ALL_BY_CUID, 
				new Object[]{cuid});
	}
	
	/**
	 * 根据vuid查询video
	 * @param vuid
	 * @return 对应得video
	 * @throws SQLException
	 */
	public Video selectVideoByVuid(String vuid) throws SQLException {
		return get(VideoSql.SELECT_BY_VUID, 
				new Object[]{vuid});
	}
	
	/**
	 * 根据vname查询video
	 * @param vname
	 * @return 相对应得video
	 * @throws SQLException
	 */
	public Video selectVideoByVname(String vname) throws SQLException {
		return get(VideoSql.SELECT_BY_VNAME, 
				new Object[]{vname});
	}
	
	/**
	 * 根据vuid删除一条video记录
	 * @param 所要删除video的vuid
	 * @return 删除成功返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean deleteVideo(String vuid) throws SQLException {
		return delete(VideoSql.DELETE_BY_VUID, 
				new Object[]{vuid});
	}

	/**
	 * 根据cuid获取视频名称的List集合
	 * @param cuid
	 * @return 对应得List集合
	 * @throws SQLException
	 */
	public List<String> selectVnames(String cuid) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(VideoSql.SELECT_ALL_NAME_BY_CUID);
		pstmt.setString(1, cuid);
		ResultSet rs = pstmt.executeQuery();
		
		List<String> vnames = new ArrayList<String>();
		while (rs.next()) {
			vnames.add(rs.getString("vname"));
		}
		
		JdbcUtil.close(conn, pstmt, rs);
		
		return vnames;
	}

	
	/**
	 * 获取又少条video记录
	 * @return video记录的数量
	 * @throws SQLException
	 */
	//疑问：哪种方法得到的记录的数量要好一些？
	//1、直接在sql语句中用聚集函数求出记录的数量
	//2、得到一个所有记录的list集合，然后返回其size
	public int selectVideoCount() throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(VideoSql.SELECT_COUNT_OF_VIDEO);
		
		ResultSet rs =  pstmt.executeQuery();
		int count = 0;
		
		if (rs.next()) {
			count = rs.getInt(1);
		}
		
		JdbcUtil.close(conn, pstmt, rs);
		
		return count;
	}

	/**
	 * 获取指定页page的video的list集合，并制定每页的数量
	 * @param 指定的页码page,每页记录的数量count
	 * @return 对应的video集合
	 * @throws SQLException
	 */
	public List<Video> selectVideosByPage(int page, int count) throws SQLException {
		int start = 0;
		if (page != 1) {
			start = (page - 1) * count;
		}

		return getAll(VideoSql.SELECT_BY_PAGE, 
				new Object[]{start, count});
	}



	@Override
	protected Video getEntity(ResultSet rs) throws DAOException {
		Video video = new Video();
		
		try {
			video.setCuid(rs.getString("cuid"));
			video.setVuid(rs.getString("vuid"));
			video.setVname(rs.getString("vname"));
			video.setVpath(rs.getString("vpath"));
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("实体对象转换失败");
		}
		
		return video;
	}

	/**
	 * 查询指定教师旗下有多少视频
	 * @param tuid
	 * @return 对应的视频数量
	 * @throws SQLException
	 */
	public int selectCountOfVideoByTuid(String tuid) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(VideoSql.SELECT_COUNT_OF_VIDEO_BY_TUID);
		pstmt.setString(1, tuid);
		ResultSet rs =  pstmt.executeQuery();
		int count = 0;
		
		if (rs.next()) {
			count = rs.getInt(1);
		}
		
		JdbcUtil.close(conn, pstmt, rs);
		
		return count;
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
		int start = 0;
		if (page != 1) {
			start = (page - 1) * count;
		}

		return getAll(VideoSql.SELECT_VIDEO_BY_TUID_PAGE, 
				new Object[]{tuid, start, count});
	}

	/**
	 * 删除指定课程下的相关视频
	 * @param cuid所有删除课程的id
	 * @return 删除成功返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean deleteVideoByCuid(String cuid) throws SQLException {
		return delete(VideoSql.DELETE_BY_CUID, 
				new Object[]{cuid});
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
		int start = 0;
		if (page != 1) {
			start = (page - 1) * count;
		}
		
		return getAll(VideoSql.SELECT_VIDEO_BY_TUID_KEY_PAGE, 
				new Object[]{"%"+key+"%", tuid, start, count});
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
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(
				VideoSql.SELECT_COUNT_BY_TUID_KEY);
		pstmt.setString(1, "%"+key+"%");
		pstmt.setString(2, tuid);
		ResultSet rs =  pstmt.executeQuery();
		int count = 0;
		
		if (rs.next()) {
			count = rs.getInt(1);
		}
		
		JdbcUtil.close(conn, pstmt, rs);
		
		return count;
	}
}
