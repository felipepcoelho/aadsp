
package org.aadsp.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.aadsp.interfaces.IUsuarioTipo;
import org.aadsp.utils.Conexao;


public class UsuarioTipoDAO 
{
    public IUsuarioTipo consultar(IUsuarioTipo model)
    {
        String query = "select * from AADSP.USUARIO.AADSP_USUARIO_TIPO WHERE ID = ?";
        ResultSet rs = null;
        try {
            Conexao conexao = new Conexao();
            Connection con = conexao.connectionOpen();
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setInt(1,model.getID());
            
            rs = pstm.executeQuery();
        
            while(rs.next()){
                model.setDescricao(rs.getString("descricao"));
            }
            return model;
        } catch (SQLException ex) {
             Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;
    }
}