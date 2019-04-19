package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DbUtil {
	Connection connection=null;
	Statement statement=null;
	PreparedStatement preparedStatement=null;
	public static final String PACK="oracle.jdbc.driver.OracleDriver";
	public static final String URL="jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	public static final String USERNAME="aa12";
	public static final String PASSWORD="123";
	public DbUtil(){
		try {
			Class.forName(PACK).newInstance();
			connection=DriverManager.getConnection(URL,USERNAME,PASSWORD);
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ResultSet Query(String sql) throws SQLException {
		statement=connection.createStatement();
		return statement.executeQuery(sql);
		
	}
	public ResultSet Query(String sql,Object...arr) throws SQLException {
		preparedStatement=connection.prepareStatement(sql);
		for (int i = 0; i < arr.length; i++) {
			preparedStatement.setObject(i+1, arr[i]);
		}
		return preparedStatement.executeQuery();
		
	}
	public int Update(String sql) throws SQLException {
		statement=connection.createStatement();
		return statement.executeUpdate(sql);
		
	}
	public int Update(String sql,Object...arr) throws SQLException {
		statement=connection.createStatement();
		for (int i = 0; i < arr.length; i++) {
			preparedStatement.setObject(i+1, arr[i]);
		}
		return preparedStatement.executeUpdate();
	}
	public void close() {
		try {
			if (preparedStatement!=null&&!preparedStatement.isClosed()) {
				preparedStatement.close();
			}
			if (statement!=null&&!statement.isClosed()) {
				statement.close();
			}
			if (connection!=null&&!connection.isClosed()) {
				connection.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
