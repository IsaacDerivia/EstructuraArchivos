package retos;
//import para lecutra de archivos
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class reto1 {
    public static void main(String[] args)  throws IOException{
        //se lee los datos de entrada txt
        BufferedReader br = new BufferedReader(new FileReader("/home/isaac/Universidad/Estroctura de archivos/untitled/src/main/java/retos/entrada.txt"));
        //se crea un arraylist para guardar los datos
        ArrayList<String> datos = new ArrayList<String>();
        //se guarda los datos en el arraylist
        String line;
        while ((line = br.readLine()) != null) {
            datos.add(line);
        }
        //se cierra el archivo
        br.close();
        //se crea una arraylist donde se lee los datos, se pone s si la letra no tiene i y n si la palabra tiene i
        ArrayList<String> resultado = new ArrayList<String>();
        //se recorre el arraylist de datos
        for (int i = 1; i < datos.size(); i++) {
            //se guarda la palabra en una variable
            String palabra = datos.get(i);
            //se crea una variable para saber si la palabra tiene i
            boolean tieneI = false;
            //se recorre la palabra
            for (int j = 0; j < palabra.length(); j++) {
                //si la palabra tiene i se cambia la variable a true
                if (palabra.charAt(j) == 'i') {
                    tieneI = true;
                }
            }
            //si la palabra tiene i se guarda n en el arraylist resultado
            if (tieneI) {
                resultado.add("s");
            } else {
                //si la palabra no tiene i se guarda s en el arraylist resultado
                resultado.add("nz");
            }
        }
        //se crea un archivo de salida
        java.io.File file = new java.io.File("/home/isaac/Universidad/Estroctura de archivos/untitled/src/main/java/retos/salida.txt");
        //se crea un archivo de salida
        java.io.PrintWriter output = new java.io.PrintWriter(file);
        //se escribe en el archivo de salida
        for (int i = 0; i < resultado.size(); i++) {
            output.println(resultado.get(i));
        }
        //se cierra el archivo de salida
        output.close();



    }
}
