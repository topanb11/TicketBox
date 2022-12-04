package com.example.ensf480.Model;

import java.util.UUID;

public class Movie {
	private final UUID id;
	private final String name;

	public Movie(UUID id, String name) {
		this.id = id;
		this.name = name;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
}
