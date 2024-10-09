import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LibraryManager {
    private ObservableList<Book> books;

    public LibraryManager() {
        books = FXCollections.observableArrayList();
    }

    public ObservableList<Book> getBooks() {
        return books;
    }

    public void addBook(String title, String author, int year) {
        books.add(new Book(title, author, year));
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public ObservableList<Book> searchBooks(String title) {
        ObservableList<Book> searchResults = FXCollections.observableArrayList();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                searchResults.add(book);
            }
        }
        return searchResults;
    }
}
