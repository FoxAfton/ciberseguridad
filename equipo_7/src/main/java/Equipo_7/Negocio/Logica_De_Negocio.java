/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Equipo_7.Negocio;

import Equipo_7.DB.Database_Service;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SrMrPandora
 */
public class Logica_De_Negocio {
    
    private Database_Service SQLite;

    public Logica_De_Negocio() {
        this.SQLite = new Database_Service();
        this.SQLite.inicializarDB();
        
    }
    
    public String ProcesarRegistro(Trabajador t){
        String error = ValidatorClass.validarTrabajador(t);
        
        if(error != null) {
            return error;
        }
        
        try{
             SQLite.insertarRegistro(t.getNombre(), t.getEmail(), t.getRFC(), t.getFechadeAlta(), t.getGenero(), t.getDepartamento(), Double.parseDouble(t.getSueldo()), t.getAltaImss());
            
            return "Registro Exitoso";
            
        }catch (SQLException e) {
            Logger.getLogger(Logica_De_Negocio.class.getName()).log(Level.SEVERE, "Registro fallido", e);
            return null;
        }
        
    }
    
    public Trabajador BuscarTrabajador (String idTexto){
        
        if (idTexto == null || idTexto.isBlank()) {
            return null;
        }
        
        if (!idTexto.matches("^[0-9]+$")) {
            return null;
        }
        
        try {
        int id = Integer.parseInt(idTexto);
        return SQLite.buscarPorId(id);

    } catch (SQLException e) {
        Logger.getLogger(Logica_De_Negocio.class.getName()).log(Level.SEVERE, "Error al buscar el id" + idTexto, e);
        return null;
    }
        
    }
    
   
    
}
