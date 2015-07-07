/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

//TODO: despues de un save, modifique o delete, cargar en archivo y actualiza la memoria.
/**
 *
 * @author kaajavi
 */
public abstract class Curso implements Serializable{
    //Array de alumono
    private static ArrayList<Alumno> alumnos = new ArrayList();
    //Alumno[] alumnos = new Alumno[34];
    private static final String archivo = "alumno.kaadb";
    private static FileInputStream fis = null;
    private static ObjectInputStream ois = null;
    private static FileOutputStream fos = null;
    private static ObjectOutputStream oos = null;
    
    public static ArrayList<Alumno> getAlumnos(){
        return alumnos;
    }
    
    public static int addAlumno(Alumno alumno){
        alumnos.add(alumno);
        save();
        load();
        return getNumberAlumno(alumno);
    }
    
    public static Alumno getAlumno(int ordenAlumno){        
        return alumnos.get(ordenAlumno);
    }
    
    
    public static int getNumberAlumno(Alumno alumno){
        for (int i=0; i< alumnos.size();i++){
            if (alumno.getLegajo() == alumnos.get(i).getLegajo()) {
                return i;
            }
        }
        return -1;
        
    }
    
    public static int getCantidadDeAlumnos(){
        return alumnos.size();
    }
    

    
    
    public static String save() {
        String ret = "Carga exitosa";
        boolean flag = true;
        String fileTmp = "tmpFile.db";
        //Se carga el array en un archivo temporal
        try {
            fos = new FileOutputStream(fileTmp);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(alumnos);
            

        } catch (FileNotFoundException ex) {
            flag = false;
            ret = "No se encuentra el archivo";
            return ret;
        } catch (IOException ex) {
            flag = false;
            ret = "Error al guardar el archivo";
            return ret;
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                    oos = null;
                }
                if (fos != null) {
                    fos.close();
                    fos = null;
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar archivo");
            }
        }
        //Si todo salió bien, se borra el archivo db actual 
        //y lo reemplaza por tmpFile.db
        File fdel = new File(archivo);
        File ftmp = new File(fileTmp);
        if (fdel.exists()) {
            if (fdel.delete()) {
                ftmp.renameTo(new File(archivo));
            }
        } else {
            ftmp.renameTo(new File(archivo));
        }


        return ret;

    }

    /*
     * Devuelve un arraylist con las ventas en disco
     */
    public static void load() {
        
        System.out.println("Intentando levantar la lista");
        try {
            fis = new FileInputStream(archivo);
            ois = new ObjectInputStream(fis);

            while (true) {
                alumnos = (ArrayList<Alumno>) ois.readObject();                
            }

        }catch (EOFException e1) {
            //END OF FILE!
            System.out.println("Sale");
        } catch (Exception e2) {
            System.out.println("Error!!!" + e2);
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                    ois = null;
                }
                if (fis != null) {
                    fis.close();
                    fis = null;
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar archivo");
            }
        }
        
    }
 
    
    
    
}