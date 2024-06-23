package pro.sky.EmployeesCollectionHomework.Controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.EmployeesCollectionHomework.Employee;
import pro.sky.EmployeesCollectionHomework.Service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentControllerNew {

    DepartmentService departmentService;

    public DepartmentControllerNew(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("{department}/salary/max")
    public Employee findMaximalSalaryEmployeeByDepartment(@PathVariable int department) {
        return departmentService.findMaximalSalaryEmployeeByDepartment(department);

    }

    @GetMapping("{department}/salary/min")
    public Employee findMinimalSalaryEmployeeByDepartment(@PathVariable int department) {
        return departmentService.findMinimalSalaryEmployeeByDepartment(department);

    }

    @GetMapping("{department}/employees")
    //@GetMapping(value = "/all", params = {"department"})
    public List<Employee> printEmployeesByDepartment(@PathVariable int department) {
        return departmentService.printEmployeesByDepartment(department);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> printEmployeesByDepartments() {
        return departmentService.printEmployeesByDepartments();
    }

    @GetMapping("{department}/salary/sum")
    public int calculateSumSalaryByDepartment (@PathVariable int department) {
        return departmentService.calculateSumSalaryByDepartment(department);
    }
}

