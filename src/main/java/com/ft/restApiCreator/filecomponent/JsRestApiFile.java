package com.ft.restApiCreator.filecomponent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsRestApiFile extends RestApiFile {
    String imports;
    @Override
    String getFileFields() {
        if (fields == null) return "";

        StringBuilder string = new StringBuilder("\n");

        for (int i = 0; i < fields.size(); i++) {

            string.append(fields.get(i).getName() + "\n");

        }
        return string.toString();
    }

    @Override
    public String createFileText(String path)  {

        return imports +

                getFileFields();
    }

}
