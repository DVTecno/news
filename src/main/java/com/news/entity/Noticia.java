package com.news.entity;

import javax.persistence.*;

/**
 * Entidad que representa una noticia en el sistema.
 * Cada noticia tiene un identificador único, un título, un cuerpo, una URL de imagen,
 * una descripción opcional y un contador de vistas.
 * @version 1.0.0 16/10/2023
 */
@Entity
public class Noticia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String cuerpo;
    @Column(name = "url_imagen")
    private String urlImagen;
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    private Integer vista;
    /**
     * Constructor predeterminado que inicializa el contador de vistas en 0.
     */
    public Noticia() {
        this.vista = 0;
    }
    /**
     * Constructor que acepta todos los atributos de la noticia para su inicialización.
     * @param id Identificador único de la noticia.
     * @param titulo Título de la noticia.
     * @param cuerpo Cuerpo o contenido de la noticia.
     * @param urlImagen URL de la imagen asociada a la noticia.
     * @param descripcion Descripción opcional de la noticia.
     * @param vista Contador de vistas de la noticia.
     */
    public Noticia(Long id, String titulo, String cuerpo, String urlImagen, String descripcion, Integer vista) {
        this.id = id;
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.urlImagen = urlImagen;
        this.descripcion = descripcion;
        this.vista = vista;
    }
    /**
     * Obtiene el identificador único de la noticia.
     * @return El identificador único de la noticia.
     */
    public Long getId() {
        return id;
    }
    /**
     * Establece el identificador único de la noticia.
     * @param id El nuevo identificador único de la noticia.
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Obtiene el título de la noticia.
     * @return El título de la noticia.
     */
    public String getTitulo() {
        return titulo;
    }
    /**
     * Establece el título de la noticia.
     * @param titulo El nuevo título de la noticia.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    /**
     * Obtiene el cuerpo o contenido de la noticia.
     * @return El cuerpo o contenido de la noticia.
     */
    public String getCuerpo() {
        return cuerpo;
    }
    /**
     * Establece el cuerpo o contenido de la noticia.
     * @param cuerpo El nuevo cuerpo o contenido de la noticia.
     */
    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }
    /**
     * Obtiene la URL de la imagen asociada a la noticia.
     * @return La URL de la imagen de la noticia.
     */
    public String getUrlImagen() {
        return urlImagen;
    }
    /**
     * Establece la URL de la imagen asociada a la noticia.
     * @param urlImagen La nueva URL de la imagen de la noticia.
     */
    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
    /**
     * Establece la descripción opcional de la noticia.
     * @param descripcion La nueva descripción opcional de la noticia.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * Obtiene la descripción opcional de la noticia.
     * @return La descripción opcional de la noticia.
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * Obtiene el contador de vistas de la noticia.
     * @return El contador de vistas de la noticia.
     */
public Integer getVista() {
        return vista;
    }
    /**
     * Establece el contador de vistas de la noticia.
     * @param vista El nuevo contador de vistas de la noticia.
     */
    public void setVista(Integer vista) {
        this.vista = vista;
    }
    /**
     * Representación en cadena de texto de la noticia.
     * @return Una cadena que representa la noticia con sus atributos.
     */
    @Override
    public String toString() {
        return "Noticia{" +
                "id=" + id +
                ", título='" + titulo + '\'' +
                ", cuerpo='" + cuerpo + '\'' +
                ", urlImagen='" + urlImagen + '\'' +
                ", descripción='" + descripcion + '\'' +
                ", vista=" + vista +
                '}';
    }
}
