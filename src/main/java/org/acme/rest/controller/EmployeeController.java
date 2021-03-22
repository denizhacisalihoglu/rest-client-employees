
package org.acme.rest.controller;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.acme.rest.entity.Employee;
import org.acme.rest.service.EmployeeService;
import org.acme.rest.models.*;

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
    public Response getEmployee(@QueryParam("limit") int limit,
                                @QueryParam("page") int page,
                                @QueryParam("orderBy") String orderBy,
                                @QueryParam("sortBy") String sortBy,
                                @QueryParam("filterByDepartment") String filterByDepartment) {
        PanacheQuery<Employee> allEmployees = employeeService.getEmployee(limit, page - 1, orderBy, sortBy, filterByDepartment);
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.data = allEmployees.list();
        employeeResponse.count = employeeService.getCount();

        return Response.ok(employeeResponse).build();
    }

    @POST
    @Produces("application/json")
    public Response addEmployee(Employee employee){
        employeeService.addEmployee(employee);
        return Response.status(201).build();
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

    @Transactional
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response delete(@QueryParam("ids") String ids) {
        String[] split = ids.split(",");
        for (String id : split) {
            employeeService.deleteById(Long.parseLong(id));
        }
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

    @GET
    @Produces("application/json")
    @Path("/department/{limit}/{page}/{department}")
    public Response getByDepartment(
            @PathParam("limit") int limit,
            @PathParam("page") int page,
            @PathParam("department") String department) {
        PanacheQuery<Employee> allEmployees = employeeService.getByDepartment(limit, page - 1, department);
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.data = allEmployees.list();
        employeeResponse.count = (long) allEmployees.list().size();

        return Response.ok(employeeResponse).build();
    }

    @Transactional
    @PUT
    @Path("/{id}")
    @Produces("application/json")
    public Employee updateEmployeeEmail(@PathParam("id") Long id, Employee employee){
        return employeeService.updateEmployee(employee);
    }

}
