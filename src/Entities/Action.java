package Entities;
public class Action {
	private String userID;
	private String timestamp;
	private String content;
	public Action(){
		
	}
	public Action(String userID,String timestamp,String content){
		this.userID=userID;
		this.timestamp=timestamp;
		this.content=content;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
