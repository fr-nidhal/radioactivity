package com.xeo.kata.radioactivity;

import java.util.List;

public class RadioactivityMap {

	private RadioactivityRespository repository;
	private RadioactivityPrinter printer;

	public RadioactivityMap(RadioactivityRespository repository, RadioactivityPrinter printer) {
		this.repository = repository;
		this.printer = printer;
	}

	public void plot(String country) {
		List<CityInfo> cityInfos = repository.find(country);
		printer.print(cityInfos);
	}

}
