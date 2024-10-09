import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class Cliente extends Application {
    private BufferedReader entrada;
    private PrintWriter escritor;
    private TextArea mensajesArea = new TextArea();

    @Override
    public void start(Stage stage) {
        BorderPane layout = new BorderPane();

        mensajesArea.setEditable(false);
        layout.setCenter(new ScrollPane(mensajesArea));

        TextField entradaTexto = new TextField();
        entradaTexto.setOnAction(event -> {
            escritor.println(entradaTexto.getText());
            entradaTexto.clear();
        });

        layout.setBottom(entradaTexto);

        Scene scene = new Scene(layout, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Cliente de Chat");
        stage.show();

        new Thread(this::conectarServidor).start();
    }

    private void conectarServidor() {
        try (Socket socket = new Socket("localhost", 12345)) {
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            escritor = new PrintWriter(socket.getOutputStream(), true);

            String mensaje;
            while ((mensaje = entrada.readLine()) != null) {
                mensajesArea.appendText(mensaje + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
