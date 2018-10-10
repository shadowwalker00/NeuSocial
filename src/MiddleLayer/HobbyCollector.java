package MiddleLayer;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import DAO.daoHobby;
import Entities.AllUsers;
import Entities.User;

public class HobbyCollector {
	private ArrayList<HashMap<String,Object>> hobbyInfo;
	
	public HobbyCollector(){
		daoHobby myDao=new daoHobby();
		this.hobbyInfo=myDao.getAllHobby();
	}
	public ArrayList<HashMap<String, Object>> getAllHobbyInfo() {
		return this.hobbyInfo;
	}
	public ArrayList<Integer> searchByUserID(String userID){
		ArrayList<Integer> hobbies=new ArrayList<Integer>(); 
		for(int i=0;i<this.hobbyInfo.size();i++){
			HashMap<String,Object> temp=this.hobbyInfo.get(i);
			if(temp.get("userID").equals(userID)){
				hobbies.add(Integer.parseInt(temp.get("sport").toString()));
				hobbies.add(Integer.parseInt(temp.get("music").toString()));
				hobbies.add(Integer.parseInt(temp.get("movie").toString()));
				hobbies.add(Integer.parseInt(temp.get("book").toString()));
			}
		}
		return hobbies;
	}
	public ArrayList<User> recommend(String userID){
		
		//初始化推荐的好友列表
		ArrayList<User> recommendUsers=new ArrayList<User>();
		//获得所有好友信息
		AllUsers allUsers=new AllUsers(); 
		//推荐好友,按照兴趣的大小排序返回UserID
		Map<String,Double> distance=new HashMap<String, Double>();
		ArrayList<Integer> myHobby=searchByUserID(userID);
		for(int i=0;i<this.hobbyInfo.size();i++){
			HashMap<String,Object> tmp=hobbyInfo.get(i);
			if(!tmp.get("userID").equals(userID)){
				ArrayList<Integer> refHobby=new ArrayList<Integer>();
				String refID=tmp.get("userID").toString();
				refHobby.add(Integer.parseInt(tmp.get("sport").toString()));
				refHobby.add(Integer.parseInt(tmp.get("music").toString()));
				refHobby.add(Integer.parseInt(tmp.get("movie").toString()));
				refHobby.add(Integer.parseInt(tmp.get("book").toString()));
				double dis=calculateDis(myHobby,refHobby);
				distance.put(refID, dis);				
			}
		}
		ArrayList<Map.Entry<String, Double>> list = new ArrayList<Map.Entry<String, Double>>(distance.entrySet());  
		Collections.sort(list, new Comparator<Map.Entry<String,Double>>() {   
		    public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {      		       
		        return (o2.getValue()).compareTo(o1.getValue());
		    }
		});
		
		for (Map.Entry<String, Double> mapping : list) {  
            System.out.println(mapping.getKey() + ":" + mapping.getValue());
            User tmp=allUsers.searchUserID(mapping.getKey());
            recommendUsers.add(tmp);
        }
		System.out.println("done/.....");
		return recommendUsers;
	}
	public double calculateDis(ArrayList<Integer> a,ArrayList<Integer> b){
		double res=0;
		double tmp=0;
		for(int i=0;i<a.size();i++){
			tmp+=Math.pow(a.get(i)-b.get(i),2);
		}
		res=Math.sqrt(tmp);
		return res;		
	}
}
