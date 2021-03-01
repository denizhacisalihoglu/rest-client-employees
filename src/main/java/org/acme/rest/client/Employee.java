package org.acme.rest.client;

import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;

public class Employee {

    public int id;
    public String name;
    public String surname;
    public String birthdate;
    public String occupation;


    public Employee(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname; 
    }
    
    public Employee(Long long1, String string) {
	}

	public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getSurname(){
        return this.surname;
    }

    public String getBirthdate(){
        return this.birthdate;
    }

    public String getOccupation(){
        return this.occupation;
    }

    private static Employee from(Row row) {
        return new Employee(row.getLong("id"), row.getString("name"));
    }

    public static Multi<Employee> findAll(PgPool client) {
        return client.query("SELECT id, name FROM fruits ORDER BY name ASC").execute()
                .onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
                .onItem().transform(Employee::from);
    }

    
}
