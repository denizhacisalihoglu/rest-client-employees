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


@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PeopleResource {

    private Set<Person> peopleList = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public PeopleResource() {
        peopleList.add(new Person(0, "John", "Doe"));
        peopleList.add(new Person(1, "Jane", "Doe"));
    }

    @GET
    public Set<Person> list() {
        return peopleList;
    }

    @GET
    @Path("/person/{id}")
    public Response getPersonById(@PathParam("id") int id){
        return Response.ok(filterPersonById(id)).build();
    }

    public Person filterPersonById(int id){
        return peopleList.stream()
        .filter(person -> person.getId() == id)
        .findAny()
        .orElse(null);
    }

    @GET
    @Path("/person")
    public Person filterPersonByName() {

        return peopleList.stream()
        .filter(person -> "John".equals(person.getName()))
        .findAny()
        .orElse(null);
    }

    @POST
    public Set<Person> add(Person person) {
        peopleList.add(person);
        return peopleList;
    }

    @DELETE
    public Set<Person> delete(Person person) {
        peopleList.removeIf(existingPerson -> existingPerson.name.contentEquals(person.name));
        return peopleList;
    }

}
