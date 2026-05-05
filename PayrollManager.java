import java.util.*;
import java.util.stream.Collectors;

public class PayrollManager {
    private List<Employee> empList = new ArrayList<>();

    public List<Employee> getEmpList() { return empList; }

    public void addEmployee(Employee emp) {
        empList.add(emp);
    }

    public void addMultipleEmployees(List<String> lines) throws InvalidEmployeeException {
        for (String line : lines) {
            Employee e = PayrollUtil.parseEmployee(line);
            addEmployee(e);
        }
    }

    public List<Employee> getEligibleEmployee(int filterYear, int currentYear, double experiencePercent) throws NoEligibleEmployeeException {
        List<Employee> eligible = empList.stream()
                .filter(PayrollUtil.joinedBefore(filterYear))
                .collect(Collectors.toList());

        if (eligible.isEmpty()) {
            throw new NoEligibleEmployeeException("No eligible employees found!");
        }

        for (Employee e : eligible) {
            double finalSalary = PayrollUtil.computeFinalSalary.apply(e, currentYear);
            PayrollUtil.applyFinalSalary(finalSalary).accept(e);
        }
        return eligible;
    }

    public Map<String, List<Employee>> groupEmployeesByExperienceRange(int currentYear) {
        Map<String, List<Employee>> map = new LinkedHashMap<>();
        map.put("Junior", new ArrayList<>());
        map.put("Mid-level", new ArrayList<>());
        map.put("Senior", new ArrayList<>());

        for (Employee e : empList) {
            int exp = currentYear - e.getJoiningYear();
            if (exp < 3) map.get("Junior").add(e);
            else if (exp < 7) map.get("Mid-level").add(e);
            else map.get("Senior").add(e);
        }
        return map;
    }

    public List<Employee> sortBySkillCountThenSalary() {
        return empList.stream()
                .sorted(PayrollUtil.compareBySkillCountThenSalary())
                .collect(Collectors.toList());
    }
}

