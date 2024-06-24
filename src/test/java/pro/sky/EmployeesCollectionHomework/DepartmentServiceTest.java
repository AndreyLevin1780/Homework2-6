package pro.sky.EmployeesCollectionHomework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.EmployeesCollectionHomework.Exceptions.EmployeeNotFoundException;
import pro.sky.EmployeesCollectionHomework.Service.DepartmentService;
import pro.sky.EmployeesCollectionHomework.Service.EmployeeService;
import pro.sky.EmployeesCollectionHomework.Service.ValidationService;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    private final List<Employee> employees = List.of(
            new Employee("Иван", "Иванов", 1, 50000),
            new Employee("Петр", "Петров", 1, 60000),
            new Employee("Билл", "Гейтс", 2, 10000000),
            new Employee("Иван", "Сидоров", 3, 40000),
            new Employee("Брюс", "Ли", 3, 700000)
    );

    @BeforeEach
    public void beforeEach() {
        when(employeeService.findAll()).thenReturn(employees);
    }

    @Test
    public void printEmployeesByDepartmentTest() {
        assertThat(departmentService.printEmployeesByDepartment(1))
                .containsExactlyInAnyOrder(
                        new Employee("Иван", "Иванов", 1, 50000),
                        new Employee("Петр", "Петров", 1, 60000)
                );
    }

    @Test
    public void calculateSumSalaryByDepartmentTest() {
        assertThat(departmentService.calculateSumSalaryByDepartment(3))
                .isEqualTo(740000);
    }

    @Test
    public void findMaximalSalaryEmployeeByDepartmentTest() {
        assertThat(departmentService.findMaximalSalaryEmployeeByDepartment(3))
                .isEqualTo(new Employee("Брюс", "Ли", 3, 700000));
    }

    @Test
    public void findMaximalSalaryEmployeeByDepartmentNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentService.findMaximalSalaryEmployeeByDepartment(4));
    }

    @Test
    public void findMinimalSalaryEmployeeByDepartmentTest() {
        assertThat(departmentService.findMinimalSalaryEmployeeByDepartment(3))
                .isEqualTo(new Employee("Иван", "Сидоров", 3, 40000));
    }

    @Test
    public void findMinimalSalaryEmployeeByDepartmentNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentService.findMaximalSalaryEmployeeByDepartment(4));
    }

    @Test
    public void printEmployeesByDepartmentsTest() {
        assertThat(departmentService.printEmployeesByDepartments())
                .containsExactlyInAnyOrderEntriesOf(
                        Map.of(
                                1, List.of(new Employee("Иван", "Иванов", 1, 50000), new Employee("Петр", "Петров", 1, 60000)),
                                2, List.of(new Employee("Билл", "Гейтс", 2, 10000000)),
                                3, List.of(new Employee("Иван", "Сидоров", 3, 40000), new Employee("Брюс", "Ли", 3, 700000))
                        )
                );
    }
}
