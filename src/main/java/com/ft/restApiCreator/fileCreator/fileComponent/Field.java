package com.ft.restApiCreator.fileCreator.fileComponent;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Field {

    String accessModifier;
    String name;
    String type;

    List<String> annotations;

    Boolean isQueryable;
    Boolean isEnum;
    String yteInputType;







}
