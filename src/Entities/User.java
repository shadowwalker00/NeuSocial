package Entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import DAO.daoUser;
public class User {
	private String userID;
	private String userName;
	private String password;
	private String email;
	private String college;
	private String major;
	private String classNum;
	private String sex;
	private String birth;
	private String iconURL;
	public User(){
		
	}
	public User(String userID,String userName,String password,String email,String college,String major,String classNum,String sex,String birth,String iconURL){
		this.userID=userID;
		this.userName=userName;
		this.password=password;
		this.email=email;
		this.college=college;
		this.major=major;
		this.classNum=classNum;
		this.sex=sex;
		this.birth=birth;
		this.iconURL=iconURL;
	}
	public Map<String,Object> getUserInfo(){
		Map<String,Object> returnRes=new HashMap<String,Object>();
		returnRes.put("userID", this.userID);
		returnRes.put("userName", this.userName);
		returnRes.put("password", this.password);
		returnRes.put("email", this.email);
		returnRes.put("college",this.college);
		returnRes.put("major",this.major);
		returnRes.put("classNum",this.classNum);
		return returnRes;
	}
	public void set(String userID,String userName,String password,String email,String college,String major,String classNum){
		this.userID=userID;
		this.userName=userName;
		this.password=password;
		this.email=email;
		this.college=college;
		this.major=major;
		this.classNum=classNum;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getIconURL() {
		return iconURL;
	}
	public void setIconURL(String iconURL) {
		this.iconURL = iconURL;
	}
	public void insertUser(){
		daoUser userAcccess=new daoUser();
		userAcccess.insertUser(this);		
	}
	public boolean checkUser(String userName,String password){
		daoUser  userAccess=new daoUser();
		if(userAccess.checkUser(userName, password)){
			return true;
		}
		else{
			return false;
		}
	}
	public ArrayList<String> getMyFriends(){
		daoUser  userAccess=new daoUser();
		ArrayList<String> myFriends=userAccess.getFriend(this.userID);
		return myFriends;
	}
}
