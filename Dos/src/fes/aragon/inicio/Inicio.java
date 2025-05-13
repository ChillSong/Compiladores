/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fes.aragon.inicio;

import fes.aragon.codigo.Lexico;
import fes.aragon.codigo.Sym;
import fes.aragon.codigo.Tokens;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author MASH
 */
public class Inicio {
    private boolean error = true;
    private Tokens tokens = null;
    private Lexico analizador = null;

    public static void main(String[] args) {
        Inicio ap = new Inicio();
        BufferedReader buf;
        try {
            buf = new BufferedReader(
                    new FileReader("C:\\Users\\anton\\Desktop\\Mike\\Dos\\src\\fes\\aragon\\archivo.txt"));
            ap.analizador = new Lexico(buf);
            ap.siguienteToken();
            ap.sentencia();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sentencia() {
        do {
            asignacion();

            // Aquí validamos que la línea termine con ; o con }
            if (tokens.getLexema() == Sym.PUNTOCOMA || tokens.getLexema() == Sym.C_CIERRE) {
                // Línea válida
                System.out.println("Valida  linea= " + (tokens.getLinea() + 1));
            } else {
                // Si no termina correctamente, la consideramos inválida
                errorSintactico();
                System.out.println("Invalida linea= " + (tokens.getLinea() + 1));
            }

            this.error = true; // Reinicia error
            siguienteToken();

        } while (tokens.getLexema() != Sym.EOF);
    }


    private void asignacion() {
        switch (tokens.getLexema()) {
            case Sym.RESERVADAS:
                siguienteToken();
                instruccion();
                break;

            case Sym.ID:
                siguienteToken();
                if (tokens.getLexema() == Sym.IGUAL) {
                    siguienteToken();
                    expresion();
                } else {
                    errorSintactico();
                }
                break;

            case Sym.SQR:
            case Sym.LOG:
            case Sym.MIN:
            case Sym.MAX:
                funciones();
                break;

            default:
                errorSintactico();
                break;
        }
    }

    private void contenido() {
        if (tokens.getLexema() == Sym.ID) {
            siguienteToken();
            contenido();
        }
        else {
            siguienteToken();
        }
    }
    private void instruccion() {


        if (tokens.getLexema() == Sym.P_APERTURA) {
            siguienteToken();

            // Aceptar 0 o más ID dentro de los paréntesis
            while (tokens.getLexema() == Sym.ID) {

                siguienteToken();
            }

            // Ahora debe venir el paréntesis de cierre
            if (tokens.getLexema() == Sym.P_CIERRE) {

                siguienteToken();

                // Luego debe venir una llave de apertura {
                if (tokens.getLexema() == Sym.C_APERTURA) {

                    siguienteToken();

                    // Aquí puedes aceptar instrucciones internas (por ahora, uno o más IDs)
                    while (tokens.getLexema() == Sym.ID) {

                        siguienteToken();
                    }

                    // Y por último, la llave de cierre }
                    if (tokens.getLexema() == Sym.C_CIERRE) {

                        // ya terminó
                    } else {
                        errorSintactico();
                    }
                } else {
                    errorSintactico();
                }
            } else {
                errorSintactico();
            }
        } else {
            errorSintactico();
        }
    }


    private void expresion() {
        if (tokens.getLexema() == Sym.ID || tokens.getLexema() == Sym.ENTERO || tokens.getLexema() == Sym.FLOAT || tokens.getLexema() == Sym.CIENTIFICA) {
            siguienteToken();
            if (tokens.getLexema() == Sym.MAS || tokens.getLexema() == Sym.MENOS || tokens.getLexema() == Sym.ENTRE ||
                    tokens.getLexema() == Sym.POR || tokens.getLexema() == Sym.PORCENTAJE || tokens.getLexema() == Sym.CIRCUNFLEJO) {
                siguienteToken();
                expresion();
            } else {
                if (tokens.getLexema() != Sym.PUNTOCOMA) {
                    errorSintactico();
                }
            }
            
        }else {
            errorSintactico();
        }/* else {
            if (tokens.getLexema() == Sym.PUNTOCOMA) {
                asignacion();
            }else{
                errorSintactico();
            }
        }*/
    }
    private void funciones() {
        switch (tokens.getLexema()) {
            case Sym.SQR:
            case Sym.LOG:
                siguienteToken();
                if (tokens.getLexema() == Sym.P_APERTURA) {
                    siguienteToken();
                    if (tokens.getLexema() == Sym.FLOAT || tokens.getLexema() == Sym.ENTERO ||
                            tokens.getLexema() == Sym.CIENTIFICA || tokens.getLexema() == Sym.PI) {
                        siguienteToken();
                        if (tokens.getLexema() == Sym.P_CIERRE){
                            siguienteToken();
                        }
                        else {
                            errorSintactico();
                        }

                    } else {
                        errorSintactico();
                    }
                } else {
                    errorSintactico();
                }
                break;

            case Sym.MAX:
            case Sym.MIN:
                siguienteToken();
                if (tokens.getLexema() == Sym.P_APERTURA) {
                    siguienteToken();
                    // Leemos el primer número
                    if (tokens.getLexema() == Sym.ENTERO || tokens.getLexema() == Sym.CIENTIFICA ||
                            tokens.getLexema() == Sym.PI || tokens.getLexema() == Sym.FLOAT) {
                        siguienteToken();

                        // Leemos los siguientes valores si vienen separados por coma
                        while (tokens.getLexema() == Sym.COMA) {
                            siguienteToken();
                            if (tokens.getLexema() == Sym.ENTERO || tokens.getLexema() == Sym.CIENTIFICA ||
                                    tokens.getLexema() == Sym.PI || tokens.getLexema() == Sym.FLOAT) {
                                siguienteToken();
                            } else {
                                errorSintactico();
                                break;
                            }
                        }
                    }

                    if (tokens.getLexema() == Sym.P_CIERRE) {
                        siguienteToken();
                    } else {
                        errorSintactico();
                    }
                } else {
                    errorSintactico();
                }
                break;

        }
    }

    private void errorSintactico() {
        this.error = false;
        //descartartodo hasta encontrar ;
        do {
            System.out.println(tokens.toString());
            if (tokens.getLexema() != Sym.PUNTOCOMA) {
                siguienteToken();
            }
        } while (tokens.getLexema() != Sym.PUNTOCOMA && tokens.getLexema() != Sym.EOF ||
                tokens.getLexema() != Sym.C_CIERRE && tokens.getLexema() != Sym.EOF);

    }

    private void siguienteToken() {
        try {
            tokens = analizador.yylex();
            if (tokens == null) {
                tokens = new Tokens("EOF", Sym.EOF, 0, 0);
                throw new IOException("Fin Archivo");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
