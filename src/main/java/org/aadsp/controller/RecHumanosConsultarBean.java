package org.aadsp.controller;


import java.io.IOException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.aadsp.annotations.Usuario;
import org.aadsp.annotations.crud.UsuarioCRUD;
import org.aadsp.interfaces.ABaseBean;
import org.aadsp.utils.FactoryHibernate;


@ManagedBean(name="recHumanosConsultarBean")
@ViewScoped
public class RecHumanosConsultarBean extends ABaseBean
{   
    private Usuario usuario;
    
    public RecHumanosConsultarBean(){
        this.usuario = new Usuario();
    }
    
    public List<Usuario> getUsuarios(){
        try {
            UsuarioCRUD crud = new UsuarioCRUD();
            crud.setSession(FactoryHibernate.getSessionFactory().openSession());
            return crud.listar();
        } catch (Exception ex) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage( FacesMessage.SEVERITY_ERROR," ERRO!!  ",  "Não foi possível buscar a lista de usuários no banco de dados!"));
        }
        return null;
    }
    
    public void editar(Usuario selecionado) throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("usuario", selecionado);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/aadsp/faces/views/recursosHumanos/editarPessoal.xhtml");
    }

}
