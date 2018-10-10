package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Entities.User;

import com.mysql.jdbc.ResultSetMetaData;

public class daoUser {
	//驱动器的名称
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	//数据库的地址
	static final String DB_URL = "jdbc:mysql://localhost:3306/neuworld";
	//数据库连接的用户名以及密码
	static final String USER = "root";
	static final String PASS = "shadow0419";
	public ArrayList<HashMap<String, Object>> getAllUser()
	{
		//返回一个数据库中所有注册的用户
		Connection conn = null;
		Statement stmt = null;
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		try{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String sql;
			stmt = conn.createStatement();
			sql="select * from users";
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();			  
			while(rs.next()){
				//print out all the users
				HashMap<String,Object> rowData = new HashMap<String,Object>();  
				int col=md.getColumnCount();
				for(int i=1;i<=col;i++){
					rowData.put(md.getColumnName(i), rs.getObject(i));  
				}
				list.add(rowData);
			}
			rs.close();
			stmt.close();
			conn.close();
		}
		catch(SQLException se) 
		{
			System.out.println("SQL Statement Error");
			se.printStackTrace();
		} 
		catch(Exception e) {
			System.out.println("classforName");
			e.printStackTrace();
		}
		return list;
	}
	public Map<String, Object> getUser(String username){
		Map<String,Object> obj=new HashMap<String,Object>();  
		Connection conn = null;
		Statement stmt = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String sql;
			stmt = conn.createStatement();
			sql="select * from users"+" where username=\""+username+"\"";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();			  
			while(rs.next()){
				//print out all the users  
				int col=md.getColumnCount();
				for(int i=1;i<=col;i++){
					obj.put(md.getColumnName(i), rs.getObject(i));  
				}
			}
		}
		catch(SQLException se) 
		{
			System.out.println("SQL Statement Error");
			se.printStackTrace();
		} 
		catch(Exception e) {
			System.out.println("classforName");
			e.printStackTrace();
		}
		return obj;
	}
	public void insertUser(User newUser){
		//插入一个新的用户
		Connection conn=null;
		Statement stmt=null;
		String userID="\'"+newUser.getUserID()+"\'";
		String userName="\'"+newUser.getUserName()+"\'";
		String password="\'"+newUser.getPassword()+"\'";
		String email="\'"+newUser.getEmail()+"\'";
		String college="\'"+newUser.getCollege()+"\'";
		String major="\'"+newUser.getMajor()+"\'";
		String classNum="\'"+newUser.getClassNum()+"\'";
		String sex="\'"+newUser.getSex()+"\'";
		String birth="\'"+newUser.getBirth()+"\'";
		String iconURL="\'"+newUser.getIconURL()+"\'";
		try{
			Class.forName(JDBC_DRIVER);
			conn=DriverManager.getConnection(DB_URL,USER,PASS);
			stmt=conn.createStatement();
			String sqlStatement="insert into users values";
			sqlStatement=sqlStatement+"("+userID+","+userName+","+password+","+email+","
					+college+","+major+","+classNum+","+birth+","+sex+","+iconURL+")";
			System.out.println("插入sql语句："+sqlStatement);
			stmt.executeUpdate(sqlStatement);
		}
		catch(SQLException sqlError){
			sqlError.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public boolean checkUser(String userID,String password){
		Connection conn=null;
		Statement stmt=null;
		try{
			Class.forName(JDBC_DRIVER);
			conn=DriverManager.getConnection(DB_URL,USER,PASS);
			stmt=conn.createStatement();
			String sqlStatement="select * from users where userID="+"\'"
			+userID+"\' "+"and psd=\'"+password+"\'";
			System.out.println(sqlStatement);
			ResultSet re=stmt.executeQuery(sqlStatement);
			re.last();
			System.out.println(re.getRow());
			if(re.getRow()!=0){
				return true;
			}
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	public List<Map<String,Object>> getAllUsers(){
		List<Map<String,Object>> res=new ArrayList<Map<String,Object>>();
		Connection conn=null;
		Statement stmt=null;
		try{
			Class.forName(JDBC_DRIVER);
			conn=DriverManager.getConnection(DB_URL,USER,PASS);
			stmt=conn.createStatement();
			String sqlStatement="select * from users";
			ResultSet querySet=stmt.executeQuery(sqlStatement);
			ResultSetMetaData md = (ResultSetMetaData) querySet.getMetaData();			  
			while(querySet.next()){
				Map<String,Object> tmp=new HashMap<String,Object>();
				//将所有用户的所有信息保存到HashMap res里面
				int col=md.getColumnCount();
				for(int i=1;i<=col;i++){
					tmp.put(md.getColumnName(i), querySet.getObject(i));  
				}
				res.add(tmp);
			}
			return res;	
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
	public ArrayList<String> getFriend(String userID){
		ArrayList<String> res=new ArrayList<String>();
		Connection conn=null;
		Statement stmt=null;
		try{
			Class.forName(JDBC_DRIVER);
			conn=DriverManager.getConnection(DB_URL,USER,PASS);
			stmt=conn.createStatement();
			String sqlStatement="select * from relation";
			sqlStatement+=" where userID="+"\'"+userID+"\'";
			ResultSet querySet=stmt.executeQuery(sqlStatement);
			while(querySet.next()){
				String friends=(String) querySet.getObject(2);
				res.add(friends);
			}
			return res;
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
}
