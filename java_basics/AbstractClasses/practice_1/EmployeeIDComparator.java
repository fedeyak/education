import java.util.Comparator;

public class EmployeeIDComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.getEmployeeID() - o2.getEmployeeID();
    }
}
