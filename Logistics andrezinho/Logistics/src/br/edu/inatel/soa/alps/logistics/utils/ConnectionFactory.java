package br.edu.inatel.soa.alps.logistics.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionFactory {

	private static Connection conn;
	
	public static Connection getConnection() {
		if(conn == null) {
			try {
				DataSource ds;
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/logisticsdb");
				conn = ds.getConnection();
				
			} catch (NamingException e) {
				throw new RuntimeException(e);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
		return conn;
	}
}
