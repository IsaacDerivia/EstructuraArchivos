package Tienda;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ventas {


    public static void main(String[] args) {

        //creamos un CRUD para: Analizis de ventas por producto, por fecha, prodducto mas venido, ventas totales y promedio, analisis de precio

        System.out.println("=== Sistema de Ventas ===");
        System.out.println("1. Analisis de ventas por producto\n2. Analisis de ventas por fecha\n3. Producto mas vendido\n4. Ventas totales y promedio\n5. Analisis de precio\n6. Salir");

        Scanner scanner = new Scanner(System.in);
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                System.out.println("Analisis de ventas por producto");
                analisisVentasProducto();
                break;
            case 2:
                System.out.println("Analisis de ventas por fecha");
                analisisVentasFecha();
                break;
            case 3:
                System.out.println("Producto mas vendido");
                productoMasVendido();
                break;
            case 4:
                System.out.println("Ventas totales y promedio");
                ventasTotalesPromedio();
                break;
            case 5:
                System.out.println("Analisis de precio");
                break;
            case 6:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }

    }

    //funcion para analizar ventas por producto
    public static void analisisVentasProducto() {
       /*Lee el archivo Ventas.txt y calcula el total de ventas para cada producto.
           Muestra los productos ordenados de mayor a menor según el total de ventas.
        */
        //creamos un mapa para almacenar las ventas por producto
        Map<String, Double> ventasPorProducto = new HashMap<>();

        //leemos el archivo Ventas.txt
        try (BufferedReader br = new BufferedReader(new FileReader("/home/isaac/Universidad/Estroctura de archivos/untitled/src/main/java/Tienda/ventas.txt"))) {
            //leemos cada línea del archivo
            String linea;
            while ((linea = br.readLine()) != null) {
                //separamos los datos de la línea
                String[] datos = linea.split(",");
                if (datos.length == 6) { // Verifica que la línea tenga el formato correcto
                    String producto = datos[2];
                    try {
                        double total = Double.parseDouble(datos[5]);
                        //actualizamos el total de ventas para el producto
                        if (ventasPorProducto.containsKey(producto)) {
                            total += ventasPorProducto.get(producto);
                        }
                        ventasPorProducto.put(producto, total);
                    } catch (NumberFormatException e) {
                        System.out.println("Error al convertir a número: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            //manejo de excepciones
            System.out.println("Error al leer el archivo Ventas.txt");
            e.printStackTrace();
        }

        List<Map.Entry<String, Double>> listaVentas = new ArrayList<>(ventasPorProducto.entrySet());
        listaVentas.sort(Map.Entry.<String, Double>comparingByValue().reversed());

        for (Map.Entry<String, Double> venta : listaVentas) {
            System.out.println("Producto: " + venta.getKey() + ", Ventas: " + venta.getValue());
        }

    }

    //funcion para analizar ventas por fecha

    public static void analisisVentasFecha() {

        //Lee el archivo Ventas.txt y calcula el total de ventas para cada fecha.

        //creamos un mapa para almacenar las ventas realizadas por día
        Map<String, List<Double>> ventasPorFecha = new HashMap<>();

        //leemos el archivo Ventas.txt
        try (BufferedReader br = new BufferedReader(new FileReader("/home/isaac/Universidad/Estroctura de archivos/untitled/src/main/java/Tienda/ventas.txt"))) {
            //leemos cada línea del archivo
            String linea;
            while ((linea = br.readLine()) != null) {
                //separamos los datos de la línea
                String[] datos = linea.split(",");
                if (datos.length == 6) { // Verifica que la línea tenga el formato correcto
                    String fecha = datos[1];
                    try {
                        double venta = Double.parseDouble(datos[5]);
                        //obtenemos la lista de ventas para la fecha
                        List<Double> ventasDelDia = ventasPorFecha.getOrDefault(fecha, new ArrayList<>());
                        //agregamos la venta a la lista
                        ventasDelDia.add(venta);
                        //actualizamos la lista de ventas para la fecha
                        ventasPorFecha.put(fecha, ventasDelDia);
                    } catch (NumberFormatException e) {
                        System.out.println("Error al convertir a número: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            //manejo de excepciones
            System.out.println("Error al leer el archivo Ventas.txt");
            e.printStackTrace();
        }

        //mostramos las totales por fecha
        for (Map.Entry<String, List<Double>> venta : ventasPorFecha.entrySet()) {
            double total = 0;
            for (double v : venta.getValue()) {
                total += v;
            }
            System.out.println("Fecha: " + venta.getKey() + ", Ventas: " + total);
        }

    }

    //funcion para analizar el producto mas vendido
    public static void productoMasVendido() {
        //Lee el archivo Ventas.txt y calcula el producto más venido, la cantidad de ventas y el porcentaje de ventas a comparacion de los otros productos.

        //creamos un mapa para almacenar las ventas por producto
        Map<String, Double> ventasPorProducto = new HashMap<>();

        //leemos el archivo Ventas.txt
        try (BufferedReader br = new BufferedReader(new FileReader("/home/isaac/Universidad/Estroctura de archivos/untitled/src/main/java/Tienda/ventas.txt"))) {
            //leemos cada línea del archivo
            String linea;
            while ((linea = br.readLine()) != null) {
                //separamos los datos de la línea
                String[] datos = linea.split(",");
                if (datos.length == 6) { // Verifica que la línea tenga el formato correcto
                    String producto = datos[2];
                    try {
                        double total = Double.parseDouble(datos[5]);
                        //actualizamos el total de ventas para el producto
                        if (ventasPorProducto.containsKey(producto)) {
                            total += ventasPorProducto.get(producto);
                        }
                        ventasPorProducto.put(producto, total);
                    } catch (NumberFormatException e) {
                        System.out.println("Error al convertir a número: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            //manejo de excepciones
            System.out.println("Error al leer el archivo Ventas.txt");
            e.printStackTrace();
        }

        //buscamos el producto más vendido
        String productoMasVendido = "";
        double totalVentas = 0;
        for (Map.Entry<String, Double> venta : ventasPorProducto.entrySet()) {
            if (venta.getValue() > totalVentas) {
                productoMasVendido = venta.getKey();
                totalVentas = venta.getValue();
            }
        }

        //mostramos el producto más vendido

        System.out.println("Producto más vendido: " + productoMasVendido + ", Ventas: " + totalVentas);

        //mostramos el porcentaje de ventas para cada producto
        for (Map.Entry<String, Double> venta : ventasPorProducto.entrySet()) {
            double porcentaje = (venta.getValue() / totalVentas) * 100;
            System.out.println("Producto: " + venta.getKey() + ", Ventas: " + venta.getValue() + ", Porcentaje: " + porcentaje + "%");
        }




    }

    //funcion para analizar ventas totales y promedio
    public static void ventasTotalesPromedio() {
        //Lee el archivo Ventas.txt
        // Calcula el total de ventas en el periodo de tiempo que abarca tu archivo.
        //Calcula el promedio de ventas diarias.

        //creamos un mapa para almacenar las ventas por fecha
        Map<String, Double> ventasPorFecha = new HashMap<>();

        //leemos el archivo Ventas.txt
        try (BufferedReader br = new BufferedReader(new FileReader("/home/isaac/Universidad/Estroctura de archivos/untitled/src/main/java/Tienda/ventas.txt"))) {
            //leemos cada línea del archivo
            String linea;
            while ((linea = br.readLine()) != null) {
                //separamos los datos de la línea
                String[] datos = linea.split(",");
                if (datos.length == 6) { // Verifica que la línea tenga el formato correcto
                    String fecha = datos[1];
                    try {
                        double venta = Double.parseDouble(datos[5]);
                        //actualizamos el total de ventas para la fecha
                        if (ventasPorFecha.containsKey(fecha)) {
                            venta += ventasPorFecha.get(fecha);
                        }
                        ventasPorFecha.put(fecha, venta);
                    } catch (NumberFormatException e) {
                        System.out.println("Error al convertir a número: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            //manejo de excepciones
            System.out.println("Error al leer el archivo Ventas.txt");
            e.printStackTrace();
        }

        //calculamos el total de ventas y el promedio
        double totalVentas = 0;
        for (Map.Entry<String, Double> venta : ventasPorFecha.entrySet()) {
            totalVentas += venta.getValue();
        }
        double promedioVentas = totalVentas / ventasPorFecha.size();

        //mostramos el total de ventas y el promedio
        System.out.println("Ventas totales: " + totalVentas);
        System.out.println("Promedio de ventas diarias: " + promedioVentas);


    }

    //funcion para analizar el precio
    public static void analisisPrecio() {
        //Calcula el precio promedio de venta de cada producto.
        //Identifica si hay productos cuyo precio unitario está por encima o por debajo del promedio.
        //Muestra una lista de productos con precios unitarios fuera del rango del 10% del promedio.

        //creamos un mapa para almacenar los precios de venta por producto
        Map<String, List<Double>> preciosPorProducto = new HashMap<>();

        //leemos el archivo Ventas.txt
        try (BufferedReader br = new BufferedReader(new FileReader("/home/isaac/Universidad/Estroctura de archivos/untitled/src/main/java/Tienda/ventas.txt"))) {
            //leemos cada línea del archivo
            String linea;
            while ((linea = br.readLine()) != null) {
                //separamos los datos de la línea
                String[] datos = linea.split(",");
                if (datos.length == 6) { // Verifica que la línea tenga el formato correcto
                    String producto = datos[2];
                    try {
                        double precio = Double.parseDouble(datos[4]);
                        //obtenemos la lista de precios para el producto
                        List<Double> precios = preciosPorProducto.getOrDefault(producto, new ArrayList<>());
                        //agregamos el precio a la lista
                        precios.add(precio);
                        //actualizamos la lista de precios para el producto
                        preciosPorProducto.put(producto, precios);
                    } catch (NumberFormatException e) {
                        System.out.println("Error al convertir a número: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            //manejo de excepciones
            System.out.println("Error al leer el archivo Ventas.txt");
            e.printStackTrace();
        }

        //calculamos el precio promedio de venta para cada producto
        Map<String, Double> precioPromedioPorProducto = new HashMap<>();
        for (Map.Entry<String, List<Double>> precio : preciosPorProducto.entrySet()) {
            double total = 0;
            for (double p : precio.getValue()) {
                total += p;
            }
            double promedio = total / precio.getValue().size();
            precioPromedioPorProducto.put(precio.getKey(), promedio);
        }

        //identificamos los productos cuyo precio unitario está por encima o por debajo del promedio
        for (Map.Entry<String, List<Double>> precio : preciosPorProducto.entrySet()) {
            for (double p : precio.getValue()) {
                double promedio = precioPromedioPorProducto.get(precio.getKey());
                if (p > promedio * 1.1 || p < promedio * 0.9) {
                    System.out.println("Producto: " + precio.getKey() + ", Precio: " + p + ", Promedio: " + promedio);
                }
            }
        }
        



    }



}


