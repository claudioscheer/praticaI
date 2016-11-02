package model;
// Generated 22/10/2016 10:09:26 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * PatAtivoImobilizado generated by hbm2java
 */
@Entity
@Table(name = "pat_ativo_imobilizado", schema = "public")
public class PatAtivoImobilizado implements java.io.Serializable {

    private int ativoCodigo;
    private EstCategoria estCategoria;
    private EstMarca estMarca;
    private PatItemNota patItemNota;
    private String ativoDescricao;
    private double ativoValorOriginal;
    private double ativoValorAtual;
    private double ativoTaxaValorResidual;
    private double ativoValorResidual;
    private int ativoEstadoBem;
    private int ativoUtilizacao;
    private Set<PatBaixa> patBaixas = new HashSet(0);
    private Set<PatHistoricoDepreciacao> patHistoricoDepreciacaos = new HashSet(0);

    @Id
    @Column(name = "ativo_codigo", nullable = false)
    public int getAtivoCodigo() {
        return this.ativoCodigo;
    }

    public void setAtivoCodigo(int ativoCodigo) {
        this.ativoCodigo = ativoCodigo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria")
    public EstCategoria getEstCategoria() {
        return this.estCategoria;
    }

    public void setEstCategoria(EstCategoria estCategoria) {
        this.estCategoria = estCategoria;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marca")
    public EstMarca getEstMarca() {
        return this.estMarca;
    }

    public void setEstMarca(EstMarca estMarca) {
        this.estMarca = estMarca;
    }

    @Column(name = "ativo_descricao", nullable = false, length = 200)
    public String getAtivoDescricao() {
        return this.ativoDescricao;
    }

    public void setAtivoDescricao(String ativoDescricao) {
        this.ativoDescricao = ativoDescricao;
    }

    @Column(name = "ativo_valor_original", nullable = false, precision = 17, scale = 17)
    public double getAtivoValorOriginal() {
        return this.ativoValorOriginal;
    }

    public void setAtivoValorOriginal(double ativoValorOriginal) {
        this.ativoValorOriginal = ativoValorOriginal;
    }

    @Column(name = "ativo_valor_atual", nullable = false, precision = 17, scale = 17)
    public double getAtivoValorAtual() {
        return this.ativoValorAtual;
    }

    public void setAtivoValorAtual(double ativoValorAtual) {
        this.ativoValorAtual = ativoValorAtual;
    }

    @Column(name = "ativo_taxa_valor_residual", nullable = false, precision = 17, scale = 17)
    public double getAtivoTaxaValorResidual() {
        return this.ativoTaxaValorResidual;
    }

    public void setAtivoTaxaValorResidual(double ativoTaxaValorResidual) {
        this.ativoTaxaValorResidual = ativoTaxaValorResidual;
    }

    @Column(name = "ativo_valor_residual", nullable = false, precision = 17, scale = 17)
    public double getAtivoValorResidual() {
        return this.ativoValorResidual;
    }

    public void setAtivoValorResidual(double ativoValorResidual) {
        this.ativoValorResidual = ativoValorResidual;
    }

    @Column(name = "ativo_estado_bem", nullable = false)
    public int getAtivoEstadoBem() {
        return this.ativoEstadoBem;
    }

    public void setAtivoEstadoBem(int ativoEstadoBem) {
        this.ativoEstadoBem = ativoEstadoBem;
    }

    @Column(name = "ativo_utilizacao", nullable = false)
    public int getAtivoUtilizacao() {
        return this.ativoUtilizacao;
    }

    public void setAtivoUtilizacao(int ativoUtilizacao) {
        this.ativoUtilizacao = ativoUtilizacao;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patAtivoImobilizado")
    public Set<PatBaixa> getPatBaixas() {
        return this.patBaixas;
    }

    public void setPatBaixas(Set<PatBaixa> patBaixas) {
        this.patBaixas = patBaixas;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patAtivoImobilizado")
    public Set<PatHistoricoDepreciacao> getPatHistoricoDepreciacaos() {
        return this.patHistoricoDepreciacaos;
    }

    public void setPatHistoricoDepreciacaos(Set<PatHistoricoDepreciacao> patHistoricoDepreciacaos) {
        this.patHistoricoDepreciacaos = patHistoricoDepreciacaos;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nota_fiscal")
    public PatItemNota getPatItemNota() {
        return patItemNota;
    }

    public void setPatItemNota(PatItemNota patItemNota) {
        this.patItemNota = patItemNota;
    }

}
