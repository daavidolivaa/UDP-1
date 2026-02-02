package es.etg.dam.usuarios;

import java.net.DatagramSocket;
import java.net.InetAddress;

import es.etg.dam.mensajes.Enviar;
import es.etg.dam.mensajes.Recibir;

public class Usuario_1 {

    public final static int PUERTO_ENVIO = 8081;
    public final static int PUERTO_ESCUCHA = 8080;

    public final static String HOST = "localhost";
    public final static String MENSAJE_PROMPT = "Escribe un mensaje: ";

    public static void main(String[] args) throws Exception {

        DatagramSocket socket = new DatagramSocket(PUERTO_ESCUCHA);
        InetAddress destino = InetAddress.getByName(HOST);

        Thread recibir = new Thread(new Recibir(socket, MENSAJE_PROMPT));
        recibir.start();

        Thread enviar = new Thread(new Enviar(socket, destino, PUERTO_ENVIO, MENSAJE_PROMPT));
        enviar.start();

    }
}