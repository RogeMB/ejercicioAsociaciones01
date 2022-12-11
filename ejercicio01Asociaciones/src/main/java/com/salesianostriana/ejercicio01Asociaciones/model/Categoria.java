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

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Producto> productoList = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    private Categoria categoriaPadre;

    @Builder.Default
    @OneToMany(mappedBy = "categoriaPadre")
    private List<Categoria> subCategorias = new ArrayList<>();

    public void addSubcategoria(Categoria c) {
        subCategorias.add(c);
        c.setCategoriaPadre(this);
    }

    public void removeSubcategoria(Categoria c) {
        subCategorias.remove(c);
        c.setCategoriaPadre(null);
    }

    public void addCategoriaPadre(Categoria c) {
        this.setCategoriaPadre(c);
        c.getSubCategorias().add(this);
    }

    public void removeCategoriaPadre(Categoria c) {
        this.setCategoriaPadre(null);
        c.getSubCategorias().remove(this);
    }

}
