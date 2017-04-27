package com.zup.test.developer.service.impl;

import com.zup.test.developer.service.PoiService;
import com.zup.test.developer.data.domain.Poi;
import com.zup.test.developer.data.repository.PoiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PoiServiceImpl implements PoiService {

    private final PoiRepository poiRepository;


    @Autowired
    public PoiServiceImpl(PoiRepository poiRepository){
        this.poiRepository = poiRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public Poi save(Poi poi) {
        return poiRepository.save(poi);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Poi poi) {
        poiRepository.delete(poi);
    }

    @Override
    @Transactional(readOnly = false)
    public Iterable<Poi> findAll() {
        return poiRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public Iterable<Poi> findByx_coordanateInAndy_coordanateIn(Long x_coordanate, Long y_coordanate, Long max_distance) {
        Iterable<Poi> iterablePoi = findAll();
        List<Poi> poiList = new ArrayList<Poi>();
        iterablePoi.forEach(poi -> {
            if (Math.abs(poi.getX_coordanate() - x_coordanate) <= max_distance && Math.abs(poi.getY_coordanate() - y_coordanate) <= max_distance){
                poiList.add(poi);
            }
        });
        return poiList;
    }
}
