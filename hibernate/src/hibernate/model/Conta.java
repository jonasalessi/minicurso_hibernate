package hibernate.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/* 
 * @author <a href="mailto:alessi.jonas@gmail.com">Jonas Alessi</a>
 *
 */
@Entity
@Table(name = "conta")
public class Conta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idconta")
    private Integer id;

    @Column(unique = true, nullable = false)
    private String agencia;

    @Column(unique = true, nullable = false)
    private String numero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idbanco", nullable = false)
    private Banco banco;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idpessoa", nullable = false)
    private Pessoa pessoa;

    public Conta() {
    }
    
    public Conta(String agencia, String numero, Banco banco, Pessoa pessoa) {
        this.agencia = agencia;
        this.numero = numero;
        this.banco = banco;
        this.pessoa = pessoa;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Integer getId() {
        return id;
    }

    public String getAgencia() {
        return agencia;
    }

    public String getNumero() {
        return numero;
    }

    public Banco getBanco() {
        return banco;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public Movimentacao deposito(BigDecimal valor) {
        return new Movimentacao(this, TipoMovimento.DEPOSITO, valor, 
                Calendar.getInstance());
    }

    public Movimentacao saque(BigDecimal valor) {
        return new Movimentacao(this, TipoMovimento.SAQUE, valor, 
                Calendar.getInstance());
    }    
}
