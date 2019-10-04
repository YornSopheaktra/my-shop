package com.my.shop.myshop.service.process.imp;

import com.my.shop.myshop.domain.ItemService;
import com.my.shop.myshop.entity.Item;
import com.my.shop.myshop.service.process.ItemProcessor;
import com.my.shop.myshop.ws.reponse.Response;
import com.my.shop.myshop.ws.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
public class ItemProcessorImp implements ItemProcessor {

    @Autowired
    private ItemService itemService;

    @Override
    public Response saveOrUpdateItem(Request request) {
        Response response= new Response();
            Item item = itemService.getItemById(Integer.valueOf(request.getData().get("itemId").toString()));
        /**
         * update the item from request what ever that has change.
         */
        if (item==null){
            item= new Item();
            item.setCreateAt(new Date());
        }
        item.setItemName(request.getData().get("itemName").toString());
        item.setItemNameKh(request.getData().get("itemNameKh").toString());
        item.setStatus(request.getData().get("status").toString());
        item.setUpdatedAt(new Date());
        itemService.saveOrUpdate(item);
        HashMap<String, Object> data=new HashMap<>();
        data.put("item",item);
        response.setData(data);
        return response;
    }

    @Override
    public Response getAllItem(Request request) {
        Response response= new Response();
        HashMap<String, Object> data=new HashMap<>();
        data.put("items",itemService.getAllItem());
        response.setData(data);
        return response;
    }

    @Override
    public Response getItemById(Request request) {
        Response response= new Response();
        HashMap<String, Object> data=new HashMap<>();
        data.put("item",itemService.getItemById(Integer.valueOf(request.getData().get("itemId").toString())));
        response.setData(data);
        return response;
    }
}
