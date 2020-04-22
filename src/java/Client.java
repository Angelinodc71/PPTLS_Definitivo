import java.io.IOException;
import java.net.*;

public class Client{
    private static volatile int opcion = -1;
    public static void main(String[] args) throws IOException, InterruptedException {

    }

    public void jugar() throws IOException, InterruptedException {

        InetAddress adrecaDesti = InetAddress.getByName("localhost");
        int portDesti = 5555;

        System.out.println(opcion+" client");
        byte[] missatge = String.valueOf(getOpcion()).getBytes();

        DatagramPacket packet = new DatagramPacket(missatge,
                missatge.length,
                adrecaDesti,
                portDesti);
        //creació d'un sòcol temporal amb el qual realitzar l'enviament
        DatagramSocket socket = new DatagramSocket();

        //Enviament del missatge
        socket.send(packet);

    }


    public int getOpcion() {
        return opcion;
    }

    public void setOpcion(int opcion) {
        Client.opcion = opcion;
    }
}
