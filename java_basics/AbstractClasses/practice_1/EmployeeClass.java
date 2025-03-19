import java.text.DecimalFormat;
import java.util.Objects;

public abstract class EmployeeClass implements Employee {

    private static int employeeAmount;
    protected Company company;
    protected JobTitle jobTitle;
    protected final int employeeID;
    protected int fixedSalary;
    protected int monthSalary;

    public EmployeeClass() {
        employeeAmount++;
        employeeID = employeeAmount;
    }

    public Company getCompany() {
        return company;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    @Override
    public int getEmployeeID() {
        return employeeID;
    }

    public int getFixedSalary() {
        return fixedSalary;
    }

    @Override
    public int getMonthSalary() {
        return monthSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeClass that = (EmployeeClass) o;
        return employeeID == that.employeeID && fixedSalary == that.fixedSalary && monthSalary == that.monthSalary && Objects.equals(company, that.company) && jobTitle == that.jobTitle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(company, jobTitle, employeeID, fixedSalary, monthSalary);
    }

    @Override
    public String toString() {
        return new DecimalFormat("###,###").format(monthSalary) + " руб.";
    }
}
