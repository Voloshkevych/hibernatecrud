package com.homework.service;

import com.homework.entity.Author;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class AuthorService {

  private static SessionFactory sessionFactory;

  static {
    sessionFactory = new Configuration().configure().buildSessionFactory();
  }

  public Author createAuthor(Author author) {
    Session session = sessionFactory.openSession();
    Transaction transaction = null;
    try {
      transaction = session.beginTransaction();
      session.save(author);
      transaction.commit();
      return author;
    } catch (RuntimeException e) {
      if (transaction != null) transaction.rollback();
      throw e;
    } finally {
      session.close();
    }
  }

  public Author getAuthor(int id) {
    Session session = sessionFactory.openSession();
    try {
      return session.get(Author.class, id);
    } finally {
      session.close();
    }
  }

  public List<Author> getAllAuthors() {
    Session session = sessionFactory.openSession();
    try {
      return session.createQuery("from Author", Author.class).list();
    } finally {
      session.close();
    }
  }

  public Author updateAuthor(Author author) {
    Session session = sessionFactory.openSession();
    Transaction transaction = null;
    try {
      transaction = session.beginTransaction();
      session.update(author);
      transaction.commit();
      return author;
    } catch (RuntimeException e) {
      if (transaction != null) transaction.rollback();
      throw e;
    } finally {
      session.close();
    }
  }

  public void deleteAuthor(Author author) {
    Session session = sessionFactory.openSession();
    Transaction transaction = null;
    try {
      transaction = session.beginTransaction();
      session.delete(author);
      transaction.commit();
    } catch (RuntimeException e) {
      if (transaction != null) transaction.rollback();
      throw e;
    } finally {
      session.close();
    }
  }
  public void shutdown() {
    if (sessionFactory != null) {
      sessionFactory.close();
    }
  }
}
