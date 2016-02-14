
package org.aadsp.annotations.crud;

import java.util.List;
import org.aadsp.annotations.PermissoesAcesso;
import org.aadsp.annotations.TipoUsuario;
import org.aadsp.interfaces.ICrud;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class PermissoesAcessoCRUD implements ICrud{
    
    private Session sessao;
   
   @Override
    public void setSession(Session sessao) {
        this.sessao = sessao;
    }

    @Override
    public void salvar(Object obj) {
        Transaction transacao = sessao.beginTransaction();
        sessao.save(obj);
        transacao.commit();
        sessao.close();
    }

    @Override
    public void atualizar(Object obj) {
        Transaction transacao = sessao.beginTransaction();
        sessao.update(obj);
        transacao.commit();
        sessao.close();
    }

    @Override
    public void excluir(Object obj) {
        Transaction transacao = sessao.beginTransaction();
        sessao.delete(obj);
        transacao.commit();
        sessao.close();
    }
    
    
    public List<PermissoesAcesso> listar()throws Exception
    {
        try{
            Query consulta = sessao.createQuery("from PermissoesAcesso");
            return consulta.list();
        }catch(Exception e){
            throw e;
        }finally{
            sessao.close();
        }
    }
    
    public List<PermissoesAcesso> listarPorTipoUsuario(TipoUsuario tipoUsuario)throws Exception
    {
        try{
            Query consulta = sessao.createQuery("from PermissoesAcesso WHERE ID_tipoUsuario = :idTipoUsuario");
            consulta.setInteger("idTipoUsuario", tipoUsuario.getID());
            return consulta.list();
        }catch(Exception e){
            throw e;
        }finally{
            sessao.close();
        }
    }

}
