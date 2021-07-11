package com.balanzasgj.app.model;

import com.j256.ormlite.field.DatabaseField;

public abstract class EntityString extends Entity{
    @DatabaseField(id = true)
    protected String    codigo       ; // Id or Primary Key

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
