package br.edu.inatel.soa.taskservices.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.Responses;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

import br.edu.inatel.soa.taskservices.model.Task;
import br.edu.inatel.soa.taskservices.model.TaskDAO;

@Path("/task")
public class TaskServices {

	private TaskDAO dao = new TaskDAO();
	
	@GET
	@Produces( {APPLICATION_JSON, APPLICATION_XML} )
	public List<Task> list(){
		return dao.list();
	}
	
	@GET
	@Path("{taskId}")
	@Produces( {APPLICATION_JSON, APPLICATION_XML} )
	public Response get(@PathParam("taskId") Long taskId){
		Task task = dao.get(taskId);
		Response response = null;
		if(task != null){
			response = Response.ok(task).build();
		}
		else{
			response = Response
					.status(Responses.NOT_FOUND)
					.entity("task not found")
					.build();
		}
		return response;
	}
	
	@POST
	@Consumes(APPLICATION_JSON)	
	public String add(Task task){
		Long taskId = dao.add(task);
		return "Task was created, ID: "+ taskId;
	}
	
	@PUT
	@Path("{taskId}")
	@Consumes(APPLICATION_JSON)	
	public String update(Task task) {
		dao.update(task);
		return "Task Updated!"; 
	}
	
	@DELETE
	@Path("{taskId}")		
	public String delete(@PathParam("taskId") Long taskId){
		dao.delete(taskId);
		return "Task deleted!"; 
	}
	
	
}
