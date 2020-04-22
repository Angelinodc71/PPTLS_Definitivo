import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import sprites.JugadaBase;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {
    private Scene scene;
    Server server = new Server();
    private GraphicsContext gc;
    public Client client = new Client();
    @FXML
    Label lblInfo;
    @FXML
    Canvas mainCanvas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(url);
        System.out.println(resourceBundle.getString("key1"));



        gc = mainCanvas.getGraphicsContext2D();


    }

    public void setScene(Scene sc) {
        scene = sc;

        JugadaBase piedra = new JugadaBase(new Image("images/0.png"));
        JugadaBase papel = new JugadaBase(new Image("images/1.png"));
        JugadaBase tijeras = new JugadaBase(new Image("images/2.png"));
        JugadaBase lagarto = new JugadaBase(new Image("images/3.png"));
        JugadaBase spock = new JugadaBase(new Image("images/4.png"));

        piedra.setPosX(200);
        papel.setPosX(336);
        tijeras.setPosX(472);
        lagarto.setPosX(608);
        spock.setPosX(744);

        piedra.render(gc);
        papel.render(gc);
        tijeras.render(gc);
        lagarto.render(gc);
        spock.render(gc);


        scene.setOnMouseClicked(mouseEvent -> {
            Point2D point = new Point2D(mouseEvent.getX(),mouseEvent.getY());
            try {

                if(piedra.isClicked(point)) {
                    client.setOpcion(0);
                }
                if(papel.isClicked(point)) {
                    client.setOpcion(1);
                }
                if(tijeras.isClicked(point)) {
                    client.setOpcion(2);
                }
                if(lagarto.isClicked(point)) {
                    client.setOpcion(3);
                }
                if(spock.isClicked(point)) {
                    client.setOpcion(4);
                }

                client.jugar();

//                gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());


//                if (server.getJugadaServer()==0) piedra.setPosX(572); piedra.render(gc);
//                if (server.getJugadaServer()==1) papel.setPosX(572); papel.render(gc);
//                if (server.getJugadaServer()==2) tijeras.setPosX(572); tijeras.render(gc);
//                if (server.getJugadaServer()==3) lagarto.setPosX(572); lagarto.render(gc);
//                if (server.getJugadaServer()==4) spock.setPosX(572); spock.render(gc);


            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

//        if (client.getOpcion()!=-1) piedra.clear(gc);
//        if (client.getOpcion()==0)  piedra.setPosX(372); piedra.render(gc);
//        if (client.getOpcion()==1)  papel.setPosX(372); papel.render(gc);
//        if (client.getOpcion()==2)  tijeras.setPosX(372); tijeras.render(gc);
//        if (client.getOpcion()==3)  lagarto.setPosX(372); lagarto.render(gc);
//        if (client.getOpcion()==4)  spock.setPosX(372); spock.render(gc);


    }
}
