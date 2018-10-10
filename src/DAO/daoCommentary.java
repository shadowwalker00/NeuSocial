package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.ResultSetMetaData;

import Entities.Action;
import Entities.Commentary;

public class daoCommentary {
	//驱动器的名称
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	//数据库的地址
	static final String DB_URL = "jdbc:mysql://localhost:3306/neuworld";
	//数据库连接的用户名以及密码
	static final String USER = "root";
	static final String PASS = "shadow0419";
	public ArrayList<HashMap<String,Object>> getAllCommentary(){
		//通过检索Action来查找相关的评论列表
		ArrayList<HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();
		Connection conn = null;
		Statement stmt = null;
		try{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String sql;
			stmt = conn.createStatement();
			sql="select * from commentary";
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
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void insertComment(Commentary newComment){
		Connection conn = null;
		Statement stmt = null;		
		try{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String sql;
			stmt = conn.createStatement();
			String userID="\'"+newComment.getUserID().toString()+"\'";
			String content="\'"+newComment.getContent().toString()+"\'";
			String actionUserID="\'"+newComment.getActionUserID().toString()+"\'";
			String actionTS="\'"+newComment.getActionTS().toString()+"\'";
			sql="insert into commentary(userID,content,ActionUserID,ActionTS) values("
				+userID+","+content+","+actionUserID+","+actionTS+")";
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
