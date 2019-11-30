package kpu.club.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kpu.club.domain.BoardVO;

public class ReportDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet res = null;
	
	String url = "jdbc:mysql://localhost/project?useSSL=false&serverTimezone=UTC";
	String user = "RAYOR";
	String pw = "1";

	private void connect() {
		try {
			conn = DriverManager.getConnection(url, user, pw);
			System.out.println("DB 연결 성공");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("DB 연결 실패");
		}
	}

	private void disconnect() {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	

	public void deleteRecommend(int id, String userID) {
		connect();
		String sql = "DELETE FROM report WHERE report_board_id=? AND report_reporter=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, userID);
			pstmt.executeUpdate();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally {
			disconnect();
		}
		
	}
	
	public int checkReport(int id, String userID) {
		connect();
		String sql = "INSERT INTO report VALUES (?,?)";
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, userID);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			result = 0; // 실패
		}
		finally {
			disconnect();
		}
		
		return result; // 성공 시, 신고증가
	}
	
	// id board_id
	// userId memberId
	public int setReport(int id, String userID) {
		int result = checkReport(id, userID);		
		int resultFlag = 0;
		String sql = "";
		
		if(result == 1) {
			sql = "UPDATE bbs SET bbs_report = bbs_report+1 WHERE bbs_id = ?";
			resultFlag = 1;
		}
		else {
			resultFlag = 0;
		}
		
		connect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			disconnect();
		}
		
		return result;
	}
	
	
	
}
