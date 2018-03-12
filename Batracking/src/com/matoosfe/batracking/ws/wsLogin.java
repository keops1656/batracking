package com.matoosfe.batracking.ws;


import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import com.matoosfe.batracking.entidad.ELogin;
import com.matoosfe.batracking.modelo.Usuario;
import com.matoosfe.batracking.negocio.seguridad.UsuarioFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;
//import org.json.JSONObject;

@Stateless
@Path("wsLogin")
public class wsLogin {
	
	@EJB
	private UsuarioFacade admUsuario;
    
    @POST
    @Consumes("application/json; charset=utf-8")
    @Produces("application/json; charset=utf-8")
    @Path("login")
    public Response acceso( ELogin login ) throws Exception {
        try {
        	Usuario usuario= new Usuario();
        	usuario = admUsuario.validarUsuario(login.getUsuario(), login.getPassword()); 
        	login = new ELogin(usuario);
        	login.setToken("ACDCACDCACDC");
        	return Response.status(200).entity(login)
        			.header("Access-Control-Allow-Origin","*")
        			.build();
        } catch (Exception e) {
            return Response.status(401).entity(e.getMessage())
        			.header("Access-Control-Allow-Origin","*")
            		.build();
        }
    }
    
}
