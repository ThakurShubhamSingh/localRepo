package com.Vishvash.library.service;

import com.Vishvash.library.dao.LibraryDAO;
import com.Vishvash.library.entity.Book;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LibraryService {

    private final LibraryDAO libraryDAO = new LibraryDAO();

    // Add a book
    public void addBook(String title, String author, double price) {
        Book book = new Book(title, author, price);
        libraryDAO.saveBook(book);
    }

    // Get all books
    public List<Book> getAllBooks() {
        return libraryDAO.getAllBooks();
    }

    // Update book
    public void updateBook(int id, String newTitle, String newAuthor, double newPrice) {
        libraryDAO.updateBook(id, newTitle, newAuthor, newPrice);
    }

    // Delete book
    public void deleteBook(int id) {
        libraryDAO.deleteBook(id);
    }

    // Search books by title (case-insensitive)
    public List<Book> searchBooksByTitle(String titleKeyword) {
        List<Book> allBooks = libraryDAO.getAllBooks();
        return allBooks.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(titleKeyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Search books by author (case-insensitive)
    public List<Book> searchBooksByAuthor(String authorKeyword) {
        List<Book> allBooks = libraryDAO.getAllBooks();
        return allBooks.stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(authorKeyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Sort books by title or price
    public List<Book> getAllBooksSorted(String sortBy) {
        List<Book> allBooks = libraryDAO.getAllBooks();

        if ("title".equalsIgnoreCase(sortBy)) {
            allBooks.sort(Comparator.comparing(Book::getTitle));
        } else if ("price".equalsIgnoreCase(sortBy)) {
            allBooks.sort(Comparator.comparing(Book::getPrice));
        } else {
            throw new IllegalArgumentException("Invalid sort field: " + sortBy);
        }
        return allBooks;
    }

    public List<Book> sortBooks(String sortBy) {
        List<Book> books = libraryDAO.getAllBooks();

        switch (sortBy.toLowerCase()) {
            case "title":
                books.sort(Comparator.comparing(Book::getTitle));
                break;
            case "author":
                books.sort(Comparator.comparing(Book::getAuthor));
                break;
            case "price":
                books.sort(Comparator.comparing(Book::getPrice));
                break;
            default:
                throw new IllegalArgumentException("Invalid sort field: " + sortBy);
        }

        return books;
    }


    // Export books to CSV
    public void exportBooksToCsv(String filePath) {
        List<Book> books = libraryDAO.getAllBooks();

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("ID,Title,Author,Price\n");

            for (Book book : books) {
                writer.write(book.getId() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getPrice() + "\n");
            }

            System.out.println("✅ Books exported successfully to: " + filePath);

        } catch (IOException e) {
            System.out.println("❌ Failed to export books to file: " + filePath);
            e.printStackTrace();
        }
    }
}







