/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.validador_correlativas_final_argpro4;

import java.util.ArrayList;

/**
 *
 * @author facub
 */
public class Alumnos {
    
    String nombre;
    int legajo;
    ArrayList<String> materiasAprobadas = new ArrayList<>();
            
    public Alumnos(String nombre, int legajo){
        
        this.nombre = nombre;
        this.legajo = legajo;

    }
    
    public Alumnos(){
    }

    public String getNombre() {
        return nombre;
    }

    public int getLegajo() {
        return legajo;
    }

    public ArrayList<String> getMateriasAprobadas() {
        return materiasAprobadas;
    }
    
    // Set 
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public void setMateriasAprobadas(ArrayList<String> materiasAprobadas) {
        this.materiasAprobadas = materiasAprobadas;
    }

    @Override
    public String toString() {
        return "Alumno: " + "nombre = " + nombre + " | legajo = " + legajo + "|  materias aprobadas = " + materiasAprobadas + '}';
    }
    
    
  
}