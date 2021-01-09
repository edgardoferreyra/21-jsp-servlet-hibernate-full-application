package org.studyeasy.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.studyeasy.hibernate.entity.Files;

public class FilesDAO {
	public class getFile extends Files {

	}
	SessionFactory factory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(Files.class)
			.buildSessionFactory();
	
	public void addFileDetails(Files file) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(file);
		session.getTransaction().commit();
		System.out.println(file.getFileName()+" Got added.");
	}
	public List<Files> listFiles(){
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		List<Files> files = (List<Files>)session.createQuery("from files").getResultList();
		//session.getTransaction().commit();
		return files;
	}
	public void updateInformation(int id, String label, String caption) {
		//We are updating a specific column in the database with Hibernate		
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Files file = session.get(Files.class, id);
		file.setLabel(label);
		file.setCaption(caption);
		session.getTransaction().commit();
		
	}
	public Files getFile(int fileId) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Files file = session.get(Files.class, fileId);
		session.getTransaction().commit();
		return file;
	}
	public void deleteFile(int fileId) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Files file = session.get(Files.class, fileId);
		session.delete(file);
		session.getTransaction().commit();
		
	}

}
