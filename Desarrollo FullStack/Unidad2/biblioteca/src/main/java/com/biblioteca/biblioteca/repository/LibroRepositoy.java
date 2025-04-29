package com.biblioteca.biblioteca.repository;

import org.springframework.stereotype.Repository;

//CRUD
import com.biblioteca.biblioteca.model.Libro;

//2 impotaciones
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


//3
import java.util.stream.Collectors;
@Repository

public class LibroRepositoy {
    //4- Agregar una lista interna que almacene los libros
    private List<Libro> listaLibros = new ArrayList<>();

    //Constructor que se inicialice automaticamente e ingrese 10 libros en mi lista
    public LibroRepositoy(){
        //id,isbn
        listaLibros.add(new Libro(1, "9788491292705", "Fuego y Sangre", "Penguin Random House Grupo Editorial", 2018, "George R. R. Martin"));
        listaLibros.add(new Libro(2, "9784088725093", "One Piece", "Shueisha", 1997, "Eiichiro Oda"));
        listaLibros.add(new Libro(3, "9784088716954", "Death Note", "Shueisha", 2003, "Tsugumi Ohba"));
        listaLibros.add(new Libro(4, "9784063842760", "Attack on Titan", "Kodansha", 2009, "Hajime Isayama"));
        listaLibros.add(new Libro(5, "9784088725086", "Naruto", "Shueisha", 1999, "Masashi Kishimoto"));
        listaLibros.add(new Libro(6, "9781591162766", "Fullmetal Alchemist", "Square Enix", 2001, "Hiromu Arakawa"));
        listaLibros.add(new Libro(7, "9784088821191", "Jujutsu Kaisen", "Shueisha", 2018, "Gege Akutami"));
        listaLibros.add(new Libro(8, "9788925598673", "Solo Leveling", "D&C Media", 2018, "Chugong"));
        listaLibros.add(new Libro(9, "9788956608271", "Tower of God", "Naver Webtoon", 2010, "SIU"));
        listaLibros.add(new Libro(10, "9784088727547", "Bleach", "Shueisha", 2001, "Tite Kubo"));
    }

    //7.- Metodo para obtener la cantidad total de libros
    public int totalLibros(){
        return listaLibros.size();
    }

    //8.- Buscar un libro por su ISBN con obtional y solo la primera coincidencia
    public Optional<Libro> buscarPorIsbn(String isbn){
        return listaLibros.stream().filter(libro -> libro.getIsbn().equals(isbn)).findFirst();
    }

    //8.- Buscar un libro por su ISBN sin ayudas
    /*public Libro buscarPorIsbn(String isbn) {
        // Recorre la lista de libros
        for (Libro libro : listaLibros) {
            // Compara el ISBN del libro con el parámetro usando equals()
            if (libro.getIsbn().equals(isbn)) {
                return libro; // Retorna el libro si encuentra coincidencia
            }
        }
        return null; // Retorna null si no se encuentra el libro
    }*/

    //9.- Metodo que cuenta los libros de un año especifico
    public long contarPorAnio(int anio){
        return listaLibros.stream().filter(libro -> libro.getFechaPublicacion() == anio).count();
    }

    //10.- Metodo que busca todos los libros de un autor especifico
    public List<Libro> buscarLibrosAutor(String autor){
        return listaLibros.stream()
                .filter(libro -> libro.getAutor().equalsIgnoreCase(autor))
                .collect(Collectors.toList());
    }

    //11- Encontrar el libro con menor año de publicacion y obtiene el minimo por año
    public Optional<Libro> libroMasAntiguo(){
        return listaLibros.stream()
                .min(Comparator.comparingInt(Libro::getFechaPublicacion));
    }

    //12.- Encontrar el libro con el año mas reciente
    public Optional<Libro> libroMasNuevo(){
        return listaLibros.stream()
                .max(Comparator.comparingInt(Libro::getFechaPublicacion));
    }

    //13.- Devolver lista completa ordenada por año ascendente
    public List<Libro> listarOrdenadosPorAnio(){
        return listaLibros.stream()
                .sorted(Comparator.comparingInt(Libro::getFechaPublicacion))
                .collect(Collectors.toList());
    }


    // Buscar un libro por su id
    public Libro buscarPorId(int id) {
        // Recorre la lista de libros
        for (Libro libro : listaLibros) {
            // Compara el id del libro con el parámetro
            if (libro.getId() == id) {
                return libro; // Retorna el libro si encuentra coincidencia
            }
        }
        return null; // Retorna null si no se encuentra el libro
    }

    // Metodo para guardar un nuevo libro en la lista
    public Libro guardar(Libro lib) {
        listaLibros.add(lib); // Agrega el libro a la lista
        return lib;           // Retorna el libro agregado
    }

    // Metodo para actualizar un libro existente en la lista
    public Libro actualizar(Libro lib) {
        int id = 0;            // Variable para almacenar el ID del libro
        int idPosicion = 0;    // Variable para almacenar la posición del libro en la lista

        // Bucle para encontrar la posición del libro en la lista según su ID
        for (int i = 0; i < listaLibros.size(); i++) {
            if (listaLibros.get(i).getId() == lib.getId()) {
                id = lib.getId();         // Guarda el ID del libro
                idPosicion = i;           // Guarda la posición del libro en la lista
            }
        }

        // Crear un nuevo objeto Libro con los datos actualizados
        Libro libro1 = new Libro();
        libro1.setId(id);                                   // Asigna el ID
        libro1.setTitulo(lib.getTitulo());                  // Asigna el título
        libro1.setAutor(lib.getAutor());                    // Asigna el autor
        libro1.setFechaPublicacion(lib.getFechaPublicacion()); // Asigna la fecha de publicación
        libro1.setEditorial(lib.getEditorial());            // Asigna la editorial
        libro1.setIsbn(lib.getIsbn());                      // Asigna el ISBN

        // Reemplaza el libro antiguo por el actualizado en la misma posición
        listaLibros.set(idPosicion, libro1);

        return libro1; // Retorna el libro actualizado
    }

}
