/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.Cliente;
import modelo.Servicio;

/**
 * FXML Controller class
 *
 * @author cifue
 */
public class ConsultarServiciosController implements Initializable {

    @FXML
    private DatePicker dtpFechaInicial;
    @FXML
    private ComboBox<Cliente> cmbClientes;
    @FXML
    private TableView<Servicio> tblServicios;
    @FXML
    private TableColumn<Servicio, String> colMatricula;
    @FXML
    private TableColumn<Servicio, String> colMarca;
    @FXML
    private TableColumn<Servicio, Integer> colPrecio;
    @FXML
    private TableColumn<Servicio, LocalDate> colFechaAlquiler;
    @FXML
    private TableColumn<Servicio, LocalDate> colFechaEntrega;
    @FXML
    private TableColumn<Servicio, Integer> colTotal;
    @FXML
    private DatePicker dtpFechaFinal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
            this.colMatricula.setCellValueFactory(new PropertyValueFactory("matriculaVehiculo"));
            this.colMarca.setCellValueFactory(new PropertyValueFactory("marca"));
            this.colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
            this.colFechaAlquiler.setCellValueFactory(new PropertyValueFactory("fechaAlquiler"));
            this.colFechaEntrega.setCellValueFactory(new PropertyValueFactory("fechaEntrega"));
            this.colTotal.setCellValueFactory(new PropertyValueFactory("total"));
            
            Servicio s = new Servicio();
            ObservableList<Servicio> items = s.getServicios();
            this.tblServicios.setItems(items);
            
            Cliente c = new Cliente();
            ObservableList<Cliente> obsC = c.getClientes();
            this.cmbClientes.setItems(obsC);
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultarServiciosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void closeVentana() {
        try {
            //abrimos la vista insertar
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/MenuVista.fxml"));
            Parent root = loader.load();
            //creamos el controlador
            MenuController controlador = loader.getController();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            stage.setScene(scene);
            stage.show();
            
            //Cuando le damos al boton introducir la ventana anterior se cierra VENTANAS NO MODALES
            Stage miStage = (Stage) this.cmbClientes.getScene().getWindow();
            miStage.close();
        } catch (IOException ex) {
            Logger.getLogger(InsertarServiciosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
