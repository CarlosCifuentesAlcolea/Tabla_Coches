/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import modelo.Cliente;
import modelo.Servicio;
import modelo.Vehiculo;

/**
 *
 * @author cifue
 */
public class ConexionMysql {

    Connection conn = null;

    public ConexionMysql(String dbURL, String user, String password) {
        try {
            //RECUERDA: PARA EJECUTAR ESTE CÓDIGO ES NECESARIO TENER MySQL FUNCIONANDO Y LAS TABLAS Y USUARIOS CREADOS
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, user, password);
            if (conn != null) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setHeaderText(null);
                alerta.setTitle("Info");
                alerta.setContentText("Conectado a la Base de Datos con éxito");
                alerta.showAndWait();
            }

        } catch (SQLException ex) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Error");
            alerta.setContentText("La dirección no es válida o el usuario y clave");
            alerta.showAndWait();

            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void cerrarConexion() {
        //SE CIERRA LA CONEXIÓN
        try {

            conn.close();
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText(null);
            alerta.setTitle("Info");
            alerta.setContentText("Desconectado a la Base de Datos con éxito");
            alerta.showAndWait();

        } catch (SQLException ex) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Error");
            alerta.setContentText("Ha sido imposible cerrar la conexion");
            alerta.showAndWait();

            ex.printStackTrace();
        }
    }

    public ObservableList cogerClientes() throws SQLException {
        Statement stmnt = conn.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT * FROM clientes");

        ObservableList<Cliente> clientes = FXCollections.observableArrayList();

        while (rs.next()) {
            String NyA = rs.getString("NyA");
            String NIF = rs.getString("NIF");
            String Direccion = rs.getString("Direcion");
            String Poblacion = rs.getString("Poblacion");
            Cliente c = new Cliente(NyA, NIF, Direccion, Poblacion);
            clientes.add(c);
        }
        rs.close();
        stmnt.close();
        return clientes;
    }
   
     public ObservableList cogerServicios() throws SQLException {
        Statement stmnt = conn.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT v.matricula, v.marca, v.precio, s.fecha_alquiler, s.fecha_entrega, s.total "
                + "FROM servicios s, vehiculos v "
                + "where s.matricula_vehiculo = v.matricula");//como necesito la marca y el precio necesito acceos a la tabla vehiculos

        ObservableList<Servicio> servicios = FXCollections.observableArrayList();

        while (rs.next()) {
            
            String matr = rs.getString("matricula");
            String marca = rs.getString("marca");
            int precio = rs.getInt("precio");
            LocalDate fA = rs.getDate("fecha_alquiler").toLocalDate();
            LocalDate fE = rs.getDate("fecha_entrega").toLocalDate();
            int total = rs.getInt("total");
            
            Servicio s = new Servicio(matr,fA,fE,total,marca,precio);
            servicios.add(s);
        }
        rs.close();
        stmnt.close();
        return servicios;
    }

    public ObservableList cogerVehiculos() throws SQLException {
        Statement stmnt = conn.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT * FROM vehiculos");

        ObservableList<Vehiculo> vehiculos = FXCollections.observableArrayList();

        while (rs.next()) {
            String matricula = rs.getString("matricula");
            String descripcion = rs.getString("descripcion");
            String marca = rs.getString("marca");
            int kilometros = rs.getInt("kilometros");
            int precio = rs.getInt("precio");
            Vehiculo v = new Vehiculo(matricula, descripcion, marca, kilometros, precio);
            vehiculos.add(v);
        }
        rs.close();
        stmnt.close();
        return vehiculos;
    }
    
    public void insertarServicios(String matr, String nif, LocalDate fechaAl, LocalDate fechaEn,int total) throws SQLException{
        Statement stmnt = conn.createStatement();
        stmnt.executeUpdate("INSERT INTO servicios (matricula_vehiculo, nif_cliente, fecha_alquiler, fecha_entrega, total)"
            + "values("
            + "'"+matr+"',"
            + "'"+nif+"',"
            + "'"+fechaAl+"',"
            + "'"+fechaEn+"',"
            +total+" )");
        
        stmnt.close();
    }
    /*
    public void ejecutarConsulta(String consulta) throws SQLException{
        Statement stmnt = conn.createStatement();
        
    }*/

}
