/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unam.aragon.mx;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

/**
 *
 * @author eliot
 */
public class Inicio {

    private String ruta;

    public Inicio() {
    }

    public String getRuta() throws URISyntaxException {
        ruta = this.getClass().getResource("/unam/aragon/mx/fuente.txt").toURI().getPath();
        return ruta;
    }

    public static void main(String[] args) {
        try {
            Inicio app = new Inicio();
            app.correr();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void correr() {
        try {
            AnalisisSintactico inicio;
            Analizador_Lexico lex = null;
            lex = new Analizador_Lexico(new InputStreamReader((new FileInputStream(this.getRuta()))));
            inicio = new AnalisisSintactico(lex);
            inicio.parse();
            System.out.println("resultado = " + lex.yytext() + inicio.resultado);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
