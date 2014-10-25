/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.dao;

import hibernate.dao.util.HibernateUtil;
import hibernate.model.Conta;
import hibernate.model.Movimentacao;
import hibernate.model.TipoMovimento;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author <a href="mailto:alessi.jonas@gmail.com">Jonas Alessi</a>
 *
 */
public class BuscaDAO {
    
    /**
     * HQL
     * @param conta
     * @return List
     */
    public List<Movimentacao> getMovimentacaoContaHQL(Conta conta) {
        Session session = HibernateUtil.getSession();
        try {
            Query query = session.createQuery("from Movimentacao m "
                    + " inner join fetch m.conta co"
                    + " where m.conta = :conta")
                    .setParameter("conta", conta);
            
            List<Movimentacao> resultado = query.list();
            
            return resultado;
        } catch (HibernateException ex) {
            throw  ex;
        } finally {
            session.close();
        }
    }
    
    /**
     * Criteria
     * @param conta
     * @return List
     */
    public List<Movimentacao> getMovimentacaoContaCriteria(Conta conta) {
        Session session = HibernateUtil.getSession();
       
        Criteria criteria = session.createCriteria(Movimentacao.class);
        criteria.add(Restrictions.eq("conta", conta));
        
        return criteria.list();
    }
    
    /**
     * SQL
     * @param conta
     * @return List
     */
    public List<Movimentacao> getMovimentacaoContaSQL(Conta conta) {
        Session session = HibernateUtil.getSession();
                
        SQLQuery q = session.createSQLQuery("select mov.* from movimentacao mov "
                + " where mov.idconta = :id ;");
        
        q.addEntity(Movimentacao.class);
        q.setParameter("id", conta.getId());
        
        return q.list();
    }
    
     public List<Movimentacao> getMovimentacaoSaque(Conta conta, TipoMovimento tipoMov) {
        Session session = HibernateUtil.getSession();
        
        Criteria criteria = session.createCriteria(Movimentacao.class);
        criteria.add(Restrictions.eq("conta", conta));
        criteria.add(Restrictions.eq("tipoMovimento", tipoMov));
        
        return criteria.list();
    }
    
}
