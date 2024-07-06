package com.literAlura.LiterAlura.model.entity.persona;

import com.literAlura.LiterAlura.model.dto.DatosPersona;
import com.literAlura.LiterAlura.model.entity.libro.Libro;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "personas")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToMany(mappedBy = "persona", fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    private Integer fechaNacimiento;

    private Integer fechaFallecimiento;

    private String nombre;

    public Persona(DatosPersona persona) {
        this.fechaFallecimiento = persona.fechaFallecimiento();
        this.fechaNacimiento = persona.fechaNacimiento();
        this.nombre = persona.nombre();
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public Persona() {}

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
