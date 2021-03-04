
package org.acme.rest.controller;

import org.acme.rest.entity.Employee;
import org.acme.rest.service.EmployeeService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.WebApplicationException;

@ApplicationScoped
@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeController {

    @Inject
    EmployeeService employeeService;

    @GET
    @Produces("application/json")
    public Response getEmployee(){
        List<Employee> prod = employeeService.getEmployee();
        return Response.ok(prod).build();
    }

    @POST
    @Produces("application/json")
    public Response addEmployee(Employee employee){
        employeeService.addEmployee(employee);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public Employee findById(@PathParam("id") Long id) {
        Optional<Employee> optional = Employee.findByIdOptional(id);
        return optional.orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }

    @Transactional
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        employeeService.deleteById(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/email/{email}")
    public Employee get(@PathParam("email") @Encoded String email) {
        return employeeService.findByEmail(email);
    }

    @GET
    @Path("/jobtitle/{jobtitle}")
    public List<Employee> findByJobTitle(@PathParam("jobtitle") @Encoded String jobtitle) {
        return employeeService.findByJobTitle(jobtitle);
    }

    @Transactional
    @PUT
    @Path("/{id}")
    @Produces("application/json")
    public Employee updateEmployeeEmail(@PathParam("id") Long id, Employee employee){
        return employeeService.updateEmployee(employee);
    }

}
