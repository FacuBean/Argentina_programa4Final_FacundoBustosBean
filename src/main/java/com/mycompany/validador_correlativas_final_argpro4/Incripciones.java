/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.validador_correlativas_final_argpro4;

import java.util.Date;

/**
 *
 * @author facub
 */

// Le pifie en el nombre de la clase xd
public class Incripciones {

    String materia_name;
    int legajo_alumno;
    boolean aprobado;
    Date fecha = new Date();
    
    
    
    public Incripciones(String materia_name, int legajo_alumno, boolean aprobado) {
        this.materia_name = materia_name;
        this.legajo_alumno = legajo_alumno;
        this.aprobado = aprobado;
    }
    
    

    public String getMateria_name() {
        return materia_name;
    }

    public void setMateria_name(String materia_name) {
        this.materia_name = materia_name;
    }

    public int getLegajo_alumno() {
        return legajo_alumno;
    }

    public void setLegajo_alumno(int legajo_alumno) {
        this.legajo_alumno = legajo_alumno;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Incripciones{" + "materia_name=" + materia_name + ", legajo_alumno=" + legajo_alumno + ", aprobado=" + aprobado + ", fecha=" + fecha + '}';
    }

    

}
