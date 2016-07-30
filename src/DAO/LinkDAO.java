package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;

public class LinkDAO {
	public static final LinkDAO instance;
	static {
		instance = new LinkDAO();
	}
	public static LinkDAO getInstance(){return instance;}
	private LinkDAO(){}
	
	public List<String> getAllLink(){
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getInstance().getConn();
			String sql = "select * from link";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				list.add( rs.getString("link") );
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
		return list;
	}
}
