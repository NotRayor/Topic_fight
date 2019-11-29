package kpu.club.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
				bbs.setBbs_id(res.getInt("bbs_id"));
				bbs.setBbs_topic1(res.getString("bbs_topic1"));;
				bbs.setBbs_topic2(res.getString("bbs_topic2"));
				bbs.setBbs_content(res.getString("bbs_content"));
				bbs.setBbs_date(res.getString("bbs_date"));
				bbs.setBbs_name(res.getString("bbs_name"));
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
		String sql = "INSERT INTO bbs VALUES (?,?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, 0); // AUTO INCREMENT
			pstmt.setString(2, bbs.getBbs_topic1());
			pstmt.setString(3, bbs.getBbs_topic2());
			pstmt.setString(4, bbs.getBbs_content());
			pstmt.setInt(5, 0); // TRIGGRE
			pstmt.setString(6, bbs.getBbs_name());

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
		String sql = "DELETE FROM bbs WHERE bbs_id=?";
		
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
				board.setBbs_id(res.getInt("bbs_id"));
				board.setBbs_topic1(res.getString("bbs_topic1"));
				board.setBbs_topic2(res.getString("bbs_topic2"));
				board.setBbs_content(res.getString("bbs_content"));
				board.setBbs_date(res.getString("bbs_date"));
				board.setBbs_name(res.getString("bbs_name"));
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
			pstmt.setString(1, board.getBbs_topic1());
			pstmt.setString(2, board.getBbs_topic2());
			pstmt.setString(3, board.getBbs_content());
			pstmt.setString(4, board.getBbs_date());
			pstmt.setInt(5, board.getBbs_id());
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
				board.setBbs_id(res.getInt("bbs_id"));
				board.setBbs_topic1(res.getString("bbs_topic1"));
				board.setBbs_topic2(res.getString("bbs_topic2"));
				board.setBbs_content(res.getString("bbs_content"));
				board.setBbs_date(res.getString("bbs_date"));
				board.setBbs_name(res.getString("bbs_name"));
				
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

 
	
	
	
	
	
	
	
}
