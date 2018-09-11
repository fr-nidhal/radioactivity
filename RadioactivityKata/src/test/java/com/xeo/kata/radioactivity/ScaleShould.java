package com.xeo.kata.radioactivity;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import feature.AcceptanceHelper;

public class ScaleShould {
	
	private Scale scale;

	@Before
	public void initialize() throws Exception {
		scale = new Scale();
	}
	
	@Test
	public void store_a_level() {
		Level level = new Level(0, 1, Color.RED);
		
		scale.addLevel(level);
		
		assertTrue(scale.allLevels().size() == 1);
		
		
	}

	@Test
	public void should_give_the_color_for_a_given_radioactivity() {
		for(Level level : AcceptanceHelper.createLevels()) {
			scale.addLevel(level);
		}
		
		assertEquals(Color.WHITE, scale.colorOf(0));
		assertEquals(Color.GRAY, scale.colorOf(5));
		assertEquals(Color.GREEN, scale.colorOf(10));
		assertEquals(Color.RED, scale.colorOf(200));
		
		
		
		
	}

}
