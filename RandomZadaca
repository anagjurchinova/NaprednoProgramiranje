//neka si stoi mi se svigja onaka

import java.io.*;
import java.util.*;

abstract class Employee implements Comparable<Employee>{
    String ID;
    String level;
    double rateByLevel;
    public Employee(String ID, String level, double rateByLevel){
        this.ID = ID;
        this.level = level;
        this.rateByLevel = rateByLevel;
    }

    public int getLevel(){
        return Integer.parseInt(level.substring(5, level.length()));
    }

    abstract double calculateSalary();

    @Override
    public int compareTo(Employee o) {
        Comparator<Employee> comparator = Comparator.comparing(Employee::calculateSalary)
                .thenComparing(Employee::getLevel).reversed();

        return comparator.compare(this, o);
    }
}
class HourlyEmployee extends Employee{
    double hours;
    static final double COEF = 1.5;
    public HourlyEmployee(String ID, String level, double rateByLevel, String hours) {
        super(ID, level, rateByLevel);
        this.hours = Double.parseDouble(hours);
    }

    double getRegularHours(){
        if(hours<=40) return hours;
        return 40;
    }
    double getOvertimeHours(){
        if(hours<=40) return 0;
        return hours-40;
    }
    @Override
    double calculateSalary() {
        if(hours<=40) return hours*rateByLevel;
        return getRegularHours()*rateByLevel + getOvertimeHours()*rateByLevel*COEF;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return String.format("Employee ID: %s Level: %s Salary: %.2f Regular hours: %.2f Overtime hours: %.2f",
                ID, level, calculateSalary(), getRegularHours(), getOvertimeHours());
    }
}
class FreelanceEmployee extends Employee{
    List<Integer> ticketPoints;

    public FreelanceEmployee(String ID, String level, double rateByLevel, List<Integer> ticketPoints) {
        super(ID, level, rateByLevel);
        this.ticketPoints = new ArrayList<>();
        this.ticketPoints = ticketPoints;
    }

    public int sumTicketPoints(){
        return ticketPoints.stream().mapToInt(i -> i).sum();
    }
    @Override
    double calculateSalary() {
        return sumTicketPoints()*rateByLevel;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return String.format("Employee ID: %s Level: %s Salary: %.2f Tickets count: %d Tickets points: %d",
                ID, level, calculateSalary(), ticketPoints.size(), sumTicketPoints());
    }
}
class PayrollSystem{
    Set<Employee> employees;
    Map<String, Double> hourlyRateByLevel;
    Map<String, Double> ticketRateByLevel;
    public PayrollSystem(Map<String,Double> hourlyRateByLevel, Map<String,Double> ticketRateByLevel){
        this.hourlyRateByLevel = new TreeMap<>();
        this.ticketRateByLevel = new TreeMap<>();
        this.hourlyRateByLevel = hourlyRateByLevel;
        this.ticketRateByLevel = ticketRateByLevel;
        this.employees = new HashSet<>();
    }

    public void printEmployees(){
        employees.stream().forEach(employee -> System.out.println(employee.toString()));
    }

    public Map<String, TreeSet<Employee>> mapEmployeesByLevel() {
        Map<String, TreeSet<Employee>> employeesByLevel = new TreeMap<>();

        employees.forEach(employee -> {
            String level = employee.level;
            employeesByLevel.putIfAbsent(level, new TreeSet<>());
            employeesByLevel.get(level).add(employee);
        });

        return employeesByLevel;
    }

    public Map<String, TreeSet<Employee>> printEmployeesByLevels(OutputStream os, Set<String> levels) {
        PrintStream out = new PrintStream(os);
        Map<String, TreeSet<Employee>> map = new TreeMap<>();

        levels.forEach(level -> {
            map.put(level, mapEmployeesByLevel().getOrDefault(level, new TreeSet<Employee>()));
        });

        return map;
    }

    public void readEmployees (InputStream is){
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        br.lines().forEach(line -> {
            String parts[] = line.split(";");
            if(parts[0].equals("F")){
                //parts[1] - id, parts[2] - level
                ArrayList<Integer> points = new ArrayList<>();
                for (int i=3; i<parts.length; i++)
                    points.add(Integer.parseInt(parts[i]));

                double rateByLevel = ticketRateByLevel.get(parts[2]);
                employees.add(new FreelanceEmployee(parts[1], parts[2], rateByLevel, points));

            }else if(parts[0].equals("H")){
                double rateByLevel = hourlyRateByLevel.get(parts[2]);
                employees.add(new HourlyEmployee(parts[1], parts[2], rateByLevel, parts[3]));
            }
        });
    }
}
public class PayrollSystemTest {

    public static void main(String[] args) {

        Map<String, Double> hourlyRateByLevel = new LinkedHashMap<>();
        Map<String, Double> ticketRateByLevel = new LinkedHashMap<>();
        for (int i = 1; i <= 10; i++) {
            hourlyRateByLevel.put("level" + i, 10 + i * 2.2);
            ticketRateByLevel.put("level" + i, 5 + i * 2.5);
        }

        PayrollSystem payrollSystem = new PayrollSystem(hourlyRateByLevel, ticketRateByLevel);

        System.out.println("READING OF THE EMPLOYEES DATA");
        payrollSystem.readEmployees(System.in);

        System.out.println("PRINTING EMPLOYEES BY LEVEL");
        Set<String> levels = new LinkedHashSet<>();
        for (int i = 5; i <= 10; i++) {
            levels.add("level" + i);
        }

        Map<String, TreeSet<Employee>> result = payrollSystem.printEmployeesByLevels(System.out, levels);
        Collection<Employee> level10Printing = result.get("level10");
        if (level10Printing!=null && !level10Printing.isEmpty()) {
            System.out.println("LEVEL: level10");
            System.out.println("Employees: ");
            level10Printing.forEach(System.out::println);
            System.out.println("------------");
        }

        result.remove("level10");

        result.forEach((level, employees) -> {
            if (!result.get(level).isEmpty()){
                System.out.println("LEVEL: " + level);
                System.out.println("Employees: ");
                employees.forEach(System.out::println);
                System.out.println("------------");
            }
        });
    }
}
