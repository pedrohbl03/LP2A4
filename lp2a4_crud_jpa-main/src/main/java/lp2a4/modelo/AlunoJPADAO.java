package lp2a4.modelo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Implementação do DAO utilizando properties. Não serve para uso em produção, 
 * apenas para um teste por vez, pois não possui mecanismos que garantam a integridade
 * em caso de race conditions. O intuito é que com o avançar do curso os dados fiquem
 * em um banco.
 * 
 * @author diego
 *
 */
public class AlunoJPADAO implements AlunoDAO {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	EntityManager em = emf.createEntityManager();
	
	@Override
	public boolean create(AlunoPOJO aluno) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.persist(aluno);
		em.getTransaction().commit();
		
		return true;
	}

	@Override
	public AlunoPOJO retrieve(String matricula) {
		AlunoPOJO aluno = em.find(AlunoPOJO.class, matricula);
		
		return aluno;
	}

	@Override
	public boolean update(AlunoPOJO aluno) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.merge(aluno);
		em.getTransaction().commit();
		
		return true;
	}

	@Override
	public boolean delete(String matricula) {
		// TODO Auto-generated method stub
		AlunoPOJO aluno = new AlunoPOJO();
		aluno.setMatricula(matricula);
		
		em.getTransaction().begin();
		em.remove(em.merge(aluno));
		em.getTransaction().commit();
		
		return true;
	}
}
