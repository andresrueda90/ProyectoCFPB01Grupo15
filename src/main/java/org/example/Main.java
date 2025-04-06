package org.example;


public class Main {
    /**
     * Método principal que ejecuta la generación de los archivos
     *
     */
    public static void main(String[] args) {
        /**
         * Instancia de GenerateInfoFiles para manejar la creación de los archivos.
         */
        GenerateInfoFiles generateInfoFiles = new GenerateInfoFiles();
        boolean createSalesManInfoFile =  generateInfoFiles.createSalesManInfoFile(10, "vendedores.csv");
        // Generar el archivo de productos con 10 productos
        boolean createProductsFile = generateInfoFiles.createProductsFile(10, "productos.csv");

        boolean createSalesMenFile = generateInfoFiles.createSalesMenFile(10, "Luis_Ruiz", 72124781);

        boolean createSalesMenFileMassive = generateInfoFiles.createSalesMenFileMassive("vendedores.csv","productos.csv");

        /**
         * Verifica si hubo un error en la creación del archivo y muestra un mensaje en la consola.
         */
        if(!createSalesManInfoFile){
            System.err.println("Error al crear el archivo vendedores");
        }

        if (!createProductsFile) {
            System.err.println("Error al crear el archivo de productos.");
        }

        // Crear un archivo de ventas para un vendedor específico
        if (!createSalesMenFile){
            System.err.println("Error al crear el archivo ventas por vendedor.");
        };

        // Crear un archivo de ventas con la lectura de archivo de vendedor y productos, cantidad aleatorio
        if (!createSalesMenFileMassive){
            System.err.println("Error al crear el archivo ventas por vendedor en lectura archivo plano.");
        };

    }


}