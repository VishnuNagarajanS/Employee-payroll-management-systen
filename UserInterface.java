import java.util.*;

public class UserInterface {
    public static void main(String[] args) {
        PayrollManager manager = new PayrollManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- PAYROLL MANAGEMENT SYSTEM ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Add Multiple Employees");
            System.out.println("3. Get Eligible Employees");
            System.out.println("4. Group Employees by Experience");
            System.out.println("5. Sort by Skill Count then Salary");
            System.out.println("6. Department wise Salary Expense");
            System.out.println("7. Check Understaffed Department");
            System.out.println("8. View All Employees");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("Enter details (id;name;dept;salary;year;rating;skill1,skill2):");
                        manager.addEmployee(PayrollUtil.parseEmployee(sc.nextLine()));
                        break;
                    case 2:
                        System.out.print("How many employees? ");
                        int n = Integer.parseInt(sc.nextLine());
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < n; i++) list.add(sc.nextLine());
                        manager.addMultipleEmployees(list);
                        break;
                    case 3:
                        System.out.print("Filter Year: ");
                        int fy = Integer.parseInt(sc.nextLine());
                        System.out.print("Current Year: ");
                        int cy = Integer.parseInt(sc.nextLine());
                        manager.getEligibleEmployee(fy, cy, 0).forEach(System.out::println);
                        break;
                    case 8:
                        manager.getEmpList().forEach(System.out::println);
                        break;
                    case 9:
                        System.out.println("Exiting...");
                        return;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}

