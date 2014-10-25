package hibernate;

import hibernate.dao.BuscaDAO;
import hibernate.dao.GenericDAO;
import hibernate.model.Banco;
import hibernate.model.Conta;
import hibernate.model.Movimentacao;
import hibernate.model.Pessoa;
import hibernate.model.TipoMovimento;
import hibernate.model.TipoPessoa;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author <a href="mailto:alessi.jonas@gmail.com">Jonas Alessi</a>
 *
 */
public class Caixa {
    
    private static GenericDAO<Banco, Integer> bancoDAO = new GenericDAO(Banco.class);
    private static GenericDAO<Conta, Integer> contaDAO = new GenericDAO(Conta.class);
    private static GenericDAO<Pessoa, Integer> pessoaDAO = new GenericDAO(Pessoa.class);
    private static GenericDAO<Movimentacao, Integer> movimentacaoDAO = new GenericDAO(Movimentacao.class);
    private static BuscaDAO buscaDAO = new BuscaDAO();

    public static void main(String[] args) {
        
        cargaInicial();
        
        Conta conta = contaDAO.get(1);
        
        System.out.println("Criteria:\t");
        System.out.println(buscaDAO.getMovimentacaoContaCriteria(conta));
        System.out.println("HQL:\t");
        System.out.println(buscaDAO.getMovimentacaoContaHQL(conta));
        System.out.println("SQL\t");
        System.out.println(buscaDAO.getMovimentacaoContaSQL(conta));
        
        System.out.println("------------ banco 1, quantidades de contas--------------\n");
        
        System.out.println(bancoDAO.get(1).getContas().size());
        
        testeBathSize();
        
        List<Movimentacao> lstMoviSaque = buscaDAO.getMovimentacaoSaque(conta, TipoMovimento.SAQUE);
        List<Movimentacao> lstMoviDepos = buscaDAO.getMovimentacaoSaque(conta, TipoMovimento.DEPOSITO);
        
        System.out.println(lstMoviDepos);
        System.out.println(lstMoviSaque);
    }

    private static void cargaInicial() {
        Banco banco1 = new Banco("Banco do Brasil");
        Banco banco2 = new Banco("Santander");
        Banco banco3 = new Banco("Itaú");
        Banco banco4 = new Banco("Bradesco");
        Banco banco5 = new Banco("Sicoob");

        bancoDAO.save(banco1);
        bancoDAO.save(banco2);
        bancoDAO.save(banco3);
        bancoDAO.save(banco4);
        bancoDAO.save(banco5);
        
        Pessoa pessoa = new Pessoa("Fernando", TipoPessoa.PESSOA_FISICA);
        pessoaDAO.save(pessoa);
        
        Conta conta = new Conta("01234", "984345", banco1, pessoa);
        contaDAO.save(conta);

        
        Movimentacao movimentacaoDepos = conta.deposito(BigDecimal.valueOf(1100.50));
        movimentacaoDAO.save(movimentacaoDepos);
        
        Movimentacao movimentacaoSaque = conta.saque(BigDecimal.valueOf(100.30));
        movimentacaoDAO.save(movimentacaoSaque);
        
    }
    
    
    /**
     * Testando o processamento em lote
     */
    public static void testeBathSize() {
        List<Banco> lst = new ArrayList<Banco>();
        
        for (int x = 0; x < 1000; x++) {
            lst.add(new Banco("Nome " + x));
        }
        bancoDAO.saveAll(lst);
    }
    
}
