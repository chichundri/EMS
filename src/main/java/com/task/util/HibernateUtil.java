package com.task.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

	private HibernateUtil() {
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {

			// loads configuration and mappings
			Configuration configuration = new Configuration().configure("resources/hibernate.cfg.xml");
			configuration.addAnnotatedClass(com.task.model.EmployeeEntity.class);
			configuration.addAnnotatedClass(com.task.model.DepartmentEntity.class);
			// since version 4.x onwards
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
			      .applySettings(configuration.getProperties()).build();

			// builds a session factory from the service registry
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
		return sessionFactory;
	}

	public static void main(String[] args) {
		// how to use
		// SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		// Session session = sessionFactory.openSession();

		// EmployeeDAOImpl impl = new EmployeeDAOImpl();
		// List<Employee> empList = impl.getAllEmployees();
		// for(Object empObj : empList){
		// Employee e = (Employee)empObj;
		// System.out.println(e.getFirstName());
		// }

		// System.out.println(session);
		// session.beginTransaction();
		// session.save(myObject);
	}
}