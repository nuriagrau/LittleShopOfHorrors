package com.itacademy.Tickets;

import com.itacademy.FlowerShopFactory.JsonFlowerShop;

import java.io.*;
import java.util.ArrayList;

public class JsonTicket extends Ticket implements Serializable {

    public JsonTicket() {
        super();
    }

    public @interface JsonSerializable {
        @interface JsonElement {
            String key() default "";
        }

        @JsonElement(key = "filePath")
        String filepath = System.getProperty("user.dir") + "/src/main/java/com/itacademy/Database/Json/";

    }

}
