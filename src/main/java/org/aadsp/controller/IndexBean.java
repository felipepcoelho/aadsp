package org.aadsp.controller;



import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.aadsp.annotations.Autenticacao;
import org.aadsp.annotations.crud.AutenticacaoCRUD;
import org.aadsp.interfaces.ABaseBean;
import org.aadsp.utils.ConexaoHibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


@ManagedBean(name="indexBean")
@ViewScoped
public class IndexBean extends ABaseBean
{   
    
    public IndexBean(){
       autenticacao = new Autenticacao();
       autenticacao.setLogin("");
       autenticacao.setSenha("");
    }

    public Autenticacao getAutenticacao() {
        return autenticacao;
    }

    public void setAutenticacao(Autenticacao autenticacao) {
        this.autenticacao = autenticacao;
    }
    
    public void autenticar() throws IOException
    {
       try{
        AutenticacaoCRUD crud = new AutenticacaoCRUD();
        Session sessao = null;
        sessao = ConexaoHibernate.getSessionFactory().openSession();
        crud.setSession(sessao);
        autenticacao = crud.autenticar(autenticacao);
        sessao = null;
        if(autenticacao != null)
        {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) facesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpSession session = request.getSession();
            session.setAttribute("autenticacao", autenticacao);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/aadsp/faces/views/menu/index.xhtml");
        }
        else
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage( FacesMessage.SEVERITY_WARN," ACESSO NEGADO  ",  "Não foi possível autenticar o usuário com os dados informados!"));
        }
       }catch(Exception e){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage( FacesMessage.SEVERITY_ERROR," ERRO!!  ",  "Não foi possível realizar a autenticação no banco de dados!"));
       }
    }
    
    private static final long serialVersionUID = 5585493974059809141L;
    private Autenticacao autenticacao;
}
