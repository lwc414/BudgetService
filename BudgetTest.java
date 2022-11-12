import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static org.testng.AssertJUnit.assertEquals;


public class BudgetTest
{
	BudgetService bs = new BudgetService(new BudgetRepository());

	@Test
	public void Oneday() {
		Double Total =bs.query("20220301","20220301");
		assertEquals(10.0,Total);

	}

	@Test
	public void HoldMonth() {
		Double Total =bs.query("20220301","20220331");
		assertEquals(310.0,Total);
	}


	@Test
	public void CrossMonth() {
		Double Total =bs.query("20220421","20220531");
		assertEquals(410.0,Total);
	}

	@Test
	public void CrossMonth2() {
		Double Total =bs.query("20220315","20220815");
		assertEquals(930.0,Total);
	}


	@Test
	public void CrossMonth3() {
		Double Total =bs.query("20210315","20230101");
		assertEquals(2760.0,Total);
	}

	@Test
	public void Error_Time() {
		Double Total =bs.query("20220301","20220201");
		assertEquals(0.0,Total);
	}

	@Test
	public void Empty() {
		Double Total =bs.query("","");
		assertEquals(0.0,Total);
	}
}