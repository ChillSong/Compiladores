package Pruebas.jflexAprendiendo.Aprendiendo.src.fes.aragon.inicio;

import Pruebas.jflexAprendiendo.Aprendiendo.src.fes.aragon.codigo.Sym;
import Pruebas.jflexAprendiendo.Aprendiendo.src.fes.aragon.codigo.Tokens;
import Pruebas.jflexAprendiendo.Aprendiendo.src.fes.aragon.codigo.Lexico;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Inicio {

    public static void main(String[] args) {
        try {
            // Se pasa el archivo de entrada al analizador léxico (puede ser desde un archivo o consola)
            Reader reader = new FileReader("C:\\Users\\anton\\Desktop\\Mike\\Pruebas\\jflexAprendiendo\\Aprendiendo\\src\\fes\\aragon\\entrada.txt"); // Asegúrate de que este archivo exista

            // Crear el analizador léxico
            Lexico lexer = new Lexico(reader);

            // Ciclo para leer y procesar los tokens
            Tokens token;
            while ((token = lexer.yylex()) != null) {
                // Procesa el token
                System.out.println("Token reconocido: " + token.getToken() + " de tipo " + Sym.terminales[token.getLexema()]);

                // Aquí puedes agregar el código para el análisis sintáctico si lo tienes
                // Si es necesario, puedes lanzar un error sintáctico

            }

            // Si el análisis termina sin errores, se muestra un mensaje de éxito
            System.out.println("Análisis léxico completado exitosamente.");

        } catch (IOException e) {
            // Si hay un error al leer el archivo, muestra el mensaje de error
            System.err.println("Error al leer el archivo: " + e.getMessage());

        } catch (Exception e) {
            // Si hay otro tipo de error, muestra el mensaje de error
            System.err.println("Error en el analizador: " + e.getMessage());
        }
    }
}
