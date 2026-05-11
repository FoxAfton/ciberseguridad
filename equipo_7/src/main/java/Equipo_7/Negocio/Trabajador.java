/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Equipo_7.Negocio;

/**
 *
 * @author SrMrPandora
 */
public class Trabajador {

    String nombre;
    String email;
    String RFC;
    String SeguroSocial;
    String FechadeAlta;
    String Genero;
    String departamento;
    String sueldo;
    String AltaImss;

    public Trabajador(String nombre, String email, String RFC, String SeguroSocial, String FechadeAlta, String Genero, String departamento, String sueldo, String AltaImss) {
        this.nombre = nombre;
        this.email = email;
        this.RFC = RFC;
        this.SeguroSocial = SeguroSocial;
        this.FechadeAlta = FechadeAlta;
        this.Genero = Genero;
        this.departamento = departamento;
        this.sueldo = sueldo;
        this.AltaImss = AltaImss;
    }

    

    public String getSeguroSocial() {
        return SeguroSocial;
    }

    public void setSeguroSocial(String SeguroSocial) {
        this.SeguroSocial = SeguroSocial;
    }

   

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getFechadeAlta() {
        return FechadeAlta;
    }

    public void setFechadeAlta(String FechadeAlta) {
        this.FechadeAlta = FechadeAlta;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getSueldo() {
        return sueldo;
    }

    public void setSueldo(String sueldo) {
        this.sueldo = sueldo;
    }

    public String getAltaImss() {
        return AltaImss;
    }

    public void setAltaImss(String AltaImss) {
        this.AltaImss = AltaImss;
    }
    
    
}
