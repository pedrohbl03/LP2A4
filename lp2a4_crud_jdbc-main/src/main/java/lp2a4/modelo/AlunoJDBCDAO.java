package lp2a4.modelo;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Implementação do DAO utilizando properties. Não serve para uso em produção, 
 * apenas para um teste por vez, pois não possui mecanismos que garantam a integridade
 * em caso de race conditions. O intuito é que com o avançar do curso os dados fiquem
 * em um banco.
 * 
 * @author diego
 *
 */
public class AlunoJDBCDAO implements AlunoDAO {

	@Override
	public boolean create(AlunoPOJO aluno) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO PESSOA (matricula, nome, endereco, dataIngresso, dataConclusao) VALUES (?,?,?,?,?);";
		PreparedStatement statement;
				
		try {
			statement = AlunoJDBCDAO.mySQLConnection().prepareStatement(query);
			statement.setString(1, aluno.getMatricula());
			statement.setString(2, aluno.getNome());
			statement.setString(3, aluno.getEndereco());
			statement.setString(4, aluno.getDataIngresso().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString());
			statement.setString(5, aluno.getDataConclusao().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString());
			
			statement.execute();
			
			statement.close();
			
			AlunoJDBCDAO.mySQLConnection().close();
			
			return true;
		}
		catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());

			return false;
		}
		
	}

	@Override
	public AlunoPOJO retrieve(String matricula) {
		// TODO Auto-generated method stub
		String query = "SELECT matricula,nome,endereco,dataIngresso,dataConclusao FROM PESSOA WHERE matricula = ?;";
		PreparedStatement statement;
		
		try {
			AlunoPOJO aluno = new AlunoPOJO();
			statement = mySQLConnection().prepareStatement(query);
			
			statement.setString(1, matricula);
			ResultSet result = statement.executeQuery();
			
			if (result.next()) {
				String nome = result.getString("nome");
				String endereco = result.getString("endereco");
				String dataIngresso = result.getString("dataIngresso");
				String dataConclusao = result.getString("dataConclusao");
				aluno.setMatricula(matricula);
				aluno.setNome(nome);
				aluno.setEndereco(endereco);
				aluno.setDataIngresso(LocalDate.parse(dataIngresso, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				aluno.setDataConclusao(LocalDate.parse(dataConclusao, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			}
			
			statement.close();
			AlunoJDBCDAO.mySQLConnection().close();
			
			return aluno;
		} 
		catch(Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
			
			return null;
		}
	}

	@Override
	public boolean update(AlunoPOJO aluno) {
		// TODO Auto-generated method stub
		String query = "UPDATE PESSOA SET matricula = ?, nome = ?, endereco = ?, dataIngresso = ?, dataConclusao = ? WHERE matricula = ?;";
		PreparedStatement statement;
		
		try {
			statement = AlunoJDBCDAO.mySQLConnection().prepareStatement(query);
			statement.setString(1, aluno.getMatricula());
			statement.setString(2, aluno.getNome());
			statement.setString(3, aluno.getEndereco());
			statement.setString(4, aluno.getDataIngresso().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString());
			statement.setString(5, aluno.getDataConclusao().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString());
			statement.setString(6, aluno.getMatricula());

			
			statement.execute();
			
			statement.close();
			
			AlunoJDBCDAO.mySQLConnection().close();
			
			return true;
		}
		catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());

			return false;
		}
	}

	@Override
	public boolean delete(String matricula) {
		// TODO Auto-generated method stub
		String query = "DELETE FROM PESSOA WHERE matricula = ?;";
		PreparedStatement statement;
		
		try {
			statement = AlunoJDBCDAO.mySQLConnection().prepareStatement(query);
			statement.setString(1, matricula);
			statement.execute();
			
			statement.close();
			AlunoJDBCDAO.mySQLConnection().close();
			return true;
		} catch (Exception ex) {
			
			System.out.println("ERROR: " + ex.getMessage());
			
			return false;
		}
	}
	
	
	public static Connection mySQLConnection() {
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/PESSOAS",
					"root",
					"Goltam-123"
			);
			
			return connect;
		} 
		catch(Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
			
			return null;
		}
	}
	
	
}
