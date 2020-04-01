/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.UsuarioDAO;
import entidades.Usuario;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Estefany Rueda
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {
    
    private Usuario usuario;
    private Usuario usuarioAutenticado =null;
    private List<Usuario> lista;
    private String email;
    private String password;
    
    
    
    @EJB
    private UsuarioDAO ejbDao;
    
    public LoginController(){
        usuario=new Usuario();
    
    }

    
    
    public void login() throws IOException{
        
        FacesContext ctx=FacesContext.getCurrentInstance();
        
        usuarioAutenticado=ejbDao.encontrarUsuarioPorLogin(usuario.getCorreo(), usuario.getClave());
        
        
        //ctx.getExternalContext().redirect("home");
        
        if (usuarioAutenticado !=null){
            ctx.getExternalContext().redirect("home.xhtml");
            
        }
        else{
            ctx.getExternalContext().redirect("index.xhtml");
        }
        
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getLista() {
        lista=ejbDao.listar();
        return lista;
    }

    public void setLista(List<Usuario> lista) {
        this.lista = lista;
    }


    /**
     * Creates a new instance of AuthController
     */

}
