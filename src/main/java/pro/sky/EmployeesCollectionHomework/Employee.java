package pro.sky.EmployeesCollectionHomework;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

public class Employee {
    private String firstName;
    private String lastName;

    private int department;

    private int salary;

    public Employee(String firstName, String lastName, int department, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.salary = salary;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){

            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }

        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    public Employee() {
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return  "Имя = " + firstName +
                ", Фамилия = " + lastName +
                "."
                ;
    }

    public int getDepartment() {
        return department;
    }
}
