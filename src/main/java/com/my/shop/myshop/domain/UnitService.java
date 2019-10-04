package com.my.shop.myshop.domain;

import com.my.shop.myshop.entity.Unit;
import java.util.List;

public interface UnitService {
    public List<Unit> getAllUnit();
    public Unit getUnitById(Integer id);
    public Unit saveOrUpdate(Unit unit);
}
