package com.itacademy.Tickets;

import java.io.Serializable;

public class JsonTicket extends Ticket implements Serializable {

    private int id;

    static int nextId = 1000;



    public JsonTicket() {
        super();
        this.id = nextId;
        nextId++;
    }


    public @interface JsonSerializable  {

        @interface JsonElement {
            String key() default "";
        }

        @JsonElement(key = "filePath")
        String filepath = System.getProperty("user.dir") + "/src/main/java/com/itacademy/Database/Json/";

    }
}
