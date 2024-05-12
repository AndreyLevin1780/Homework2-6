package pro.sky.EmployeesCollectionHomework.Service;

import org.springframework.stereotype.Service;
import pro.sky.EmployeesCollectionHomework.Employee;
import pro.sky.EmployeesCollectionHomework.Exceptions.EmployeeNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee findMaximalSalaryEmployeeByDepartment(int department) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(e -> e.getSalary()))
                .orElseThrow(EmployeeNotFoundException::new);

    }

    public Employee findMinimalSalaryEmployeeByDepartment(int department) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(e -> e.getSalary()))
                .orElseThrow(EmployeeNotFoundException::new);

    }

    public List<Employee> printEmployeesByDepartment(int department) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> printEmployeesByDepartments () {
        return employeeService.findAll().stream()
                .collect(groupingBy(e -> e.getDepartment()));
    }
}
