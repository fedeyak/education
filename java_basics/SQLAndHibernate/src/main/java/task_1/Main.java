package task_1;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class Main {

    private static Map<String, List<Date>> courses = new HashMap<>();

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "Feo@7720";
        String query = "select * from purchaselist " +
                "where subscription_date >= '2018-01-01' " +
                "and subscription_date <= '2018-12-31'" +
                "order by course_name, subscription_date";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet rSet = statement.executeQuery(query);

            while (rSet.next()) {
                Course course = new Course(rSet.getString("course_name"), rSet.getDate("subscription_date"));
                if (makeKey(course)) {continue;}
                courses.get(course.getName()).add(course.getDate());
            }
            rSet.close();
            statement.close();
            connection.close();

            for (String str : courses.keySet()) {
                int firstMonth = courses.get(str).get(0).getMonth();
                int lastMonth = courses.get(str).get(courses.get(str).size() - 1).getMonth();
                int totalMonths = lastMonth - firstMonth + 1;
                int totalPurchases = courses.get(str).size();
                double avgPurchasesPerMonth = (double) totalPurchases / totalMonths;
                String avgPurchases = String.format("%.2f", avgPurchasesPerMonth);

                System.out.println(str);
                System.out.println("Cреднее количество покупок в месяц: " + avgPurchases);
                System.out.println();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    private static boolean makeKey(Course course) {
        if (!courses.containsKey(course.getName())) {
            List<Date> dates = new ArrayList<>();
            dates.add(course.getDate());
            courses.put(course.getName(), dates);
            return true;
        }
        return false;
    }
}
