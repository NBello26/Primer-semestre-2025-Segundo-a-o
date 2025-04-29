package com.biblioteca.biblioteca.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//1.- Llamar a lombok para escribir menos código repititivo
@Data
@AllArgsConstructor //Agregando constructor con todos los datos
@NoArgsConstructor //Agregando constructor vacío

public class Libro {
    //Atributos de mi clase
    private int id;
    private String isbn;
    private String titulo;
    private String editorial;
    private int fechaPublicacion;
    private String autor;
}
