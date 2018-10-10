package MiddleLayer;

import java.util.ArrayList;
import java.util.HashMap;

import DAO.daoActions;
import Entities.*;

public class ActionsCollector {
	private ArrayList<HashMap<String,Object>> actionsInfo;
	public ActionsCollector(){
		daoActions myDao=new daoActions();
		this.actionsInfo=myDao.getAllActions();
	}
	public ArrayList<HashMap<String, Object>> getUsersInfo() {
		return actionsInfo;
	}
	public void setUsersInfo(ArrayList<HashMap<String, Object>> usersInfo) {
		this.actionsInfo = usersInfo;
	}
	public ArrayList<String> getActions(String userID){
		ArrayList<String> oneUserActions=new ArrayList<String>();
		for(int i=0;i<this.actionsInfo.size();i++){
			HashMap<String,Object> temp=this.actionsInfo.get(i);
			if(temp.get("userID").equals(userID)){
				oneUserActions.add(temp.get("content").toString());
			}
		}
		return oneUserActions;
	}
	public ArrayList<String> searchByUserID(String userID){
		ArrayList<String> actions=new ArrayList<String>(); 
		for(int i=0;i<this.actionsInfo.size();i++){
			HashMap<String,Object> temp=this.actionsInfo.get(i);
			if(temp.get("userID").equals(userID)){
				actions.add(temp.get("content").toString());
			}
		}
		return actions;
	}
	public ArrayList<Action> pickLatestActions(){
		daoActions myDao=new daoActions();
		ArrayList<HashMap<String,Object>> descOrderActions=myDao.getOrderedActions();
		ArrayList<Action> res=new ArrayList<Action>();
		for(int i=0;i<descOrderActions.size();i++){
			HashMap<String,Object> tmp=descOrderActions.get(i);
			Action temp=new Action(tmp.get("userID").toString(),tmp.get("TS").toString(),tmp.get("content").toString());
			res.add(temp);
		}
		return res;
	}
}
