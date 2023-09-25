package com.ft.restApiCreator.filecomponent;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.ft.restApiCreator.filecomponent.Utils.capFirstLetter;

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


    String getAnnotationsText(  ) {
        if (annotations == null) return "";

        StringBuilder string = new StringBuilder(" ");

        for (int i = 0; i < annotations.size(); i++) {
            string.append("@" + capFirstLetter(annotations.get(i)) + "\n");
        }

        return string.toString();


    }


}
