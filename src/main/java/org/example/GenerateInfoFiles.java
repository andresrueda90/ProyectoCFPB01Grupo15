package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


/**
 * Clase para generar información de los archivos formato .csv
 */
public class GenerateInfoFiles {
    private static final String DIRECTORY_PATH = "archivos";

    /** Lista de nombres disponibles para los vendedores. */
    private static final List<String> FIRST_NAMES = Arrays.asList("Carlos", "Juan", "Andres", "Felipe", "Luis", "Miguel", "David", "Santiago", "Pedro", "Jorge");

    /** Lista de apellidos disponibles para los vendedores. */
    private static final List<String> LAST_NAMES = Arrays.asList("Gomez", "Rodriguez", "Perez", "Fernandez", "Lopez", "Diaz", "Martinez", "Torres", "Ramirez", "Vargas");

    /** Lista de tipos de documentos disponibles. */
    private static final List<String> DOCUMENT_TYPES = Arrays.asList("Cedula", "Nit", "Pasaporte");

    /** Generador de numeros Aleatorios . */
    private static final Random RANDOM = new Random();

    /**
     * Permite crear el directorio.
     *
     * @param nameDir Nombre del directorio.
     *
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
     * @param nameFile Nombre del archivo.
     *
     * @return true si se crear el archivo plano correctamente , de lo contrario retorna false
     */
    public boolean createSalesManInfoFile(int salesmanCount, String nameFile) {
        if (!createPathDirectory(DIRECTORY_PATH)) {
            return false;
        }

        String fileName = DIRECTORY_PATH + "/" +nameFile;
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
}
