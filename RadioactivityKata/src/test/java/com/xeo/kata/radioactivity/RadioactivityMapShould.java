package com.xeo.kata.radioactivity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.*;


@RunWith(MockitoJUnitRunner.class)
public class RadioactivityMapShould {

	@Mock
	RadioactivityRespository repository;
	@Mock
	RadioactivityPrinter printer;
	private RadioactivityMap radioactivityMap;

	@Before
	public void initialize() throws Exception {
		radioactivityMap =  new RadioactivityMap(repository,printer);
	}

	@Test
	public void find_all_cities_and_plot_the_map_for_all_the_cities() {
		String country = "France";
		List<CityInfo> cityInfos = Collections.emptyList();
		given(repository.find(country)).willReturn(cityInfos);

		radioactivityMap.plot(country);

		verify(repository).find(country);
		verify(printer).print(cityInfos);
	}

}
