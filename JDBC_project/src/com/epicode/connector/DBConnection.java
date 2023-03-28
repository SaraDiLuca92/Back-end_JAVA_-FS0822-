package com.epicode.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.epicode.model.User;

public class DBConnection {
	
	private String url = "jdbc:postgresql://localhost:5432/";
	private String dbName = "JDBC_java";
	private String username = "postgres";
	private String password = "root";
	Statement st;
	
	public DBConnection() throws SQLException {
		Connection conn =  DriverManager.getConnection(url+dbName, username, password );
		st =  conn.createStatement();
		creaTabUser();
	}
	
	public void creaTabUser() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS Users ("
				+ "id_user SERIAL PRIMARY KEY,"
				+ "nome VARCHAR NOT NULL,"
				+ "cognome VARCHAR NOT NULL,"
				+ "anni INT2 NOT NULL,"
				+ "email VARCHAR NOT NULL UNIQUE)";
		this.st.executeUpdate(sql);
	}
	
	public void createUser(User u) throws SQLException {
		String sql = "INSERT INTO Users (nome, cognome, anni, email) "
				+ "VALUES ('"+u.getNome()+"', '"+u.getCognome()+"', "+u.getAnni()+", '"+u.getEmail()+"')";
		this.st.executeUpdate(sql);
		System.out.println("Utente creato!!!");
	}
	
	public User findUser(Integer id) throws SQLException {
		User utente = null;
		String sql = "SELECT * FROM Users WHERE id_user = " + id;
		ResultSet rs = this.st.executeQuery(sql);
		if(rs.next()) {
			Long id_user = rs.getLong("id_user");
			String nome = rs.getString("nome");
			String cognome = rs.getString("cognome");
			int anni = rs.getInt("anni");
			String email = rs.getString("email");
			utente = new User(id_user, nome, cognome, anni, email);
		}
		return utente;
	}
	
	public void updateUser(User u) throws SQLException {
		String sql = "UPDATE Users "
					+"SET nome='"+u.getNome()+"', cognome='"+u.getCognome()+"', "
					+ "anni="+u.getAnni()+", email='"+u.getEmail()+"' "
					+"WHERE id_user = " + u.getId();
		this.st.executeUpdate(sql);
		System.out.println("Utente modificato!!!");
	}
	
	public void deleteUser(User u) throws SQLException {
		String sql = "DELETE FROM Users WHERE id_user = " + u.getId();
		this.st.executeUpdate(sql);
		System.out.println("Utente eliminato!!!");
	}
	
	public ArrayList<User> findAllUsers() throws SQLException {
		ArrayList<User> users = new ArrayList<User>();
		String sql = "SELECT * FROM Users";
		ResultSet rs = this.st.executeQuery(sql);
		while(rs.next()) {
			Long id_user = rs.getLong("id_user");
			String nome = rs.getString("nome");
			String cognome = rs.getString("cognome");
			int anni = rs.getInt("anni");
			String email = rs.getString("email");
			User utente = new User(id_user, nome, cognome, anni, email);
			users.add(utente);
		}
		return users;
	}

}
