package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.ScheduleReservationDTO;
import db.DBConnection;

public class UserDAO {
	private static UserDAO instance;
	static {
		instance = new UserDAO();
	}
	private UserDAO() {
	}
	public static UserDAO getInstance(){
		return instance;
	}
	public int insertUser(String nickName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0 ;
		try {
			conn = DBConnection.getInstance().getConn();
			conn.setAutoCommit(false);
			String sql = "insert into user(nickname) values(?);";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickName);
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
	public boolean searchNickName(String nickName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getInstance().getConn();
			String sql = "select * from user where nickname=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickName);
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				/* nickname이 있는경우  */
				String nickN = rs.getString("nickname");
				return nickN.equals(nickName); 
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
		/*무조건 없는 경우.*/
		return false;
	}
}
