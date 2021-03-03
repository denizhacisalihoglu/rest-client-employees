package org.acme.rest.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.acme.rest.entity.Employee;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmployeeRepository implements PanacheRepository<Employee> {

    public List<Employee> find(Long id) {
        List<Employee> found = find("id", id).list();
        return found;
    }

    public Employee findByEmail(String email) {
        return find("email", email).firstResult();
    }

    public List<Employee> findByFirstName(String firstName) {
        List<Employee> found = find(firstName).list();
        return found;
    }

    public void delete(Long id) {
        delete("id", id);
    }


}
