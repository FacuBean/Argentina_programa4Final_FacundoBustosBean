/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.validador_correlativas_final_argpro4;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.ArrayList;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

/**
 *
 * @author facub
 */
public class Validador_Correlativas_Final_ARGPRO4 {

    public static Connection_to_db connect = new Connection_to_db();
    private static Scanner sc = new Scanner(System.in).useDelimiter("\n");

    public static void main(String[] args) throws SQLException {

//        try{
//        
        connect.estableceConexion();

        int option = 0;

        //Para construir el alumno
        String nombre_alumno = "";
        int legajo_alumno = 0;
        HashMap<String, Boolean> materias_aprobadas = new HashMap<String, Boolean>();

        Gson gson = new Gson();

        //query para subir objeto alumno a base de datos
        String alumno_query = "INSERT INTO alumnos (nombre, legajo, materias_aprobadas) VALUES (?, ?, ?)";

        // Para construir una materia
        String nombre_materia = "";
        HashMap<String, Boolean> materias_correlativas = new HashMap<String, Boolean>();

        //query para subir objeto alumno a base de datos
        String materia_query = "INSERT INTO materias (nombre_materia, correlativas) VALUES (?, ?)";

        //Para construir Inscripcion
        int legajo_alumno_i;

        Statement smt = connect.conectar.createStatement();

        System.out.println("¿Que deseas hacer?\n-1)Gestionar alumnos.\n-2)Gestionar materias.\n-3)Verificar inscripcion de materia por legajo de alumno");
        option = sc.nextInt();

        switch (option) {
            case 1:
                System.out.println("¿Que deseas hacer ahora?\n-1)Ver datos de los alumnos.\n-2)Crear nuevo alumno.");
                option = sc.nextInt();

                switch (option) {

                    case 1:
                        ResultSet alumnos_rs = smt.executeQuery("SELECT * FROM alumnos");
                        while (alumnos_rs.next()) {

                            System.out.println("Nombre: " + alumnos_rs.getString("nombre") + " | Legajo: " + alumnos_rs.getString("legajo") + " | Materias aprobadas:" + alumnos_rs.getString("materias_aprobadas"));
                        }
                        break;

                    case 2:

                        //Tomar datos para el objeto alumno
                        System.out.println("Ingrese el nombre del alumno:");
                        nombre_alumno = sc.next();

                        System.out.println("Ingrese el legajo del alumno - TIENE QUE TENER 5 NUMEROS");
                        legajo_alumno = sc.nextInt();
                        sc.nextLine();

                        while (String.valueOf(legajo_alumno).length() != 5) {
                            System.out.println("El legajo debe tener 5 números. Ingrese nuevamente:");
                            legajo_alumno = sc.nextInt();
                            sc.nextLine();
                        }

                        String continuar_while_materia = "";
                        while (!continuar_while_materia.equals("0")) {
                            System.out.println("Ingrese el nombre de una materia aprobada (o ingrese \"0\" para finalizar)");
                            continuar_while_materia = sc.next();

                            if (!continuar_while_materia.equals("0")) {
                                materias_aprobadas.put(continuar_while_materia, true);
                            }
                        }

                        //Creador de objeto alumno
                        Alumnos alumno = new Alumnos(nombre_alumno, legajo_alumno);
                        alumno.setMateriasAprobadas(new ArrayList<String>(materias_aprobadas.keySet()));
                        String materiasAprobadasJson = gson.toJson(alumno.getMateriasAprobadas());

                        System.out.println(alumno.toString());

                        //Para subir los datos del objeto alumno a la base de datos
                        try (PreparedStatement pstmt = connect.prepareStatement(alumno_query)) {
                            pstmt.setString(1, nombre_alumno);
                            pstmt.setInt(2, alumno.getLegajo());
                            pstmt.setString(3, materiasAprobadasJson);

                            pstmt.executeUpdate();

                            System.out.println("SE CREO EL ALUMNO CORRECTAMENTE!");

                        } catch (SQLException e) {
                            System.out.println("Ocurrio un error: " + e.getMessage());
                        }

                        break;
                }

                break;

            case 2:
                System.out.println("¿Que deseas hacer ahora?\n-1)Ver datos de las materias y sus correlavitas.\n-2)Crear nueva materia.");
                option = sc.nextInt();

                switch (option) {

                    case 1:
                        ResultSet materias_rs = smt.executeQuery("SELECT * FROM materias");
                        while (materias_rs.next()) {
                            System.out.println("Nombre de la materia: " + materias_rs.getString("nombre_materia") + " | Correlativas: " + materias_rs.getString("correlativas"));
                        }
                        break;

                    case 2:

                        // Tomar datos para el objeto materia
                        System.out.println("Ingrese el nombre de la materia:");
                        nombre_materia = sc.next();

                        String continuar_while_materia_correlativa = "";
                        while (!continuar_while_materia_correlativa.equals("0")) {
                            System.out.println("Ingrese el nombre de una materia correlativa (o ingrese \"0\" para finalizar)");
                            continuar_while_materia_correlativa = sc.next();

                            if (!continuar_while_materia_correlativa.equals("0")) {
                                materias_correlativas.put(continuar_while_materia_correlativa, true);
                            }
                        }

                        //Creador objeto materia
                        Materias materia = new Materias();
                        materia.setMateria(nombre_materia);
                        materia.setCorrelativas(new ArrayList<String>(materias_correlativas.keySet()));
                        String materiasCorrelativasJson = gson.toJson(materia.getCorrelativas());

                        //Para subir los datos del objeto alumno a la base de datos
                        try (PreparedStatement pstmt = connect.prepareStatement(materia_query)) {
                            pstmt.setString(1, nombre_materia);
                            pstmt.setString(2, materiasCorrelativasJson);

                            pstmt.executeUpdate();

                            System.out.println("SE CREO LA MATERIA CORRECTAMENTE!");

                        } catch (SQLException e) {
                            System.out.println("Ocurrio un error: " + e.getMessage());
                        }

                        break;
                }

                break;

            case 3:
                sc.useDelimiter("\n");
                System.out.println("Ingrese el legajo del alumno:");
                legajo_alumno_i = sc.nextInt();

                System.out.println("Ingrese el nombre de la materia que quiere inscribirse:");
                String materia_inscripcion = sc.next();

                Incripciones inscripcion = new Incripciones(materia_inscripcion, legajo_alumno_i, false);

                ResultSet alumnos_materias_rs = smt.executeQuery("SELECT materias_aprobadas FROM alumnos WHERE legajo = " + legajo_alumno_i);

                ArrayList<String> ListaMateriasCorrelativas = new ArrayList<>();
                ArrayList<String> ListaAlumnoCorrelativas = new ArrayList<>();

                while (alumnos_materias_rs.next()) {
                    String materias_aprobadasJson = alumnos_materias_rs.getString("materias_aprobadas");
                    Type materias_aType = new TypeToken<List<String>>() {
                    }.getType();
                    List<String> materias_aprobadasI = new Gson().fromJson(materias_aprobadasJson, materias_aType);

                    for (String mc : materias_aprobadasI) {
                        ListaAlumnoCorrelativas.add(mc);
                    }

                }
                ResultSet materias_correlativas_rs = smt.executeQuery("SELECT correlativas FROM materias WHERE nombre_materia = \"" + materia_inscripcion + "\"");

                while (materias_correlativas_rs.next()) {
                    String materias_correlativasJson = materias_correlativas_rs.getString("correlativas");
                    Type correlativasType = new TypeToken<List<String>>() {
                    }.getType();
                    List<String> materias_correlativasI = new Gson().fromJson(materias_correlativasJson, correlativasType);

                    for (String mc : materias_correlativasI) {
                        ListaMateriasCorrelativas.add(mc);
                    }

                }

                System.out.println(ListaAlumnoCorrelativas);
                System.out.println(ListaMateriasCorrelativas);

//                int cantidad_aprobadas = ListaAlumnoCorrelativas.size();
                int cantidad_correlativas = ListaMateriasCorrelativas.size();
                int counter = 0;
                
                for (String aprobadas : ListaAlumnoCorrelativas) {
                    for (String correlativas : ListaMateriasCorrelativas) {
                        if (aprobadas.equals(correlativas)) {
                            counter += 1;
                            if (counter == cantidad_correlativas) {
                                inscripcion.setAprobado(true);
                                System.out.println("SE PUEDE INSCRIBIR.");
                                break;
                            }
                        }
                    }
                }
                
                if (counter < cantidad_correlativas){
                    System.out.println("NO SE PUEDE INSCRIBIR");
                }
  
                break;
        }

        connect.cerrarConexion();
    }

}
