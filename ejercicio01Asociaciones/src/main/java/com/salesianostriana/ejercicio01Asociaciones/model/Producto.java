package com.salesianostriana.ejercicio01Asociaciones.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Setter @Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private double pvp;

    @ManyToOne
    @JoinColumn(name="categoria", foreignKey = @ForeignKey(name= "FK_PRODUCTO_CATEGORIA"))
    private Categoria categoria;

    public void addCategoria(Categoria c) {
        this.categoria = c;
        c.getProductoList().add(this);
    }

    public void removeCategoria(Categoria c) {
        c.getProductoList().remove(this);
        this.categoria = null;
    }

}
