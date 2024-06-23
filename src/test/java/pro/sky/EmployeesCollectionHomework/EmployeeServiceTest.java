package pro.sky.EmployeesCollectionHomework;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pro.sky.EmployeesCollectionHomework.Exceptions.EmployeeAlreadyAddedException;
import pro.sky.EmployeesCollectionHomework.Exceptions.EmployeeNotFoundException;
import pro.sky.EmployeesCollectionHomework.Exceptions.EmployeeStorageIsFullException;
import pro.sky.EmployeesCollectionHomework.Service.EmployeeService;
import pro.sky.EmployeesCollectionHomework.Service.ValidationService;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class EmployeeServiceTest {

    private final EmployeeService employeeService = new EmployeeService(new ValidationService());
    private final List<Employee> employees = List.of(
            new Employee("Иван", "Иванов", 1, 50000),
            new Employee("Петр", "Петров", 1, 60000),
            new Employee("Билл", "Гейтс", 2, 10000000)
    );


    @AfterEach
    public void AfterEach() {
        employeeService.findAll().forEach(employee -> employeeService.remove(employee.getFirstName(),employee.getLastName()));
    }

    @BeforeEach
    public void BeforeEach() {
        employees.forEach(employee -> employeeService.add(employee.getFirstName(), employee.getLastName(), employee.getDepartment(), employee.getSalary()));
    }

    @Test
    public void addTest() {
        Employee expected = new Employee("Вася", "Пупкин", 2, 30000);
        Employee actual = employeeService.add("Вася", "Пупкин", 2, 30000);
        assertThat(actual).isEqualTo(expected);
        assertThat(actual).isEqualTo(employeeService.find("Вася", "Пупкин"));
        assertThat(actual).isIn(employeeService.findAll());
    }

    @Test
    public void addNegativeTest() {
        employeeService.add("Вася", "Пупкин", 2, 30000);
        employeeService.add("Уилл", "Смит", 3, 1000000);
        assertThatExceptionOfType(EmployeeStorageIsFullException.class)
                .isThrownBy(() -> employeeService.add("Гарри", "Поттер", 1, 40000));
    }

    @Test
    public void addNegativeAlreadyExistTest() {
        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.add("Билл", "Гейтс", 2, 10000000));
    }

    @Test
    public void findTest() {
        Employee expected = new Employee("Петр", "Петров", 1, 60000);
        assertThat(employeeService.findAll()).contains(expected);
        Employee actual = employeeService.find("Петр", "Петров");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void findNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("Петр", "Сидоров"));
    }

    @Test
    public void removeTest() {
        Employee expected = new Employee("Петр", "Петров", 1, 60000);
        assertThat(employeeService.findAll()).contains(expected);
        Employee actual = employeeService.remove("Петр", "Петров");
        assertThat(actual).isEqualTo(expected);
        assertThat(actual).isNotIn(employeeService.findAll());
    }

    @Test
    public void removeNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.remove("Петр", "Сидоров"));
    }

    @Test
    public void findAllTest() {
        assertThat(employeeService.findAll()).containsExactlyInAnyOrderElementsOf(employees);

    }
}

