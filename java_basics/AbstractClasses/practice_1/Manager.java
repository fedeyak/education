
public class Manager extends EmployeeClass {
    public static final int MIN_EARNING = 115_000;
    public static final int MAX_EARNING = 140_000;
    public static final double BONUS_RATE = 0.05;
    private int moneyEarned;

    // It would be right to add some logic to update the monthSalary every month.
    // If one month has passed since the last monthSalary date -> update moneyEarned and update monthSalary.
    // But since this was not in the task and for the sake of timing I decided to skip this part
    public Manager() {
        jobTitle = JobTitle.MANAGER;
        fixedSalary = 100_000;
        moneyEarned = calculateMoneyEarned();
        monthSalary = calculateMonthSalary();
    }

    public int getMoneyEarned() {
        return moneyEarned;
    }

    private int calculateMonthSalary() {
        return (int) (fixedSalary + (moneyEarned * BONUS_RATE));
    }

    private int calculateMoneyEarned() {
        return (int) (Math.random() * (MAX_EARNING - MIN_EARNING) + MIN_EARNING);
    }
}
