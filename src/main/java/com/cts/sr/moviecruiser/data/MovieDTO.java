package com.cts.sr.moviecruiser.data;

import com.cts.sr.moviecruiser.model.Movie;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MovieDTO {
	
	@JsonProperty("id")
	private Long id;

	@JsonProperty("title")
	private String name;
	
	@JsonProperty("poster_path")
	private String posterPath;
	
	@JsonProperty("release_date")
	private String releaseDate;
	
	@JsonProperty("overview")
	private String description;
	
	@JsonProperty("vote_average")
	private Double voteAverage;
	
	@JsonProperty("vote_count")
	private Long voteCount;
	
	@JsonProperty("comment")
	private String comment;
	
	public MovieDTO() {}
	
	public MovieDTO(Long id, String name, String posterPath, String releaseDate, Double voteAverage,
			Long voteCount) {
		this.id = id;
		this.name = name;
		this.posterPath = posterPath;
		this.releaseDate = releaseDate;
		this.voteAverage = voteAverage;
		this.voteCount = voteCount;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPosterPath() {
		return posterPath;
	}
	
	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}
	
	public String getReleaseDate() {
		return releaseDate;
	}
	
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	public Double getVoteAverage() {
		return voteAverage;
	}
	
	public void setVoteAverage(Double voteAverage) {
		this.voteAverage = voteAverage;
	}
	
	public Long getVoteCount() {
		return voteCount;
	}
	
	public void setVoteCount(Long voteCount) {
		this.voteCount = voteCount;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void copy(Movie model){
		
		if(model.getId() != null)
			this.setId(model.getId());
		if(model.getName() != null)
			this.setName(model.getName());
		if(model.getPosterPath() != null)
			this.setPosterPath(model.getPosterPath());
		if(model.getDescription() != null)
			this.setDescription(model.getDescription());
		if(model.getReleaseDate() != null)
			this.setReleaseDate(model.getReleaseDate());
		if(model.getVoteAverage() != null)
			this.setVoteAverage(model.getVoteAverage());
		if(model.getVoteCount() != null)
			this.setVoteCount(model.getVoteCount());
		if(model.getComment() != null)
			this.setComment(model.getComment());
		
	}
	
}