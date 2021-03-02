package org.acme.rest.service;
import org.acme.rest.repository.EmployeeRepository;
import org.acme.rest.entity.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class EmployeeService {

    @Inject
    private EmployeeRepository employeeRepository;

    public List<Employee> getEmployee(){
        return employeeRepository.listAll();
    }

    @Transactional
    public void addEmployee(Employee employee){
        employeeRepository.persist(employee);
    }

    public List<Employee> findById(Long id) {
        return employeeRepository.find(id);
    }

    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    public void deleteById(Long id) {
        employeeRepository.delete(id);
    }


    
}
