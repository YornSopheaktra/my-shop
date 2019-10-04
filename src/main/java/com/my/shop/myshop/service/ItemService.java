package com.my.shop.myshop.service;

import com.my.shop.myshop.service.process.ItemProcessor;
import com.my.shop.myshop.ws.reponse.Response;
import com.my.shop.myshop.ws.request.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemProcessor itemProcessor;

    private Logger log = LoggerFactory.getLogger(ItemService.class);

    public Response run(Request request){
        Response response= new Response();
        try {
            if ("add".equals(request.getStep()) || "update".equals(request.getStep())) {
                return itemProcessor.saveOrUpdateItem(request);
            } else if ("list_items".equals(request.getStep())) {
                return itemProcessor.getAllItem(request);
            }else if ("get_item".equals(request.getStep())){
                return itemProcessor.getItemById(request);
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
