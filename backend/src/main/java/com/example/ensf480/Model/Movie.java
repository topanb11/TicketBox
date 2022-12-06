package com.example.ensf480.Model;

import java.util.UUID;


// Model for Movie entity object
public class Movie {
	private final UUID id; 			
	private final String name;	
	private final String cover;	

	/**
	 * 
	 * @param id - Unique identifier for movie
	 * @param name - Name of movie
	 * @param cover - Image URL of movie
	 */
	public Movie(UUID id, String name, String cover) {
		this.id = id;
		this.name = name;
		this.cover = cover;
	}

	/**
	 * Getter for id
	 * @return id: UUID
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * Getter for name of movie
	 * @return name: String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for image URL of movie
	 * @return cover: String
	 */
	public String getCover() {
		return cover;
	}
	
}
