/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.ConexionMysql;

/**
 *
 * @author cifue
 */
public class Cliente {
    
    private String NyA;
    private String NIF;
    private String Direccion;
    private String Poblacion;

    public Cliente() {
    }

    public Cliente(String NyA, String NIF, String Direccion, String Poblacion) {
        this.NyA = NyA;
        this.NIF = NIF;
        this.Direccion = Direccion;
        this.Poblacion = Poblacion;
    }

    public String getNyA() {
        return NyA;
    }

    public void setNyA(String NyA) {
        this.NyA = NyA;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getPoblacion() {
        return Poblacion;
    }

    public void setPoblacion(String Poblacion) {
        this.Poblacion = Poblacion;
    }
    
    @Override
    public String toString() {
        return NyA;
    }
    
    public ObservableList<Cliente> getClientes() throws SQLException{
        
        ObservableList<Cliente> obs = FXCollections.observableArrayList();
        
        ConexionMysql conexion = new ConexionMysql("jdbc:mysql://localhost:3306/alquiler_vehiculos","root","");
        
        obs = conexion.cogerClientes();
        
        //conexion.cerrarConexion();
        
        return obs;
        
    }
}
