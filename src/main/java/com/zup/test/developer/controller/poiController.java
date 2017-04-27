package com.zup.test.developer.controller;

import com.zup.test.developer.service.PoiService;
import com.zup.test.developer.data.domain.Poi;
import com.zup.test.developer.data.repository.PoiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/")
public class poiController {

    private static final Logger LOGGER = LoggerFactory.getLogger( poiController.class );

    @Autowired
    private PoiRepository poiRepository;

    @Autowired
    private PoiService poiService;

    @RequestMapping( value = "/pois", method = RequestMethod.GET )
    public @ResponseBody ResponseEntity<List<Poi>> getPois() {

        try {
            List<Poi> poiList =  poiRepository.findAll();
            return new ResponseEntity<List<Poi>>( poiList, HttpStatus.OK );
        } catch ( Exception e ) {
            LOGGER.warn( "Poi not found", e );
            List<Poi> poiList = null;
            return new ResponseEntity<List<Poi>>( poiList, HttpStatus.OK );
        }
    }

    @RequestMapping(value = "/poi", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Poi> setPoi(@RequestBody Poi poiSave){
        if (poiSave == null) {
            LOGGER.warn("Poi can not be null ");
            Poi poi = new Poi();
            return new ResponseEntity<Poi>(poi, HttpStatus.NOT_FOUND);
        } else {
            try {
                poiSave = poiRepository.save(poiSave);
                System.out.println(poiSave.toString());
            } catch (Exception e) {
                LOGGER.warn("Poi can not be save ");
                Poi poi = new Poi();
                return new ResponseEntity<Poi>(poi, HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<Poi>(poiSave, HttpStatus.OK);
    }

    @RequestMapping(value = "/poi/{x_coordante}/{y_coordante}/{max_distance}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<Poi>> getPoi(@PathVariable Long x_coordante, @PathVariable Long y_coordante, @PathVariable Long max_distance){
        List<Poi> poiList = new ArrayList<>();
        System.out.println(x_coordante);
        if (x_coordante == null || y_coordante == null || max_distance == null) {
            LOGGER.warn("Values can not be nulls ");
            List<Poi> poi = new ArrayList<>();
            return new ResponseEntity<List<Poi>>(poi, HttpStatus.NOT_FOUND);
        } if(x_coordante < 0 || y_coordante < 0 || max_distance < 0){
            LOGGER.warn("Values can not be negatives ");
            List<Poi> poi = new ArrayList<>();
            return new ResponseEntity<List<Poi>>(poi, HttpStatus.NOT_FOUND);
        } else {
            try {
                poiList = (List<Poi>) poiService.findByx_coordanateInAndy_coordanateIn(x_coordante, y_coordante, max_distance);
            } catch (Exception e) {
                LOGGER.warn("Poi can not be find ");
                List<Poi> poi = new ArrayList<>();
                return new ResponseEntity<List<Poi>>(poi, HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<List<Poi>>(poiList, HttpStatus.OK);
    }

}
