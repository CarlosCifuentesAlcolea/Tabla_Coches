/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author cifue
 */
public class Main extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/vista/MenuVista.fxml"));
        Pane root = (Pane) loader.load();
        
        Scene scene = new Scene(root);
        Image ico = new Image("images/coche.png");
        primaryStage.getIcons().add(ico);
        primaryStage.setTitle("Servicio coches");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
