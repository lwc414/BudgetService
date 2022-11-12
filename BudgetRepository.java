import java.util.Arrays;
import java.util.List;

public class BudgetRepository implements IBudgetRepo{

	public List<Budget> getAll() {
		return Arrays.asList(new Budget[]{new Budget("202201", 310.0),
										  new Budget("202203", 310.0),
										  new Budget("202204", 300.0),
										  new Budget("202205", 310.0),
										  new Budget("202208", 310.0),
										  new Budget("202209", 300.0),
										  new Budget("202210", 310.0),
										  new Budget("202211", 300.0),
										  new Budget("202212", 310.0)});
	}

}
