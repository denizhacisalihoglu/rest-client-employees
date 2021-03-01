package org.acme.rest.client;

import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import io.agroal.api.AgroalDataSource;
import javax.ws.rs.GET;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    @Inject
    AgroalDataSource dataSource;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() throws SQLException {
        StringBuilder sb = new StringBuilder();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps =
                 connection.prepareStatement("select * from public.\"Employees\"");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                // sb.append(rs.getString("firstName")).append("\n");
                sb.append(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)).append("\n");
            }
        }
        return sb.toString();
    }


}
