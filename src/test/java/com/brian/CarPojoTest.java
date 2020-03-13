package com.brian;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

public class CarPojoTest {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testPojoToJson() throws JsonProcessingException {
        CarPojo car = new CarPojo("yellow", "renault");
        String json = mapper.writeValueAsString(car);

        assertThat(json).isEqualTo("{\"color\":\"yellow\",\"type\":\"renault\"}");
    }

    @Test
    public void testJsonToPojo() throws JsonProcessingException {
        String json = "{\"color\":\"yellow\",\"type\":\"renault\"}";
        CarPojo car = mapper.readValue(json, CarPojo.class);

        assertThat(car).extracting("color").isEqualTo("yellow");
    }
}