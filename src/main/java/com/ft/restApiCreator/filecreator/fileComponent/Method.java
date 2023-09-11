package com.ft.restApiCreator.filecreator.fileComponent;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Method {

    String accessModifier;
    String returnType;
    String name;
    String implementation;

    List<Parametre> parametres;

    List<String> annotations;

}