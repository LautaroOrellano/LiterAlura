package com.literAlura.LiterAlura.model.entity.libro;

import com.literAlura.LiterAlura.model.dto.DatosLibro;
import com.literAlura.LiterAlura.model.dto.DatosPersona;
import com.literAlura.LiterAlura.model.entity.persona.Persona;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    private String titulo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Persona persona;

    private String lenguajes;

    @Column(name = "cantidad_de_descargas")
    private Integer cantidadDescargas;


    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        Optional<DatosPersona> persona = datosLibro.autores().stream().findFirst();
        if (persona.isPresent()) {
            this.persona = new Persona(persona.get());
        } else {
            this.persona = null;
            System.out.println("No se pudo encontar el autor");
        }
        this.lenguajes = datosLibro.lenguajes().get(0);
        this.cantidadDescargas = datosLibro.cantidadDescargas();
    }

    public Libro() {
    }

    public void setPersona(Persona autor) {
        this.persona = autor;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Persona getPersona() {
        return persona;
    }

    public String getLenguajes() {
        return lenguajes;
    }

    public void setLenguajes(String lenguajes) {
        this.lenguajes = lenguajes;
    }

    public Integer getCantidadDescargas() {
        return cantidadDescargas;
    }

    public void setCantidadDescargas(Integer cantidadDescargas) {
        this.cantidadDescargas = cantidadDescargas;
    }

    public Libro(String titulo, Persona persona, String lenguajes, Integer cantidadDescargas) {
        this.titulo = titulo;
        this.persona = persona;
        this.lenguajes = lenguajes;
        this.cantidadDescargas = cantidadDescargas;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "Id=" + Id +
                ", titulo='" + titulo + '\'' +
                ", autores=" + persona +
                ", lenguajes='" + lenguajes + '\'' +
                ", cantidadDescargas=" + cantidadDescargas +
                '}';
    }
}
