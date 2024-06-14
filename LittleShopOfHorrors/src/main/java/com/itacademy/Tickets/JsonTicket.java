package com.itacademy.Tickets;

import java.io.Serializable;

public class JsonTicket extends Ticket implements Serializable {

    public JsonTicket() {
        super();
    }




    public @interface JsonSerializable  {

        @interface JsonElement {
            String key() default "";
        }

        @JsonElement(key = "filePath")
        String filepath = System.getProperty("user.dir") + "/src/main/java/com/itacademy/Database/Json/";

    }
}
