import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sprites.JugadaBase;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {
    private Scene scene;
    private GraphicsContext gc;
    public Client client = new Client();
    @FXML
    Label lblInfo;
    @FXML
    Canvas mainCanvas;

    JugadaBase piedra;
    JugadaBase papel;
    JugadaBase tijeras;
    JugadaBase lagarto;
    JugadaBase spock;

    JugadaBase vs;
    private String texto;
    String ganador;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(url);
        System.out.println(resourceBundle.getString("key1"));



        gc = mainCanvas.getGraphicsContext2D();

        piedra = new JugadaBase(new Image("images/0.png"));
        papel = new JugadaBase(new Image("images/1.png"));
        tijeras = new JugadaBase(new Image("images/2.png"));
        lagarto = new JugadaBase(new Image("images/3.png"));
        spock = new JugadaBase(new Image("images/4.png"));

        vs = new JugadaBase(new Image("images/vs.png"));

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

    }

    public void setScene(Scene sc) {
        scene = sc;




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

                gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

                if (client.getOpcion()==0)  {piedra.setPosX(372); piedra.render(gc);}
                if (client.getOpcion()==1)  {papel.setPosX(372); papel.render(gc);}
                if (client.getOpcion()==2)  {tijeras.setPosX(372); tijeras.render(gc);}
                if (client.getOpcion()==3)  {lagarto.setPosX(372); lagarto.render(gc);}
                if (client.getOpcion()==4)  {spock.setPosX(372); spock.render(gc);}
                System.out.println(piedra.getHeight());
                System.out.println(vs.getHeight());


                System.out.println(client.getGanador());

                gc.setFont(new Font("Arial",72));


                gc.setFill( Color.WHITE );
                gc.fillText( client.getGanador(), 320, 300 );

                gc.setStroke( Color.BLACK );
                gc.strokeText( client.getGanador(), 320, 300 );

                vs.setPosX(486);
                vs.render(gc);

                if (client.getOpcionServer()==0) {piedra.setPosX(600); piedra.render(gc);}
                if (client.getOpcionServer()==1) {papel.setPosX(600); papel.render(gc);}
                if (client.getOpcionServer()==2) {tijeras.setPosX(600); tijeras.render(gc);}
                if (client.getOpcionServer()==3) {lagarto.setPosX(600); lagarto.render(gc);}
                if (client.getOpcionServer()==4) {spock.setPosX(600); spock.render(gc);}

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
