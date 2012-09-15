package com.edicsem.pe.sie.model.dao.impl;



import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.edicsem.pe.sie.model.dao.LoginDAO;

@Stateless
public class LoginDAOImpl implements LoginDAO{


	//Acuerdate que es EJB
	//siempre tienes que hacer este persistence ya que es tu conexion, asi es como trabaja EJB y Jboss.
	//esa metodologia esta bien pero en clase, y esto es la vida real pero la diferencia que es con EJB xD!
//compiallo y me avisas com te resulto
	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.LoginDAO#validacionLogin(java.lang.String, java.lang.String)
	 */
	//le quitan el Override porq nosotros no podemos tener esto ya que puede que el proyecto se caiga en el trascurso del tiempo
	// asi que lo eliminas

	public boolean validacionLogin(String usuario, String contrasena) {
		boolean estado = false;
		try{//como se esta trabajando es de la siguiente manenra
			/*EntityManager em = Database.getInstance().getFactory().createEntityManager();
			em.getTransaction().begin(); 
			Query q = em.createQuery("SELECT u FROM EmpleadoSie u WHERE u.usuario = :username AND u.contrasena = :password " );
			q.setParameter("username",usuario);
			q.setParameter("password",contrasena);*/
			Query q = em.createQuery("SELECT u FROM EmpleadoSie u WHERE u.usuario = :username AND u.contrasena = :password " );
			q.setParameter("username",usuario);
			q.setParameter("password",contrasena);
			if(q.getSingleResult()!=null){
				estado = true;
			};
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return estado;
	}
		
	//cuando hagas un implement tienes que hacerlo de la siguiente manera.
	

	
	
		
	}
	
	
	




	
	
	

