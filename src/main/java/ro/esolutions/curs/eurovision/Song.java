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
@JsonPropertyOrder({ "metadata", "code_count", "code", "tag" })

public class Song {
	
	@JsonProperty("metadata")
	private Metadata metadata;
	@JsonProperty("code_count")
	private Integer codeCount;
	@JsonProperty("code")
	private String code;
	@JsonProperty("tag")
	private Integer tag;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	public Metadata getMetadata() {
		return metadata;
	}
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
	public Integer getCodeCount() {
		return codeCount;
	}
	public void setCodeCount(Integer codeCount) {
		this.codeCount = codeCount;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getTag() {
		return tag;
	}
	public void setTag(Integer tag) {
		this.tag = tag;
	}
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

}
