/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.ConexionMysql;

/**
 *
 * @author cifue
 */
public class Servicio {
    
    private int idServicio;
    private String matriculaVehiculo;
    private String NIFCliente;
    private LocalDate fechaAlquiler;
    private LocalDate fechaEntrega;
    private int total;

    //CREAMOS ATRIBUTOS ESPECIALES PARA LA TABLA
    private String marca;
    private int precio;
    
    public Servicio() {
    }

    public Servicio(int idServicio, String matriculaVehiculo, String NIFCliente, LocalDate fechaAlquiler, LocalDate fechaEntrega, int total) {
        this.idServicio = idServicio;
        this.matriculaVehiculo = matriculaVehiculo;
        this.NIFCliente = NIFCliente;//con esto relacionamos las tablas
        this.fechaAlquiler = fechaAlquiler;
        this.fechaEntrega = fechaEntrega;
        this.total = total;
    }

    //CONSTRUCTOR ESPECIAL PARA LA TABLA

    public Servicio(String matriculaVehiculo, LocalDate fechaAlquiler, LocalDate fechaEntrega, int total, String marca, int precio) {
        this.matriculaVehiculo = matriculaVehiculo;
        this.fechaAlquiler = fechaAlquiler;
        this.fechaEntrega = fechaEntrega;
        this.total = total;
        this.marca = marca;
        this.precio = precio;
    }
    
    
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getMatriculaVehiculo() {
        return matriculaVehiculo;
    }

    public void setMatriculaVehiculo(String matriculaVehiculo) {
        this.matriculaVehiculo = matriculaVehiculo;
    }

    public String getNIFCliente() {
        return NIFCliente;
    }

    public void setNIFCliente(String NIFCliente) {
        this.NIFCliente = NIFCliente;
    }

    public LocalDate getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(LocalDate fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    //Creamos un construcor sin id ya que este es autoincremental y se lo añade la BD
    public Servicio(String matriculaVehiculo, String NIFCliente, LocalDate fechaAlquiler, LocalDate fechaEntrega, int total) {
        this.matriculaVehiculo = matriculaVehiculo;
        this.NIFCliente = NIFCliente;
        this.fechaAlquiler = fechaAlquiler;
        this.fechaEntrega = fechaEntrega;
        this.total = total;
    }
    
    public boolean insertarServicio() throws SQLException{
        ConexionMysql conexion = new ConexionMysql("jdbc:mysql://localhost:3306/alquiler_vehiculos","root","");
        conexion.insertarServicios(this.getMatriculaVehiculo(),this.getNIFCliente(),this.getFechaAlquiler(),this.getFechaEntrega(),total);
        return true;
    }
    
    public ObservableList<Servicio> getServicios() throws SQLException{
        
        ObservableList<Servicio> obs = FXCollections.observableArrayList();
        
        ConexionMysql conexion = new ConexionMysql("jdbc:mysql://localhost:3306/alquiler_vehiculos","root","");
        
        obs = conexion.cogerServicios();
        
        //conexion.cerrarConexion();
        
        return obs;
        
    }
    
    
}
