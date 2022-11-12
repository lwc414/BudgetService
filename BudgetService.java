import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.HashMap;

public class BudgetService
{
	private final DateTimeFormatter dffull = DateTimeFormatter.ofPattern("yyyyMMdd");
	private final DateTimeFormatter dfmonth = DateTimeFormatter.ofPattern("yyyyMM");

	private IBudgetRepo implBudget;

	public BudgetService(IBudgetRepo impl)
	{
		this.implBudget = impl;
	}

	public Double query(String strStartDate, String strEndDate)
	{
		double dblTotalBudget = 0.0;
		LocalDate ldStart = this.validateInputDate(strStartDate), ldEnd = this.validateInputDate(strEndDate);

		if (null == ldStart || null == ldEnd || (!ldEnd.equals(ldStart) && ldEnd.isBefore(ldStart)))
			return dblTotalBudget;

		LocalDate ldOperationDate = ldStart;
		List<Budget> lstAllBudget = this.implBudget.getAll();
		HashMap<String, Budget> hmpAllBudget = new HashMap<String, Budget>(lstAllBudget.size(), 1f);

		for (Budget b : lstAllBudget)
			hmpAllBudget.put(b.strBudgetPeriod, b);

System.out.println(ldStart + ";" + ldEnd);

		while (ldOperationDate.isBefore(ldEnd) || ldEnd.equals(ldStart))
		{
			int intDayCountOfMonth = ldOperationDate.getMonth().length(ldOperationDate.isLeapYear());
			LocalDate endDateOfMonth = ldOperationDate.withDayOfMonth(intDayCountOfMonth);
			String strOperatingPeriod = ldOperationDate.format(dfmonth); //YearMonth

System.out.println("loop -> " + strOperatingPeriod);

			if (hmpAllBudget.containsKey(strOperatingPeriod)) // 當月有預算
			{
				double dblPeriodBudget = hmpAllBudget.get(strOperatingPeriod).dblBudget;
				intDayCountOfMonth = ldOperationDate.getMonth().length(ldOperationDate.isLeapYear());
				endDateOfMonth = ldOperationDate.withDayOfMonth(intDayCountOfMonth);
				long lngComputingDayCOunt = ChronoUnit.DAYS.between(ldOperationDate, (ldEnd.isBefore(endDateOfMonth) ? ldEnd : endDateOfMonth)) + 1;

System.out.println("loop with budget-> " + dblPeriodBudget + "; daycount=" + intDayCountOfMonth + ";enddate=" + endDateOfMonth + ";lowerbound=" + ldOperationDate);
System.out.println("computing day count = >> " + lngComputingDayCOunt);

				dblTotalBudget += dblPeriodBudget * (lngComputingDayCOunt / (double) intDayCountOfMonth);
			}

			if (ldEnd.isAfter(endDateOfMonth))
				ldOperationDate = ldOperationDate.withDayOfMonth(1);
			else if (ldEnd.equals(ldOperationDate))
				break;

			ldOperationDate = ldOperationDate.plusMonths(1);

		}
		return dblTotalBudget;
	}

	private LocalDate validateInputDate(String strDate)
	{
		try { return LocalDate.parse(strDate, this.dffull); }
		catch (Exception exc)
		{
			System.out.println("Invalid Input: " + strDate);
			return null;
		}
	}
}