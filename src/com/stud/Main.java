package com.stud;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class).buildSessionFactory();

		Session session = sessionFactory.getCurrentSession();

		try {
			session.beginTransaction();
			// session.save(new Student("mounir", "houhou", "mhouho@hotmail.com"));
//			Student s = session.get(Student.class, 2);
//
//			if (null != s) {
//				System.out.println("Student..");
//				System.out.println(s);
//			} else {
//				System.out.println("Student not found!!");
//			}
			 System.out.println("student details: ");
			 List<Student> students = session.createQuery("select s from Student s where s.firstName like 's%'")
			 .getResultList();
			 for (Student s : students) {
			 System.out.println(s);
			 }

			// Student student = session.get(Student.class, 1);
			// student.setFirstName("Samah");
			// session.createQuery("update Student set email='aaaa@hotmail.com' where
			// firstName='samah'").executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				session.close();
				sessionFactory.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
