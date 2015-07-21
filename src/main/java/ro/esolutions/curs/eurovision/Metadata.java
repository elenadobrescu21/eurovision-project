package ro.esolutions.curs.eurovision;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "artist", "release", "title", "genre", "bitrate",
		"sample_rate", "duration", "filename", "samples_decoded",
		"given_duration", "start_offset", "version", "codegen_time",
		"decode_time" })

public class Metadata {
	
	@JsonProperty("artist")
	private String artist;
	@JsonProperty("release")
	private String release;
	@JsonProperty("title")
	private String title;
	@JsonProperty("genre")
	private String genre;
	@JsonProperty("bitrate")
	private Integer bitrate;
	@JsonProperty("sample_rate")
	private Integer sampleRate;
	@JsonProperty("duration")
	private Integer duration;
	@JsonProperty("filename")
	private String filename;
	@JsonProperty("samples_decoded")
	private Integer samplesDecoded;
	@JsonProperty("given_duration")
	private Integer givenDuration;
	@JsonProperty("start_offset")
	private Integer startOffset;
	@JsonProperty("version")
	private Double version;
	@JsonProperty("codegen_time")
	private Double codegenTime;
	@JsonProperty("decode_time")
	private Double decodeTime;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getRelease() {
		return release;
	}
	public void setRelease(String release) {
		this.release = release;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public Integer getBitrate() {
		return bitrate;
	}
	public void setBitrate(Integer bitrate) {
		this.bitrate = bitrate;
	}
	public Integer getSampleRate() {
		return sampleRate;
	}
	public void setSampleRate(Integer sampleRate) {
		this.sampleRate = sampleRate;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Integer getSamplesDecoded() {
		return samplesDecoded;
	}
	public void setSamplesDecoded(Integer samplesDecoded) {
		this.samplesDecoded = samplesDecoded;
	}
	public Integer getGivenDuration() {
		return givenDuration;
	}
	public void setGivenDuration(Integer givenDuration) {
		this.givenDuration = givenDuration;
	}
	public Integer getStartOffset() {
		return startOffset;
	}
	public void setStartOffset(Integer startOffset) {
		this.startOffset = startOffset;
	}
	public Double getVersion() {
		return version;
	}
	public void setVersion(Double version) {
		this.version = version;
	}
	public Double getCodegenTime() {
		return codegenTime;
	}
	public void setCodegenTime(Double codegenTime) {
		this.codegenTime = codegenTime;
	}
	public Double getDecodeTime() {
		return decodeTime;
	}
	public void setDecodeTime(Double decodeTime) {
		this.decodeTime = decodeTime;
	}
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
	
	


}
