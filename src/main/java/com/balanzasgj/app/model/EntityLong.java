package com.balanzasgj.app.model;

import com.j256.ormlite.field.DatabaseField;

public abstract class EntityLong extends Entity{
    @DatabaseField(id = true)
    protected Long    codigo       ; // Id or Primary Key

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
}
