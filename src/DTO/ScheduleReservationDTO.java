package DTO;

import java.io.Serializable;

public class ScheduleReservationDTO implements Serializable{
	private int sr;
	private String thumbnail;
	private String title;
	private String broadcastingTime;
	private String broadcastDay;
	private String genre;
	private float rating;
	public ScheduleReservationDTO() {
		super();
	}
	public ScheduleReservationDTO(int sr, String thumbnail, String title, String broadcastingTime, String broadcastDay,
			String genre, float rating) {
		super();
		this.sr = sr;
		this.thumbnail = thumbnail;
		this.title = title;
		this.broadcastingTime = broadcastingTime;
		this.broadcastDay = broadcastDay;
		this.genre = genre;
		this.rating = rating;
	}
	public int getSr() {
		return sr;
	}
	public void setSr(int sr) {
		this.sr = sr;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBroadcastingTime() {
		return broadcastingTime;
	}
	public void setBroadcastingTime(String broadcastingTime) {
		this.broadcastingTime = broadcastingTime;
	}
	public String getBroadcastDay() {
		return broadcastDay;
	}
	public void setBroadcastDay(String broadcastDay) {
		this.broadcastDay = broadcastDay;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "ScheduleReservation [sr=" + sr + ", thumbnail=" + thumbnail + ", title=" + title + ", broadcastingTime="
				+ broadcastingTime + ", broadcastDay=" + broadcastDay + ", genre=" + genre + ", rating=" + rating + "]";
	}
	
}