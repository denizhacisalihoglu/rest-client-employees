package org.acme.rest.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.acme.rest.entity.Employee;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmployeeRepository implements PanacheRepository<Employee> {
}
