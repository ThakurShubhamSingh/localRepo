
import com.Vishvash.library.controller.LibraryController;
import com.Vishvash.library.entity.Book;
import com.Vishvash.library.exception.ExceptionHandler;

import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        LibraryController controller = new LibraryController();
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\nLibrary Management System");
                System.out.println("1. Add Book");
                System.out.println("2. Show All Books");
                System.out.println("3. Update Book");
                System.out.println("4. Delete Book");
                System.out.println("5. Search Book");
                System.out.println("6. Sort Books");
                System.out.println("7. Export Books to CSV");
                System.out.println("8. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter title: ");
                        sc.nextLine();
                        String title = sc.nextLine();
                        System.out.print("Enter author: ");
                        String author = sc.nextLine();
                        System.out.print("Enter the price: ");
                        double price = sc.nextDouble();

                        controller.addBook(title, author, price);
                        break;

                    case 2:
                        List<Book> books = controller.showAllBooks();
                        books.forEach(System.out::println);
                        break;

                    case 3:
                        System.out.print("Enter Book ID to Update: ");
                        int updateId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter new title: ");
                        String newTitle = sc.nextLine();
                        System.out.print("Enter new author: ");
                        String newAuthor = sc.nextLine();
                        System.out.print("Enter new price: ");
                        double newPrice = sc.nextDouble();

                        controller.updateBook(updateId, newTitle, newAuthor, newPrice);
                        break;

                    case 4:
                        System.out.print("Enter Book ID to Delete: ");
                        int deleteId = sc.nextInt();
                        controller.deleteBook(deleteId);
                        break;

                    case 5:
                        System.out.println("Search by: 1. Title  2. Author");
                        int searchChoice = sc.nextInt();
                        sc.nextLine();

                        if (searchChoice == 1) {
                            System.out.print("Enter Title Keyword: ");
                            String searchTitle = sc.nextLine();
                            List<Book> booksByTitle = controller.searchBooksByTitle(searchTitle);
                            booksByTitle.forEach(System.out::println);
                        } else if (searchChoice == 2) {
                            System.out.print("Enter Author Keyword: ");
                            String searchAuthor = sc.nextLine();
                            List<Book> booksByAuthor = controller.searchBooksByAuthor(searchAuthor);
                            booksByAuthor.forEach(System.out::println);
                        } else {
                            System.out.println("Invalid choice!");
                        }
                        break;

                    case 6:
                        System.out.println("Sort by: 1. Title  2. Price");
                        int sortChoice = sc.nextInt();
                        sc.nextLine();

                        String sortBy = (sortChoice == 1) ? "title" : "price";
                        List<Book> sortedBooks = controller.sortBooks(sortBy);
                        sortedBooks.forEach(System.out::println);
                        break;

                    case 7:
                        sc.nextLine();
                        System.out.print("Enter file path (e.g., D:/books.csv): ");
                        String filePath = sc.nextLine();
                        controller.exportBooksToCsv(filePath);
                        break;

                    case 8:
                        System.out.println("Exiting...");
                        sc.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            } catch (Exception e) {
                ExceptionHandler.handleException(e);
            }
        }
    }
}
