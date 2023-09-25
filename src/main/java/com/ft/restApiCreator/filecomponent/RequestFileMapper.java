package com.ft.restApiCreator.filecomponent;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RequestFileMapper {

    RequestFileMapper INSTANCE = Mappers.getMapper(RequestFileMapper.class);

    RequestFile entityToDto( RequestFileDto proje);
    DirectoryFile  entityToDto2( DirectoryFileDto proje);


}
