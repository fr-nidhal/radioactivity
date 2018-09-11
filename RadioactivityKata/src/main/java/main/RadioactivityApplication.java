package main;

import java.util.ArrayList;
import java.util.List;

import com.xeo.kata.radioactivity.CityInfo;
import com.xeo.kata.radioactivity.Color;
import com.xeo.kata.radioactivity.Console;
import com.xeo.kata.radioactivity.Level;
import com.xeo.kata.radioactivity.RadioactivityMap;
import com.xeo.kata.radioactivity.RadioactivityPrinter;
import com.xeo.kata.radioactivity.RadioactivityRespository;
import com.xeo.kata.radioactivity.Scale;

public class RadioactivityApplication {

	public static void main(String[] args) {
		
		
		RadioactivityRespository repository = new OnMemeoryRepository();
		Console console = new Console();
		Scale scale = new SimpleScale();
		RadioactivityPrinter printer = new RadioactivityPrinter(console, scale);
		RadioactivityMap radioactivityMap = new RadioactivityMap(repository, printer);

		
		radioactivityMap.plot("France");
	}
	
	private static class SimpleScale extends Scale{
		
		@Override
		public List<Level> allLevels() {
			List<Level> levels = new ArrayList<Level>();
			levels.add(new Level(0, 0, Color.WHITE));
			levels.add(new Level(0, 10, Color.GRAY));
			levels.add(new Level(10, 100, Color.GREEN));
			levels.add(new Level(100, Integer.MAX_VALUE, Color.RED));
			return levels;
		}
		
		@Override
		public String unit() {
			return "Bq";
		}
		
	}
	
	private static class OnMemeoryRepository implements RadioactivityRespository{
		
		@Override
		public List<CityInfo> find(String country) {
			List<CityInfo> cityInfos = new ArrayList<>();
			cityInfos.add(new CityInfo("PARIS", 0));
			cityInfos.add(new CityInfo("LYON", 5));
			cityInfos.add(new CityInfo("MARSEILLE", 0));
			cityInfos.add(new CityInfo("LILLE", 5));
			cityInfos.add(new CityInfo("BORDEAUX", 200));

			return cityInfos;
		}
	}

}
