import java.sql.*;
import java.util.*;

public class my_crud {

	public static void main(String[] args) {
		String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		String DB_URL = "jdbc:mysql://localhost:3306/araujo_db";
		String USER = "root";  //root --> default username of XAMPP
		String PASS = ""; //blank --> default password of XAMPP
		
		try {
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			
			String query = "CREATE TABLE IF NOT EXISTS users (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(255), email VARCHAR(255), PRIMARY KEY (id))"; //users is the name of our table with colum of id, name, and email, id is our primary key.
			
			stmt.executeUpdate(query);
			
			Scanner scan = new Scanner(System.in);
			
			System.out.println("1. Adicionar Usuário");
			System.out.println("2. Ler Usuário");
			System.out.println("3. Atualizar Usuário");
			System.out.println("4. Deletar Usuário");
			
			System.out.print("Escolha: ");
			String choice = scan.nextLine();
			
			switch(choice) {
			case "1":
				//Add User
				System.out.print("Insira o seu nome: ");
				String name = scan.nextLine();
				
				System.out.print("Insira seu email: ");
				String email = scan.nextLine();
				
				query = "INSERT INTO users (name, email) VALUES ('"+name+"','"+email+"')";
				
				stmt.executeUpdate(query);
				break;
			case "2":
				//Read User
				System.out.print("Insira seu ID de usuário: ");
				int id = scan.nextInt();
				
				query = "SELECT * FROM users WHERE id ="+ id;
				
				ResultSet rs = stmt.executeQuery(query);
				
				if(rs.next()) {
					System.out.println("ID: "+rs.getInt("id"));
					System.out.println("Nome: "+rs.getString("name"));
					System.out.println("Email: "+rs.getString("email"));
				}else {
					System.out.println("Usuário não encontrado!");
				}
				
				break;
			case "3":
				System.out.print("Coloque seu ID: ");
				id = scan.nextInt();
				
				scan.nextLine(); //pular linha
				
				System.out.print("Insira seu novo nome: ");
				name = scan.nextLine();
				
				System.out.print("Insira seu novo email: ");
				email = scan.nextLine();
				
				query = "UPDATE users SET name = '"+name+"', email = '"+email+"' WHERE id = " + id;
				
				int result = stmt.executeUpdate(query);
				
				if(result > 0) {
					System.out.println("Usuário atualizado com sucesso!");
				}else {
					System.out.println("Usuário não encontrado!");
				}
				
				break;
			case "4":
				System.out.print("Coloque seu ID: ");
				id = scan.nextInt();
				query = "DELETE FROM users WHERE id = " + id;
				
				result = stmt.executeUpdate(query);
				
				if(result > 0) {
					System.out.println("Usuário deletado com sucesso!");
				}else {
					System.out.println("Usuário não encontrado!");
				}
			}
			
			stmt.close();
			conn.close();
			
		}catch(Exception e) {
			System.out.print("Error: "+e.getMessage());
			//System.out.print("Error: "+e.getMessage());
		}
	}

}
