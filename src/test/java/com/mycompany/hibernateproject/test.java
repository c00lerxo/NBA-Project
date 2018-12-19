package com.mycompany.hibernateproject;
import com.mycompany.hibernateproject.db.DatabaseManager;
import com.mycompany.hibernateproject.jsonxml.JSONXMLHandler;
import com.mycompany.hibernateproject.models.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class test {

    @Test
    public void check() throws IOException {
        Player examplePlayer = DatabaseManager.entityManager.find(Player.class, 1);
        JSONXMLHandler handler = new JSONXMLHandler();

       // handler.serialize(examplePlayer, "json");
        Player player1 = handler.deserializePlayerJson(new File("src/main/resources/json/serialized/0.json"));

        DatabaseManager.addToDatabase(player1);
    }
}
