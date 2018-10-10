package MiddleLayer;

import java.util.ArrayList;
import java.util.HashMap;

import DAO.daoCommentary;
import Entities.Action;
import Entities.Commentary;

public class CommentaryCollector {
	private ArrayList<HashMap<String,Object>> allCommentary;
	
	public CommentaryCollector(){
		daoCommentary dao=new daoCommentary();
		this.allCommentary=dao.getAllCommentary();
	}
	
	public ArrayList<HashMap<String, Object>> getAllCommentary() {
		return allCommentary;
	}
	
	public void setAllCommentary(ArrayList<HashMap<String, Object>> allCommentary) {
		this.allCommentary = allCommentary;
	}
	
	public ArrayList<Commentary> searchByAction(Action queryAction ){
		ArrayList<Commentary> searchRes=new ArrayList<Commentary>();
		for(int i=0;i<this.allCommentary.size();i++){
			HashMap<String,Object> tmp=this.allCommentary.get(i);
			System.out.println("原始:"+tmp.get("ActionTS").toString());
			System.out.println("现在:"+queryAction.getTimestamp());
			if((tmp.get("ActionUserID").toString().equals(queryAction.getUserID()))&&(tmp.get("ActionTS").toString().equals(queryAction.getTimestamp()))){
				System.out.println("进入");
				Commentary insertComment=new Commentary();
				insertComment.setUserID(tmp.get("userID").toString());
				insertComment.setTimestamp(tmp.get("TS").toString());
				insertComment.setContent(tmp.get("content").toString());
				insertComment.setActionUserID(tmp.get("ActionUserID").toString());
				insertComment.setActionTS(tmp.get("ActionTS").toString());
				searchRes.add(insertComment);
			}
		}
		System.out.println("大小="+searchRes.size());
		return searchRes;
	}
}
