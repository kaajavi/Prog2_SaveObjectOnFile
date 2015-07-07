/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.io.Serializable;

/**
 *
 * @author kaajavi
 */
public class Alumno implements Serializable{
    private int legajo;
    private int dni;
    private String nombre;
    private String telefono;


    public Alumno(int legajo, int dni, String nombre, String telefono) {
        this.legajo = legajo;
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
    }
    
    
     public String toString(){
         return "" + this.legajo + "  -  " + this.nombre;
     }
    

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
    
}
