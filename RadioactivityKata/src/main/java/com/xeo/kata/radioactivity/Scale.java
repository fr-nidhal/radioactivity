package com.xeo.kata.radioactivity;

import java.util.ArrayList;
import java.util.List;

public class Scale {

	private String unit;
	protected List<Level> levels = new ArrayList<>();

	public List<Level> allLevels() {
		return levels;
	}

	public String unit() {
		return unit;
	}

	public Color colorOf(int radioactivity) {
		Color color = Color.WHITE;
		for (Level level : allLevels()) {
			if (isInLevel(radioactivity, level)) {
				return level.getColor();
			}
		}
		return color;

	}

	private boolean isInLevel(int radioactivity, Level level) {
		return (level.getFrom() == level.getTo() && radioactivity == level.getFrom())
				|| level.getFrom() <= radioactivity
				&& level.getTo() > radioactivity;
	}

	public void addLevel(Level level) {
		levels.add(level);
	}

}
