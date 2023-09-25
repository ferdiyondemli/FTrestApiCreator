package com.ft.restApiCreator.service;


import com.ft.restApiCreator.filecomponent.RequestFile;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestApiFileCreatorServiceImpl implements RestApiFileCreatorService {


    @Override
    public void createFiles( RequestFile requestFile) {

        requestFile.getDirectoryFiles().forEach(directoryFile -> {
            try {
                directoryFile.setDirectoryPath(requestFile.getPath());
                directoryFile.createDirectory();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }


}
