package kpu.club.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kpu.club.domain.BoardVO;

public class BoardDAO {
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
	
	public boolean write(BoardVO stu) {
		connect();
		String sql = "INSERT INTO jsp_member VALUES (?,?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);


			pstmt.executeUpdate();

		} catch (Exception e) {
			return false;
		} finally {
			disconnect();
		}
		return true;
	}
	
}
