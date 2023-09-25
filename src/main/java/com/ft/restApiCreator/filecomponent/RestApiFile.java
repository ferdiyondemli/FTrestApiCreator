package com.ft.restApiCreator.filecomponent;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


@Getter
@Setter
@Slf4j
public abstract class RestApiFile {


    String packageName;

    List<String> annotations;

    String javaName;
    String extenedClass;
    List<String> implementedInterfaces;

    List<Field> fields;
    List<Method> methods;

    String type;
    String extension ;

    public void createJavaFile(String path) throws IOException {
        File myObj = new File(path + "/" + javaName + "." + extension);
        if (myObj.createNewFile()) {
            log.info("File created: " + myObj.getName() + path);
            writeFile(myObj.getPath(), path);
        } else {
            throw  new RuntimeException("File: "+javaName+" already exists.");
        }
    }

    public void writeFile(String fileName, String path) {
        String text = createFileText(path);
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(text);
            myWriter.close();
            log.info("Successfully wrote to the file.");
        } catch (IOException e) {
            e.printStackTrace();

            throw  new RuntimeException("An error occurred while writing text");
        }
    }

    abstract String createFileText(String path);

    abstract String getFileFields();

}