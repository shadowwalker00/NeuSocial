package Entities;
public class Commentary {
	private String userID;
	private String timestamp;
	private String content;
	private String actionUserID;
	private String actionTS;
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
	public String getActionUserID() {
		return actionUserID;
	}
	public void setActionUserID(String actionUserID) {
		this.actionUserID = actionUserID;
	}
	public String getActionTS() {
		return actionTS;
	}
	public void setActionTS(String actionTS) {
		this.actionTS = actionTS;
	}
}
