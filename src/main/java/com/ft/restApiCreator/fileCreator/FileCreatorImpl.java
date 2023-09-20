package com.ft.restApiCreator.fileCreator;

import com.ft.restApiCreator.fileCreator.fileComponent.*;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.File;
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
    public void createJavaFile(RestApiFile file, String path) throws IOException {

        File myObj = new File(path + "/" + file.getJavaName() + "." + file.getExtension());
        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName() + path);
            writeFile(file, myObj.getPath(), path);
        } else {
            System.out.println("File already exists.");
        }

    }

    @Override
    public void writeFile(RestApiFile file, String fileName, String path) {
        String text = "";
        if(file.getExtension().equals("java")){
            text = createJavaFileText((JavaRestApiFile) file, path);
        }else {
            text = createJsFileText((JsRestApiFile) file, path);

        }
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
    public String createJavaFileText(JavaRestApiFile javaFile, String path) {
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

                getJavaFileFields(javaFile.getFields()) +

                getMethods(javaFile.getMethods(), javaFile.getType()) +

                "\n }";

    }

    @Override
    public String createJsFileText(JsRestApiFile jsFile, String path) {

        return " import {\n" +
                "    isEmptyString,\n" +
                "    navigate,\n" +
                "    T,\n" +
                "    translate,\n" +
                "    useLocation,\n" +
                "    YteAddComponent as AddComponent,\n" +
                "  YteUpdateComponent as UpdateComponent,\n"+
                "  YteViewComponent as ViewComponent,\n"+
                "    YteAutonumericInput,\n" +
                "    YteButton,\n" +
                "    YteDateInput,\n" +
                "    YteForm as Form,\n" +
                "    YteFormElement as FormElement,\n" +
                "    YteMoneyInput,\n" +
                "    YteNumberInput,\n" +
                "    YtePanel as Panel,\n" +
                "    YteColumn as Column,\n" +
                "    YteCriteriaAndQueryPanel as CriteriaAndQueryPanel,\n" +
                "    YteSelectInput,\n" +
                "    YteTextInput,\n" +
                "    YteValidator as Validator\n" +
                "} from \"hbs-react-core\";"+
                "import {HbsInput, HbsIputContext} from \"../../../../genel/HbsInput\";"+
                "  const " + jsFile.getJavaName() +

                getJsFileFields(jsFile.getFields());
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
    private String getJsFileFields(List<Field> fields) {
        if (fields == null) return "";

        StringBuilder string = new StringBuilder("\n");

        for (int i = 0; i < fields.size(); i++) {

            string.append( fields.get(i).getName()  + "\n");

        }
        return string.toString();
    }
    private String getJavaFileFields(List<Field> fields) {
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
