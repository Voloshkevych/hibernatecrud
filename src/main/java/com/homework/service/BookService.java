package com.homework.service;

import com.homework.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class BookService {

  private static final SessionFactory sessionFactory;

  static {
    sessionFactory = new Configuration().configure().buildSessionFactory();
  }

  public Book createBook(Book book) {
    Session session = sessionFactory.openSession();
    Transaction transaction = null;
    try {
      transaction = session.beginTransaction();
      session.save(book);
      transaction.commit();
      return book;
    } catch (RuntimeException e) {
      if (transaction != null) transaction.rollback();
      throw e;
    } finally {
      session.close();
    }
  }

  public Book getBook(int id) {
    Session session = sessionFactory.openSession();
    try {
      return session.get(Book.class, id);
    } finally {
      session.close();
    }
  }

  public List<Book> getAllBooks() {
    Session session = sessionFactory.openSession();
    try {
      return session.createQuery("from Book", Book.class).list();
    } finally {
      session.close();
    }
  }

  public Book updateBook(Book book) {
    Session session = sessionFactory.openSession();
    Transaction transaction = null;
    try {
      transaction = session.beginTransaction();
      session.update(book);
      transaction.commit();
      return book;
    } catch (RuntimeException e) {
      if (transaction != null) transaction.rollback();
      throw e;
    } finally {
      session.close();
    }
  }

  public void deleteBook(Book book) {
    Session session = sessionFactory.openSession();
    Transaction transaction = null;
    try {
      transaction = session.beginTransaction();
      session.delete(book);
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
