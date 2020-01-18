package br.edu.inatel.soa.taskservices.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.inatel.soa.taskservices.model.Task;
import br.edu.inatel.soa.taskservices.model.TaskDAO;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TaskDAO dao = new TaskDAO();
		// create task
		Task task = new Task();
		task.setDescription("Get it done until 5:30 pm - now: " + new Date());
		
		// add
		Long taskId = dao.add(task);
		System.out.println("Created Task id: "+ taskId);
		request.setAttribute("taskId", taskId);
		
		// get
		task = dao.get(taskId);
		request.setAttribute("task", task);
		System.out.println("Retrieved Task id: "+ task.getDescription());
				
		//update
		task.setDescription("I think we can do it in time");
		dao.update(task);
		System.out.println("Updated Task: "+ task.getDescription());
		
		dao.delete(task.getId());
		System.out.println("Deleted Task: "+ task.getDescription());
		
		//list
		List<Task> taskList = dao.list();
		request.setAttribute("taskList", taskList);
		System.out.println("Number of retrieved Tasks: "+ taskList.size());
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/test.jsp");
		rd.forward(request, response);
	}

}
