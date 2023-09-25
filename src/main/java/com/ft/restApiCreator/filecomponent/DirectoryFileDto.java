package com.ft.restApiCreator.filecomponent;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Getter
@Setter
@Slf4j
public class DirectoryFileDto {
    String directoryName;
    String directoryPath;
    List<JavaRestApiFile> javaFiles;
    List<DirectoryFileDto> directoryFiles;


}
