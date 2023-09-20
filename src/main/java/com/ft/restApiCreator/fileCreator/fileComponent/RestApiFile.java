package com.ft.restApiCreator.fileCreator.fileComponent;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class RestApiFile {
    String packageName;

    List<String> annotations;

    String javaName;
    String extenedClass;
    List<String> implementedInterfaces;

    List<Field> fields;
    List<Method> methods;

    String type;
    String extension = "java";
}