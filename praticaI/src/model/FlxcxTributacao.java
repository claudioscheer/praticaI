package model;
// Generated 22/10/2016 10:09:26 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * FlxcxTributacao generated by hbm2java
 */
@Entity
@Table(name="flxcx_tributacao"
    ,schema="public"
)
public class FlxcxTributacao  implements java.io.Serializable {


     private int tribCodigo;
     private String tribDescricao;
     private Set<FlxcxOperacoes> flxcxOperacoes = new HashSet<>();

    public FlxcxTributacao() {
    }

	
    public FlxcxTributacao(int tribCodigo) {
        this.tribCodigo = tribCodigo;
    }
    public FlxcxTributacao(int tribCodigo, String tribDescricao, Set<FlxcxOperacoes> flxcxOperacoes) {
       this.tribCodigo = tribCodigo;
       this.tribDescricao = tribDescricao;
       this.flxcxOperacoes = flxcxOperacoes;
    }
   
     @Id 

    
    @Column(name="trib_codigo", unique=true, nullable=false)
    public int getTribCodigo() {
        return this.tribCodigo;
    }
    
    public void setTribCodigo(int tribCodigo) {
        this.tribCodigo = tribCodigo;
    }

    
    @Column(name="trib_descricao", length=20)
    public String getTribDescricao() {
        return this.tribDescricao;
    }
    
    public void setTribDescricao(String tribDescricao) {
        this.tribDescricao = tribDescricao;
    }

    
    @ManyToMany(mappedBy = "flxcxTributacaos")
    public Set<FlxcxOperacoes> getFlxcxOperacoes() {
        return this.flxcxOperacoes;
    }
    
    public void setFlxcxOperacoes(Set<FlxcxOperacoes> flxcxOperacoes) {
        this.flxcxOperacoes = flxcxOperacoes;
    }




}


