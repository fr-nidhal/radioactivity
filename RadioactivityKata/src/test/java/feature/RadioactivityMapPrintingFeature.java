package feature;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.xeo.kata.radioactivity.CityInfo;
import com.xeo.kata.radioactivity.Console;
import com.xeo.kata.radioactivity.Level;
import com.xeo.kata.radioactivity.RadioactivityMap;
import com.xeo.kata.radioactivity.RadioactivityPrinter;
import com.xeo.kata.radioactivity.RadioactivityRespository;
import com.xeo.kata.radioactivity.Scale;

@RunWith(MockitoJUnitRunner.class)
public class RadioactivityMapPrintingFeature {

	@Mock
	Console console;

	private RadioactivityMap radioactivityMap;

	@Before
	public void initialize() {
		Scale scale = new ScaleTest();
		RadioactivityPrinter printer = new RadioactivityPrinter(console, scale);
		RadioactivityRespository repository = new RadioactivityTestRespository();
		radioactivityMap = new RadioactivityMap(repository, printer);
	}

	

	@Test
	public void a_console_should_plot_all_cities_according_to_radioactivity_measure_classified_by_color() {

		radioactivityMap.plot("France");

		InOrder inOrder = inOrder(console);
		inOrder.verify(console).printLine("REGION | COLOR");

		verify(console).printLine("PARIS | WHITE");
		verify(console).printLine("LYON | GRAY");
		verify(console).printLine("MARSEILLE | WHITE");
		verify(console).printLine("LILLE | GRAY");
		verify(console).printLine("BORDEAUX | RED");

		verify(console).printLine("Color | UNIT (Bq)");
		verify(console).printLine("RED | Higher than 100");
		verify(console).printLine("GREEN | Between 10 and 100");
		verify(console).printLine("GRAY | Between 0 and 10");
		verify(console).printLine("WHITE | 0");
	}

	private class RadioactivityTestRespository implements RadioactivityRespository {

		@Override
		public List<CityInfo> find(String country) {
			if ("France".equals(country)) {
				return AcceptanceHelper.createCitiesInfo();
			}
			return Collections.emptyList();
		}
	}
	
	private class ScaleTest extends Scale{
		
		@Override
		public List<Level> allLevels() {
			return AcceptanceHelper.createLevels();
		}
		
		@Override
		public String unit() {
			return "Bq";
		}
		
	}

}
