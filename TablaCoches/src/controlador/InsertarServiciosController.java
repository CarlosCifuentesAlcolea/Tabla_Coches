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
import java.time.Period;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Cliente;
import modelo.Servicio;
import modelo.Vehiculo;

/**
 * FXML Controller class
 *
 * @author cifue
 */
public class InsertarServiciosController implements Initializable {

    @FXML
    private Button btnGrabar;
    @FXML
    private ComboBox<Cliente> cmbClientes;
    @FXML
    private ComboBox<Vehiculo> cmbVehiculos;
    @FXML
    private TextField txtDirCli;
    @FXML
    private TextField txtNIFCli;
    @FXML
    private TextField txtPrecioVeh;
    @FXML
    private TextField txtKmVeh;
    @FXML
    private TextField txtMarcaVeh;
    @FXML
    private TextField txtDescripcionVeh;
    @FXML
    private TextField txtPobCli;
    @FXML
    private DatePicker dtpFechaAlquiler;
    @FXML
    private DatePicker dtpFechaEntrega;
    @FXML
    private TextField txtTotal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            // TODO
            iniciarComboBox();
        } catch (SQLException ex) {
            Logger.getLogger(InsertarServiciosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void iniciarComboBox() throws SQLException {

        Cliente c = new Cliente();
        ObservableList<Cliente> obsC = c.getClientes();
        this.cmbClientes.setItems(obsC);

        Vehiculo v = new Vehiculo();
        ObservableList<Vehiculo> obsV = v.getVehiculos();
        this.cmbVehiculos.setItems(obsV);

    }

    public void closeVentana() {
        try {
            //abrimos la vista insertar
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/MenuVista.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

            //Cuando le damos al boton introducir la ventana anterior se cierra VENTANAS NO MODALES
            Stage miStage = (Stage) this.btnGrabar.getScene().getWindow();
            miStage.close();
        } catch (IOException ex) {
            Logger.getLogger(InsertarServiciosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void grabarServicio(ActionEvent event) throws SQLException {

        Vehiculo v = this.cmbVehiculos.getValue();
        Cliente c = this.cmbClientes.getValue();
        LocalDate inicio = this.dtpFechaAlquiler.getValue();
        LocalDate fin = this.dtpFechaEntrega.getValue();

        int total = Integer.parseInt(this.txtTotal.getText());

        String errores = "";

        if (c == null) {
            errores = "Debes seleccionar un cliente \n";
        }
        if (v == null) {
            errores = "Debes seleccionar un vehiculo \n";
        }
        if (inicio == null) {
            errores = "Debes seleccionar una fecha de alquiler \n";
        }
        if (fin == null) {
            errores = "Debes seleccionar una fecha de entrega \n";
        }

        if (inicio != null && fin != null && inicio.isAfter(fin)) {//EL METODO ISAFTER COMPRUEBA SI UNA FECHA SUPERA A OTRA
            errores = "La fecha de alquiler no puede superar a la de entrega \n";
        }

        if (errores.isEmpty()) {
            Servicio s = new Servicio(v.getMatricula(), c.getNIF(), inicio, fin, total);
            if (s.insertarServicio()) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setHeaderText(null);
                alerta.setTitle("Exito");
                alerta.setContentText("Se insertó correctamente");
                alerta.showAndWait();
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setHeaderText(null);
                alerta.setTitle("Error");
                alerta.setContentText("No se insertó correctamente");
                alerta.showAndWait();
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Error");
            alerta.setContentText(errores);
            alerta.showAndWait();
        }

    }

    @FXML
    private void insertarFechaAlquiler(ActionEvent event) {
        calcularTotalServicio();
    }

    @FXML
    private void insertarFechaEntrga(ActionEvent event) {
        calcularTotalServicio();
    }

    public void calcularTotalServicio() {
        //OBTENGO LAS FECHAS DE INICIO Y FIN DE LOS DATEPICKERS
        LocalDate inicio = this.dtpFechaAlquiler.getValue();
        LocalDate fin = this.dtpFechaEntrega.getValue();
        //COMPROBAMOS QUE LOS REGISTROS NO SEAN NULOS
        if (inicio != null && fin != null && this.cmbVehiculos.getValue() != null) {
            //OBTENGO LA DIFERENCIA DE DIAS
            Period p = Period.between(inicio, fin);
            long dias = p.getDays();
            //CALCULO EL TOTAL
            int precio = Integer.parseInt(this.txtPrecioVeh.getText());
            long total = dias * precio;

            if (total < 0) {
                this.txtTotal.setText("0");
            } else {
                this.txtTotal.setText(String.valueOf(total));
            }
        } else {
            //SI LOS CAMPOS SON NULOS EL TOTAL A PAGAR ES 0
            this.txtTotal.setText("0");
        }
    }

    @FXML
    private void insertarCliente(ActionEvent event) {
        //OBTENGO EL VALOR DEL COMBO CLIENTE
        Cliente c = this.cmbClientes.getValue();

        if (c != null) {
            this.txtNIFCli.setText(c.getNIF());
            this.txtDirCli.setText(c.getDireccion());
            this.txtPobCli.setText(c.getPoblacion());
        }
    }

    @FXML
    private void insertarVehiculo(ActionEvent event) {
        // Obtengo el valor del combo del vehiculo
        Vehiculo v = this.cmbVehiculos.getValue();

        if (v != null) {

            // Seteo los valores
            this.txtDescripcionVeh.setText(v.getDescripcion());
            this.txtMarcaVeh.setText(v.getMarca());
            this.txtKmVeh.setText(v.getKm() + "");
            this.txtPrecioVeh.setText(v.getPrecio() + "");
            calcularTotalServicio();

        }

    }

}
