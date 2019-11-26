package kpu.club.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import kpu.club.domain.MemberVO;
import kpu.club.domain.MemberVO;

public class MemberDAO {

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

	void disconnect() {
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

	public boolean join(MemberVO stu) {
		connect();
		String sql = "INSERT INTO jsp_member VALUES (?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stu.getId());
			pstmt.setString(2, stu.getPassword());
			pstmt.setString(3, stu.getUsername());
			pstmt.setString(4, stu.getNickname());
			pstmt.setString(5, stu.getEmail());

			pstmt.executeUpdate();

		} catch (Exception e) {
			return false;
		} finally {
			disconnect();
		}
		return true;
	}

	// 선택된 튜플만 읽는다.
	public MemberVO read(String id) {
		connect();
		System.out.println("read input id : " + id);
		MemberVO member = new MemberVO();
		String sql = "SELECT * FROM jsp_member WHERE id = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			res = pstmt.executeQuery();

			while (res.next()) {
				member.setId(res.getString("id"));
				member.setPassword(res.getString("passwd"));
				member.setUsername(res.getString("username"));
				member.setEmail(res.getString("email"));
				member.setNickname(res.getString("nickname"));
			}
			res.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("read 에러");
			e.printStackTrace();
			return null;
		} finally {
			disconnect();
		}
		return member;
	}
	
	//로그인
	public int login(String id, String password) {
		connect();
		MemberVO member = new MemberVO();
		String sql = "SELECT * FROM jsp_member WHERE id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			res = pstmt.executeQuery();

			if(res.next()) {
				if(res.getString("passwd").equals(password))
					return 1; // 로그인 성공
				else
					return -1; // 비밀번호 오류
			}
			
			return 0; // 아이디 오류
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return -2; // DB 오류
	}

	public boolean update(MemberVO mem) {
		connect();
		String sql = "UPDATE jsp_member SET passwd=?, username=?, email=?, nickname=? where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem.getPassword());
			pstmt.setString(2, mem.getUsername());
			pstmt.setString(3, mem.getEmail());
			pstmt.setString(4, mem.getNickname());
			pstmt.setString(5, mem.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("update 에러");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public ArrayList<MemberVO> getmemberList() {
		connect();
		ArrayList<MemberVO> memberList = new ArrayList<MemberVO>();
		String sql = "SELECT * FROM jsp_member";

		try {
			pstmt = conn.prepareStatement(sql);
			res = pstmt.executeQuery();

			while (res.next()) {
				MemberVO member = new MemberVO();
				member.setId(res.getString("id"));
				member.setPassword(res.getString("passwd"));
				member.setUsername(res.getString("username"));
				member.setEmail(res.getString("email"));
				member.setNickname(res.getString("nickname"));
				memberList.add(member);
			}
			res.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return memberList;
	}
}
