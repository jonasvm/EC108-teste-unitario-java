package br.edu.inatel.soa.alps.logistics.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.edu.inatel.soa.alps.logistics.model.User;
import br.edu.inatel.soa.alps.logistics.utils.ConnectionFactory;

public class UserDAO {

	private Connection conn;
	
	public UserDAO() {
		conn = ConnectionFactory.getConnection();
	}
	
	public ArrayList<User> list() {
		ArrayList<User> list = new ArrayList<User>();
		
		String sql = "SELECT id, name, username, password, role FROM user ORDER BY id";
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				User user = loadUser(rs);
				list.add(user);
			}
			rs.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);			
		}
		
		return list;
	}
	
	public ArrayList<User> listByRole(String role) {
		ArrayList<User> list = new ArrayList<User>();
		
		String sql = "SELECT id, name, username, password, role FROM user WHERE role LIKE ? ORDER BY id";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, role);
			ResultSet rs = preparedStatement.executeQuery(sql);
			while(rs.next()) {
				User user = loadUser(rs);
				list.add(user);
			}
			rs.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);			
		}
		
		return list;
	}
	
	public User getById(Long id) {
		User user = null;
		String sql = "SELECT id, name, username, password, role FROM user WHERE id = " + id;
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
//			preparedStatement.setLong(1, id);
			ResultSet rs = preparedStatement.executeQuery(sql);
			if(rs.next()) {
				user = loadUser(rs);
			}
			rs.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);			
		}
		
		return user;
	}
	
	public User search(String username) {
		User user = null;
		String sql = "SELECT id, name, username, password, role FROM user WHERE username='" + username + "'";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
//			preparedStatement.setString(1, username);
//			preparedStatement.setNString(2, password);
			ResultSet rs = preparedStatement.executeQuery(sql);
			if(rs.next()) {
				user = loadUser(rs);
			}
			rs.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);			
		}
		
		return user;
	}
	
	public User search(String username, String password) {
		User user = null;
		String sql = "SELECT id, name, username, password, role FROM user WHERE username='" + username + "' AND password='" + password + "'";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
//			preparedStatement.setString(1, username);
//			preparedStatement.setNString(2, password);
			ResultSet rs = preparedStatement.executeQuery(sql);
			if(rs.next()) {
				user = loadUser(rs);
			}
			rs.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);			
		}
		
		return user;
	}

	private User loadUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getLong(1));
		user.setName(rs.getString(2));
		user.setUserName(rs.getString(3));
		user.setPassword(rs.getString(4));
		user.setRole(rs.getString(5));
		return user;
	}

	public Long add(User user) {
		String sql = "INSERT INTO user (name, username, password, role) VALUES (?, ?, ?, ?);";
		Long taskId = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getName());
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getRole());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				taskId = rs.getLong(1);
			}
			rs.clearWarnings();
			ps.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return taskId;
	}

	public void update(User user) {
		String sql = "UPDATE user SET name=?, password=? WHERE id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setLong(3, user.getId());
			ps.execute();
			ps.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void delete(User user) {
		String sql = "DELETE FROM user WHERE id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, user.getId());
			ps.execute();
			ps.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
