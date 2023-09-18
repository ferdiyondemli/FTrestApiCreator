package com.ft.restApiCreator.fileCreator;

import com.ft.restApiCreator.fileCreator.fileComponent.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class FileCreatorImpl implements FileCreator {
    @Override
    public void createDirectory(DirectoryFile directoryFile, String path) throws IOException {
        File f = new File(path + "/" + directoryFile.getDirectoryName());

        if (f.mkdir()) {
            System.out.println("Directory named as " + directoryFile.getDirectoryName() + " has been created successfully");

            if (directoryFile.getDirectoryFiles() != null) {
                String finalPath = path;
                directoryFile.getDirectoryFiles().forEach(directoryFiled -> {

                    try {
                        createDirectory(directoryFiled, finalPath + "/" + directoryFile.getDirectoryName());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }


                });

            }


            if (directoryFile.getJavaFiles() != null) {

                String finalPath = path;
                directoryFile.getJavaFiles().forEach(javaFile -> {
                    try {
                        createJavaFile(javaFile, finalPath + "/" + directoryFile.getDirectoryName());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                });
            }


        } else {
            throw new RuntimeException("Directory named as " + directoryFile.getDirectoryName() + " cannot be created " + path);

        }
    }


    @Override
    public void createJavaFile(JavaFile javaFile, String path) throws IOException {

        File myObj = new File(path + "/" + javaFile.getJavaName() + ".java");
        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName() + path);
            writeFile(javaFile, myObj.getPath(), path);
        } else {
            System.out.println("File already exists.");
        }

    }

    @Override
    public void writeFile(JavaFile javaFile, String fileName, String path) {

        String text = createJavaFileText(javaFile, path);
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(text);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public String createJavaFileText(JavaFile javaFile, String path) {
        String packageName = "";
        if (path.contains("java")) {
            packageName = "package " + path.split("java/")[1].replace("/", ".") + "; \n";
        }

        return packageName +

                getAnnotations(javaFile.getAnnotations(), "\n") +

                "public " + javaFile.getType() + " " + javaFile.getJavaName() +

                getExtenedClassText(javaFile.getExtenedClass()) +

                getImplementedInterfaces(javaFile.getImplementedInterfaces()) +

                " {" +

                getFields(javaFile.getFields()) +

                getMethods(javaFile.getMethods(), javaFile.getType()) +

                "\n }";

    }


    private String getMethods(List<Method> methods, String type) {
        if (methods == null) return "";


        StringBuilder string = new StringBuilder("\n");
        for (int i = 0; i < methods.size(); i++) {
            String implementationTemp = Objects.equals(type, "class") ? "{" + "\n" + methods.get(i).getImplementation() + "\n }" : ";";
            String annotationTemp = Objects.equals(type, "class") ? getAnnotations(methods.get(i).getAnnotations(), "\n") : "";
            String accessModifierTemp = Objects.equals(type, "class") ? methods.get(i).getAccessModifier() : "" + " ";

            string.append(annotationTemp

                    + accessModifierTemp + " " + methods.get(i).getReturnType() + " " + methods.get(i).getName() + "(" + getParametres(methods.get(i).getParametres()) + ")" + implementationTemp + "\n");

        }
        return string.toString();
    }

    private String getFields(List<Field> fields) {
        if (fields == null) return "";

        StringBuilder string = new StringBuilder("\n");

        for (int i = 0; i < fields.size(); i++) {

            string.append(getAnnotations(fields.get(i).getAnnotations(), "\n") + fields.get(i).getAccessModifier() + " " + fields.get(i).getType() + " " + fields.get(i).getName() + ";" + "\n");

        }
        return string.toString();
    }

    private String getParametres(List<Parametre> parametres) {
        if (parametres == null) return "";

        StringBuilder string = new StringBuilder("");

        for (int i = 0; i < parametres.size(); i++) {
            String combiner = i == parametres.size() - 1 ? " " : ",";

            string.append(getAnnotations(parametres.get(i).getAnnotations(), " ") + " " + parametres.get(i).getType() + " " + parametres.get(i).getName() + combiner);

        }
        return string.toString();
    }

    private String getAnnotations(List<String> strings, String combiner) {
        if (strings == null) return "";

        StringBuilder string = new StringBuilder(" ");

        for (int i = 0; i < strings.size(); i++) {
            string.append("@" + strings.get(i) + combiner);
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

    private String getExtenedClassText(String extenedClass) {

        if (extenedClass == null) return "";


        return " extends " + extenedClass;
    }

}
