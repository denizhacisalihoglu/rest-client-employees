package org.acme.rest.client;

import javax.ws.rs.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;


@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    private Set<Employee> listOfEmployees = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public EmployeeResource() {
        listOfEmployees.add(new Employee(0, "John", "Doe"));
        listOfEmployees.add(new Employee(1, "Jane", "Doe"));
    }

    @GET
    public Set<Employee> list() {
        return listOfEmployees;
    }

    @GET
    @Path("/employee/{id}")
    public Response getPersonById(@PathParam("id") int id){
        return Response.ok(filterEmployeeById(id)).build();
    }

    public Employee filterEmployeeById(int id){
        return listOfEmployees.stream()
        .filter(employee -> employee.getId() == id)
        .findAny()
        .orElse(null);
    }

    @GET
    @Path("/employee")
    public Employee filterPersonByName() {

        return listOfEmployees.stream()
        .filter(employee -> "John".equals(employee.getName()))
        .findAny()
        .orElse(null);
    }

    @POST
    public Set<Employee> add(Employee employee) {
        listOfEmployees.add(employee);
        return listOfEmployees;
    }

    @DELETE
    public Set<Employee> delete(Employee employee) {
        listOfEmployees.removeIf(existingEmployee -> existingEmployee.name.contentEquals(employee.name));
        return listOfEmployees;
    }

}
