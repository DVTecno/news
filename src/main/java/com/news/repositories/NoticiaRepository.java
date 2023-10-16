package com.news.repositories;

import com.news.entity.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Repositorio que maneja las operaciones de base de datos para la entidad Noticia.
 * Proporciona métodos para realizar consultas personalizadas sobre las noticias almacenadas.
 * @version 1.0.0 16/10/2023
 */
@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Long> {
    /**
     * Busca una noticia por su título.
     * @param titulo El título de la noticia a buscar.
     * @return La noticia encontrada, o null si no se encuentra ninguna coincidencia.
     */
    @Query("SELECT n FROM Noticia n WHERE n.titulo = :titulo")
    public Noticia buscarPorTitulo(@Param("titulo") String titulo);

    /**
     * Obtiene todas las noticias ordenadas por su ID de forma descendente.
     * @return Una lista de noticias ordenadas por su ID de forma descendente.
     */
    public List<Noticia> findAllByOrderByIdDesc();
    /**
     * Obtiene la última noticia registrada en la base de datos.
     * @return La última noticia registrada, o null si no hay noticias en la base de datos.
     */
     public Noticia findFirstByOrderByIdDesc();
    // O alternativamente:
    // Noticia findTopByOrderByIdDesc();
    /**
     * Obtiene la última noticia registrada en la base de datos mediante una consulta personalizada.
     * @return La última noticia registrada, o null si no hay noticias en la base de datos.
     */
    @Query("SELECT n FROM Noticia n ORDER BY n.id DESC")
    Noticia findUltimaNoticia();
    /**
     * Obtiene las últimas noticias registradas ordenadas por el número de vistas de forma descendente.
     * @return Una lista de noticias ordenadas por el número de vistas de forma descendente.
     */
    List<Noticia> findAllByOrderByVistaDesc();
}
