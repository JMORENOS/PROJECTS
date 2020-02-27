
package principal;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {
    private static final int PORT=5000;
    private static students Students;

public static void main(String[] args) throws IOException {
    Students = new students();    
    ServerSocket SocketServidor= new ServerSocket(PORT);
        while(true){
        try (Socket SocketCliente = SocketServidor.accept()) {
            PrintWriter pw = new PrintWriter(SocketCliente.getOutputStream());
            pw.println("DSD 4CV3");
            pw.close();
        }
        }
        
        
        
        // TODO code application logic here
    }
}
