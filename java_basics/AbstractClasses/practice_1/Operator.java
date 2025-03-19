public class Operator extends EmployeeClass {

    public static final int MIN_SALARY = 40_000;
    public static final int MAX_SALARY = 60_000;

    public Operator() {
        jobTitle = JobTitle.OPERATOR;
        fixedSalary = calculateFixedSalary();
        monthSalary = fixedSalary;
    }

    private int calculateFixedSalary() {
        return (int) (Math.random() * (MAX_SALARY - MIN_SALARY) + MIN_SALARY);
    }
}
