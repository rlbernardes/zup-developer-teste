package com.zup.test.developer.service;

import com.zup.test.developer.data.domain.Poi;

public interface PoiService {
    Poi save(Poi poi);
    void delete(Poi poi);
    Iterable<Poi> findAll();
    Iterable<Poi> findByx_coordanateInAndy_coordanateIn(Long x_coordanate, Long y_coordanate, Long max_distance);
}
