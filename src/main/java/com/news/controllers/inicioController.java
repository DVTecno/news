package com.news.controllers;

import com.news.entity.Noticia;
import com.news.exceptions.MyException;
import com.news.services.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
/**
 * Controlador para manejar solicitudes HTTP relacionadas con la página de inicio del sistema.
 * @version 1.0.0 16/10/2023
 */

@Controller
@RequestMapping("/inicio") // http://localhost:8080/inicio
public class inicioController {
	private final NoticiaService noticiaService;
	/**
	 * Constructor que inyecta una instancia de NoticiaService en el controlador.
	 * @param noticiaService El servicio utilizado para realizar operaciones relacionadas con las noticias.
	 */
	@Autowired
	public inicioController(NoticiaService noticiaService) {
		this.noticiaService = noticiaService;
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Maneja solicitudes GET en la ruta raíz ("/") y muestra la página de inicio del sistema.
	 * Carga la lista de noticias más recientes y la lista de noticias del historial,
	 * y las pasa al modelo para ser mostradas en la vista "inicio.html".
	 * @param model El modelo utilizado para pasar datos a la vista.
	 * @return La vista "inicio.html" que muestra la lista de noticias más recientes y del historial.
	 */
	@GetMapping("/") // http://localhost:8080/inicio/
	public String index(Model model) {
		// Inicializa una lista de noticias para almacenar las noticias más recientes.
		List<Noticia> noticias = null;
		try {
			// Intenta obtener todas las noticias a través del servicio noticiaService.
			noticias = noticiaService.obtenerTodasLasNoticias();
		} catch (MyException e) {
			// Si se produce un error (MyException), se añade el mensaje de error al modelo.
			model.addAttribute("error", e.getMessage());
		}
		// Inicializa una lista de noticias para almacenar las noticias del historial.
		List<Noticia> historial = null;
		try {
			// Intenta obtener todas las noticias del historial a través del servicio noticiaService.
			historial = noticiaService.obtenerTodasLasNoticiasPorVistas();
		} catch (MyException e) {
			// Si se produce un error (MyException), se añade el mensaje de error al modelo.
			model.addAttribute("error", e.getMessage());
		}
		// Agrega la lista de noticias más recientes al modelo con el nombre "noticias".
		model.addAttribute("noticias", noticias);
		// Agrega la lista de noticias del historial al modelo con el nombre "historial".
		model.addAttribute("historial", historial);
		// Retorna la vista "inicio.html" para mostrar las noticias más recientes y del historial.
		return "inicio.html";
	}



}
