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
    private String urlImagen;
    @Column(columnDefinition = "TEXT")
    private String encabezado;
    private Integer vista;
    private String primerSubTitulo;
    @Column(columnDefinition = "TEXT")
    private String cuerpo;
    private String segundoSubTitulo;
    @Column(columnDefinition = "TEXT")
    private String primerParrafo;
    private String tercerSubTitulo;
    @Column(columnDefinition = "TEXT")
    private String segundoParrafo;


    public Noticia() {
        this.vista = 0;
    }

    /**
     * Constructor que acepta todos los atributos de la noticia para su inicialización.
     *
     * @param titulo     Título de la noticia.
     * @param cuerpo     Cuerpo o contenido de la noticia.
     * @param urlImagen  URL de la imagen asociada a la noticia.
     * @param encabezado Descripción opcional de la noticia.
     * @param vista      Contador de vistas de la noticia.
     */
    public Noticia(String titulo, String urlImagen, String encabezado, Integer vista, String primerSubTitulo, String cuerpo, String segundoSubTitulo, String primerParrafo, String tercerSubTitulo, String segundoParrafo) {
        this.titulo = titulo;
        this.urlImagen = urlImagen;
        this.encabezado = encabezado;
        this.vista = vista;
        this.primerSubTitulo = primerSubTitulo;
        this.cuerpo = cuerpo;
        this.segundoSubTitulo = segundoSubTitulo;
        this.primerParrafo = primerParrafo;
        this.tercerSubTitulo = tercerSubTitulo;
        this.segundoParrafo = segundoParrafo;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(String encabezado) {
        this.encabezado = encabezado;
    }

    public Integer getVista() {
        return vista;
    }

    public void setVista(Integer vista) {
        this.vista = vista;
    }

    public String getPrimerSubTitulo() {
        return primerSubTitulo;
    }

    public void setPrimerSubTitulo(String primerSubTitulo) {
        this.primerSubTitulo = primerSubTitulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getSegundoSubTitulo() {
        return segundoSubTitulo;
    }

    public void setSegundoSubTitulo(String segundoSubTitulo) {
        this.segundoSubTitulo = segundoSubTitulo;
    }

    public String getPrimerParrafo() {
        return primerParrafo;
    }

    public void setPrimerParrafo(String primerParrafo) {
        this.primerParrafo = primerParrafo;
    }

    public String getTercerSubTitulo() {
        return tercerSubTitulo;
    }

    public void setTercerSubTitulo(String tercerSubTitulo) {
        this.tercerSubTitulo = tercerSubTitulo;
    }

    public String getSegundoParrafo() {
        return segundoParrafo;
    }

    public void setSegundoParrafo(String segundoParrafo) {
        this.segundoParrafo = segundoParrafo;
    }

    @Override
    public String toString() {
        return "Noticia{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", urlImagen='" + urlImagen + '\'' +
                ", encabezado='" + encabezado + '\'' +
                ", vista=" + vista +
                ", primerSubTitulo='" + primerSubTitulo + '\'' +
                ", cuerpo='" + cuerpo + '\'' +
                ", segundoSubTitulo='" + segundoSubTitulo + '\'' +
                ", primerParrafo='" + primerParrafo + '\'' +
                ", tercerSubTitulo='" + tercerSubTitulo + '\'' +
                ", segundoParrafo='" + segundoParrafo + '\'' +
                '}';
    }
}
