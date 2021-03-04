package org.acme.rest.service;
import io.quarkus.panache.common.Sort;
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
    EmployeeRepository employeeRepository;

    public List<Employee> getEmployee(){
        return employeeRepository.listAll(Sort.by("firstName"));
    }

    @Transactional
    public void addEmployee(Employee employee){
        employeeRepository.persist(employee);
    }

    public Employee findById(Long id) {
        return employeeRepository.find(id);
    }

    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    public void deleteById(Long id) {
        employeeRepository.delete(id);
    }

    public List<Employee> findByJobTitle(String jobTitle) {
        System.out.println("jobTitle");
        return employeeRepository.findByJobTitle(jobTitle);
    }

}
