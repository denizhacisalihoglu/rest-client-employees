package org.acme.rest.service;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
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

    public PanacheQuery<Employee> getEmployee(int limit, int pageIndex,
                                              String orderBy,
                                              String sortBy,
                                              String filterByDepartment){
        String query = "";

        if (filterByDepartment != null && !filterByDepartment.isEmpty()) {
            query += "department like '%" + filterByDepartment + "%'";
        }

        if (orderBy != null && !orderBy.isEmpty()) {
            query += " order by " + orderBy;

            if (sortBy != null && !sortBy.isEmpty()) {
                query += " " + sortBy;
            }
        }

        return employeeRepository.find(query)
                .page(pageIndex, limit);
    }

    public PanacheQuery<Employee> getByDepartment(int limit, int pageIndex, String department){
        return employeeRepository.find("department", department).page(pageIndex, limit);
    }

    public long getCount(){
        return employeeRepository.findAll().count();
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

    @Transactional
    public Employee updateEmployee(Employee employee){
        employeeRepository.persist(employee);
        return employee;
    }
}
