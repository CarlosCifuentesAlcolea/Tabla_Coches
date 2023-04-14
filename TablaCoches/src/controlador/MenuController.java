/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cifue
 */
public class MenuController implements Initializable {

    @FXML
    private Button btnIntroducir;
    @FXML
    private Button btnConsultar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void introducirServicios(ActionEvent event) {
        try {
            //abrimos la vista insertar
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/InsertarServiciosVista.fxml"));
            Parent root = loader.load();
            //creamos el controlador
            InsertarServiciosController controlador = loader.getController();

            Scene scene = new Scene(root);
            Image ico = new Image("images/coche.png");
            
            Stage stage = new Stage();
            stage.getIcons().add(ico);
            stage.setTitle("Servicio coches");
            stage.setResizable(false);

            stage.setScene(scene);
            stage.show();
            
            stage.setOnCloseRequest(e -> controlador.closeVentana());
            
            Stage miStage = (Stage) this.btnIntroducir.getScene().getWindow();//se cierra la ventana principal y se abre itroducir servicios
            miStage.close();
            

        }catch(IOException ex){
             Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        }

        @FXML
        private void consultarServicios(ActionEvent event) {
            
            try {
            //abrimos la vista insertar
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/ConsultarServiciosVista.fxml"));
            Parent root = loader.load();
            //creamos el controlador
            ConsultarServiciosController controlador = loader.getController();

            Scene scene = new Scene(root);
            Image ico = new Image("images/coche.png");
            
            Stage stage = new Stage();
            stage.getIcons().add(ico);
            stage.setTitle("Servicio coches");
            stage.setResizable(false);

            stage.setScene(scene);
            stage.show();
            
            stage.setOnCloseRequest(e -> controlador.closeVentana());
            
            Stage miStage = (Stage) this.btnIntroducir.getScene().getWindow();//se cierra la ventana principal y se abre itroducir servicios
            miStage.close();
            

        }catch(IOException ex){
             Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
