package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import Entities.Hobby;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.ResultSetMetaData;

public class daoHobby {
	//驱动器的名称
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	//数据库的地址
	static final String DB_URL = "jdbc:mysql://localhost:3306/neuworld";
	//数据库连接的用户名以及密码
	static final String USER = "root";
	static final String PASS = "shadow0419";
	public ArrayList<HashMap<String, Object>> getAllHobby(){
		//获取所有填写兴趣表的用户的兴趣
		Connection conn = null;
		Statement stmt = null;
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		try{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String sql;
			stmt = conn.createStatement();
			sql="select * from hobby";
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
			ArrayList<String> columnName=new ArrayList<String>();
			for(int i=0;i<md.getColumnCount();i++){
				columnName.add(md.getColumnName(i+1));
			}
			while(rs.next()){
				HashMap<String,Object> tempMap=new HashMap<String, Object>();
				for(int i=0;i<md.getColumnCount();i++){
					tempMap.put(columnName.get(i), rs.getObject(i+1));
				}
				list.add(tempMap);
			}
			return list;
		}
		catch(SQLException se){
			se.printStackTrace();
			return null;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public void insertHobby(Hobby newHobby){
		Connection conn = null;
		Statement stmt = null;
		String userID="\'"+newHobby.getUserID().toString()+"\'";
		String sport="\'"+Integer.toString(newHobby.getSport())+"\'";
		String music="\'"+Integer.toString(newHobby.getMusic())+"\'";
		String movie="\'"+Integer.toString(newHobby.getMovie())+"\'";
		String book="\'"+Integer.toString(newHobby.getBook())+"\'";
		try{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String sql;
			stmt = conn.createStatement();
			sql="insert into hobby values("+userID+","+sport+","+music+","+movie+","+book+")";
			stmt.executeUpdate(sql);
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
