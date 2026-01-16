package es.etg.dam;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Recibir extends Thread {

    private final DatagramSocket socket;
    private final String mensajePrompt;
    private final static int BUFFER = 256;
    private final static String MENSAJE = "\tMensaje recibido: ";

    public Recibir(DatagramSocket socket, String mensajePrompt) {
        this.socket = socket;
        this.mensajePrompt = mensajePrompt;
    }

    @Override
    public void run() {
        try {
            while (true) {
                byte[] buffer = new byte[BUFFER];
                DatagramPacket msg = new DatagramPacket(buffer, buffer.length);
                socket.receive(msg);
                String datos = new String(msg.getData(), 0, msg.getLength());

                System.out.println();
                System.out.println(MENSAJE + datos);
                System.out.print(mensajePrompt);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
