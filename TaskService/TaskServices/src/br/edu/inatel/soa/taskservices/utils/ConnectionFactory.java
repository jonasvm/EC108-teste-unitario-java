package br.edu.inatel.soa.taskservices.utils;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectionFactory {
	public static Connection conn;

	public static Connection getConnection() {
		if (conn == null ) {
			try {
				DataSource ds;
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/tasksdb");
				conn = ds.getConnection();
			} catch (Exception nex) {
				throw new RuntimeException(nex);
			}
		}
		return conn;
	}
}
