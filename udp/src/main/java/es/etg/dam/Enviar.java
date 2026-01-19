package es.etg.dam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Enviar extends Thread {

    private final DatagramSocket socket;
    private final InetAddress destino;
    private final int puertoDestino;
    private final String mensajePrompt;

    public Enviar(DatagramSocket socket, InetAddress destino, int puertoDestino, String mensajePrompt) {
        this.socket = socket;
        this.destino = destino;
        this.puertoDestino = puertoDestino;
        this.mensajePrompt = mensajePrompt;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.print(mensajePrompt);
                String texto = br.readLine();
                byte[] mensaje = texto.getBytes();
                DatagramPacket msg = new DatagramPacket(mensaje, mensaje.length, destino, puertoDestino);
                socket.send(msg);
            }
        } catch (Exception e) {
            new RuntimeException(e);
        }
    }
}
