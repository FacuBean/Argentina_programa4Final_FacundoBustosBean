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
public class Materias {
    
    String materia;
    ArrayList<String> correlativas = new ArrayList<>();

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public ArrayList<String> getCorrelativas() {
        return correlativas;
    }

    public void setCorrelativas(ArrayList<String> correlativas) {
        this.correlativas = correlativas;
    }

    @Override
    public String toString() {
        return "Materias{" + "materia=" + materia + ", correlativas=" + correlativas + '}';
    }
    
    
    
}
