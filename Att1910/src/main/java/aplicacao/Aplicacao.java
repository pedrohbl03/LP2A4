package aplicacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import lp2a4.modelo.Estudante;

public class Aplicacao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Estudante estudante1 = new Estudante();
		
		estudante1.setMatricula("SP3073785");
		estudante1.setEmail("pedro.beserra@aluno.ifsp.edu.br");
		estudante1.setNome("Pedro Lima");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
		EntityManager em = emf.createEntityManager();
		
		
		em.getTransaction().begin();
		em.persist(estudante1);
		em.getTransaction().commit();
				
		
		Estudante estudanteFound = em.find(Estudante.class, "SP3073785");
		System.out.println("Estudante encontrado" + estudanteFound);
		
		estudante1.setEmail("lorem@ifsp.edu.br");
		em.getTransaction().begin();
		em.persist(estudante1);
		em.getTransaction().commit();
				
		
		Estudante estudanteUpdated = em.find(Estudante.class, "SP3073785");
		System.out.println("Estudante atualizado" + estudanteUpdated);
		
		
		Estudante estudanteDelete = new Estudante();
		estudanteDelete.setMatricula("SP123456");
		
		Estudante estudanteDeleteAtached = em.merge(estudanteDelete);
		
		em.getTransaction().begin();
		em.remove(estudanteDeleteAtached);
		em.getTransaction().commit();
		
		
		// ultima coisa a se fazer =)
		em.close();
		emf.close();
		
	}

}
