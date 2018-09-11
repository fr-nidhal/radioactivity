package feature;

import java.util.ArrayList;
import java.util.List;

import com.xeo.kata.radioactivity.CityInfo;
import com.xeo.kata.radioactivity.Color;
import com.xeo.kata.radioactivity.Level;

public class AcceptanceHelper {
	
	
	public static List<CityInfo> createCitiesInfo() {
		List<CityInfo> cityInfos = new ArrayList<>();
		cityInfos.add(new CityInfo("PARIS", 0));
		cityInfos.add(new CityInfo("LYON", 5));
		cityInfos.add(new CityInfo("MARSEILLE", 0));
		cityInfos.add(new CityInfo("LILLE", 5));
		cityInfos.add(new CityInfo("BORDEAUX", 200));

		return cityInfos;
	}
	
	public static List<Level> createLevels() {
		List<Level> levels = new ArrayList<Level>();
		levels.add(new Level(0, 0, Color.WHITE));
		levels.add(new Level(0, 10, Color.GRAY));
		levels.add(new Level(10, 100, Color.GREEN));
		levels.add(new Level(100, Integer.MAX_VALUE, Color.RED));

		return levels;
	}

}
