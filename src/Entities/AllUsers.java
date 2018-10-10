package Entities;
import java.util.ArrayList;
import java.util.HashMap;
import Entities.User;
import DAO.daoUser;

public class AllUsers {
	private ArrayList<HashMap<String,Object>> usersInfo;
	public AllUsers(){
		daoUser myDao=new daoUser();
		this.usersInfo=myDao.getAllUser();
	}
	public ArrayList<User> getAllUsers(){
		ArrayList<User> res=new ArrayList<User>();
		for(int i=0;i<usersInfo.size();i++){
			HashMap<String,Object> temp=usersInfo.get(i);
			String userID=(String) temp.get("userID");
			String username=(String) temp.get("username");
			String email=(String) temp.get("email");
			String college=(String) temp.get("college");
			String major=(String) temp.get("major");
			String classNum=(String) temp.get("class");
			String sex =(String)temp.get("sex");
			String birth=(String) temp.get("birth");
			String iconURL=(String) temp.get("iconURL");
			User newUser=new User(userID,username,"",email,college,major,classNum,sex,birth,iconURL);
			res.add(newUser);	
		}
		return res;
	}
	public User searchUserID(String userID){
		User res=new User();
		for(int i=0;i<usersInfo.size();i++){
			HashMap<String,Object> temp=usersInfo.get(i);
			if(temp.get("userID").equals(userID)){
				res.setUserID(temp.get("userID").toString());
				res.setUserName(temp.get("username").toString());
				res.setPassword(temp.get("psd").toString());
				res.setCollege(temp.get("college").toString());
				res.setMajor(temp.get("major").toString());
				res.setClassNum(temp.get("class").toString());
				res.setSex(temp.get("sex").toString());
				res.setBirth(temp.get("birth").toString());
				res.setIconURL(temp.get("iconURL").toString());
				
				return res;
			}
		}
		return null;
	}
}
