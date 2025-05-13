/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fes.aragon.codigo;

/**
 *
 * @author MASH
 */
public class Sym {
    public static final int ENTERO = 0;
    public static final int ID = 1;
    public static final int PUNTOCOMA = 2;
    public static final int SALTOLINEA = 3;
    public static final int EOF = 4;
    public static final int IGUAL = 5;
    public static final int MAS = 6;
    public static final int MENOS = 7;
    public static final int ENTRE = 8;
    public static final int POR = 9;
    public static final int RESERVADAS = 10;
    public static final int FLOAT = 11;
    public static final int CIENTIFICA = 12;
    public static final int P_APERTURA = 13;
    public static final int P_CIERRE = 14;
    public static final int C_APERTURA = 15;
    public static final int C_CIERRE = 16;


    public static final String[] terminales = new String[] {
            "ENTERO",
            "ID",
            "PUNTOCOMA",
            "SALTOLINEA",
            "EOF",
            "IGUAL",
            "MAS",
            "MENOS",
            "ENTRE",
            "POR",
            "RESERVADAS",
            "FLOAT",
            "CIENTIFICA",
            "P_APERTURA",
            "P_CIERRE",
            "C_APERTURA",
            "C_CIERRE",

    };
}
