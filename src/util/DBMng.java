package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBMng {
	private static Connection conn =null;
	
	private DBMng() {
		//싱클턴패턴
	
	}
	/*
	 * DBMS 커넥션을 반환하는 메서드
	 * @return java.sql.Connection
	 * @throws SQLException : DBMS에 문제가 생겼을 때
	 * */
	public static Connection getConnection() throws SQLException{
		
		if(conn == null) {
			try {
				Class.forName("org.mariadb.jdbc.Driver");
				
				conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/myapplication?user=root&password=456456");
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}//end try
			
		}//end if
		
		
		return conn;
		
	}
	
	public static void closeConnection() {
		if(conn != null) {
			try {
				conn.close();
				
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
