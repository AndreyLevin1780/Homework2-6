package pro.sky.EmployeesCollectionHomework;

import org.springframework.stereotype.Service;
import pro.sky.EmployeesCollectionHomework.Exceptions.EmployeeAlreadyAddedException;
import pro.sky.EmployeesCollectionHomework.Exceptions.EmployeeNotFoundException;
import pro.sky.EmployeesCollectionHomework.Exceptions.EmployeeStorageIsFullException;

import java.util.*;

@Service
public class EmployeeService {

    private final int maxNumberOfEmployees = 10;
    private final Map<String, Employee> employees = new HashMap();

    public Employee add(String firstName, String lastName) {
        String key = buildKey(firstName, lastName);
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() >= maxNumberOfEmployees) {
            throw new EmployeeStorageIsFullException();
        }
        employees.put(key,employee);
        return employee;
    }

    public Employee remove(String firstName, String lastName) {
        String key = buildKey(firstName, lastName);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException();
        }
        return employees.remove(key);
    }

    public Employee find(String firstName, String lastName) {
        String key = buildKey(firstName, lastName);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(key);
    }

    public String buildKey (String name, String surename) {
        return name + surename;
    }

    public List<Employee> findAll() {
        return List.copyOf(employees.values());
    }
}
