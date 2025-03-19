import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    public static final int TOP_MANAGERS_TO_HIRE = 10;
    public static final int MANAGERS_TO_HIRE = 80;
    public static final int OPERATORS_TO_HIRE = 180;

    public static void main(String[] args) {

        Company company = new Company("My Company");

        for (int i = 0; i < TOP_MANAGERS_TO_HIRE; i++) {
            company.hire(new TopManager(company));
        }
        for (int i = 0; i < MANAGERS_TO_HIRE; i++) {
            company.hire(new Manager());
        }
        for (int i = 0; i < OPERATORS_TO_HIRE; i++) {
            company.hire(new Operator());
        }

        ArrayList<Employee> topEmployees = (ArrayList<Employee>) company.getTopSalaryStaff(15);
        System.out.println();
        System.out.println("Highest paid employees:");
        topEmployees.forEach(System.out::println);
        System.out.println();

        ArrayList<Employee> lowEmployees = (ArrayList<Employee>) company.getLowestSalaryStaff(30);
        System.out.println("Lowest paid employees:");
        lowEmployees.forEach(System.out::println);

        int halfEmployees = company.getEmployees().size() / 2;

        for (int i = 0; i < halfEmployees; i++) {
            company.fire(company.getEmployees().first());
        }

        topEmployees = (ArrayList<Employee>) company.getTopSalaryStaff(15);
        System.out.println();
        System.out.println("Highest paid employees after firing:");
        topEmployees.forEach(System.out::println);
        System.out.println();

        lowEmployees = (ArrayList<Employee>) company.getLowestSalaryStaff(30);
        System.out.println("Lowest paid employees after firing:");
        lowEmployees.forEach(System.out::println);
    }
}
