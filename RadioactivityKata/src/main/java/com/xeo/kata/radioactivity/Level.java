package com.xeo.kata.radioactivity;

public class Level {
	
	private int from;
	private int to;
	
	private Color color;

	public Level(int from, int to, Color color) {
		this.from = from;
		this.to = to;
		this.color = color;
	}

	public int getFrom() {
		return from;
	}

	public int getTo() {
		return to;
	}

	public Color getColor() {
		return color;
	}
	
	
	

	
}
