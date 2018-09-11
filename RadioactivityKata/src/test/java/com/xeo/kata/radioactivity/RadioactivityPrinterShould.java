package com.xeo.kata.radioactivity;

import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.*;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import feature.AcceptanceHelper;

@RunWith(MockitoJUnitRunner.class)
public class RadioactivityPrinterShould {

	private static final List<CityInfo> NO_CITIES = Collections.emptyList();
	@Mock
	Console console;
	@Mock
	Scale scale;

	RadioactivityPrinter radioactivityPrinter;

	@Before
	public void initialize() throws Exception {
		radioactivityPrinter = new RadioactivityPrinter(console, scale);

		given(scale.allLevels()).willReturn(createLevels());
		given(scale.unit()).willReturn("Bq");
	}

	@Test
	public void always_print_the_header() {

		radioactivityPrinter.print(NO_CITIES);

		verify(console).printLine("REGION | COLOR");

	}

	@Test
	public void always_print_the_scale_in_inversed_order() {

		radioactivityPrinter.print(NO_CITIES);
		
		verify(console).printLine("\n");

		verify(console).printLine("Color | UNIT (Bq)");
		verify(console).printLine("RED | Higher than 100");
		verify(console).printLine("GREEN | Between 10 and 100");
		verify(console).printLine("GRAY | Between 0 and 10");
		verify(console).printLine("WHITE | 0");

	}

	private List<Level> createLevels() {
		return AcceptanceHelper.createLevels();
	}

	@Test
	public void print_the_cities_according_to_the_given_scale() {

		List<CityInfo> cityInfos = createCitiesInfo();
		given(scale.colorOf(0)).willReturn(Color.WHITE);
		given(scale.colorOf(5)).willReturn(Color.GRAY);
		given(scale.colorOf(200)).willReturn(Color.RED);

		radioactivityPrinter.print(cityInfos);

		verify(console).printLine("PARIS | WHITE");
		verify(console).printLine("LYON | GRAY");
		verify(console).printLine("MARSEILLE | WHITE");
		verify(console).printLine("LILLE | GRAY");
		verify(console).printLine("BORDEAUX | RED");

	}

	private List<CityInfo> createCitiesInfo() {

		return AcceptanceHelper.createCitiesInfo();
	}

}
