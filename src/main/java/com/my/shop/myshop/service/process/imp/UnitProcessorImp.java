package com.my.shop.myshop.service.process.imp;

import com.my.shop.myshop.domain.UnitService;
import com.my.shop.myshop.entity.Unit;
import com.my.shop.myshop.service.process.UnitProcessor;
import com.my.shop.myshop.ws.reponse.Response;
import com.my.shop.myshop.ws.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
public class UnitProcessorImp implements UnitProcessor {
    @Autowired
    private UnitService unitService;

    @Override
    public Response getAllUnit(Request request) {
        Response response= new Response();
        HashMap<String, Object> data=new HashMap<>();
        data.put("items",unitService.getAllUnit());
        response.setData(data);
        return response;
    }

    @Override
    public Response getUnitById(Request request) {
        Response response= new Response();
        HashMap<String, Object> data=new HashMap<>();
        data.put("item",unitService.getUnitById(Integer.valueOf(request.getData().get("unitId").toString())));
        response.setData(data);
        return response;
    }

    @Override
    public Response saveOrUpdate(Request request) {
        Response response= new Response();
        Unit unit = unitService.getUnitById(Integer.valueOf(request.getData().get("itemId").toString()));
        /**
         * save or update update the unit from request what ever that has change.
         */
        if (unit==null){
            unit= new Unit();
            unit.setCreatedAt(new Date());
        }
        unit.setUnitName(request.getData().get("unitName").toString());
        unit.setUnitNameKh(request.getData().get("unitNameKh").toString());
        unit.setStatus(request.getData().get("status").toString());
        unit.setUpdatedAt(new Date());
        unitService.saveOrUpdate(unit);
        HashMap<String, Object> data=new HashMap<>();
        data.put("unit",unit);
        response.setData(data);
        return response;
    }
}
