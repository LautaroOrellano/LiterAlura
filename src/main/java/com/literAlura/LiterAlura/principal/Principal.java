package com.literAlura.LiterAlura.principal;

import com.literAlura.LiterAlura.model.dto.Datos;
import com.literAlura.LiterAlura.model.dto.DatosLibro;
import com.literAlura.LiterAlura.model.entity.libro.Libro;
import com.literAlura.LiterAlura.model.entity.libro.LibroRepository;
import com.literAlura.LiterAlura.model.entity.persona.Autor;
import com.literAlura.LiterAlura.model.entity.persona.PersonaRepository;
import com.literAlura.LiterAlura.service.ConsumoAPI;
import com.literAlura.LiterAlura.service.ConvierteDatos;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner scanner = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    public final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository libroRepository;
    private PersonaRepository personaRepository;

    public Principal(LibroRepository libroRepository, PersonaRepository personaRepository) {
        this.libroRepository = libroRepository;
        this.personaRepository = personaRepository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                
                ****************************
                *  Bienvenido a LiterAlura *
                ****************************
                
                Elija una opcion por favor:      
                                        
                    1 - Buscar libros por titulos 
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma       
                    0 - Salir                    
                    """;
            System.out.println(menu);

            try {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        buscarLibrosPorTitulos();
                        break;
                    case 2:
                        listarLibrosRegistrados();
                        break;
                    case 3:
                        listarAutoresRegistrados();
                        break;
                    case 4:
                        listarAutoresVivosPorAño();
                        break;
                    case 5:
                        ListarPorIdioma();
                        break;
                    case 0:
                        System.out.println("Cerrando la aplicación...");
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: La opcion elegida no es un numero.");
                scanner.nextLine();
            }
        }
    }

    private void leerLibro(Libro libro) {
        String nombreAutor = (libro.getAutor() != null) ? libro.getAutor().getNombre() : "Autor no encontrado";
        System.out.printf("""
                        ----- LIBRO -----
                        Titulo: %s
                        Autor: %s
                        Idioma: %s
                        Numero de descargas: %d
                        -------------------- \n
                        """,
                libro.getTitulo(),
                nombreAutor,
                libro.getLenguajes(),
                libro.getCantidadDescargas()
        );
    }

    private void leerPersona(Autor autor) {
        System.out.printf("""
                        Autor: %s
                        Fecha de nacimiento: %s
                        Fecha de fallecimiento: %s
                        """,
                autor.getNombre(),
                autor.getFechaNacimiento(),
                autor.getFechaFallecimiento()
        );

        var libros = autor.getLibros().stream()
                .map(a -> a.getTitulo())
                .collect(Collectors.toList());

        System.out.println("Libros: " + libros + "\n");
    }

    private void buscarLibrosPorTitulos() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var nombreTitulo = scanner.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + nombreTitulo.replace(" ", "%20") );
        List<DatosLibro> libros = conversor.obtenerDatos(json, Datos.class).resultados();
        Optional<DatosLibro> libroOptional = libros.stream()
                .filter(l -> l.titulo().toLowerCase().contains(nombreTitulo.toLowerCase()))
                .findFirst();
        if (libroOptional.isPresent()) {
            var libro = new Libro(libroOptional.get());
            libroRepository.save(libro);
            leerLibro(libro);
        } else {
            System.out.println("\n" + "Error: El libro no existe");
        }
    }

    private void listarLibrosRegistrados() {
        List<Libro> libros = libroRepository.findAll();
        libros.stream()
                .forEach(this::leerLibro);
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = personaRepository.findAll();
        autores.stream()
                .forEach(this::leerPersona);
    }

    private void listarAutoresVivosPorAño() {
        System.out.println("Ingresa el año del autor(es) que desea buscar");
        Integer año = scanner.nextInt();
        List<Autor> autores = personaRepository.findByFechaFallecimientoGreaterThan(año);
       if(autores.isEmpty()) {
           System.out.println("No hay autores registrados con esa fecha");
       } else {
           autores.stream().forEach(this::leerPersona);
       }
    }

    private void ListarPorIdioma(){
        System.out.println("""
               
                Ingrese el idioma para buscar los libros
                
                es - español
                en - ingles
                fr - frances
                pt - portugues
                
                """);
        String idioma = scanner.next();
        List<Libro> libros = libroRepository.findByLenguajes(idioma);
        if(libros.isEmpty()) {
            System.out.println("No hay libros con ese idioma");
        } else {
            libros.stream().forEach(this::leerLibro);
        }
    }
}
