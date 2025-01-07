package learnbyteaching.guestbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import learnbyteaching.guestbook.vo.GuestbookVo;

public class GuestbookDaoImpl extends BaseDao implements GuestbookDao {

	//	Constructor
	public GuestbookDaoImpl(String dbUser, String dbPass) {
		super(dbUser, dbPass);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public List<GuestbookVo> getList() {
		List<GuestbookVo> list = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "SELECT no, name, password, content, reg_date FROM guestbook ORDER BY reg_date DESC";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String password = rs.getString(3);
				String content = rs.getString(4);
				Date regDate = rs.getDate(5);
				
				GuestbookVo info = new GuestbookVo(no, 
						name,
						password,
						content,
						regDate);
				list.add(info);
			}
		} catch (Exception e) {
			System.err.println("ERROR:" + e.getMessage());
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				System.err.println("ERROR:" + e.getMessage());
			}
		}
		return list;
	}
	
	@Override
	public boolean insert(GuestbookVo vo) {
		int insertedCount = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "INSERT INTO guestbook (name, content) VALUES (?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getContent());						
			insertedCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.err.println("ERROR:" + e.getMessage());
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				System.err.println("ERROR:" + e.getMessage());
			}
		}
		
		return 1 == insertedCount;
	}
	@Override
	public boolean delete(Long no) {
		int deletedCount = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "DELETE FROM guestbook WHERE no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			deletedCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				System.err.println("ERROR:" + e.getMessage());
			}
		}
		return 1 == deletedCount;
	}

}