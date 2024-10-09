import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {
    private LibraryManager libraryManager = new LibraryManager();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gestor de Biblioteca");

        // Table to display books
        TableView<Book> tableView = new TableView<>();
        TableColumn<Book, String> titleColumn = new TableColumn<>("Título");
        titleColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTitle()));
        TableColumn<Book, String> authorColumn = new TableColumn<>("Autor");
        authorColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getAuthor()));
        TableColumn<Book, Integer> yearColumn = new TableColumn<>("Año");
        yearColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getYear()));
        tableView.getColumns().addAll(titleColumn, authorColumn, yearColumn);

        // Input fields for new books
        TextField titleInput = new TextField();
        titleInput.setPromptText("Título");
        TextField authorInput = new TextField();
        authorInput.setPromptText("Autor");
        TextField yearInput = new TextField();
        yearInput.setPromptText("Año");

        // Buttons
        Button addButton = new Button("Agregar libro");
        addButton.setOnAction(e -> {
            String title = titleInput.getText();
            String author = authorInput.getText();
            int year = Integer.parseInt(yearInput.getText());
            libraryManager.addBook(title, author, year);
            tableView.setItems(libraryManager.getBooks());
        });

        Button removeButton = new Button("Eliminar libro");
        removeButton.setOnAction(e -> {
            Book selectedBook = tableView.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                libraryManager.removeBook(selectedBook);
                tableView.setItems(libraryManager.getBooks());
            }
        });

        // Search functionality
        TextField searchInput = new TextField();
        searchInput.setPromptText("Buscar por título");
        Button searchButton = new Button("Buscar");
        searchButton.setOnAction(e -> {
            String searchText = searchInput.getText();
            ObservableList<Book> searchResults = libraryManager.searchBooks(searchText);
            tableView.setItems(searchResults);
        });

        // Layout
        HBox inputLayout = new HBox(10, titleInput, authorInput, yearInput, addButton, removeButton);
        inputLayout.setPadding(new Insets(10));

        HBox searchLayout = new HBox(10, searchInput, searchButton);
        searchLayout.setPadding(new Insets(10));

        VBox layout = new VBox(10, inputLayout, searchLayout, tableView);
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
