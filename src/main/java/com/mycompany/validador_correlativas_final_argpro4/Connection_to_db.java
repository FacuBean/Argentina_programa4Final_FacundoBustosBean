/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.validador_correlativas_final_argpro4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author facub
 */
public class Connection_to_db {
    
    Connection conectar = null;

    String usuario = "root";
    String contraseña = "root";
    String bd = "validador_correlativas";
    String ip = "localhost";
    String puerto = "3306";

    String ruta = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;
    
    public Connection estableceConexion(){
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(ruta, usuario, contraseña);
            System.out.println("Se conecto");
            
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "NO se conecto correctamente:" + e);
        }
        return conectar;
    }
    
    public void cerrarConexion(){
        
        try{
            conectar.close();
            System.out.println("Se cerro la conexion");
        } catch (Exception e){
        }
    }

//    PreparedStatement prepareStatement(String query) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
    
    public PreparedStatement prepareStatement(String sql) throws SQLException {
    return conectar.prepareStatement(sql);
}

    
    
}
