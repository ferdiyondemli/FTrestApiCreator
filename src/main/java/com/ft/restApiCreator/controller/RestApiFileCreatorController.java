package com.ft.restApiCreator.controller;


import com.ft.restApiCreator.filecomponent.RequestFile;
import com.ft.restApiCreator.filecomponent.RequestFileDto;
import com.ft.restApiCreator.filecomponent.RequestFileMapper;
import com.ft.restApiCreator.service.RestApiFileCreatorService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

  @Controller
@RequiredArgsConstructor
public class RestApiFileCreatorController {
    private final RestApiFileCreatorService restApiFileCreatorService;

    @PostMapping("/createRestApiFiles")
    @ResponseBody
    public void createFiles(@RequestBody RequestFileDto dto) {
     RequestFile requestFile=  RequestFileMapper.INSTANCE.entityToDto(dto);
        restApiFileCreatorService.createFiles(requestFile);
    }

    @GetMapping("/createRestApiFiles")
    public String loadClientCodes() {
        return "index";
    }



    @GetMapping("/getPath")
    @ResponseBody
    public String getPath() {
        return System.getProperty("user.dir");
    }


}
