import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class BudgetTest
{
	public static void main(String[] straryParameters)
	{
//		System.out.println(ChronoUnit.DAYS.between(
//				LocalDate.parse("20221001", DateTimeFormatter.ofPattern("yyyyMMdd")),
//				LocalDate.parse("20220930", DateTimeFormatter.ofPattern("yyyyMMdd"))));
//
//		System.out.println(LocalDate.parse("20221005", DateTimeFormatter.ofPattern("yyyyMMdd")).format(DateTimeFormatter.ofPattern("yyyyMM")));
//		System.out.println(ld.withDayOfMonth(ld.getMonth().length(ld.isLeapYear())));
		
		BudgetService bs = new BudgetService(new BudgetRepository());
		System.out.println(bs.query("20220101", "20220102"));
	}
}