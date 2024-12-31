package hva.employees;
import java.io.Serializable;

public abstract class Employee implements Serializable{
    private final String _EMPLOYEE_KEY;
    private String _name;

    public Employee(String employeeKey, String name){
        _EMPLOYEE_KEY = employeeKey;
        _name = name;
    }

    public String getKey() {return _EMPLOYEE_KEY;}

    public String getName() {return _name;}

    public abstract void removeResponsability(String responsabilityKey);

    public abstract boolean containsResponsability(String responsabilityKey);

    public abstract double calculateSatisfaction();

    @Override
    public String toString() {
        return "|" + _EMPLOYEE_KEY + "|" + _name;
    }
}
