# Proyecto JavaFX

Este es un proyecto desarrollado en **JavaFX** utilizando **IntelliJ IDEA**. La aplicación tiene como objetivo desarrollar el trabaja colaborativo .

## Tecnologías utilizadas
- Java 21+ 
- JavaFX
- IntelliJ IDEA
- [Maven]

## Instalación y configuración

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/andresrueda90/FilesGenerator.git
   cd FilesGenerator
   ```
2. **Abrir en IntelliJ IDEA**
   - Selecciona "Abrir proyecto" y elige la carpeta clonada.
   - Espera a que IntelliJ configure las dependencias.

3. **Ejecutar el proyecto**
   - Si usas **Maven**:
     ```bash
     mvn clean javafx:run
     ```

## Estructura del proyecto
```
📂 FilesGenerator
 ┣ 📂 src
 ┃ ┣ 📂 main
 ┃ ┃ ┣ 📂 java
 ┃ ┃ ┃ ┗ 📂 com.example.filesgenerator
 ┃ ┃ ┃ ┃ ┣ HelloApplication.java
 ┃ ┃ ┃ ┃ ┗ HelloController.java
 ┃ ┃ ┗ 📂 resources
 ┃ ┃ ┃ ┗ 📂 views
 ┃ ┃ ┃ ┃ ┗ hello-view.fxml
 ┣ 📜 pom.xml (si usas Maven)
 ┣ 📜 build.gradle (si usas Gradle)
 ┗ 📜 README.md
```


