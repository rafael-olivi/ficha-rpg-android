package mds.rpg.dominio.entidade;

import java.io.Serializable;

/**
 * Created by rboli on 19/12/2016.
 */

public class Registro implements Serializable {

    private int id;
    private int id_ficha;
    private String nome;
    private String raca;
    private String classe;
    private String nivel;
    private String exp_total;
    private String forca;
    private String destreza;
    private String constituicao;
    private String inteligencia;
    private String sabedoria;
    private String carisma;
    private String mod_forca;
    private String mod_destreza;
    private String mod_constituicao;
    private String mod_inteligencia;
    private String mod_sabedoria;
    private String mod_carisma;
    private String pv;
    private String pv_Atual;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_ficha() {
        return id_ficha;
    }

    public void setId_ficha(int id_ficha) {
        this.id_ficha = id_ficha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getExp_total() {
        return exp_total;
    }

    public void setExp_total(String exp_total) {
        this.exp_total = exp_total;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getForca() { return forca; }

    public void setForca(String forca) { this.forca = forca; }

    public String getDestreza() { return destreza; }

    public void setDestreza(String destreza) { this.destreza = destreza; }

    public String getConstituicao() { return constituicao; }

    public void setConstituicao(String constituicao) { this.constituicao = constituicao; }

    public String getInteligencia() { return inteligencia; }

    public void setInteligencia(String inteligencia) { this.inteligencia = inteligencia; }

    public String getSabedoria() { return sabedoria; }

    public void setSabedoria(String sabedoria) { this.sabedoria = sabedoria; }

    public String getCarisma() { return carisma; }

    public void setCarisma(String carisma) { this.carisma = carisma; }

    public String getPv() { return pv; }

    public void setPv(String pv) { this.pv = pv; }

    public String getPv_Atual() { return pv_Atual; }

    public void setPv_Atual(String pv_Atual) { this.pv_Atual = pv_Atual; }

    public String getMod_forca() {
        return mod_forca;
    }

    public void setMod_forca(String mod_forca) {
        this.mod_forca = mod_forca;
    }

    public String getMod_destreza() {
        return mod_destreza;
    }

    public void setMod_destreza(String mod_destreza) {
        this.mod_destreza = mod_destreza;
    }

    public String getMod_constituicao() {
        return mod_constituicao;
    }

    public void setMod_constituicao(String mod_constituicao) {
        this.mod_constituicao = mod_constituicao;
    }

    public String getMod_inteligencia() {
        return mod_inteligencia;
    }

    public void setMod_inteligencia(String mod_inteligencia) {
        this.mod_inteligencia = mod_inteligencia;
    }

    public String getMod_sabedoria() {
        return mod_sabedoria;
    }

    public void setMod_sabedoria(String mod_sabedoria) {
        this.mod_sabedoria = mod_sabedoria;
    }

    public String getMod_carisma() {
        return mod_carisma;
    }

    public void setMod_carisma(String mod_carisma) {
        this.mod_carisma = mod_carisma;
    }
}
