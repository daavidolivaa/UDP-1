package es.etg.dam.mensajes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Enviar implements Runnable {

    private final DatagramSocket socket;
    private final InetAddress destino;
    private final int puertoDestino;
    private final String mensajePrompt;

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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}