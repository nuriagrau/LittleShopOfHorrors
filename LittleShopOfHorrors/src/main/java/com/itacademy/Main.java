package com.itacademy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itacademy.App.Application;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Application.startShow();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // fer les custom exceptions
    // implementar 2 patrones (abstractFactory, per ex singleton)
    // metodos private, parametros
    // alguns throws en if () throws dins del mètode
}