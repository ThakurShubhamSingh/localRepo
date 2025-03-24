package com.Vishvash.library.dao;

import com.Vishvash.library.entity.Book;
import com.Vishvash.library.exception.BookNotFoundException;
import com.Vishvash.library.exception.DatabaseException;
import com.Vishvash.library.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class LibraryDAO {

    // Create a new book
    public void saveBook(Book book) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
            System.out.println("Book saved successfully!");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Read all books
    public static List<Book> getAllBooks() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Book", Book.class).list();
        }
    }

    // Update a book (now including price)
    public void updateBook(int id, String newTitle, String newAuthor, double newPrice) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Book book = session.get(Book.class, id);
            if (book != null) {
                book.setTitle(newTitle);
                book.setAuthor(newAuthor);
                book.setPrice(newPrice);   // price भी update करो
                session.update(book);
                transaction.commit();
                System.out.println("Book updated successfully!");
            } else {
                System.out.println("Book not found with ID: " + id);
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Delete a book
    public void deleteBook(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Book book = session.get(Book.class, id);
            if (book != null) {
                session.delete(book);
                transaction.commit();
                System.out.println("✅ Book deleted successfully!");
            } else {
                throw new BookNotFoundException("Book with ID " + id + " not found!");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new DatabaseException("Failed to delete book", e);
        }
    }


    public static List<Book> searchBooksByTitle(String title) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Book WHERE title LIKE :title";
            return session.createQuery(hql, Book.class)
                    .setParameter("title", "%" + title + "%")
                    .list();
        }
    }

    public static List<Book> searchBooksByAuthor(String author) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Book WHERE author LIKE :author";
            return session.createQuery(hql, Book.class)
                    .setParameter("author", "%" + author + "%")
                    .list();
        }
    }

    public static List<Book> getAllBooksSorted(String sortBy) {
        String hql = "FROM Book ORDER BY " + sortBy;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(hql, Book.class).list();
        }
    }

    public Optional<Book> getBookById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Book.class, id));
        }
    }

}
