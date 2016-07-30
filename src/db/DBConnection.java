package db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection {
	public static final DBConnection dbConnection;
	static {
		dbConnection = new DBConnection();
	}
	public Connection getConn() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/tvtalk");
		
		return ds.getConnection();
	}
	public static DBConnection getInstance() {
		return dbConnection;
	}
	private DBConnection() {}
	/**
	 * 단위테스트
	 */
	public static void main(String [] args) {
		DBConnection.getInstance();
	}
}