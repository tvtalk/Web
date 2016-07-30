package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.ScheduleReservationDTO;
import db.DBConnection;

public class ScheduleReservationDAO {
	private static ScheduleReservationDAO instance;
	static {
		instance = new ScheduleReservationDAO();
	}
	private ScheduleReservationDAO (){}
	public static ScheduleReservationDAO getInstance(){
		return instance;
	}
	/**
	 * 葛电 规价 data get.
	 * @return
	 */
	public List<ScheduleReservationDTO> getAllData(){
		List<ScheduleReservationDTO> list = new ArrayList<ScheduleReservationDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null ;
		ResultSet rs = null;
		try {
			conn = DBConnection.getInstance().getConn();
			String sql = "select * from schedule_reservation;";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(new ScheduleReservationDTO(
						(int)rs.getInt("sr") ,
						(String)rs.getString("thumbnail"),
						(String)rs.getString("title"),
						(String)rs.getString("broadcasting_time"),
						(String)rs.getString("broadcast_day"),
						(String)rs.getString("genre"),
						(float)rs.getFloat("rating")
						));
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			try {
				pstmt.close();
				conn.close();
				rs.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;
	}
	/**
	 * Database俊 data insert.
	 * @param thumbnail 芥匙老 傅农
	 * @param title 力格
	 * @param broadcastingTime 规价矫埃 00~24
	 * @param broadcastDay 规价老
	 * @param genre 厘福
	 * @param rating 矫没伏
	 * @return
	 */
	public int insertScheduleReservation(String thumbnail , String title, String broadcastingTime, String broadcastDay , String genre , float rating){
		int result=0;
		Connection conn = null;;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnection.getInstance().getConn();
			conn.setAutoCommit(false);
			String sql = "insert into schedule_reservation(thumbnail,title,broadcasting_time,broadcast_day,genre,rating) values(?,?,?,?,?,?);";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, thumbnail);
			pstmt.setString(2, title);
			pstmt.setString(3, broadcastingTime);
			pstmt.setString(4, broadcastDay);
			pstmt.setString(5, genre);
			pstmt.setFloat(6, rating);
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
	/**
	 * 力格栏肺 规价茫扁.
	 * @param title 力格全焊
	 * @return
	 */
	public List<ScheduleReservationDTO> searchBroadcastByTitle(String title){
		List<ScheduleReservationDTO> list = new ArrayList<ScheduleReservationDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getInstance().getConn();
			String sql = "select * from schedule_reservation where title=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, '%'+title+'%');
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				list.add(new ScheduleReservationDTO(
						(int)rs.getInt("sr") ,
						(String)rs.getString("thumbnail"),
						(String)rs.getString("title"),
						(String)rs.getString("broadcasting_time"),
						(String)rs.getString("broadcast_day"),
						(String)rs.getString("genre"),
						(float)rs.getFloat("rating")
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
	public List<ScheduleReservationDTO> searchBroadcastByGener(String Gener){
		List<ScheduleReservationDTO> list = new ArrayList<ScheduleReservationDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getInstance().getConn();
			String sql = "select * from schedule_reservation where gener like ?;";
			pstmt = conn.prepareStatement('%'+sql+'%');
			pstmt.setString(1, Gener);
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				list.add(new ScheduleReservationDTO(
						(int)rs.getInt("sr") ,
						(String)rs.getString("thumbnail"),
						(String)rs.getString("title"),
						(String)rs.getString("broadcasting_time"),
						(String)rs.getString("broadcast_day"),
						(String)rs.getString("genre"),
						(float)rs.getFloat("rating")
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
	public List<ScheduleReservationDTO> searchBroadcastDayBy(String broadcastDay){
		List<ScheduleReservationDTO> list = new ArrayList<ScheduleReservationDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getInstance().getConn();
			String sql = "select * from schedule_reservation where broadcast_day like ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, '%'+broadcastDay+'%');
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				list.add(new ScheduleReservationDTO(
						(int)rs.getInt("sr") ,
						(String)rs.getString("thumbnail"),
						(String)rs.getString("title"),
						(String)rs.getString("broadcasting_time"),
						(String)rs.getString("broadcast_day"),
						(String)rs.getString("genre"),
						(float)rs.getFloat("rating")
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
	public List<ScheduleReservationDTO> searchAllKeyword(String keyword){
		List<ScheduleReservationDTO> list = new ArrayList<ScheduleReservationDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getInstance().getConn();
			String sql = "select * from schedule_reservation where title like ? or broadcating_time like ? or broadcast_day like ? or genre like ? or rating like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, '%'+keyword+'%');
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				list.add(new ScheduleReservationDTO(
						(int)rs.getInt("sr") ,
						(String)rs.getString("thumbnail"),
						(String)rs.getString("title"),
						(String)rs.getString("broadcasting_time"),
						(String)rs.getString("broadcast_day"),
						(String)rs.getString("genre"),
						(float)rs.getFloat("rating")
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
}