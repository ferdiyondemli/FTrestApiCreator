package com.ft.restApiCreator.fileCreator;

import com.ft.restApiCreator.fileCreator.fileComponent.DirectoryFile;
import com.ft.restApiCreator.fileCreator.fileComponent.RestApiFile;
import com.ft.restApiCreator.fileCreator.fileComponent.JavaRestApiFile;
import com.ft.restApiCreator.fileCreator.fileComponent.JsRestApiFile;

 import java.io.IOException;

public interface FileCreator {

    void createDirectory(DirectoryFile directoryFile, String path) throws IOException;

    void createJavaFile(RestApiFile javaFile, String path) throws IOException;

    void writeFile(RestApiFile javaFile, String fileName, String path);

    String createJavaFileText(JavaRestApiFile javaFile, String path);
    String createJsFileText(JsRestApiFile jsFile, String path);

}
