package resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pojo.Employee;

import db.DbManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Path("/employees")
public class EmployeesResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllEmployeesJSON() {
		String result = "[";
		try {
			List<Employee> employees = this.getEmployees();
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

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String getAllEmployeesXML() {
		String result = "<?xml version=\"1.0\"?>\n<employees>\n";
		try {
			List<Employee> employees = this.getEmployees();
			for(int i=0; i<employees.size(); i++) {
				result += employees.get(i).toXML();
			}
			result += "</employees>";
		}
		catch (Exception exc) {
			result = "{\"error\":{\"text\":" + exc.getMessage() + "}}";
			System.out.println("Error: "+ exc.getMessage());
		}
		return result;
	}

	private List<Employee> getEmployees() throws Exception {
		List<Employee> employees = new ArrayList<Employee>();
		Connection conn = DbManager.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT * from employee;");
		while (rset.next()) {
			employees.add(new Employee(rset));
		}
		rset.close();
		stmt.close();
		conn.close();
		return employees;
	}
}
