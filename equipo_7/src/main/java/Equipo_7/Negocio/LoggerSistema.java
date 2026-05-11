/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Equipo_7.Negocio;

/**
 *
 * @author SrMrPandora
 */
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LoggerSistema {

    public static void guardarLog(String mensaje) {
        try {
            FileWriter writer = new FileWriter("logs.txt", true);

            writer.write(
                LocalDateTime.now() + " -> " + mensaje + "\n"
            );

            writer.close();

        } catch (IOException e) {
            System.out.println("Error al guardar log");
        }
    }
}