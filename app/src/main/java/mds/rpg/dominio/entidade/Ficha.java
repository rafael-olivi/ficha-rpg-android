package mds.rpg.dominio.entidade;

import java.io.Serializable;

/**
 * Created by rboli on 18/12/2016.
 */

public class Ficha implements Serializable {

    private int id;
    private String nome;


    public Ficha(){
        id = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString(){
        return nome;
    }
}
