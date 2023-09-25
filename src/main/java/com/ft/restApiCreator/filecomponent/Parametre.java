package com.ft.restApiCreator.filecomponent;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.ft.restApiCreator.filecomponent.Utils.capFirstLetter;

@Getter
@Setter
public class Parametre {
    public String type;
    public String name;

    public List<String> annotations;

    public String getAnnotationsText( ) {
        if (annotations == null) return "";

        StringBuilder string = new StringBuilder(" ");

        for (int i = 0; i < annotations.size(); i++) {
            string.append("@" + capFirstLetter(annotations.get(i)) + " ");
        }

        return string.toString();


    }


}
