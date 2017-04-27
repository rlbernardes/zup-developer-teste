package com.zup.test.developer.test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.zup.test.developer.data.domain.Poi;
import com.zup.test.developer.data.repository.PoiRepository;


import java.io.IOException;
import java.util.List;

//Inicializamos o SpringRunner
@RunWith(SpringRunner.class)
//A classe de testes foi setada com o Server inicializando em uma porta fixa
//Essa porta pode é a 8080 ou a que desejarmos setada no nosso aplication.properties
//Acesse src/test/resources/application.properties caso deseje alterar
@EntityScan(basePackages = { "com.zup.test.developer.service", "com.zup.test.developer.data.domain", "com.zup.test.developer.service.impl"})
@EnableJpaRepositories(basePackages = { "com.zup.test.developer.data.repository"})
@ComponentScan(basePackages = {"com.zup.test.developer.controller", "com.zup.test.developer.data.domain", "com.zup.test.developer.service.impl"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PoiEndPointTest {
    //URL base para onde as requests serão feitas
    final String BASE_PATH = "http://localhost:8080/";

    //Injetamos o repositório para acesso ao Banco de dados
    @Autowired
    private PoiRepository poiRepository;

    //Definimos o restTemplate
    private RestTemplate restTemplate;

    //Definimos o JacksonMapper responsável por converter
    //JSON para Objeto e vice versa
    private ObjectMapper MAPPER = new ObjectMapper();

    //Definimos o que será feito antes da execução do teste
    @Before
    public void setUp() throws Exception {

        //Deletamos todos os registros do banco
        poiRepository.deleteAll();

        //Inserimos alguns pois no banco
        poiRepository.save(new Poi("Lanchonete", (long)27, (long)12));
        poiRepository.save(new Poi("Posto", (long)31, (long)18));
        poiRepository.save(new Poi("Joalheria", (long)15, (long)12));
        poiRepository.save(new Poi("Floricultura", (long)19, (long)21));
        poiRepository.save(new Poi("Pub", (long)12, (long)8));
        poiRepository.save(new Poi("Supermercado", (long)23, (long)6));
        poiRepository.save(new Poi("Churrascaria", (long)28, (long)2));
        //Inicializamos o objeto restTemplate
        restTemplate = new RestTemplate();
    }

    @Test
    public void testCreatePoi() throws JsonProcessingException {

        //Criamos um novo poi
        Poi poi = new Poi("Boate", (long)15, (long)5);

        //Fazemos um HTTP request do tipo POST passando o poi como parâmetro
        Poi response = restTemplate.postForObject(BASE_PATH +"/poi", poi, Poi.class);

        //Verificamos se o resultado da requisição é igual ao esperado
        //Se sim significa que tudo correu bem
        Assert.assertEquals("Boate", response.getName());
    }

    @Test
    public void testFindPoyByDistance() throws IOException {

        //Fazemos uma requisição HTTP GET buscando por todas as pessoas
        String response = restTemplate
                .getForObject(BASE_PATH + "/poi/20/10/10", String.class);

        //Convertemos a resposta JSON para Objeto usando op Jackson
        List<Poi> pois = MAPPER.readValue(response,
                MAPPER.getTypeFactory().constructCollectionType(List.class, Poi.class));

        //Verificamos se o resultado da requisição é igual ao esperado
        //Se sim significa que tudo correu bem
        Assert.assertNotNull(pois.get(0));
        Assert.assertEquals("Lanchonete", pois.get(0).getName());
        Assert.assertEquals("27", pois.get(0).getX_coordanate().toString());
        Assert.assertEquals("12", pois.get(0).getY_coordanate().toString());
    }

    @Test
    public void testFindAll() throws IOException{
        String response = restTemplate.getForObject(BASE_PATH + "/pois", String.class);
        List<Poi> pois = MAPPER.readValue(response, MAPPER.getTypeFactory().constructCollectionType(List.class, Poi.class));
        Assert.assertEquals("Lanchonete", pois.get(0).getName());
        Assert.assertEquals("Posto", pois.get(1).getName());
    }

}
