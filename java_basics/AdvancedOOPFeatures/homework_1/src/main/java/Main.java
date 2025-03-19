import java.util.Comparator;
import java.util.List;

public class Main {

    public static final String STAFF_TXT = "AdvancedOOPFeatures/homework_1/data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        sortBySalaryAndAlphabet(staff);
        staff.forEach(System.out::println);
    }

    public static void sortBySalaryAndAlphabet(List<Employee> staff) {
        staff.sort(Comparator.comparing(Employee::getSalary).thenComparing(Employee::getName));
    }
}