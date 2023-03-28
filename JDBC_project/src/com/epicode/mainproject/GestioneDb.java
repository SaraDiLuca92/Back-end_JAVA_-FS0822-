package com.epicode.mainproject;

import java.sql.SQLException;
import java.util.ArrayList;

import com.epicode.connector.DBConnection;
import com.epicode.model.User;

public class GestioneDb {

	public static void main(String[] args) {

		try {
			DBConnection db = new DBConnection();
			
			// Aggiunge un utente nel DB
			// User utente = new User("Mario", "Rossi", 25, "m.rossi@example.com");
			// User utente = new User("Giuseppe", "Verdi", 32, "g.verdi@example.com");
			// User utente = new User("Francesca", "Neri", 29, "f.neri@example.com");
			// db.createUser(utente);
			
			// Legge un utente nel DB
			// User utenteLetto = db.findUser(1);
			// System.out.println(utenteLetto);
			
			// Modifica un utente nel DB
			// utenteLetto.setAnni(44);
			// db.updateUser(utenteLetto);
			
			// Elimino un utente del DB
			// db.deleteUser(utenteLetto);
			
			ArrayList<User> userList = db.findAllUsers();
			userList.forEach(u -> System.out.println(u));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
