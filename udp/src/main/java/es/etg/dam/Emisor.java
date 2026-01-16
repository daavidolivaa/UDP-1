package es.etg.dam;

import java.net.DatagramSocket;
import java.net.InetAddress;

public class Emisor {

    private final static int PUERTO_ENVIO = 8081;
    private final static int PUERTO_ESCUCHA = 8080;

    private final static String HOST = "localhost";
    private final static String MENSAJE_PROMPT = "Escribe un mensaje: ";

    public static void main(String[] args) throws Exception {

        DatagramSocket socket = new DatagramSocket(PUERTO_ESCUCHA);
        InetAddress destino = InetAddress.getByName(HOST);

        Recibir recibir = new Recibir(socket, MENSAJE_PROMPT);
        Enviar enviar = new Enviar(socket, destino, PUERTO_ENVIO, MENSAJE_PROMPT);

        recibir.start();
        enviar.start();
    }
}
