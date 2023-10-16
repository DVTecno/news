package com.news.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Controlador para manejar solicitudes HTTP relacionadas con la funcionalidad de inicio de sesión y registro.
 * @version 1.0.0 16/10/2023
 */
@Controller
@RequestMapping("/login") // http://localhost:8080/login
public class LoginController {
    /**
     * Maneja solicitudes GET en la ruta raíz ("/login") y muestra la página de inicio de sesión.
     * @return La vista "iniciar-sesion.html" que contiene el formulario de inicio de sesión.
     */
    @GetMapping("/")
    public String login() { // http://localhost:8080/login
        return "iniciar-sesion.html";
    }
    /**
     * Maneja solicitudes GET en la ruta "/registro" y muestra la página de creación de cuenta.
     * @return La vista "crear-cuenta.html" que contiene el formulario de registro de usuario.
     */
    @GetMapping("/registro")
    public String registro() { // http://localhost:8080/login/registro
        return "crear-cuenta.html";
    }

}
