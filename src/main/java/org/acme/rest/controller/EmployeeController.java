
package org.acme.rest.controller;

import org.acme.rest.entity.Employee;
import org.acme.rest.service.EmployeeService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import javax.ws.rs.WebApplicationException;

@ApplicationScoped
@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeController {

    @Inject
    private EmployeeService employeeService;

    @GET
    @Produces("application/json")
    public Response getEmployee(){
        List<Employee> prod = employeeService.getEmployee();
        return Response.ok(prod).build();
    }

    @POST
    @Path("/add")
    @Produces("application/json")
    public Response addEmployee(Employee employee){
        employeeService.addEmployee(employee);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public List<Employee> findById(@PathParam("id") Long id) {
        List<Employee> p = employeeService.findById(id);

        if(p.size() == 0)
            throw new WebApplicationException(Response.Status.NOT_FOUND);

        return p;
    }

    
}