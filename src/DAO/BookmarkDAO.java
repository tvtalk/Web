package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.BookmarkDTO;
import db.DBConnection;

public class BookmarkDAO {
	private static BookmarkDAO instance;
	static {
		instance = new BookmarkDAO();
	}
	private BookmarkDAO(){}
	public static BookmarkDAO getInstance(){return instance;}
	
	public List<BookmarkDTO> getBookmark(String nickName) {
		List<BookmarkDTO> list = new ArrayList<BookmarkDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getInstance().getConn();
			String sql = "select * from bookmark where nickname=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickName);
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				/* nickname이 있는경우 / 없는경우 */
				list.add(new BookmarkDTO(
						rs.getInt("bk"),
						rs.getInt("sr"),
						rs.getString("nickname"),
						rs.getString("title")
						)); 
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;
	}
	public int insertBookmark(String nickName,String title , int sr) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0 ;
		try {
			conn = DBConnection.getInstance().getConn();
			conn.setAutoCommit(false);
			String sql = "insert into bookmark(sr,nickname,title) values(?,?,?);";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sr);
			pstmt.setString(2, nickName);
			pstmt.setString(3, title);
			result = pstmt.executeUpdate();
			conn.commit();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	public BookmarkDTO isBookmark(String nickName, String title, int sr) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BookmarkDTO bookmarkDTO = null;
		try {
			conn = DBConnection.getInstance().getConn();
			String sql = "select * from bookmark where nickname=? and title = ? and sr = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickName);
			pstmt.setString(2, title);
			pstmt.setInt(3, sr);
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				/* nickname이 있는경우 / 없는경우 */
				bookmarkDTO = new BookmarkDTO(
						rs.getInt("bk"),
						rs.getInt("sr"),
						rs.getString("nickname"),
						rs.getString("title")
						); 
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		return bookmarkDTO;
	}
}
