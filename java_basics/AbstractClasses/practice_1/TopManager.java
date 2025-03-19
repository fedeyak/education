public class TopManager extends EmployeeClass {

    public static final long BONUS_THRESHOLD = 10_000_000;
    public static final double BONUS_RATE = 1.5;

    public TopManager(Company company) {
        this.company = company;
        jobTitle = JobTitle.TOP_MANAGER;
        fixedSalary = 150_000;
        monthSalary = calculateMonthSalary();
    }

    private int calculateMonthSalary() {
        int bonus = 0;
        if (company.getIncome() > BONUS_THRESHOLD) {
            bonus = (int) (fixedSalary * BONUS_RATE);
        }
        return fixedSalary + bonus;
    }
}
