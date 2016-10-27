package model;
// Generated 22/10/2016 10:09:26 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * FlxcxOperacoes generated by hbm2java
 */
@Entity
@Table(name = "flxcx_operacoes", schema = "public"
)
public class FlxcxOperacoes implements java.io.Serializable {

    private int opCodigo;
    private String opDescricao;
    private Set<FlxcxTributacao> flxcxTributacaos = new HashSet(0);
    private Set<FlxcxMovimentoBancario> flxcxMovimentoBancarios = new HashSet(0);
    private Set<CarCapContas> carCapContass =  new HashSet(0);

    public FlxcxOperacoes() {
    }

    public FlxcxOperacoes(int opCodigo) {
        this.opCodigo = opCodigo;
    }

    public FlxcxOperacoes(int opCodigo, String opDescricao, Set<FlxcxTributacao> flxcxTributacaos, Set<CarCapContas> carCapContass, Set<FlxcxMovimentoBancario> flxcxMovimentoBancarios) {
        this.opCodigo = opCodigo;
        this.opDescricao = opDescricao;
        this.flxcxTributacaos = flxcxTributacaos;
        this.carCapContass = carCapContass;
        this.flxcxMovimentoBancarios = flxcxMovimentoBancarios;
    }

    @Id

    @Column(name = "op_codigo", unique = true, nullable = false)
    public int getOpCodigo() {
        return this.opCodigo;
    }

    public void setOpCodigo(int opCodigo) {
        this.opCodigo = opCodigo;
    }

    @Column(name = "op_descricao", length = 250)
    public String getOpDescricao() {
        return this.opDescricao;
    }

    public void setOpDescricao(String opDescricao) {
        this.opDescricao = opDescricao;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "flxcxOperacoes")   
    public Set<FlxcxTributacao> getFlxcxTributacaos() {
        return this.flxcxTributacaos;
    }

    public void setFlxcxTributacaos(Set<FlxcxTributacao> flxcxTributacaos) {
        this.flxcxTributacaos = flxcxTributacaos;
    }


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "flxcxOperacoes")
    public Set<FlxcxMovimentoBancario> getFlxcxMovimentoBancarios() {
        return this.flxcxMovimentoBancarios;
    }

    public void setFlxcxMovimentoBancarios(Set<FlxcxMovimentoBancario> flxcxMovimentoBancarios) {
        this.flxcxMovimentoBancarios = flxcxMovimentoBancarios;
    }

    @OneToMany(mappedBy = "flxcxOperacoes")
    public Set<CarCapContas> getCarCapContass() {
        return carCapContass;
    }

    public void setCarCapContass(Set<CarCapContas> carCapContass) {
        this.carCapContass = carCapContass;
    }

}
