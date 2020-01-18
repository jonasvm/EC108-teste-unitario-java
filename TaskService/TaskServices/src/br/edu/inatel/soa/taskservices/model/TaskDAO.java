package br.edu.inatel.soa.taskservices.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.inatel.soa.taskservices.utils.ConnectionFactory;

public class TaskDAO {
	private Connection conn;

	public TaskDAO() {		
		this.conn = ConnectionFactory.getConnection();
	}
	
	public Long add(Task task){
		String sql = "INSERT INTO tasks(done, description) VALUES (?,?)";
		Long taskId = null;		
		try {			
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setBoolean(1, task.getDone());
			stmt.setString(2, task.getDescription());
			stmt.execute();
			
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				taskId = rs.getLong(1);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {		
			throw new RuntimeException();
		}		
		return taskId;
	}
	
	public Task get(Long taskId){
		String sql = "SELECT id, done, description FROM tasks WHERE id = ?";
		Task task = null;
		try {			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, taskId);
			stmt.execute();
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				task = createObject(rs);
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {		
			throw new RuntimeException();
		}		
		return task;
	}
	
	public List<Task> list() {
		String sql = "SELECT id, done, description FROM tasks";
		Task task = null;
		List<Task> tasklist = new ArrayList<Task>();
		try {			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				task = createObject(rs);
				tasklist.add(task);
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		}		
		return tasklist;
	}
	
	public Integer update(Task task){
		String sql = "UPDATE tasks SET done = ?, description=? WHERE id = ?";
		
		int numRows = 0;		
		try {			
			PreparedStatement stmt = conn.prepareStatement(sql);			
			stmt.setBoolean(1, task.getDone());
			stmt.setString(2, task.getDescription());
			stmt.setLong(3, task.getId());
			
			numRows = stmt.executeUpdate();				
			stmt.close();
		} catch (SQLException e) {		
			throw new RuntimeException();
		}		
		return numRows;
	}
	
	public void delete(Long taskId) {
		String sql = "DELETE FROM tasks WHERE id = ?";		
		try {			
			PreparedStatement stmt = conn.prepareStatement(sql);			
			stmt.setLong(1, taskId);			
			stmt.execute();				
			stmt.close();
		} catch (SQLException e) {		
			throw new RuntimeException();
		}	
	}

	private Task createObject(ResultSet rs) throws SQLException {
		Task task;
		task = new Task();
		task.setId(rs.getLong("id"));
		task.setDone(rs.getBoolean("done"));
		task.setDescription(rs.getString("description"));
		return task;
	}
}
