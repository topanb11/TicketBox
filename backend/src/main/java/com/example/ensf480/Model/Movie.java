package com.example.ensf480.Model;

import java.util.UUID;

public class Movie {
	private final UUID id;
	private final String name;
	private final String cover;

	public Movie(UUID id, String name, String cover) {
		this.id = id;
		this.name = name;
		this.cover = cover;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCover() {
		return cover;
	}
	
}
