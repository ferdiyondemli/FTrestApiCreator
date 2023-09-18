package com.ft.restApiCreator.fileCreator;

import com.ft.restApiCreator.fileCreator.fileComponent.DirectoryFile;
import com.ft.restApiCreator.fileCreator.fileComponent.JavaFile;

import java.io.IOException;

public interface FileCreator {

    void createDirectory(DirectoryFile directoryFile, String path) throws IOException;

    void createJavaFile(JavaFile javaFile, String path) throws IOException;

    void writeFile(JavaFile javaFile, String fileName, String path);

    String createJavaFileText(JavaFile javaFile, String path);

}
