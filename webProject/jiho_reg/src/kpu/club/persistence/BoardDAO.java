package kpu.club.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public String getDate() {
		connect();
		String sql ="SELECT NOW()";
		String result = "";
		try {
			pstmt = conn.prepareStatement(sql);
			res = pstmt.executeQuery();
			
			if(res.next()) {
				result = res.getString(1);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		disconnect();
		return result;
	}
	
	public BoardVO view(int id) {
		connect();
		BoardVO bbs = new BoardVO();
		String sql = "SELECT * FROM bbs WHERE bbs_id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id); // AUTO INCREMENT
			res = pstmt.executeQuery();
			
			if(res.next()) {
				bbs.setBbsId(res.getInt("bbs_id"));
				bbs.setBbsTopic1(res.getString("bbs_topic1"));;
				bbs.setBbsTopic2(res.getString("bbs_topic2"));
				bbs.setBbsContent(res.getString("bbs_content"));
				bbs.setBbsDate(res.getString("bbs_date"));
				bbs.setBbsPostId(res.getString("bbs_post_id"));
				bbs.setBbsRecommend(res.getInt("bbs_recommend"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			disconnect();
		}
		return bbs;
	}
	
	public boolean write(BoardVO bbs) {
		connect();
		String sql = "INSERT INTO bbs VALUES (?,?,?,?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, 0); // AUTO INCREMENT
			pstmt.setString(2, bbs.getBbsTopic1());
			pstmt.setString(3, bbs.getBbsTopic2());
			pstmt.setString(4, bbs.getBbsContent());
			pstmt.setInt(5, 0); // TRIGGRE
			pstmt.setString(6, bbs.getBbsPostId());
			pstmt.setInt(7, 0); // recommend
			pstmt.setInt(8, 0); // report

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}
	
	public boolean delete(int id) {
		connect();
		String sql = "DELETE FROM jsp_member WHERE bbs_id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}finally {
			disconnect();
		}
		return true;
	}
	
	public BoardVO read(int id) {
		connect();
		String sql = "SELECT * FROM bbs WHERE bbs_id=?";
		BoardVO board = new BoardVO();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			res = pstmt.executeQuery();
			if(res.next()) {
				board.setBbsId(res.getInt("bbs_id"));
				board.setBbsTopic1(res.getString("bbs_topic1"));
				board.setBbsTopic2(res.getString("bbs_topic2"));
				board.setBbsContent(res.getString("bbs_content"));
				board.setBbsDate(res.getString("bbs_date"));
				board.setBbsPostId(res.getString("bbs_post_id"));
				board.setBbsRecommend(res.getInt("bbs_recommend"));
				res.close();
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {
			disconnect();
		}
		return board;
	}
	
	public boolean update(BoardVO board) {
		connect();
		String sql = "UPDATE bbs SET bbs_topic1=?, bbs_topic2=?, bbs_content=?, bbs_date=? WHERE bbs_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getBbsTopic1());
			pstmt.setString(2, board.getBbsTopic2());
			pstmt.setString(3, board.getBbsContent());
			pstmt.setString(4, board.getBbsDate());
			pstmt.setInt(5, board.getBbsId());
			pstmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}finally {
			disconnect();
		}
		return true;
	}
	
	public ArrayList<BoardVO> getBoardList() {
		connect();
		ArrayList<BoardVO> boardList = new ArrayList<BoardVO>();
		String sql = "SELECT * FROM bbs ORDER BY bbs_id DESC";
		
		try {
			pstmt = conn.prepareStatement(sql);
			res = pstmt.executeQuery();
			
			while(res.next()) {
				BoardVO board = new BoardVO();
				board.setBbsId(res.getInt("bbs_id"));
				board.setBbsTopic1(res.getString("bbs_topic1"));
				board.setBbsTopic2(res.getString("bbs_topic2"));
				board.setBbsContent(res.getString("bbs_content"));
				board.setBbsDate(res.getString("bbs_date"));
				board.setBbsPostId(res.getString("bbs_post_id"));
				board.setBbsRecommend(res.getInt("bbs_recommend"));
				board.setBbsReport(res.getInt("bbs_Report"));
				
				boardList.add(board);
			}
			res.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return boardList;
	}

	public ArrayList<BoardVO> getBoardListTop3() {
		connect();
		ArrayList<BoardVO> boardList = new ArrayList<BoardVO>();
		String sql = "SELECT * FROM bbs ORDER BY bbs_recommend DESC LIMIT 3";
		
		try {
			pstmt = conn.prepareStatement(sql);
			res = pstmt.executeQuery();
			
			while(res.next()) {
				BoardVO board = new BoardVO();
				board.setBbsId(res.getInt("bbs_id"));
				board.setBbsTopic1(res.getString("bbs_topic1"));
				board.setBbsTopic2(res.getString("bbs_topic2"));
				board.setBbsContent(res.getString("bbs_content"));
				board.setBbsDate(res.getString("bbs_date"));
				board.setBbsPostId(res.getString("bbs_post_id"));
				board.setBbsRecommend(res.getInt("bbs_recommend"));
				
				boardList.add(board);
			}
			res.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return boardList;
	}

	public void deleteRecommend(int id, String userID) {
		connect();
		String sql = "DELETE FROM likely WHERE like_id=? AND like_userId=?";
		
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
	
	public int checkRecommend(int id, String userID) {
		connect();
		String sql = "INSERT INTO likely VALUES (?,?)";
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, userID);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			result = 0; // 실패 시, 중복 추천 감소
		}
		finally {
			disconnect();
		}
		
		return result; // 성공 시, 추천 증가
	}
	
	// id board_id
	// userId memberId
	public void setRecommend(int id, String userID) {
		int result = checkRecommend(id, userID);		
		String sql = "";
		if(result == 1) 
			sql = "UPDATE bbs SET bbs_recommend = bbs_recommend+1 WHERE bbs_id = ?";
		else {
			sql = "UPDATE bbs SET bbs_recommend = bbs_recommend-1 WHERE bbs_id = ?";
			deleteRecommend(id, userID);
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
	}
	
	
	public String getNickname(BoardVO bbs) {
		connect();
		String result = "";
		String sql = "SELECT DISTINCT nickname FROM bbs, jsp_member "
				+ "WHERE bbs.bbs_post_id = ? AND jsp_member.id = ?";
	
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bbs.getBbsPostId());
			pstmt.setString(2, bbs.getBbsPostId());
			res = pstmt.executeQuery();
			
			if(res.next()) {
				result = res.getString("nickname");
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally {
			disconnect();
		}
		
		return result; // 성공 시, 추천 증가
	}
	
	
}
