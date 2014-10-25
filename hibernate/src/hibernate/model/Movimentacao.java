/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/* 
 * @author <a href="mailto:alessi.jonas@gmail.com">Jonas Alessi</a>
 *
 */
@Entity
@Table(name="movimentacao")
public class Movimentacao implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "idconta")
    private Conta conta;
    
    @Enumerated(EnumType.STRING)
    private TipoMovimento tipoMovimento;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataMovimentacao;
    
    private BigDecimal valor;

    public Movimentacao() {
    }

    public Movimentacao(Conta conta, TipoMovimento tipoMovimento, 
            BigDecimal valor, Calendar dataMovimentacao) {
        this.conta = conta;
        this.tipoMovimento = tipoMovimento;
        this.valor = valor;
        this.dataMovimentacao = dataMovimentacao;
    }

    public Integer getId() {
        return id;
    }

    public Conta getConta() {
        return conta;
    }

    public TipoMovimento getTipoMovimento() {
        return tipoMovimento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Calendar getDataMovimentacao() {
        return dataMovimentacao;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public void setTipoMovimento(TipoMovimento tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setDataMovimentacao(Calendar dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    @Override
    public String toString() {
        return tipoMovimento.name() + " na Conta/Agência:" + conta.getNumero() + "/" + conta.getAgencia() 
                + " Valor:" + valor.toString();
    }
    
    
    
}
