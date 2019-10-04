package com.my.shop.myshop.service;

import com.my.shop.myshop.service.process.UnitProcessor;
import com.my.shop.myshop.ws.reponse.Response;
import com.my.shop.myshop.ws.request.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitService {

    @Autowired
    private UnitProcessor unitProcessor;

    private Logger log = LoggerFactory.getLogger(ItemService.class);


    public Response run(Request request){
        Response  response = new Response();
        try {
            if ("add".equals(request.getStep()) || "update".equals(request.getStep())) {
                return unitProcessor.saveOrUpdate(request);
            } else if ("list_units".equals(request.getStep())) {
                return unitProcessor.getAllUnit(request);
            }else if ("get_unit".equals(request.getStep())){
                return unitProcessor.getUnitById(request);
            }else {
                response.setStatus("F");
                response.setErrorCode("E0001");
                response.setMessage("Invalid Item's step!!!");
            }
        }catch (Exception e){
            log.error("{}",e);
        }finally {
            log.info("Request: {}", request);
            log.info("Response: {}", response);
        }
        return response;
    }
}
