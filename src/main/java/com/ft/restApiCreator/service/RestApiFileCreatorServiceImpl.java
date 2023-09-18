package com.ft.restApiCreator.service;


import com.ft.restApiCreator.fileCreator.FileCreator;
import com.ft.restApiCreator.fileCreator.fileComponent.RequestFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestApiFileCreatorServiceImpl implements RestApiFileCreatorService {

    private final FileCreator fileCreator;

    @Override
    public void createFiles( RequestFile requestFile) {

        requestFile.getDirectoryFiles().forEach(directoryFile -> {
            try {
                fileCreator.createDirectory(directoryFile, requestFile.getPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }


}
