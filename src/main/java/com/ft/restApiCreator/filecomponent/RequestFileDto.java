package com.ft.restApiCreator.filecomponent;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
 public class RequestFileDto {

    String path;
    List<DirectoryFileDto> directoryFiles;

}
