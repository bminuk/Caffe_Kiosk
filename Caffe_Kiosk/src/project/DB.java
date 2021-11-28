package project;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	public static Connection conn;
	public static Statement stmt;
	public static ResultSet rs;
	
	public static void init() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@paranUk.iptime.org:1521:XE", "min", "156");
			stmt = conn.createStatement();

			
			
			System.out.println("DB연결 성공");
			
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 에러");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("DB 연결 오류 또는 쿼리 오류");
			e.printStackTrace();
		}
	}
	public static ResultSet getRs(String sql) {
		try {
			Statement stmt = conn.createStatement();
			return stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
		
	}
	public static void executeQuery(String sql) {
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("쿼리오류");
			e.printStackTrace();
		}
	}
	public static void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("종료 오류");
			e.printStackTrace();
		}
	}
	
	

}
