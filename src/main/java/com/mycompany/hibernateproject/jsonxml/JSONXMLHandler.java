package com.mycompany.hibernateproject.jsonxml;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.mycompany.hibernateproject.models.*;

import java.io.File;
import java.io.IOException;

public class JSONXMLHandler {

    private int xmlCounter = 0;
    private int jsonCounter = 0;
    private ObjectMapper objectMapper = new ObjectMapper();
    private XmlMapper xmlMapper = new XmlMapper();

    public JSONXMLHandler() {
        JodaModule joda = new JodaModule();
        objectMapper.registerModule(joda);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
        xmlMapper.registerModule(joda);
        xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void serialize(Object object, String type) throws IOException {
        File file = new File("src/main/resources/" + type + "/serialized/" + (type.equals("json") ? jsonCounter : xmlCounter) + "." + type);
        file.createNewFile();
        if (type.equals("json")) {
            objectMapper.writeValue(file, object);
            jsonCounter++;
        }
        if (type.equals("xml")) {
            xmlMapper.writeValue(file, object);
            xmlCounter++;
        }
    }

    public Player deserializePlayerJson(File file) throws IOException {
        return objectMapper.readValue(file, Player.class);
    }

    public Team deserializeTeamJson(File file) throws IOException {
        return objectMapper.readValue(file, Team.class);
    }

    public Coach deserializeCoachJson(File file) throws IOException {
        return objectMapper.readValue(file, Coach.class);
    }

    public City deserializeCityJson(File file) throws IOException {
        return objectMapper.readValue(file, City.class);
    }

    public Arena deserializeArenaJson(File file) throws IOException {
        return objectMapper.readValue(file, Arena.class);
    }

    public Player deserializePlayerXml(File file) throws IOException {
        return xmlMapper.readValue(file, Player.class);
    }

    public Team deserializeTeamXml(File file) throws IOException {
        return xmlMapper.readValue(file, Team.class);
    }

    public Coach deserializeCoachXml(File file) throws IOException {
        return xmlMapper.readValue(file, Coach.class);
    }

    public City deserializeCityXml(File file) throws IOException {
        return xmlMapper.readValue(file, City.class);
    }

    public Arena deserializeArenaXml(File file) throws IOException {
        return xmlMapper.readValue(file, Arena.class);
    }
}
