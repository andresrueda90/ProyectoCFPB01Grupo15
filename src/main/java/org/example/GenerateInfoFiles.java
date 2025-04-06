package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.io.*;
import java.util.*;


/**
 * Clase para generar información de los archivos formato .csv
 */
public class GenerateInfoFiles {
    private static final String DIRECTORY_PATH = "datos";

    /**
     * Generador de numeros Aleatorios .
     */
    private static final Random RANDOM = new Random();
    /**
     * Lista de nombres disponibles para los vendedores.
     */
    private static final List<String> FIRST_NAMES = Arrays.asList("Carlos", "Juan", "Andres", "Felipe", "Luis", "Miguel", "David", "Santiago", "Pedro", "Jorge");

    /**
     * Lista de apellidos disponibles para los vendedores.
     */
    private static final List<String> LAST_NAMES = Arrays.asList("Gomez", "Rodriguez", "Perez", "Fernandez", "Lopez", "Diaz", "Martinez", "Torres", "Ramirez", "Vargas");

    /**
     * Lista de tipos de documentos disponibles.
     */
    private static final List<String> DOCUMENT_TYPES = Arrays.asList("Cedula", "Nit", "Pasaporte");


    /**
     * Permite crear el directorio.
     *
     * @param nameDir Nombre del directorio.
     * @return true si el directorio fue creado o ya existía,  y  false si ocurre  un error.
     */
    public boolean createPathDirectory(String nameDir) {
        try {
            File directory = new File(nameDir);
            if (!directory.exists()) {
                directory.mkdir();
            }
            return true;
        } catch (Exception e) {
            System.err.println("Error al crear directorio: " + e.getMessage());
        }

        return false;
    }

    /**
     * Crea un archivo con información de vendedores ".
     *
     * @param salesmanCount Cantidad de vendedores a generar.
     * @param nameFile      Nombre del archivo.
     * @return true si se crear el archivo plano correctamente , de lo contrario retorna false
     */


    public boolean createSalesManInfoFile(int salesmanCount, String nameFile) {
        if (!createPathDirectory(DIRECTORY_PATH)) {
            return false;
        }

        String fileName = DIRECTORY_PATH + "/" + nameFile;
        try (FileWriter writer = new FileWriter(fileName)) {
            ;
            for (int i = 0; i < salesmanCount; i++) {
                if (i == 0) {
                    /** Se crea el encabezado del archivo */
                    String line = String.format("%s;%s;%s;%s\n", "Tipo Documento", "Numero Documento", "Nombres Vendedor", "Apellidos Vendedor");
                    writer.write(line);
                }
                String docType = DOCUMENT_TYPES.get(RANDOM.nextInt(DOCUMENT_TYPES.size())); // Por medio del random se elige un tipo de documento de la lista
                String docNumber = String.valueOf(10000000 + RANDOM.nextInt(90000000)); // Número de documento con al menos 8 digitos
                String name = FIRST_NAMES.get(RANDOM.nextInt(FIRST_NAMES.size())); // Por medio del random se elige un nombre de la lista
                String lastName = LAST_NAMES.get(RANDOM.nextInt(LAST_NAMES.size())); // Por medio del random se elige un apellido de la lista

                String line = String.format("%s;%s;%s;%s\n", docType, docNumber, name, lastName); // formatea la linea de texto
                writer.write(line);// Se agrega a linea de texto al archivo
            }
            System.out.println("Archivo generado correctamente: " + fileName);
            return true;
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
            return false;
        }
    }



    /**
     * Crea un archivo con información pseudoaleatoria de productos basada en archivos existentes.
     *
     * @param productsCount Cantidad de productos a generar.
     * @param nameFile Nombre del archivo a generar.
     *
     * @return true si el archivo se crea correctamente, false en caso de error.
     */
    public boolean createProductsFile(int productsCount, String nameFile) {
        File directory = new File(DIRECTORY_PATH);

        if (!directory.exists() || !directory.isDirectory()) {
            System.err.println("El directorio no existe o no es una carpeta válida.");
            return false;
        }

        String fileName = DIRECTORY_PATH + "/" + nameFile;
        Set<String> existingProducts = new HashSet<>();
        List<String> productNamesPool = Arrays.asList("Laptop", "Mouse", "Teclado", "Monitor", "Impresora",
                "Escritorio", "Silla Gamer", "Disco Duro", "Memoria USB", "Auriculares");

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("ID Producto;Nombre Producto;Precio Por Unidad\n");

            for (int i = 1; i <= productsCount; i++) {
                String productId = String.format("P%04d", i);

                // Selecciona un nombre de producto único
                String productName;
                do {
                    productName = productNamesPool.get(RANDOM.nextInt(productNamesPool.size()));
                } while (existingProducts.contains(productName));

                existingProducts.add(productName);
                double price = 10 + (990 * RANDOM.nextDouble()); // Precio entre 10 y 1000

                writer.write(String.format(Locale.US, "%s;%s;%.2f\n", productId, productName, price));

                //writer.write(String.format("%s;%s;%.2f\n", productId, productName, price));
            }

            System.out.println("Archivo de productos generado correctamente: " + fileName);
            return true;
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo de productos: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método para generar un archivo plano pseudoaleatorio de ventas de un vendedor.
     *
     * @param randomSalesCount Cantidad de ventas aleatorias.
     * @param name             Nombre del vendedor.
     * @param id               ID del vendedor.
     */
    public static boolean createSalesMenFile(int randomSalesCount, String name, long id) {
        File directory = new File(DIRECTORY_PATH);

        if (!directory.exists() || !directory.isDirectory()) {
            System.err.println("El directorio no existe o no es una carpeta válida.");
            return false;
        }

        String fileName = DIRECTORY_PATH + "/ventas_" + id + ".csv";

        Random random = new Random();
        try (FileWriter writer = new FileWriter(fileName)) {
            for (int i = 0; i < randomSalesCount; i++) {
                writer.write(name + ";" + id + "\n");
                for (int j = 0; j < random.nextInt(10) + 1; j++) {
                    int productId = random.nextInt(100) + 1; // Id de producto aleatorio entre 1 y 100
                    int quantity = random.nextInt(100) + 1; // Cantidad vendida aleatoria entre 1 y 100
                    writer.write(productId + ";" + quantity + "\n");
                }
            }
            System.out.println("Archivo de ventas del vendedor " + name + " creado correctamente.");
            return true;
        } catch (IOException e) {
            System.err.println("Error al crear el archivo de ventas del vendedor " + name + ": " + e.getMessage());
            return false;
        }
    }

    /**
     * Método para generar un archivos planos de ventas de vendedores en lectura de archivo vendedor y productos, cantidad aleatorias.
     *
     * @param vendedores nombre archivo con datos de vendedores.
     * @param productos  Nombre  archivo con datos de productos.
     */
    public static boolean createSalesMenFileMassive(String vendedores, String productos) {
        String fileSellers = null;
        String fileProducts = null;
        String fileName = null;
        String lineSellers = null;

        File directory = new File(DIRECTORY_PATH);

        if (!directory.exists() || !directory.isDirectory()) {
            System.err.println("El directorio no existe o no es una carpeta válida.");
            return false;
        }

        fileSellers = DIRECTORY_PATH+"/"+vendedores;
        fileProducts = DIRECTORY_PATH+"/"+productos;
        File fileSellersB = new File(fileSellers);
        File fileProductsB = new File(fileProducts);

        if (!fileSellersB.exists() || !fileProductsB.exists()){
            System.err.println("Alguno de los archivos no existe o no es válido.");
            return false;
        }

        try {
            FileReader openFileSellers = new FileReader(fileSellersB);
            BufferedReader readFileSellers = new BufferedReader(openFileSellers);
            int lineFileSellers = 0;
            String sCadena = null;
            while ((sCadena = readFileSellers.readLine())!=null) {
                lineFileSellers++;
            }
            Random random = new Random();
            int selectedSeller = 0;
            selectedSeller = random.nextInt(lineFileSellers) + 1;
            lineFileSellers=-1;
            String id = null;
            String name = null;
            openFileSellers = new FileReader(fileSellersB);
            readFileSellers = new BufferedReader(openFileSellers);
            while ((sCadena = readFileSellers.readLine())!=null) {
                String[] sellerDetail = sCadena.split(";");
                lineFileSellers++;
                if (lineFileSellers==selectedSeller){
                    id = sellerDetail[1];
                    name = sellerDetail[2]+"_"+sellerDetail[3];
                    break;
                }
            }
            int randomSalesCount = random.nextInt(10) + 1;

            fileName = DIRECTORY_PATH + "/ventas_" + id + ".csv";

            try (FileWriter writer = new FileWriter(fileName)) {
                writer.write(name + ";" + id + "\n");
                for (int i = 0; i < randomSalesCount; i++) {
                        int productId = random.nextInt(100) + 1; // Id de producto aleatorio entre 1 y 100
                        int quantity = random.nextInt(100) + 1; // Cantidad vendida aleatoria entre 1 y 100
                        writer.write(productId + ";" + quantity + "\n");
                }
                System.out.println("Archivo de ventas del vendedor " + name + " creado correctamente.");
                return true;
            } catch (IOException e) {
                System.err.println("Error al crear el archivo de ventas del vendedor " + name + ": " + e.getMessage());
                return false;
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo  vendedor " + fileSellers + ": " + e.getMessage());
            return false;
        }

    }

    //Creacion de generacion Reporte de Ventas
    public boolean generateReportSales(String productFileName, String summaryFileName) {
        Map<String, ProductInfo> productMap = new HashMap<>();
        String productFile = DIRECTORY_PATH + "/" + productFileName;
        String summaryFile = DIRECTORY_PATH + "/" + summaryFileName;

        // Codigo que lee el archivo de productos.csv y construye el encabezado de los produtocs
        try (BufferedReader br = new BufferedReader(new FileReader(productFile))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 3) {
                    String id = parts[0];
                    String name = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    productMap.put(id, new ProductInfo(id, name, price));
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo archivo de productos: " + e.getMessage());
            return false;
        }

        // Lee el archivo de ventas y suma las cantidades
        File folder = new File(DIRECTORY_PATH);
        File[] files = folder.listFiles((dir, name) -> name.startsWith("ventas_") && name.endsWith(".csv"));
        if (files == null) return false;

        for (File file : files) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.contains(";")) {
                        String[] parts = line.split(";");
                        if (parts.length == 2) {
                            String productId = parts[0];
                            int quantity = Integer.parseInt(parts[1]);

                            ProductInfo info = productMap.get(productId);
                            if (info != null) {
                                info.addSales(quantity);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Error leyendo archivo de ventas " + file.getName() + ": " + e.getMessage());
            }
        }

        // Logica que ordena los productos por cantidad
        List<ProductInfo> ordered = new ArrayList<>(productMap.values());
        ordered.removeIf(p -> p.promedioTotal == 0); // Solo los que se vendieron
        ordered.sort(Comparator.comparingInt(p -> -p.promedioTotal)); // Descendente

        // Logica que elimina el archivo y vuelve y lo crea
        File summary = new File(summaryFile);
        if (summary.exists()) {
            if (!summary.delete()) {
                System.err.println("No se pudo eliminar el archivo existente: " + summaryFile);
                return false;
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(summary))) {
            for (ProductInfo p : ordered) {
                bw.write(String.format(Locale.US, "%s;%.2f;%d\n", p.nombre, p.precio, p.promedioTotal));
            }
            System.out.println("Reporte Ventas generado correctamente: " + summaryFile);
            return true;
        } catch (IOException e) {
            System.err.println("Error escribiendo el reporte de ventas: " + e.getMessage());
            return false;
        }
    }

    // Organizacion de la informacion de productos interno
    private static class ProductInfo {
        String id;
        String nombre;
        double precio;
        int promedioTotal;

        ProductInfo(String id, String name, double price) {
            this.id = id;
            this.nombre = name;
            this.precio = price;
            this.promedioTotal = 0;
        }

        void addSales(int quantity) {
            this.promedioTotal += quantity;
        }
    }

    /* inicio  generateReportSalesVendors */

    public boolean generateReportSalesVendors(String nameReportVendors) {
        Set<Vendor> vendors = new HashSet<>();
        String summaryFile = DIRECTORY_PATH + "/" + nameReportVendors;
        File folder = new File(DIRECTORY_PATH);
        File[] files = folder.listFiles((dir, name) -> name.startsWith("ventas_") && name.endsWith(".csv"));
        if (files != null) {
            for (File archivo : files) {
                readFile(archivo, vendors);
            }
        }
        File fileReportVendors = new File(summaryFile);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileReportVendors))) {
            for (Vendor vendor : vendors) {
                bw.write(vendor.toString());
            }
            System.out.println("Reporte ventas de vendedores generado correctamente: " + summaryFile);
            return true;
        } catch (IOException e) {
            System.err.println("Error escribiendo el reporte de ventas de vendedores: " + e.getMessage());
            return false;
        }
    }

    public void readFile(File archivo, Set<Vendor> vendors) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            String id = "";
            String nameVendor = "";
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (!isNum(partes[0])) {
                    id = partes[1].trim();
                    nameVendor = partes[0].trim();
                    addVendor(vendors, id, nameVendor, 0);
                }else if(isNum(partes[0]) && !id.isEmpty()){
                    int cant = Integer.parseInt(partes[1].trim());
                    addVendor(vendors, id, nameVendor, cant);
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo archivo " + archivo.getName() + ": " + e.getMessage());
        }
    }

    public void addVendor(Set<Vendor> vendors, String identificacion, String nombre, int cantidad) {
        Vendor newVendor = new Vendor(identificacion, nombre, cantidad);
        if (vendors.contains(newVendor)) {
            for (Vendor v : vendors) {
                if (v.equals(newVendor)) {
                    v.agregarProductos(cantidad);
                    break;
                }
            }
        } else {
            vendors.add(newVendor);
        }
    }

    public boolean isNum(String str) {
        return str.matches("-?\\d+(\\.\\d+)?"); // valida si es numero  acepta positivos negativos  y puntos
    }

    public class Vendor {
        private String id;
        private String name;
        private int cant;

        public Vendor(String id, String name, int cant) {
            this.id = id;
            this.name = name;
            this.cant = cant;
        }

        public void agregarProductos(int cantidad) {
            this.cant += cantidad;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Vendor)) return false;
            Vendor v = (Vendor) o;
            return id.equals(v.id) && name.equals(v.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        @Override
        public String toString() {
            return String.format(Locale.US, "%s;%s;%d\n", id, name, cant);
        }
    }

    /* fin  generateReportSalesVendors */

}
