package resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pojo.Employee;

import db.DbManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Path("/employees/search/{name}")
public class SearchEmployeesResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String searchEmployeesByNameJSON(@PathParam("name") String name) {
		String result = "[";
		try {
			List<Employee> employees = this.getEmployeesByName(name);
			for(int i=0; i<employees.size(); i++) {
				result += employees.get(i).toJSON();
				if(i != employees.size() - 1) {
					result += ",";
				}
			}
			result += "]";
		}
		catch (Exception exc) {
			result = "{\"error\":{\"text\":" + exc.getMessage() + "}}";
			System.out.println("Error: "+ exc.getMessage());
		}
		return result;
	}

	@Produces(MediaType.APPLICATION_XML)
	public String searchEmployeesByNameXML(@PathParam("name") String name) {
		String result = "<?xml version=\"1.0\"?>\n<employees>\n";
		try {
			List<Employee> employees = this.getEmployeesByName(name);
			for(int i=0; i<employees.size(); i++) {
				result += employees.get(i).toJSON();
				if(i != employees.size() - 1) {
					result += ",";
				}
			}
			result += "</employees>";
		}
		catch (Exception exc) {
			result = "{\"error\":{\"text\":" + exc.getMessage() + "}}";
			System.out.println("Error: "+ exc.getMessage());
		}
		return result;
	}

	private List<Employee> getEmployeesByName(String name) throws Exception {
		List<Employee> employees = new ArrayList<Employee>();
		Connection conn = DbManager.getConnection();
		Statement stmt = conn.createStatement();
		String query = "SELECT * from employee where CONCAT_WS(' ', firstName, lastName) LIKE '%" + name + "%';";
		ResultSet rset = stmt.executeQuery(query);
		while (rset.next()) {
			employees.add(new Employee(rset));
		}
		rset.close();
		stmt.close();
		conn.close();
		return employees;
	}
}
