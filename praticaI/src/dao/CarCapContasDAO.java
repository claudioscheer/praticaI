package dao;

import enumeraveis.StatusConta;
import enumeraveis.TipoConta;
import java.util.Date;
import java.util.List;
import model.CarCapContas;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

public class CarCapContasDAO {

    public boolean update(CarCapContas conta) {
        
        try {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(conta);
        session.getTransaction().commit();
        session.close();
         return true;
        } catch (Exception e) {
            
            System.out.println(e);
            return false;
        }
       
       
    }
    
    
     public CarCapContas altera(int id,CarCapContas cont) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Query query = session.createQuery("from CarCapContas as a where contaID =:codigo ");

        query.setParameter("codigo", id);

        
        
        
        
        session.update(cont);
        session.getTransaction().commit();
        session.close();
        
        return cont;

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
      public CarCapContas BuscarContasId(int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Query query = session.createQuery("from CarCapContas as a where contaID =:codigo ");

        query.setParameter("codigo", id);

        CarCapContas contas = (CarCapContas) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        
        return contas;

    }
    
    
    
    
    
    
    
    
    
    
    
    

    public Boolean insert(CarCapContas conta) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(conta);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public Boolean delete(CarCapContas conta) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.delete(conta);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public List<CarCapContas> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("from CarCapContas as a");
        List<CarCapContas> contas = query.list();
        session.getTransaction().commit();
        session.close();
        return contas;
    }

    public List<CarCapContas> ListarTodos(Date dataInicial, Date dataFinal) {

        System.out.println("DataInicial: " + dataInicial + " DataFinal: " + dataFinal);

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("from CarCapContas as a where contadataemissao BETWEEN :datainicial and :datafinal order by contadataemissao");
        query.setParameter("datainicial", dataInicial);
        query.setParameter("datafinal", dataFinal);
        List<CarCapContas> contas = query.list();
        session.getTransaction().commit();
        session.close();
        return contas;
    }
    
    
    

    public List<CarCapContas> ListarTodosPaginacao(int paginaBuscar, Date dataInicial, Date dataFinal) {

        System.out.println("DataInicial: " + dataInicial + " DataFinal: " + dataFinal);

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("from CarCapContas as a where contadataemissao BETWEEN :datainicial and :datafinal");
        query.setParameter("datainicial", dataInicial);
        query.setParameter("datafinal", dataFinal);
        
        int maxResults = utils.Utils.MaxResultQuery;
        
        query.setMaxResults(maxResults);
        query.setFirstResult(paginaBuscar * maxResults);
        
        List<CarCapContas> contas = query.list();   
        session.getTransaction().commit();
        session.close();
        return contas;
    }
    
    
    public List<CarCapContas> ListarContas(TipoConta tipo, Date dataInicial, Date dataFinal) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Query query = session.createQuery("from CarCapContas as a where contadataemissao BETWEEN :datainicial and :datafinal and contatipo= :tipo");
        query.setParameter("datainicial", dataInicial);
        query.setParameter("datafinal", dataFinal);

        query.setParameter("tipo", tipo.ordinal());

        List<CarCapContas> contas = query.list();
        session.getTransaction().commit();
        session.close();
        return contas;

    }

    public List<CarCapContas> BuscarContasOperacao(int codigoOperacao, Date dataInicial, Date dataFinal) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Query query = session.createQuery("from CarCapContas as a where flxcxoperacoes_opcodigo = :codigo and contadataemissao BETWEEN :dataInicial and :dataFinal and contaStatus = :status");

        query.setParameter("codigo", codigoOperacao);
        query.setParameter("dataInicial", dataInicial);
        query.setParameter("dataFinal", dataFinal);
        query.setParameter("status", StatusConta.Fechada);
        
        
        List<CarCapContas> contas = query.list();
        session.getTransaction().commit();
        session.close();
        return contas;

    }
    
      

    public double SomarContas(TipoConta tipo, Date dataInicial, Date dataFinal) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        System.out.println("DataFinal" + dataFinal + "DataInicial: " + dataInicial);

        Query query = session.createQuery("select sum(a.contaValorPago) from CarCapContas as a where contaTipo =:tipo and contadataemissao BETWEEN :dataInicial and :dataFinal");
        query.setParameter("dataInicial", dataInicial);
        query.setParameter("dataFinal", dataFinal);
        query.setParameter("tipo", tipo);

        double total = (double) (query.uniqueResult() == null ? 0.0 : query.uniqueResult());

        session.getTransaction().commit();
        session.close();
        return total;

    }

    public Date BuscaUltimaData(TipoConta tipo, Date dataInicial, Date dataFinal) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        System.out.println("DataFinal" + dataFinal + "DataInicial: " + dataInicial);

        Query query;
        
        if (tipo == TipoConta.ambos) {
            query = session.createQuery("select max(a.contaDataEmissao) as max from CarCapContas as a where contadataemissao BETWEEN :dataInicial and :dataFinal");
            query.setParameter("dataInicial", dataInicial);
            query.setParameter("dataFinal", dataFinal);            
        } else {
            query = session.createQuery("select max(a.contaDataEmissao) as max from CarCapContas as a where contaTipo =:tipo and contadataemissao BETWEEN :dataInicial and :dataFinal");
            query.setParameter("dataInicial", dataInicial);
            query.setParameter("dataFinal", dataFinal);
            query.setParameter("tipo", tipo);
        }

        Date maior = (Date) (query.uniqueResult() == null ? null : query.uniqueResult());

        session.getTransaction().commit();
        session.close();
        return maior;

    }
    
    public double Fechamento(Date data) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Query query = session.createQuery("select sum(a.contaValorPago) from CarCapContas as a where contaTipo =:tipo and contadataemissao <= :dataInicial and contaStatus = :status");
        query.setParameter("dataInicial", data);
        query.setParameter("status", StatusConta.Fechada);
        query.setParameter("tipo", TipoConta.Entrada);

        double entrada = (double) (query.uniqueResult() == null ? 0.0 : query.uniqueResult());
        
        query = session.createQuery("select sum(a.contaValorPago) from CarCapContas as a where contaTipo =:tipo and contadataemissao <= :dataInicial and contaStatus = :status");
        query.setParameter("dataInicial", data);
        query.setParameter("status", StatusConta.Fechada);
        query.setParameter("tipo", TipoConta.Saida);

        double saida = (double) (query.uniqueResult() == null ? 0.0 : query.uniqueResult());
        
        
        double total = entrada - saida;

        session.getTransaction().commit();
        session.close();
        return total;

    }

}
