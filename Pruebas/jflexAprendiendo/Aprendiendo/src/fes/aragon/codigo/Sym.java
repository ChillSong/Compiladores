package Pruebas.jflexAprendiendo.Aprendiendo.src.fes.aragon.codigo;

public class Sym {
    public static final int PAREN_A = 0;
    public static final int PAREN_C = 1;
    public static final int MAS = 2;
    public static final int MENOS = 3;
    public static final int POR = 4;
    public static final int POTENCIA = 5;
    public static final int PORCENTAJE = 6;
    public static final int IGUAL = 7;
    public static final int FLOTANTE = 8;
    public static final int FUNCION = 9;
    public static final int RESERVADA = 10;
    public static final int ID = 11;
    public static final int INSTRUCCION_COMPLETA = 12;

    // Arreglo con los nombres de los tokens
    public static final String[] terminales = new String[] {
            "PAREN_A",  // 0
            "PAREN_C",  // 1
            "MAS",      // 2
            "MENOS",    // 3
            "POR",      // 4
            "POTENCIA", // 5
            "PORCENTAJE", // 6
            "IGUAL",    // 7
            "FLOTANTE", // 8
            "FUNCION",  // 9
            "RESERVADA", // 10
            "ID"        // 11
    };
}
