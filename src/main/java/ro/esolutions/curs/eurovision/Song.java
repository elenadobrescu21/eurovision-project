package ro.esolutions.curs.eurovision;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Song {
	@JsonProperty("name")
	private String songName;
	@JsonProperty("artist")
	private String artistName;
	@JsonProperty("duration")
	private int duration;
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	
	

}
