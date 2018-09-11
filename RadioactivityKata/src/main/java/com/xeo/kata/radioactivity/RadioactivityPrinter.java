package com.xeo.kata.radioactivity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class RadioactivityPrinter {

	private static final String HEADER = "REGION | COLOR";
	private static final String AND = " and ";
	private static final String BETWEEN = "Between ";
	private static final String HIGHER_THAN = "Higher than ";
	private static final String SEPARATOR = " | ";
	private Console console;
	private Scale scale;

	public RadioactivityPrinter(Console console, Scale scale) {
		this.console = console;
		this.scale = scale;
	}

	public void print(List<CityInfo> cityInfos) {
		printHeader();
		printCitiesInfo(cityInfos);
		printScale();
	}


	private void printHeader() {
		console.printLine(HEADER);
	}

	private void printCitiesInfo(List<CityInfo> cityInfos) {
		cityInfos.stream().map(cityInfo -> buildCityInfoEntry(cityInfo))
				.collect(Collectors.toCollection(ArrayList::new)).forEach(console::printLine);

	}

	private String buildCityInfoEntry(CityInfo cityInfo) {
		StringBuilder builder = new StringBuilder();
		builder.append(cityInfo.getCityName());
		builder.append(SEPARATOR);
		builder.append(scaleColorOf(cityInfo.getRadioactivity()));
		return builder.toString();
	}

	private Object scaleColorOf(int radioactivity) {
		return scale.colorOf(radioactivity);
	}

	private void printScale() {
		printTheScaleHeader();
		printTheScaleLevels();

	}

	private void printTheScaleHeader() {
		String scaleHeader = buildTheScaleHeader();
		console.printLine("\n");
		console.printLine(scaleHeader);
	}

	private void printTheScaleLevels() {
		scale.allLevels().stream().map(level -> buildTheScaleLevel(level))
				.collect(Collectors.toCollection(LinkedList::new)).descendingIterator()
				.forEachRemaining(console::printLine);
	}

	private String buildTheScaleHeader() {
		return "Color | UNIT (" + scale.unit() + ")";
	}

	private String buildTheScaleLevel(Level level) {
		StringBuilder builder = new StringBuilder();
		builder.append(describeTheColor(level));
		builder.append(SEPARATOR);
		builder.append(describeTheScale(level));
		return builder.toString();
	}

	private Color describeTheColor(Level level) {
		return level.getColor();
	}

	private String describeTheScale(Level level) {
		StringBuilder builder = new StringBuilder();
		if (isTheLowestLevel(level)) {
			builder.append(level.getFrom());
		} else if (isTheHighestLevel(level)) {
			builder.append(HIGHER_THAN);
			builder.append(level.getFrom());
		} else {
			builder.append(BETWEEN);
			builder.append(level.getFrom());
			builder.append(AND);
			builder.append(level.getTo());
		}
		return builder.toString();
	}

	private boolean isTheHighestLevel(Level level) {
		return level.getTo() == Integer.MAX_VALUE;
	}

	private boolean isTheLowestLevel(Level level) {
		return level.getFrom() == 0 && level.getTo() == 0;
	}

}
