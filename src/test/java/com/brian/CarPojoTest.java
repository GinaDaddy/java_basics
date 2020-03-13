package com.brian;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

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

    @Test
    public void createListFromJsonArray() throws JsonProcessingException {
        String jsonArray = "[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" : \"FIAT\" }]";
        List<CarPojo> listCar = mapper.readValue(jsonArray, new TypeReference<List<CarPojo>>() {
        });

        assertThat(listCar).extracting("color", "type")
            .contains(
                tuple("Black", "BMW"),
                tuple("Red", "FIAT"));
    }

    @Test
    public void createMapFromJsonString() throws JsonProcessingException {
        String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
        Map<String, Object> map = mapper.readValue(json, new TypeReference<Map<String, Object>>(){});

        assertThat(map.get("color")).isEqualTo("Black");
        assertThat(map.get("type")).isEqualTo("BMW");
    }
}