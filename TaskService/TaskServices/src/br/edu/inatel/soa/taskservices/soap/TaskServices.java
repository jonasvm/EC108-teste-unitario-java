package br.edu.inatel.soa.taskservices.soap;

import br.edu.inatel.soa.taskservices.model.Task;
import br.edu.inatel.soa.taskservices.model.TaskDAO;

public class TaskServices {
	private TaskDAO dao = new TaskDAO();
	
	public Long add(Task task){
		Long taskId = dao.add(task);
		return taskId;
	}
	
}
