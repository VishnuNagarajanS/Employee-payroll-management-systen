import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class PayrollUtil {

    public static Employee parseEmployee(String input) throws InvalidEmployeeException {
        try {
            String[] parts = input.split(";");
            if (parts.length != 7) {
                throw new InvalidEmployeeException("Invalid employee format");
            }
            String empId = parts[0];
            String name = parts[1];
            String dept = parts[2];
            double salary = Double.parseDouble(parts[3]);
            int year = Integer.parseInt(parts[4]);
            double rating = Double.parseDouble(parts[5]);
            List<String> skills = Arrays.asList(parts[6].split(","));

            return new Employee(empId, name, dept, salary, year, rating, skills);
        } catch (Exception e) {
            throw new InvalidEmployeeException("Invalid employee data");
        }
    }

    public static Predicate<Employee> joinedBefore(int year) {
        return e -> e.getJoiningYear() < year;
    }

    public static Function<Employee, Double> computeBonus = (e) -> {
        if (e.getRating() > 4.5) return 0.20;
        if (e.getRating() > 4.0) return 0.15;
        return 0.10;
    };

    public static BiFunction<Employee, Integer, Double> computeFinalSalary = (e, currentYear) -> {
        double bonus = computeBonus.apply(e);
        int experience = currentYear - e.getJoiningYear();
        double expBonus = 0;
        if (experience >= 5) {
            expBonus = e.getBasicSalary() * 0.05;
        }
        return e.getBasicSalary() + (e.getBasicSalary() * bonus) + expBonus;
    };

    public static Consumer<Employee> applyFinalSalary(double finalSalary) {
        return e -> e.setBasicSalary(finalSalary);
    }

    public static Comparator<Employee> compareBySkillCountThenSalary() {
        return Comparator.comparing((Employee e) -> e.getSkills().size()).reversed()
                .thenComparing(Employee::getBasicSalary, Comparator.reverseOrder());
    }

    public static double departmentWiseSalaryExpense(List<Employee> list, String department) {
        return list.stream()
                .filter(e -> e.getDepartment().equalsIgnoreCase(department))
                .mapToDouble(Employee::getBasicSalary)
                .sum();
    }

    public static boolean isDepartmentUnderstaffed(List<Employee> list, String department, int minimum) {
        long count = list.stream()
                .filter(e -> e.getDepartment().equalsIgnoreCase(department))
                .count();
        return count < minimum;
    }
}

