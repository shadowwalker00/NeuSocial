package Entities;

public class Hobby {
	private String userID;
	private int sport;
	private int music;
	private int movie;
	private int book;
	public Hobby(){
		
	}
	public Hobby(String userID,int sport,int music,int movie,int book){
		this.userID=userID;
		this.sport=sport;
		this.music=music;
		this.book=book;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public int getSport() {
		return sport;
	}
	public void setSport(int sport) {
		this.sport = sport;
	}
	public int getMusic() {
		return music;
	}
	public void setMusic(int music) {
		this.music = music;
	}
	public int getMovie() {
		return movie;
	}
	public void setMovie(int movie) {
		this.movie = movie;
	}
	public int getBook() {
		return book;
	}
	public void setBook(int book) {
		this.book = book;
	}
	
}
