package es.etg.dam.mensajes;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Recibir implements Runnable {

    private final DatagramSocket socket;
    private final String mensajePrompt;
    private final static int BUFFER = 256;
    private final static String MENSAJE = "\tMensaje recibido: ";

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
            throw new RuntimeException(e);
        }
    }
}