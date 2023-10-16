package com.news.services;

import com.news.entity.Noticia;
import com.news.exceptions.MyException;
import com.news.repositories.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
/**
 * Servicio para realizar operaciones relacionadas con las noticias.
 * @version 1.0.0 16/10/2023
 */
@Service
public class NoticiaService {
    private final NoticiaRepository noticiaRepository;
    /**
     * Constructor que inyecta una instancia de NoticiaRepository en el servicio.
     * @param noticiaRepository El repositorio utilizado para acceder a los datos de las noticias en la base de datos.
     */
    @Autowired
    public NoticiaService(NoticiaRepository noticiaRepository) {
        this.noticiaRepository = noticiaRepository;
    }
    /**
     * Recupera una lista de todas las noticias existentes en el sistema.
     * Este método consulta la base de datos para obtener todas las noticias almacenadas en el sistema.
     * @return Una lista de objetos {@code Noticia} que representa todas las noticias en el sistema.
     * @throws MyException Si no hay noticias disponibles en el sistema. Esto puede ocurrir si no se han publicado noticias todavía.
     */
    public List<Noticia> listarNoticias() throws MyException {
        List<Noticia> noticias = noticiaRepository.findAll();
        if (noticias.isEmpty()) {
            throw new MyException("No hay noticias disponibles en el sistema.");
        }
        return noticias;
    }
    /**
     * <p>Crea una nueva noticia en el sistema.</p>
     *
     * <p><strong>Parámetros:</strong></p>
     * <ul>
     *   <li><code>titulo</code> - El título de la noticia (obligatorio).</li>
     *   <li><code>cuerpo</code> - El cuerpo de la noticia (obligatorio).</li>
     *   <li><code>urlImagen</code> - La URL de la imagen de la noticia (obligatorio).</li>
     *   <li><code>descripcion</code> - La descripción opcional de la noticia.</li>
     * </ul>
     *
     * @param titulo El título de la noticia.
     * @param cuerpo El cuerpo de la noticia.
     * @param urlImagen La URL de la imagen de la noticia.
     * @param descripcion La descripción opcional de la noticia.
     * @throws MyException Si no se puede crear la noticia debido a campos obligatorios faltantes.
     */
    @Transactional
    public void createNoticia(String titulo, String cuerpo, String urlImagen, String descripcion) throws MyException {
        // Realiza la validación de los campos obligatorios
        if (titulo == null || titulo.isEmpty() || cuerpo == null || cuerpo.isEmpty() || urlImagen == null || urlImagen.isEmpty()) {
            throw new MyException("No se puede crear la noticia: campos obligatorios faltantes");
        }

        // Crea la noticia solo si la descripción no es nula
        Noticia noticia = new Noticia();
        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
        noticia.setUrlImagen(urlImagen);

        // Establece la descripción si no es nula, de lo contrario, deja la descripción como nula en la entidad
        if (descripcion != null) {
            noticia.setDescripcion(descripcion);
        }
        noticiaRepository.save(noticia);
    }
    /**
     * Recupera una lista de todas las noticias existentes en el sistema.
     * Este método consulta la base de datos para obtener todas las noticias almacenadas en el sistema.
     * @return Una lista de objetos {@code Noticia} que representa todas las noticias en el sistema.
     * @throws MyException Si no hay noticias disponibles en el sistema. Esto puede ocurrir si no se han publicado noticias todavía.
     */
    public List<Noticia> obtenerTodasLasNoticias() throws MyException {
        if(noticiaRepository.findAll().isEmpty()){
            throw new MyException("No hay noticias disponibles en el sistema.");
        }
        return noticiaRepository.findAll();
    }
    /**
     * Recupera una noticia por su identificador único.
     * Este método busca en la base de datos una noticia con el ID proporcionado y la devuelve si se encuentra.
     * @param id El identificador único de la noticia que se desea recuperar.
     *           Debe ser de tipo Long.
     * @return La noticia con el ID proporcionado.
     * @throws MyException Si no se encuentra la noticia con el ID proporcionado en la base de datos.
     */
    public Noticia obtenerNoticiaPorId(Long id) throws MyException {
        Optional<Noticia> noticia = noticiaRepository.findById(id);
        if (noticia.isEmpty()) {
            throw new MyException("No se encontró la noticia con el ID: " + id);
        }
        return noticia.get();
    }
    /**
     * Recupera una lista de todas las noticias del historial en el sistema.
     * Este método consulta la base de datos para obtener todas las noticias almacenadas en el historial del sistema.
     * @return Una lista de objetos {@code Noticia} que representa todas las noticias en el historial del sistema.
     * @throws MyException Si no hay noticias disponibles en el historial del sistema. Esto puede ocurrir si no se han publicado noticias históricas todavía.
     */
    public List<Noticia> obtenerTodasLasNoticiasDelHistorial() throws MyException {
        if(noticiaRepository.findAll().isEmpty()){
            throw new MyException("No hay noticias disponibles en el sistema.");
        }
        return noticiaRepository.findAll();
    }
    /**
     * Incrementa el contador de vistas de una noticia por su identificador único.
     * Este método busca la noticia en la base de datos por su ID, incrementa el contador de vistas en 1 y guarda
     * los cambios en la base de datos. Si la noticia no se encuentra con el ID proporcionado, se lanza una excepción.
     * @param id El identificador único de la noticia cuyo contador de vistas se desea incrementar.
     *           Debe ser de tipo Long.
     * @throws MyException Si no se encuentra la noticia con el ID proporcionado en la base de datos.
     */
    public void incrementarVista(Long id) throws Exception {
        Optional<Noticia> optionalNoticia = noticiaRepository.findById(id);

        if (optionalNoticia.isPresent()) {
            Noticia noticiaEncontrada = optionalNoticia.get();
            if (noticiaEncontrada.getVista() == null) {
                noticiaEncontrada.setVista(0); // Inicializa con 0 si es nulo
            }
            noticiaEncontrada.setVista(noticiaEncontrada.getVista() + 1);
            noticiaRepository.save(noticiaEncontrada);
        } else {
            throw new MyException("No se encontró la noticia con el ID: " + id);
        }
    }
    /**
     * Recupera una lista de todas las noticias en el sistema ordenadas por número de vistas en orden descendente.
     *
     * @return Lista de noticias ordenadas por vistas.
     * @throws MyException Si no hay noticias disponibles en el sistema.
     * @see Noticia
     * @see NoticiaService#incrementarVista(Long)
     */
    public List<Noticia> obtenerTodasLasNoticiasPorVistas() throws MyException {
        if(noticiaRepository.findAllByOrderByVistaDesc().isEmpty()){
            throw new MyException("No hay noticias disponibles en el sistema.");
        }
        return noticiaRepository.findAllByOrderByVistaDesc();
    }
}
