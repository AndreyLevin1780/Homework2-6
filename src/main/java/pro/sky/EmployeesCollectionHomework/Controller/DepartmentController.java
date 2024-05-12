package pro.sky.EmployeesCollectionHomework.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.EmployeesCollectionHomework.Employee;
import pro.sky.EmployeesCollectionHomework.Exceptions.EmployeeNotFoundException;
import pro.sky.EmployeesCollectionHomework.Service.DepartmentService;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee findMaximalSalaryEmployeeByDepartment(@RequestParam int department) {
        return departmentService.findMaximalSalaryEmployeeByDepartment(department);

    }

    @GetMapping("/min-salary")
    public Employee findMinimalSalaryEmployeeByDepartment(@RequestParam int department) {
        return departmentService.findMinimalSalaryEmployeeByDepartment(department);

    }

    @GetMapping(value = "/all", params = {"department"})
    public List<Employee> printEmployeesByDepartment(@RequestParam int department) {
        return departmentService.printEmployeesByDepartment(department);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> printEmployeesByDepartments () {
        return departmentService.printEmployeesByDepartments();
    }
}
