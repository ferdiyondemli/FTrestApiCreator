package com.ft.restApiCreator.filecomponent;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Slf4j
public class DirectoryFile  {
     String directoryName;
     String directoryPath;
    List<? extends RestApiFile> javaFiles;
    List<DirectoryFile> directoryFiles;

    public void createDirectory( ) throws IOException {


        File f = new File(directoryPath + "/" + directoryName);

        if (f.mkdir()) {
            log.info("Directory named as " + getDirectoryName() + " has been created successfully");

            if (getDirectoryFiles() != null) {

                getDirectoryFiles().forEach(directoryFile -> {
                    directoryFile.directoryPath = directoryPath + "/" + directoryName;
                    try {
                        directoryFile.createDirectory();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                });

            }


            if (getJavaFiles() != null) {

                String finalPath = directoryPath;
                getJavaFiles().forEach(javaFile -> {
                    try {
                        javaFile.createJavaFile(finalPath + "/" + getDirectoryName());
                        log.info("File named as " + javaFile.getJavaName() + " has been created successfully");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                });
            }


        } else {
            throw new RuntimeException("Directory named as " + directoryName + " cannot be created path: " + directoryPath);

        }
     }

}
