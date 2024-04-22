package Encapsular;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ArchivoEmpleados {

    static Scanner sc = new Scanner(System.in);

    private static  final String FILENAME = "/home/isaac/Universidad/Estroctura de archivos/untitled/src/main/java/Encapsular/Empleados.txt";

    public static void agregarEmpleados(Empleados empleado) {
        try(FileWriter fw = new FileWriter(FILENAME,true);
        BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {
            out.println(empleado.toString());
            System.out.println("Empleado agregado correctamente");

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static  List<Empleados> ListarEmpleados(){
        List<Empleados> empleados = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String Line;
            while ((Line = br.readLine())!= null) {
                String[] partes = Line.split(",");
                if (partes.length < 5) {
                    System.err.println("salte la linea por no cumplir con el formato " + Line);
                    continue;
                }
                String nombre = partes[0].split(":")[1].trim();
                int edad = Integer.parseInt(partes[1].split(":")[1].trim());
                String departamento = partes[2].split(":")[1].trim();
                double salario = Double.parseDouble(partes[3].split(":")[1].trim());
                int id = Integer.parseInt(partes[4].split(":")[1].trim());
                empleados.add(new Empleados(nombre, edad, departamento, salario,id));
            }


        } catch (IOException e){
            e.printStackTrace();
        }

        return empleados;

    }

    //metodo para buscar empleado por id
    public static Empleados buscarEmpleado(int id, List<Empleados> empleados) {
        //convertir id a string
        String idString = String.valueOf(id);

        try {
            BufferedReader br = new BufferedReader(new FileReader(FILENAME));
            String Line;
            while ((Line = br.readLine()) != null) {
                if (Line.contains("id: " + idString)) {
                    //se encontro el empleado
                    String[] partes = Line.split(",");
                    //empleado entontrado, buscarlo en la lista de empleados y retornarlo
                    for (Empleados empleado : empleados) {
                        if (empleado.getId() == id) {
                            return empleado;
                        }
                    }


                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;


    }


    public static void main(String[] args) {
        Empleados empleado1 = new Empleados("Juan perez", 30, "Ventas", 2500, 1);
        Empleados empleados2 = new Empleados("Maria Lopez", 35, "Marketing", 2500, 2);


        //agregar empleados
        agregarEmpleados(empleado1);
        agregarEmpleados(empleados2);

        //Listar los empleados
        System.out.println("\nListado de todos los empleados");
        List<Empleados> todolosEmpleados = ListarEmpleados();
        for (Empleados empleados : todolosEmpleados) {
            System.out.println(empleados);
        }

        //busca empleado por id
        System.out.println("Ingrese el id del empleado a buscar");
        int id = sc.nextInt();
        Empleados empleado = buscarEmpleado(id, todolosEmpleados);
        if (empleado != null) {
            System.out.println("Empleado encontrado: " + empleado);
        } else {
            System.out.println("Empleado no encontrado");
        }

        //modificar empleado con el id ingresado
        //mostrar empleado
        System.out.println(empleado);
        //modificar empleado
        System.out.println("Ingrese el nuevo nombre del empleado");
        String nombre = sc.next();
        empleado.setNombre(nombre);
        System.out.println("Ingrese la nueva edad del empleado");
        int edad = sc.nextInt();
        empleado.setEdad(edad);
        System.out.println("Ingrese el nuevo departamento del empleado");
        String departamento = sc.next();
        empleado.setDepartamento(departamento);
        System.out.println("Ingrese el nuevo salario del empleado");
        double salario = sc.nextDouble();
        empleado.setSalario(salario);
        //mostrar empleado modificado
        System.out.println(empleado);

        //modificar en el archivo el empleado modificado
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME));
             BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME));
             PrintWriter out = new PrintWriter(bw)) {
            String Line;
            while ((Line = br.readLine()) != null) {
                if (Line.contains("id: " + id)) {
                    out.println(empleado.toString());
                } else {
                    out.println(Line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //eliminar un empleado
        System.out.println("Ingrese el id del empleado a eliminar");
        int idEliminar = sc.nextInt();
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME));
             BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME));
             PrintWriter out = new PrintWriter(bw)) {
            String Line;
            while ((Line = br.readLine()) != null) {
                if (Line.contains("id: " + idEliminar)) {
                    continue;
                } else {
                    out.println(Line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        






    }




}
