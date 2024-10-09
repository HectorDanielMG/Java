import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {
    private static Set<PrintWriter> clientes = new HashSet<>();
    private static final int PUERTO = 12345;

    public static void main(String[] args) {
        System.out.println("Servidor de chat iniciado...");
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            while (true) {
                new ManejadorCliente(servidor.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ManejadorCliente extends Thread {
        private Socket socket;
        private PrintWriter escritor;

        public ManejadorCliente(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                escritor = new PrintWriter(socket.getOutputStream(), true);

                synchronized (clientes) {
                    clientes.add(escritor);
                }

                String mensaje;
                while ((mensaje = entrada.readLine()) != null) {
                    System.out.println("Mensaje recibido: " + mensaje);
                    for (PrintWriter cliente : clientes) {
                        cliente.println(mensaje);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (escritor != null) {
                    synchronized (clientes) {
                        clientes.remove(escritor);
                    }
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
