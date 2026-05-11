/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Equipo_7.DB;

import Equipo_7.Negocio.Trabajador;
import java.sql.*;

/**
 *
 * @author SrMrPandora
 */
public class Database_Service {

    private static final String URL = "jdbc:sqlite:" + System.getProperty("user.dir") + "/Imss.db";

    // Obtener conexión
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public void inicializarDB() {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "nombre TEXT NOT NULL," +
                     "email TEXT NOT NULL,"
                +    "RFC TEXT NOT NULL,"
                +    "fecha_de_alta TEXT NOT NULL,"
                +    "genero TEXT NOT NULL,"
                +    "departamento TEXT NOT NULL,"
                +    "sueldo REAL NOT NULL,"
                +    "alta_imss TEXT NOT NULL)";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Error al crear tabla: " + e.getMessage());
        }
    }
    
    public void insertarRegistro(String nombre, String email, String RFC, String FechadeAlta, String genero, String departamento, double sueldo, String AltaImss) throws SQLException {
        String sql = "INSERT INTO usuarios (nombre, email, RFC, SeguroSocial fechaAlta, genero, departamento, sueldo, alta) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); 
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, email);
            pstmt.setString(3, RFC);
            pstmt.setString(4, FechadeAlta);
            pstmt.setString(5, genero);
            pstmt.setString(6, departamento);
            pstmt.setDouble(7, sueldo);
            pstmt.setString(8, AltaImss);
            pstmt.executeUpdate();
        }
    }
    
    public Trabajador buscarPorId(int id) throws SQLException {
    String sql = "SELECT * FROM usuarios WHERE id = ?";
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            return new Trabajador(
                rs.getString("nombre"),
                rs.getString("email"),
                rs.getString("RFC"),
                rs.getString("SeguroSocial"),
                rs.getString("fecha_de_alta"),
                rs.getString("genero"),
                rs.getString("departamento"),
                String.valueOf(rs.getDouble("sueldo")),
                rs.getString("alta_imss")
            );
        }
        return null; 
    }
    }
}
