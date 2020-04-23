import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    int resultado = -1;
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

            comprobar(server.getJugadaServer(),jugadaClient);

            byte[] jugadaServer = String.valueOf(server.jugadaServer).getBytes();
            DatagramPacket packetJugadaServer = new DatagramPacket(jugadaServer,
                    jugadaServer.length,
                    adrecaDesti,
                    port);
            socket.send(packetJugadaServer);

        }




    }
    public static void comprobar(int jugadaServer, int jugadaClient){
        //piedra
        if (jugadaServer==0 & jugadaClient==2 | jugadaServer==0 & jugadaClient==3) {
            System.out.println("Gana Server");
            server.setResultado(1);
            return;
        }
        if (jugadaClient==0 & jugadaServer==2 | jugadaClient==0 & jugadaServer==3) {
            System.out.println("Gana Cliente");
            server.setResultado(0);
            return;
        }

        //papel
        if (jugadaServer==1 & jugadaClient==0 | jugadaServer==1 & jugadaClient==4) {
            System.out.println("Gana Server");
            server.setResultado(1);
            return;
        }
        if (jugadaClient==1 & jugadaServer==0 | jugadaClient==1 & jugadaServer==4) {
            System.out.println("Gana Cliente");
            server.setResultado(0);
            return;
        }

        //tijeras
        if (jugadaServer==2 & jugadaClient==1 | jugadaServer==2 & jugadaClient==3) {
            System.out.println("Gana Server");
            server.setResultado(1);
            return;
        }
        if (jugadaClient==2 & jugadaServer==1 | jugadaClient==2 & jugadaServer==3) {
            System.out.println("Gana Cliente");
            server.setResultado(0);
            return;
        }

        //lagarto
        if (jugadaServer==3 & jugadaClient==4 | jugadaServer==3 & jugadaClient==1) {
            System.out.println("Gana Server");
            server.setResultado(1);
            return;
        }
        if (jugadaClient==3 & jugadaServer==4 | jugadaClient==3 & jugadaServer==1) {
            System.out.println("Gana Cliente");
            server.setResultado(0);
            return;
        }

        //spock
        if (jugadaServer==4 & jugadaClient==0 | jugadaServer==4 & jugadaClient==2) {
            System.out.println("Gana Server");
            server.setResultado(1);
            return;
        }
        if (jugadaClient==4 & jugadaServer==0 | jugadaClient==4 & jugadaServer==2) {
            System.out.println("Gana Cliente");
            server.setResultado(0);
            return;
        }

        //empate
        else {
            System.out.println("empate");
        }
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public int getJugadaServer() {
        return jugadaServer;
    }

    public void setJugadaServer(int jugadaServer) {
        this.jugadaServer = jugadaServer;
    }

}
