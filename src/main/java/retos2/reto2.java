package retos2;

import java.io.*;
import java.util.*;

public class reto2 {
    public static void main(String[] args) throws IOException {
        // Crear un BufferedReader para leer el archivo de entrada
        BufferedReader br = new BufferedReader(new FileReader("/home/isaac/Universidad/Estroctura de archivos/untitled/src/main/java/retos2/entrada.txt"));

        // Crear una lista de listas para almacenar los bloques de nombres
        ArrayList<ArrayList<String>> bloques = new ArrayList<>();
        String line;

        // Leer el archivo de entrada línea por línea
        while ((line = br.readLine()) != null) {
            // Convertir la línea leída a un número entero que representa la cantidad de nombres en el bloque
            int numNombres = Integer.parseInt(line);

            // Crear una lista para almacenar los nombres en el bloque actual
            ArrayList<String> bloque = new ArrayList<>();
            for (int i = 0; i < numNombres; i++) {
                // Agregar cada nombre al bloque
                bloque.add(br.readLine());
            }

            // Agregar el bloque a la lista de bloques
            bloques.add(bloque);
        }
        // Cerrar el BufferedReader
        br.close();

        // Crear un PrintWriter para escribir en el archivo de salida
        PrintWriter output = new PrintWriter(new File("/home/isaac/Universidad/Estroctura de archivos/untitled/src/main/java/retos2/salida.txt"));

        // Iterar sobre cada bloque de nombres
        for (ArrayList<String> bloque : bloques) {
            // Inicializar contadores para 'D' y 'R'
            int totalD = 0, totalR = 0;

            // Contar la cantidad de 'D' y 'R' en cada nombre del bloque
            for (String palabra : bloque) {
                for (char c : palabra.toCharArray()) {
                    if (c == 'D') totalD++;
                    if (c == 'R') totalR++;
                }
            }


            // Inicializar el contador para "DR"
            int countDR = 0;
            boolean nextIsD = true;

            // Simular la asignación de letras a los colores
            for (String palabra : bloque) {
                for (char c : palabra.toCharArray()) {
                    if (nextIsD && c == 'D') {
                        nextIsD = false;
                    } else if (!nextIsD && c == 'R') {
                        nextIsD = true;
                        countDR++;
                    }
                }
            }

            // Escribir el contador de "DR" en el archivo de salida
            output.println(countDR);
        }
        // Cerrar el PrintWriter
        output.close();
    }
}