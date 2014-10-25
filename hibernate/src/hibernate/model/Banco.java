package hibernate.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/* 
 * @author <a href="mailto:alessi.jonas@gmail.com">Jonas Alessi</a>
 *
 */
@Entity
@Table(name = "banco")
public class Banco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idbanco")
    private Integer id;

    @Column(unique = true)
    private String nome;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "banco")
    private List<Conta> contas;

    public Banco(String nome) {
        this.nome = nome;
    }

    public Banco() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<Conta> getContas() {
        return contas;
    }

}
