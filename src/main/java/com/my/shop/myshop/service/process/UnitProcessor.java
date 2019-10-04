package com.my.shop.myshop.service.process;

import com.my.shop.myshop.entity.Unit;
import com.my.shop.myshop.ws.reponse.Response;
import com.my.shop.myshop.ws.request.Request;

import java.util.List;

public interface UnitProcessor {
    public Response getAllUnit(Request request);
    public Response getUnitById(Request request);
    public Response saveOrUpdate(Request request);
}
