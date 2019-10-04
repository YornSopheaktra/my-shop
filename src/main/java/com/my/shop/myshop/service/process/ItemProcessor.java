package com.my.shop.myshop.service.process;

import com.my.shop.myshop.ws.reponse.Response;
import com.my.shop.myshop.ws.request.Request;

public interface ItemProcessor {
    public Response saveOrUpdateItem(Request request);
    public Response getAllItem(Request request);
    public Response getItemById(Request request);

}
