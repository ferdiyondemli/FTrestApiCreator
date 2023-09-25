package com.ft.restApiCreator.filecomponent;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class JavaRestApiFile extends RestApiFile {
    {
        extension = "java";
    }

    @Override
    public String createFileText(String path) {

        String packageName = "";
        if (path.contains("java")) {
            packageName = "package " + path.split("java/")[1].replace("/", ".") + "; \n";
        }

        return packageName +

                getAnnotationsText( "\n") +

                "public " + getType() + " " + getJavaName() +

                getExtenedClassText(getExtenedClass()) +

                getImplementedInterfaces(implementedInterfaces) +

                " {" +

                getFileFields() +

                getMethodsText(methods, type) +

                "\n }";
    }

    private String getExtenedClassText(String extenedClass) {

        if (extenedClass == null) return "";


        return " extends " + extenedClass;
    }

    @Override
    String getFileFields( ) {
        if (fields == null) return "";

        StringBuilder string = new StringBuilder("\n");

        for (int i = 0; i < fields.size(); i++) {

            string.append(fields.get(i).getAnnotationsText( ) + fields.get(i).getAccessModifier() + " " + fields.get(i).getType() + " " + fields.get(i).getName() + ";" + "\n");

        }
        return string.toString();
    }

    private String getImplementedInterfaces(List<String> implementedInterfaces) {
        if (implementedInterfaces == null) return "";

        if (implementedInterfaces.size() == 0) {
            return "";
        }
        return " implements " + getListText(implementedInterfaces);
    }

    private String getListText(List<String> strings) {
        StringBuilder string = new StringBuilder(" ");

        for (int i = 0; i < strings.size(); i++) {
            String combiner = i == strings.size() - 1 ? " " : ",";
            string.append(strings.get(i) + combiner);

        }

        return string.toString();
    }

    private String getMethodsText(List<Method> methods, String type) {
        if (methods == null) return "";


        StringBuilder string = new StringBuilder("\n");
        for (int i = 0; i < methods.size(); i++) {
            String implementationTemp = Objects.equals(type, "class") ? "{" + "\n" + methods.get(i).getImplementation() + "\n }" : ";";
            String annotationTemp = Objects.equals(type, "class") ?  methods.get(i).getAnnotationsText( ) : "";
            String accessModifierTemp = Objects.equals(type, "class") ? methods.get(i).getAccessModifier() : "" + " ";

            string.append(annotationTemp

                    + accessModifierTemp + " " + methods.get(i).getReturnType() + " " + methods.get(i).getName() + "(" +  methods.get(i).getParametresText() + ")" + implementationTemp + "\n");

        }
        return string.toString();
    }
    String getAnnotationsText(  String combiner) {
        if (annotations == null) return "";

        StringBuilder string = new StringBuilder(" ");

        for (int i = 0; i < annotations.size(); i++) {
            string.append("@" + annotations.get(i) + combiner);
        }

        return string.toString();


    }

    @Override
    public void setExtenedClass(final String extenedClass) {

        this.extenedClass = extenedClass.substring(0, 1).toUpperCase() + extenedClass.substring(1);;
    }

}
