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
public class Vehiculo {
    
    private String matricula;
    private String descripcion;
    private String marca;
    private int km;
    private int precio;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Vehiculo(String matricula, String descripcion, String marca, int km, int precio) {
        this.matricula = matricula;
        this.descripcion = descripcion;
        this.marca = marca;
        this.km = km;
        this.precio = precio;
    }

    public Vehiculo() {
    }
    
    @Override
    public String toString(){
        //necesario para el combobox
        return matricula;
    }
    
    public ObservableList<Vehiculo> getVehiculos() throws SQLException{
        
        ObservableList<Vehiculo> obs = FXCollections.observableArrayList();
        
        ConexionMysql conexion = new ConexionMysql("jdbc:mysql://localhost:3306/alquiler_vehiculos","root","");
        
        obs = conexion.cogerVehiculos();
        
        //conexion.cerrarConexion();
        
        return obs;
        
    }
    
}
