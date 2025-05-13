import java.io.*;

public class Uno {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\anton\\Desktop\\Mike\\Uno\\Texto.txt"));
            String input;

            while ((input = reader.readLine()) != null) {
                System.out.println("Cadena leída: " + input);
                boolean esValida = procesarCadena(input);
                if (esValida) {
                    System.out.println("Resultado: Cadena válida\n");
                } else {
                    System.out.println("Resultado: Cadena no válida\n");
                }
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public static boolean procesarCadena(String input) {
        int estado = 1;

        for (int i = 0; i < input.length(); i++) {
            char simbolo = input.charAt(i);

            switch (estado) {
                case 1:
                    if (simbolo == 'a') {
                        estado = 1;
                    } else if (simbolo == 'b') {
                        estado = 2;
                    } else {
                        return false;
                    }
                    break;

                case 2:
                    if (simbolo == 'a') {
                        estado = 3;
                    } else {
                        return false;
                    }
                    break;

                case 3:
                    return false;
            }
        }

        return estado == 3;
    }
}
