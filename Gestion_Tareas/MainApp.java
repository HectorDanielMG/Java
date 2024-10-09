import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.SQLException;

public class MainApp extends Application {
    private TaskManager taskManager;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        taskManager = new TaskManager("jdbc:sqlite:tasks.db");
        primaryStage.setTitle("Gestor de Tareas");

        VBox layout = new VBox();
        ListView<Task> taskList = new ListView<>();
        TextField titleInput = new TextField();
        TextArea descriptionInput = new TextArea();
        Button addButton = new Button("Agregar Tarea");

        addButton.setOnAction(event -> {
            String title = titleInput.getText();
            String description = descriptionInput.getText();
            if (!title.isEmpty()) {
                try {
                    Task task = new Task(0, title, description, false);
                    taskManager.addTask(task);
                    taskList.getItems().add(task);
                    titleInput.clear();
                    descriptionInput.clear();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        layout.getChildren().addAll(titleInput, descriptionInput, addButton, taskList);

        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        try {
            taskManager.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
