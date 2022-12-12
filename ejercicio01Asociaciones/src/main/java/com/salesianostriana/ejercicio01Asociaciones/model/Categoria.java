package com.salesianostriana.ejercicio01Asociaciones.model;


import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Categoria {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    @Builder.Default
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Producto> productoList = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    private Categoria categoriaPadre;

    @Builder.Default
    @OneToMany(mappedBy = "categoriaPadre", cascade = CascadeType.ALL)
    private List<Categoria> subCategorias = new ArrayList<>();


    @PreRemove
    public void preremoveProducts() {
        productoList.forEach(producto -> producto.setCategoria(null));
        if(this.getCategoriaPadre() != null) {
            this.getCategoriaPadre().getSubCategorias().remove(this);
        }
        if(!this.getSubCategorias().isEmpty()){
            this.getSubCategorias().forEach(categoria -> {
                categoria.setCategoriaPadre(null);
            });
        }
    }


    public void addSubcategoria(Categoria c) {
        subCategorias.add(c);
        c.setCategoriaPadre(this);
    }

    public void removeSubcategoria(Categoria c) {
        this.productoList.forEach(producto -> producto.setCategoria(null));
        subCategorias.remove(c);
        c.setCategoriaPadre(null);
    }

    public void addCategoriaPadre(Categoria c) {
        this.setCategoriaPadre(c);
        c.getSubCategorias().add(this);
    }

    public void removeCategoriaPadre(Categoria c) {
        this.productoList.forEach(producto -> producto.setCategoria(null));
        this.setCategoriaPadre(null);
        c.getSubCategorias().remove(this);
    }

}
