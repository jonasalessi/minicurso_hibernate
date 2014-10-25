package hibernate.dao;

import hibernate.dao.util.HibernateUtil;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/* 
 * @author <a href="mailto:alessi.jonas@gmail.com">Jonas Alessi</a>
 *
 */
public class GenericDAO<T, PK extends Serializable> {
    
    private final Class classe;
    
    public GenericDAO(Class classe) {
        this.classe = classe;
    }

    /**
     * Persistir o objeto no banco relacional
     * @param object
     * @return T
     */
    public T save(T object) {
        Session session = HibernateUtil.getSession();
        try {
            //Iniciar transação
            session.getTransaction().begin();

            //Persistir o objeto
            T objSaida = (T) session.save(object);

            //Terminar a transação
            session.getTransaction().commit();

            return objSaida;
        } catch (HibernateException ex) {
            session.getTransaction().rollback();
            throw ex;
        } finally {
            session.close();
        }
    }

    /**
     * Atualiza o objeto
     * @param objetc 
     */
    public void update(T objetc) {
        Session session = HibernateUtil.getSession();
        try {
            //Iniciar transação
            session.getTransaction().begin();

            //Atualizar o objeto
            session.update(objetc);

            //Terminar a transação
            session.getTransaction().commit();

        } catch (HibernateException ex) {
            session.getTransaction().rollback();
            throw ex;
        } finally {
            session.close();
        }
    }

    /**
     * Retorna todos os registros
     * @return List
     */
    public List<T> getAll() {
        Session session = HibernateUtil.getSession();
        try {
            return  session.createQuery("from " + this.classe.getName()).list();
        } catch (HibernateException ex) {
            throw ex;
        } finally {
            session.close();
        }
    }

    /**
     * Retorna um objeto
     * @param id PK do objeto
     * @return 
     */
    public T get(PK id) {
        Session session = HibernateUtil.getSession();
        try {
            return (T) session.get(this.classe, id);
        } catch (HibernateException ex) {
            throw ex;
        } finally {
            session.close();
        }
    }
    
    /**
     * Salvar uma lista de registros em lote
     * @param lista 
     */
    public void saveAll(List<T> lista) {
          Session session = HibernateUtil.getSession();
        try {
            //Iniciar transação
            session.getTransaction().begin();
            int x = 0;
            for (T obj : lista) {
                //20 é quantidade de lote configurada no hibernate.cfg (hibernate.jdbc.batch_size)
                if (x % 20 == 0) {
                    session.flush();
                    session.clear();
                }
                x++;
                session.save(obj);
            }

            //Terminar a transação
            session.getTransaction().commit();

        } catch (HibernateException ex) {
            session.getTransaction().rollback();
            throw ex;
        } finally {
            session.close();
        }
    }

}
