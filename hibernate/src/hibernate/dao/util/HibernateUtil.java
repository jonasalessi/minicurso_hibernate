package hibernate.dao.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

/* 
 * @author <a href="mailto:alessi.jonas@gmail.com">Jonas Alessi</a>
 *
 */
public class HibernateUtil {

	private static SessionFactory sessionFactory;

	static {
		Configuration configure = new Configuration().configure();
		sessionFactory = configure.buildSessionFactory(
				new ServiceRegistryBuilder()
                                        .applySettings(configure.getProperties()).buildServiceRegistry());
                // ----Deprecado, não utilizar
                //new Configuration().configure().buildSessionFactory();
	}

	public static Session getSession() {
            return sessionFactory.openSession();
        }
        
        /* Utilizado para reaproveitar a sessão 
        Se for utilizar deverá ser descomentado a probriedade hibernate.current_session_context_class no hibernate.cfg.xml
        public static Session getSession() {
            if (sessionFactory.getCurrentSession() != null) {
                return sessionFactory.getCurrentSession();
            } 
            return sessionFactory.openSession();
        }*/
}
