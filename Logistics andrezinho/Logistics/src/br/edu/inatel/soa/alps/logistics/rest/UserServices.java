package br.edu.inatel.soa.alps.logistics.rest;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import br.edu.inatel.soa.alps.logistics.dao.UserDAO;
import br.edu.inatel.soa.alps.logistics.model.User;

@Path("/user")
public class UserServices {
	
	private UserDAO userDAO = new UserDAO();
	
	@GET
	@RolesAllowed({"admin"})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<User> list() {
		return userDAO.list();
	}
	
	@GET
	@Path("{userId}")
	@RolesAllowed({"admin", "user"})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getById(@PathParam("userId") Long userId, @Context SecurityContext sc) {
		User user = userDAO.getById(userId);
		if(user == null) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).entity("User Not Found.").build();			
		}
		
		// Verify if User is the Owner of the register if he is not a Administrator
		if(!sc.isUserInRole("Admin")) {
			String name = sc.getUserPrincipal().getName();
			if(!name.equals(user.getUserName())) {
				return Response.status(HttpServletResponse.SC_FORBIDDEN).entity("Only the Administrator or the Owner of this register can Update his information.").build();			
			}
		}
		
		return Response.ok(user).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(User user) {
		if(userDAO.search(user.getUserName()) != null) {
			return Response.status(HttpServletResponse.SC_BAD_REQUEST).entity("Could not Add. There is another User with username = " + user.getUserName()).build();			
		}
		
		user.setRole("user");
		Long id = userDAO.add(user);
		return Response.status(HttpServletResponse.SC_CREATED).entity(("The User was Created, ID = " + id)).build();
	}
	
	@PUT
	@Path("{userId}")
	@RolesAllowed({"admin", "user"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("userId") Long userId, User user, @Context SecurityContext sc) {
		User databaseUser = userDAO.getById(userId);
		if(databaseUser == null) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).entity("Could not find User with ID = " + userId).build();			
		}
		
		// Verify if User is the Owner of the register if he is not a Administrator
		if(!sc.isUserInRole("Admin")) {
			String name = sc.getUserPrincipal().getName();
			if(!name.equals(databaseUser.getUserName())) {
				return Response.status(HttpServletResponse.SC_FORBIDDEN).entity("Only the Administrator or the Owner of this register can Update his information").build();			
			}
		}
		
		databaseUser.setName(user.getName());
		databaseUser.setPassword(user.getPassword());
		userDAO.update(databaseUser);
		
		return Response.ok("The User was Updated. ID = " + userId).build();
	}
	
	@DELETE
	@Path("{userId}")
	@RolesAllowed({"admin", "user"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("userId") Long userId, @Context SecurityContext sc) {
		User databaseUser = userDAO.getById(userId);
		if(databaseUser == null) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).entity("Could not find User with ID = " + userId).build();			
		}
		
		// Verify if User is the Owner of the register if he is not a Administrator
		if(!sc.isUserInRole("Admin")) {
			String name = sc.getUserPrincipal().getName();
			if(!name.equals(databaseUser.getUserName())) {
				return Response.status(HttpServletResponse.SC_FORBIDDEN).entity("Only the Administrator or the Owner of this register can Update his information").build();			
			}
		}
		
		userDAO.delete(databaseUser);
		return Response.ok("User " + userId + " Deleted.").build();
	}
}
