package com.salesianostriana.ejercicio01Asociaciones.model;


import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, fetch = FetchType.EAGER) //por defecto aquí es lazy en los onetomany
    //@Fetch(FetchMode.JOIN)
    private List<Producto> productoList = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_padre", foreignKey = @ForeignKey(name = "FK_CATEGORIAPADRE_SUBCATEGORIA"))
    private Categoria categoriaPadre;

    @Builder.Default
    @OneToMany(mappedBy = "categoriaPadre", cascade = {CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private List<Categoria> subCategorias = new ArrayList<>();


    @PreRemove
    public void setNullSubCategoria(){
        subCategorias.forEach(categoria -> categoria.setCategoriaPadre(null));
    }
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

    public void addToCategoriaPadre(Categoria c) {
        //this.setCategoriaPadre(c);
        categoriaPadre=c;
        c.getSubCategorias().add(this);
    }
    public void removeFromCategoriaPadre(Categoria c) {
        this.productoList.forEach(producto -> producto.setCategoria(null));
        categoriaPadre=null;
        //this.setCategoriaPadre(null);
        c.getSubCategorias().remove(this);
    }

}
