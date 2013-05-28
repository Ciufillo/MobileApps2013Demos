package resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import pojo.Employee;

import db.DbManager;

import java.sql.*;

@Path("/employees/{id}")
public class EmployeeResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String searchEmployeesByIdJSON(@PathParam("id") String id) {
		String result = "";
		try {
			result = this.getEmployee(id).toJSON();
		}
		catch (Exception exc) {
			result = "{\"error\":{\"text\":" + exc.getMessage() + "}}";
			exc.printStackTrace();
		}
		return result;
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String searchEmployeesByIdXML(@PathParam("id") String id) {
		String result = "";
		try {
			result = "<?xml version=\"1.0\"?>\n";
			result += this.getEmployee(id).toXML();
		}
		catch (Exception exc) {
			result = "{\"error\":{\"text\":" + exc.getMessage() + "}}";
			exc.printStackTrace();
		}
		return result;
	}

	@DELETE
	public void deleteEmployee(@PathParam("id") String id) {
		try {
			Connection conn = DbManager.getConnection();
			String query = "DELETE from employee where id = " + id + ";";
			Statement stmt = conn.prepareStatement(query);
			stmt.executeUpdate(query);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			throw new WebApplicationException(404);
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	// TODO this method must be tested
	public void updateEmployee(@PathParam("id") String id, String payload) {
		try {
			Employee employee = new Employee(payload);
			Connection conn = DbManager.getConnection();
			Statement stmt = conn.createStatement();
			String query = "UPDATE INTO employee SET " + 
					"firstName = '" + employee.getFirstName() + "', " +
					"lastName = '" + employee.getLastName() + "', " +
					"managerId = " + employee.getManagerId() + ", " +
					"title = '" + employee.getTitle() + "', " +
					"department = '" + employee.getDepartment() + "', " +
					"officePhone = '" + employee.getOfficePhone() + "', " +
					"cellPhone = '" + employee.getCellPhone() + "', " +
					"email = '" + employee.getEmail() + "', " +
					"city = '" + employee.getCity() + "', " +
					"picture = '" + employee.getPicture() + "', " +
					"twitter = '" + employee.getTwitterId() + "', " +
					"blogUrl = '" + employee.getBlogUrl() + "'" +
					" WHERE id = " + id + ";";
			stmt.executeUpdate(query);
			stmt.close();
			conn.close();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	// TODO this method must be tested
	public String addEmployee(String payload) {
		String result = "";
		try {
			Employee employee = new Employee(payload);
			Connection conn = DbManager.getConnection();
			Statement stmt = conn.createStatement();
			String query = "INSERT INTO employee VALUES (0,'" + employee.getFirstName() + ",'" +
					employee.getLastName() + "','" +
					employee.getManagerId() + "','" +
					employee.getTitle() + "','" +
					employee.getDepartment() + "','" +
					employee.getOfficePhone() + "','" +
					employee.getCellPhone() + "','" +
					employee.getEmail() + "','" +
					employee.getCity() + "','" +
					employee.getPicture() + "','" +
					employee.getTwitterId() + "','" +
					employee.getBlogUrl() + "');";
			ResultSet rset = stmt.executeQuery(query);
			result = new Employee(rset).toJSON();
			stmt.close();
			conn.close();
		} catch (Exception exc) {
			result = "{\"error\":{\"text\":" + exc.getMessage() + "}}";
			exc.printStackTrace();
		}
		return result;
	}

	private Employee getEmployee(String id) throws Exception {
		Employee employee = null;
		Connection conn = DbManager.getConnection();
		Statement stmt = conn.createStatement();
		String query = "SELECT * from employee where id = " + id + ";";
		ResultSet rset = stmt.executeQuery(query);
		if (rset.next()) {
			employee = new Employee(rset);
		}
		rset.close();
		stmt.close();
		conn.close();
		return employee;
	}
}
