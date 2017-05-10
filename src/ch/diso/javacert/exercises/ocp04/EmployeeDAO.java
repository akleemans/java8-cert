package ch.diso.javacert.exercises.ocp04;

public interface EmployeeDAO {

    void add(Employee emp);

    void update(Employee emp);

    void delete(int id);

    Employee findById(int id);

    Employee[] getAllEmployees();

}