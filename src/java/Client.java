import java.io.IOException;
import java.net.*;

public class Client{
    private static volatile int opcion = -1;
    private int opcionServer = -1;
    private String ganador = "";
    int resultado = -1;

    public static Client client = new Client();

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


        DatagramPacket packet2 = new DatagramPacket(missatge, missatge.length);
        socket.receive(packet2);


        opcionServer = Integer.parseInt((new String(packet2.getData(), 0, packet2.getLength())));
        comprobar(opcionServer,getOpcion());

        System.out.println("Server "+opcionServer);

        if (client.getResultado()==1)ganador="Gana Server";
        if (client.getResultado()==0)ganador="Gana Cliente";
        if (client.getResultado()==2)ganador="Empate";

        System.out.println(ganador);

    }

    public static void comprobar(int jugadaServer, int jugadaClient){
        //piedra
        if (jugadaServer==0 & jugadaClient==2 | jugadaServer==0 & jugadaClient==3) {
            System.out.println("Gana Server");
            client.setResultado(1);
            return;
        }
        if (jugadaClient==0 & jugadaServer==2 | jugadaClient==0 & jugadaServer==3) {
            System.out.println("Gana Cliente");
            client.setResultado(0);
            return;
        }

        //papel
        if (jugadaServer==1 & jugadaClient==0 | jugadaServer==1 & jugadaClient==4) {
            System.out.println("Gana Server");
            client.setResultado(1);
            return;
        }
        if (jugadaClient==1 & jugadaServer==0 | jugadaClient==1 & jugadaServer==4) {
            System.out.println("Gana Cliente");
            client.setResultado(0);
            return;
        }

        //tijeras
        if (jugadaServer==2 & jugadaClient==1 | jugadaServer==2 & jugadaClient==3) {
            System.out.println("Gana Server");
            client.setResultado(1);
            return;
        }
        if (jugadaClient==2 & jugadaServer==1 | jugadaClient==2 & jugadaServer==3) {
            System.out.println("Gana Cliente");
            client.setResultado(0);
            return;
        }

        //lagarto
        if (jugadaServer==3 & jugadaClient==4 | jugadaServer==3 & jugadaClient==1) {
            System.out.println("Gana Server");
            client.setResultado(1);
            return;
        }
        if (jugadaClient==3 & jugadaServer==4 | jugadaClient==3 & jugadaServer==1) {
            System.out.println("Gana Cliente");
            client.setResultado(0);
            return;
        }

        //spock
        if (jugadaServer==4 & jugadaClient==0 | jugadaServer==4 & jugadaClient==2) {
            System.out.println("Gana Server");
            client.setResultado(1);
            return;
        }
        if (jugadaClient==4 & jugadaServer==0 | jugadaClient==4 & jugadaServer==2) {
            System.out.println("Gana Cliente");
            client.setResultado(0);
            return;
        }

        //empate
        else {
            System.out.println("empate");
            client.setResultado(2);
        }
    }
    public int getOpcionServer() {
        return opcionServer;
    }

    public int getOpcion() {
        return opcion;
    }

    public void setOpcion(int opcion) {
        Client.opcion = opcion;
    }

    public int getResultado() {
        return resultado;
    }

    public String getGanador() {
        return ganador;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }
}
