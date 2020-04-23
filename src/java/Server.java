import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    int jugadaServer = -1;
    public static Server server = new Server();

    public Server() {

    }

    public static void main(String[] args) throws IOException {
        int portAEscoltar = 5555;

        byte[] missatge = new byte[1024];
        InetAddress adrecaDesti = InetAddress.getByName("localhost");

        // 0 gana el cliente
        // 1 gana el servidor
        // else empate

//creació del paquet en el qual rebre les dades de 1.024 bytes com a màxim
        DatagramPacket packet = new DatagramPacket(missatge, missatge.length);
//creació d'un sòcol que escolti el port passat per paràmetre
        DatagramSocket socket = new DatagramSocket(portAEscoltar);

        while(true) {
        server.jugadaServer = (int) (Math.random() * 4);
        System.out.println("Server:"+server.getJugadaServer());

//recepció d'un paquet
            socket.receive(packet);
            int port = packet.getPort();

            int jugadaClient = Integer.parseInt((new String(packet.getData(), 0, packet.getLength())));

            System.out.println("Client:"+jugadaClient);


            byte[] jugadaServer = String.valueOf(server.jugadaServer).getBytes();
            DatagramPacket packetJugadaServer = new DatagramPacket(jugadaServer,
                    jugadaServer.length,
                    adrecaDesti,
                    port);
            socket.send(packetJugadaServer);

        }
    }




    public int getJugadaServer() {
        return jugadaServer;
    }

    public void setJugadaServer(int jugadaServer) {
        this.jugadaServer = jugadaServer;
    }

}
