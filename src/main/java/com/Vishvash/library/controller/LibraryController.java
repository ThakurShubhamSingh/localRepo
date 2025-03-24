package com.Vishvash.library.controller;

import com.Vishvash.library.entity.Book;
import com.Vishvash.library.service.LibraryService;

import java.util.List;

public class LibraryController
{
    private final LibraryService libraryService = new LibraryService();

    public void addBook(String title, String author, double price) {
        libraryService.addBook(title, author, price);
    }

    public List<Book> showAllBooks() {
        return libraryService.getAllBooks();
    }

    public void updateBook(int id, String newTitle, String newAuthor, double newPrice) {
        libraryService.updateBook(id, newTitle, newAuthor, newPrice);
    }

    public void deleteBook(int id) {
        libraryService.deleteBook(id);
    }

    public List<Book> searchBooksByTitle(String title) {
        return libraryService.searchBooksByTitle(title);
    }

    public List<Book> searchBooksByAuthor(String author) {
        return libraryService.searchBooksByAuthor(author);
    }

    public List<Book> sortBooks(String sortBy) {
        return libraryService.getAllBooksSorted(sortBy);
    }

    public void exportBooksToCsv(String filePath) {
        libraryService.exportBooksToCsv(filePath);
    }
}
