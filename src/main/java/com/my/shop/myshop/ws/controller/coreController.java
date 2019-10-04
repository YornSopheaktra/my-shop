package com.my.shop.myshop.ws.controller;

import com.my.shop.myshop.service.ItemService;
import com.my.shop.myshop.ws.reponse.Response;
import com.my.shop.myshop.ws.request.Request;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api
public class coreController {

    @Autowired
    ItemService itemService;

    @GetMapping(value = "/home")
    public String Home(){

        System.out.println("#====================================================== Home");
        return "Welcome Home";
    }

    @PostMapping(value = "/item", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response item(@RequestBody Request request){
        return  itemService.run(request);
    }


    @PostMapping(value = "/unit", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response sale(@RequestBody Request request){
        return  itemService.run(request);
    }
}
