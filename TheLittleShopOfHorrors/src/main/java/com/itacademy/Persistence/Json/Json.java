package com.itacademy.Persistence.Json;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.itacademy.Tickets.JsonTicket;

import java.io.File;

public class Json {

    public @interface JsonSerializable {
        @interface JsonElement {
            String key() default "";
        }

        @JsonTicket.JsonSerializable.JsonElement(key = "filePath")
        String filepath = System.getProperty("user.dir") + "/src/main/java/com/itacademy/Database/Json/";


    }

}

