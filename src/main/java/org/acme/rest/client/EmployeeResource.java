package org.acme.rest.client;

import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import io.agroal.api.AgroalDataSource;
import javax.ws.rs.GET;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    @Inject
    AgroalDataSource dataSource;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        Connection conn = null;
        Statement stmt = null;
        try {

            //STEP 1: Open a connection
            System.out.println("Connecting to database...");
            conn = dataSource.getConnection();
            stmt = conn.createStatement();

            //STEP 2: Execute queries
            String querySelectAllEmployees = "select * from public.\"Employees\"";
            ResultSet rs=stmt.executeQuery(querySelectAllEmployees);
            while(rs.next())
                System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4));

            // STEP 3: Clean-up environment
            // stmt.close();
            // conn.close();
        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
                System.out.println("Nothing to do.");
            } 
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try
        System.out.println("Goodbye!");
        return "Executed queries";
    }

}
