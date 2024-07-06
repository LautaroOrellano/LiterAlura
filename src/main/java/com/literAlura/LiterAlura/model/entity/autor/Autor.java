package com.literAlura.LiterAlura.model.entity.autor;

import com.literAlura.LiterAlura.model.dto.DatosAutor;
import com.literAlura.LiterAlura.model.entity.libro.Libro;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "personas")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToMany(mappedBy = "autor", fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    private Integer fechaNacimiento;

    private Integer fechaFallecimiento;

    private String nombre;

    public Autor(DatosAutor autor) {
        this.fechaFallecimiento = autor.fechaFallecimiento();
        this.fechaNacimiento = autor.fechaNacimiento();
        this.nombre = autor.nombre();
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public Autor() {}

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(Integer fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "Id=" + Id +
                ", libros=" + libros +
                ", fechaNacimiento=" + fechaNacimiento +
                ", fechaFallecimiento=" + fechaFallecimiento +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
