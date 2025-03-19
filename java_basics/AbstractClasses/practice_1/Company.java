import java.util.*;

public class Company {
    public static final double MIN_INCOME = 8_000_000.0;
    public static final double MAX_INCOME = 12_000_000.0;
    private final String CompanyName;
    protected double income;
    private final Comparator<Employee> employeeComparator = new EmployeeSalaryComparator().thenComparing(new EmployeeIDComparator());

    private TreeSet<Employee> employees;

    public Company(String companyName) {
        CompanyName = companyName;
        income = calculateIncome();
        employees = new TreeSet(employeeComparator);
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public double getIncome() {
        return income;
    }

    private int calculateIncome() {
        return (int) (Math.random() * (MAX_INCOME - MIN_INCOME) + MIN_INCOME);
    }

    public TreeSet<Employee> getEmployees() {
        return employees;
    }

    public void hire(Employee employee) {
        if (employee instanceof EmployeeClass) {
            ((EmployeeClass) employee).company = this;
        }
        employees.add(employee);
    }

    public void hireAll(Collection<Employee> employes) {
        for (Employee employee : employes) {
            if (employee instanceof EmployeeClass) {
                ((EmployeeClass) employee).company = this;
            }
        }
        employees.addAll(employes);
    }

    public void fire(Employee employee) {
        employees.remove(employee);
    }

    public List<Employee> getTopSalaryStaff(int count) {
        Iterator<Employee> iterator = employees.descendingIterator();
        return getStaffSalary(count, iterator);
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        Iterator<Employee> iterator = employees.iterator();
        return getStaffSalary(count, iterator);
    }

    private List<Employee> getStaffSalary(int count, Iterator<Employee> iterator) {
        if (count > employees.size()) {
            return null;
        }
        if (count < 0) {
            return null;
        }
        List<Employee> staff = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            staff.add(iterator.next());
        }
        return staff;
    }
}
