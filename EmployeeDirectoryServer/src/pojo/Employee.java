package pojo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee {

	private int id;
	private String firstName;
	private String lastName;
	private int managerId;
	private String title;
	private String department;
	private String officePhone;
	private String cellPhone;
	private String email;
	private String city;
	private String picture;
	private String twitterId;
	private String blogUrl;
	
	public Employee(int id, String firstName, String lastName, int managerId,
			String title, String department, String officePhone,
			String cellPhone, String email, String city, String picture,
			String twitterId, String blogUrl) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.managerId = managerId;
		this.title = title;
		this.department = department;
		this.officePhone = officePhone;
		this.cellPhone = cellPhone;
		this.email = email;
		this.city = city;
		this.picture = picture;
		this.twitterId = twitterId;
		this.blogUrl = blogUrl;
	}
	
	public Employee(ResultSet resultSet) {
		try {
			this.id = resultSet.getInt("id");
			this.firstName = resultSet.getString("firstName");
			this.lastName = resultSet.getString("lastName");
			this.managerId = resultSet.getInt("managerId");
			this.title = resultSet.getString("title");
			this.department = resultSet.getString("department");
			this.officePhone = resultSet.getString("officePhone");
			this.cellPhone = resultSet.getString("cellPhone");
			this.email = resultSet.getString("email");
			this.city = resultSet.getString("city");
			this.picture = resultSet.getString("picture");
			this.twitterId = resultSet.getString("twitterId");
			this.blogUrl = resultSet.getString("blogUrl");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Employee(String json) {
		// TODO here we have to put the code to parse the JSON String and set the attributes
		System.out.println("The JSON parser of Employee is not implemented");
	}
	
	public String toJSON() {
		return "{\"id\":\"" + this.id + "\"," + 
				"\"firstName\":\"" + this.firstName + "\"," +
				"\"lastName\":\"" + this.lastName + "\"," +
				"\"managerId\":\"" + this.managerId + "\"," +
				"\"title\":\"" + this.title + "\"," +
				"\"department\":\"" + this.department + "\"," +
				"\"officePhone\":\"" + this.officePhone + "\"," +
				"\"cellPhone\":\"" + this.cellPhone + "\"," +
				"\"email\":\"" + this.email + "\"," +
				"\"city\":\"" + this.city + "\"," +
				"\"picture\":\"" + this.picture + "\"," +
				"\"twitterId\":\"" + this.twitterId + "\"," +
				"\"blogUrl\":\"" + this.blogUrl + "\"" +
				"}";
	}
	
	// this is just for demonstration purposes, please never create XML documents as Strings!
	public String toXML() {
		return "<employee>\n" +
				"<id>" + this.id + "</id>\n" + 
				"<firstName>" + this.firstName + "</firstName>\n" +
				"<lastName>" + this.lastName + "</lastName>\n" +
				"<managerId>" + this.managerId + "</managerId>\n" +
				"<title>" + this.title + "</title>\n" +
				"<department>" + this.department + "</department>\n" +
				"<officePhone>" + this.officePhone + "</officePhone>\n" +
				"<cellPhone>" + this.cellPhone + "</cellPhone>\n" +
				"<email>" + this.email + "</email>\n" +
				"<city>" + this.city + "</city>\n" +
				"<picture>" + this.picture + "</picture>\n" +
				"<twitterId>" + this.twitterId + "</twitterId>\n" +
				"<blogUrl>" + this.blogUrl + "</blogUrl>\n" +
				"</employee>\n";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getOfficePhone() {
		return officePhone;
	}
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getTwitterId() {
		return twitterId;
	}
	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}
	public String getBlogUrl() {
		return blogUrl;
	}
	public void setBlogUrl(String blogUrl) {
		this.blogUrl = blogUrl;
	}
}
