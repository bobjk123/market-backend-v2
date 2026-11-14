package com.tecnm.merida.market_backend_v2.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CompraProductoPK implements Serializable {

    @Column(name = "id_compra")
    private Integer idCompra;

    @Column(name = "id_producto")
    private Integer idProducto;

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompraProductoPK that = (CompraProductoPK) o;
        return java.util.Objects.equals(idCompra, that.idCompra) &&
                java.util.Objects.equals(idProducto, that.idProducto);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(idCompra, idProducto);
    }
}
