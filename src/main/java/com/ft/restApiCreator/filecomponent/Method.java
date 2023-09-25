package com.ft.restApiCreator.filecomponent;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.ft.restApiCreator.filecomponent.Utils.capFirstLetter;

@Getter
@Setter
public class Method {

    String accessModifier;
    String returnType;
    String name;
    String implementation;

    List<Parametre> parametres;

    List<String> annotations;

    String getParametresText() {
        if (parametres == null) return "";

        StringBuilder string = new StringBuilder("");

        for (int i = 0; i < parametres.size(); i++) {
            String combiner = i == parametres.size() - 1 ? " " : ",";

            string.append(parametres.get(i).getAnnotationsText() + " " + parametres.get(i).getType() + " " + parametres.get(i).getName() + combiner);

        }
        return string.toString();
    }


    String getAnnotationsText() {
        if (annotations == null) return "";

        StringBuilder string = new StringBuilder(" ");

        for (int i = 0; i < annotations.size(); i++) {
            string.append("@" + capFirstLetter(annotations.get(i)) + "\n");
        }

        return string.toString();


    }


}
