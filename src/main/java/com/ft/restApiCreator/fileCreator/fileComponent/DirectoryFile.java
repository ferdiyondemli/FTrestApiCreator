package com.ft.restApiCreator.fileCreator.fileComponent;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DirectoryFile {
    String directoryName;
    String directoryPath;
    List<? extends RestApiFile> javaFiles;
    List<DirectoryFile> directoryFiles;

}
