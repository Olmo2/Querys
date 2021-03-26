package com.olmo.Querys;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.olmo.Querys.entity.Departamento;
import com.olmo.Querys.service.HibernateUtil;

/**
 * Hello world!
 *
 */
public class App {

	// Obtenemos el SessionFactory
	static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	// Abrimos la sesión mediante el SessionFactory
	static Session session = sessionFactory.openSession();

	public static void main(String[] args) {

		paginacion();
	}

	public static void ejemploQuery() {

		Query query = session.createQuery("SELECT d FROM Departamento d ", Departamento.class);
		List<Departamento> depts = query.list();
		for (Departamento dept : depts) {
			System.out.println(dept.getNombre());
		}
		session.close();
		sessionFactory.close();
	}

	public static void listaObj() {
		Query query = session.createQuery("SELECT d.id,d.nombre FROM Departamento d");
		List<Object[]> listDatos = query.list();
		for (Object[] datos : listDatos) {
			System.out.println(datos[0] + "--" + datos[1]);
		}
		
		

	}

	private static void uniqueResult() {
		
		Query query =session.createQuery("SELECT d FROM Departamento d WHERE id=1");
				 Departamento dept =(Departamento)query.uniqueResult();
				if(dept!=null)
				 System.out.println("Dept con Id 1="+
						 dept.getNombre());
				else
				 System.out.println("Error: no existe el dept buscado");
				 session.close();
				 sessionFactory.close();
		
	}
	
	public static void paginacion() {
		int tamanyoPagina =2;
		int paginaAMostrar =0;

		 Query query =session.createQuery("SELECT d FROM Departamento d Order By d.id");
		 query.setMaxResults(tamanyoPagina);
		 query.setFirstResult(paginaAMostrar * tamanyoPagina);
		 List<Departamento> depts =query.list();

		for(Departamento dept : depts){
		 System.out.println(dept.getNombre());
		}
		 
		/*Numero de páginas*/
		 long numTotalObjetos =(Long)session.createQuery("SELECT count(*) FROM Departamento d").uniqueResult();
		 int numPaginas =(int)Math.ceil((double)numTotalObjetos /(double)tamanyoPagina);
		 
		 
		 session.close();
		 sessionFactory.close();
	}
}
