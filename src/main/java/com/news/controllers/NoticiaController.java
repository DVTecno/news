package com.news.controllers;

import com.news.entity.Noticia;
import com.news.exceptions.MyException;
import com.news.services.NoticiaService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controlador para manejar solicitudes HTTP relacionadas con las noticias.
 * Este controlador gestiona las operaciones CRUD (Crear, Leer, Actualizar, Borrar) para las noticias.
 * Las solicitudes HTTP son manejadas de acuerdo con las rutas definidas en el mapeo de esta clase.
 *
 * @version 1.0.0 16/10/2023
 */
@Controller
@RequestMapping("/noticias")
public class NoticiaController {
	private final NoticiaService noticiaService;
	/**
	 * Constructor que inyecta una instancia de NoticiaService en el controlador.
	 * @param noticiaService El servicio utilizado para realizar operaciones relacionadas con las noticias.
	 */
	@Autowired
	public NoticiaController(NoticiaService noticiaService) {
		this.noticiaService = noticiaService;
	}
	////////////////////////////////////////////////////////////////////////

	/**
	 * Método para manejar solicitudes GET y mostrar la lista de noticias disponibles.
	 * @param modelo El modelo utilizado para pasar datos a la vista.
	 * @return Si hay noticias disponibles, retorna la vista "noticias.html" con la lista de noticias.
	 *         Si no hay noticias disponibles, muestra un mensaje de error en la vista "noticia.html".
	 *         Si ocurre un error al cargar las noticias, muestra un mensaje de error en la vista "inicio.html".
	 *         <p><strong>URL:</strong> <a href="http://localhost:8080/noticias/">http://localhost:8080/noticias/</a></p>
	 */
	@GetMapping("/")
	public String listar(ModelMap modelo) {
		try {
			// Intenta obtener la lista de noticias desde el servicio.
			List<Noticia> noticias = noticiaService.listarNoticias();
			// Si no hay noticias disponibles, muestra un mensaje de error.
			if (noticias.isEmpty()) {
				modelo.addAttribute("error", "No hay noticias disponibles en este momento.");
				return "noticia.html";
			}
			// Agrega la lista de noticias al modelo para ser mostrada en la vista.
			modelo.addAttribute("noticia", noticias);
			// Muestra la vista "noticias.html" con la lista de noticias.
			return "noticias.html";
		} catch (MyException e) {
			// Si hay un error al cargar las noticias, muestra un mensaje de error en la vista "inicio.html".
			modelo.addAttribute("error", "Ocurrió un error al cargar las noticias: " + e.getMessage());
			return "inicio.html";
		}
	}

	/**
	 * Método para manejar solicitudes GET y mostrar el panel de administración con la lista de noticias.
	 *
	 * @param model El modelo utilizado para pasar datos a la vista.
	 * @return Si hay noticias disponibles, retorna la vista "panel-admin.html" con la lista de noticias.
	 *         Si no hay noticias disponibles, muestra un mensaje de error en la vista "error.html".
	 *         Si ocurre un error al obtener las noticias, muestra un mensaje de error en la vista "panel-admin.html".
	 *         <p><strong>URL:</strong> <a href="http://localhost:8080/noticias/admin">http://localhost:8080/noticias/admin</a></p>
	 */
	@GetMapping("/admin")
	public String panelAdmin(ModelMap model) {
		try {
			// Intenta obtener la lista de noticias desde el servicio.
			List<Noticia> noticias = noticiaService.listarNoticias();
			// Si no hay noticias disponibles, muestra un mensaje de error.
			if (noticias.isEmpty()) {
				model.addAttribute("error", "No hay noticias disponibles en este momento.");
				return "error.html";
			}
			// Agrega la lista de noticias al modelo para ser mostrada en la vista.
			model.addAttribute("noticias", noticias);
			// Muestra la vista del panel de administración con la lista de noticias.
			return "panel-admin.html";
		} catch (MyException e) {
			model.addAttribute("error", "Por favor publique una noticia: " + e.getMessage());
			// Si hay un error al obtener las noticias, muestra un mensaje de error en la vista.
			return "panel-admin.html";
		}
	}

	/**
	 * Método para manejar solicitudes GET y mostrar el formulario de registro de noticias.
	 * @return La vista "registrar-noticia.html" que contiene el formulario para registrar una nueva noticia.
	 * <p><strong>URL:</strong> <a href="http://localhost:8080/noticias/registrar">http://localhost:8080/noticias/registrar</a></p>
	 */
	@GetMapping("/registrar")
	public String registrar() {
		// Retorna la vista "registrar-noticia.html" para mostrar el formulario de registro de noticias.
		return "registrar-noticia.html";
	}
	/**
	 * <p><strong>Manejo de Noticias</strong>
	 *
	 * <p>
	 * Método para manejar solicitudes POST para registrar noticias en el sistema.
	 * </p>
	 *
	 * <p><strong>Parámetros:</strong></p>
	 * <ul>
	 *   <li>noticia - El objeto Noticia enviado como parte de la solicitud.</li>
	 *   <li>model - El modelo para pasar datos a la vista.</li>
	 * </ul>
	 *
	 * <p><strong>Return:</strong></p>
	 * <ul>
	 *   <li>Si la noticia se crea con éxito, redirige al usuario a la página de inicio.</li>
	 *   <li>Si ocurre un error, muestra un mensaje de error en la vista "registrar-noticia.html".</li>
	 * </ul>
	 *
	 * <p><strong>URL:</strong> <a href="http://localhost:8080/noticias/registrar">http://localhost:8080/noticias/registrar</a></p>
	 *
	 * @param noticia El objeto Noticia enviado como parte de la solicitud.
	 * @param model El modelo para pasar datos a la vista.
	 * @return Si la noticia se crea con éxito, redirige al usuario a la página de inicio.
	 *         Si ocurre un error, muestra un mensaje de error en la vista "registrar-noticia.html".
	 *
	 * @see Noticia
	 * @see NoticiaService#createNoticia(String, String, String, String)
	 *
	 * @since 1.0
	 */
	@PostMapping("/registrar")
	public String registrar(@ModelAttribute("noticia") @NotNull Noticia noticia, Model model) {
		try {
			// Intenta crear la noticia utilizando el servicio noticiaService.
			noticiaService.createNoticia(noticia.getTitulo(), noticia.getCuerpo(), noticia.getUrlImagen(), noticia.getDescripcion());
		} catch (MyException e) {
			// Captura la excepción y agrega un mensaje de error al modelo.
			model.addAttribute("error", "No se pudo publicar la noticia: " + e.getMessage());
			// Retorna la vista "registrar-noticia.html" para mostrar el mensaje de error.
			return "registrar-noticia.html";
		}
		// Si la noticia se crea correctamente, redirige al usuario a la página de inicio.
		return "redirect:/inicio.html/";
	}



	/**
	 * Método para manejar solicitudes GET y registrar visitas a noticias individuales.
	 * @param id El identificador único de la noticia que se está visitando.
	 *           Debe ser de tipo Long.
	 * @param model El modelo utilizado para pasar datos a la vista.
	 * @return Si la noticia se encuentra y se visualiza correctamente, retorna la vista "noticia.html" con los detalles de la noticia.
	 *         Si la noticia no se encuentra, muestra un mensaje de error en la vista "error.html".
	 *         <p><strong>URL:</strong> <a href="http://localhost:8080/noticias/1">http://localhost:8080/noticias/{id}</a></p>
	 */
	@GetMapping("/{id}")
	public String verNoticia(@PathVariable Long id, Model model) {
		try {
			// Intenta incrementar el contador de visitas de la noticia identificada por el ID proporcionado.
			noticiaService.incrementarVista(id);
			// Obtiene los detalles de la noticia a partir del ID proporcionado.
			Noticia noticia = noticiaService.obtenerNoticiaPorId(id);
			// Agrega la noticia al modelo para ser mostrada en la vista "noticia.html".
			model.addAttribute("noticia", noticia);
			// Retorna la vista "noticia.html" para mostrar los detalles de la noticia.
			return "noticia.html";
		} catch (Exception e) {
			// Captura una excepción si la noticia no se encuentra y muestra un mensaje de error en la vista "error.html".
			model.addAttribute("error", "No hay noticia con este id: "+id+ ". " + e.getMessage());
			// Retorna la vista "error.html" para mostrar el mensaje de error.
			return "error.html";
		}
	}

}