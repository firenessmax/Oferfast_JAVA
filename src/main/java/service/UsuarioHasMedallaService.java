package service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import facade.UsuarioHasMedallaFacade;
import model.UsuarioHasMedalla;

public class UsuarioHasMedallaService {

	@EJB 
	UsuarioHasMedallaFacade usuarioHasMedallaEJB;
	
	Logger logger = Logger.getLogger(OfertaService.class.getName());
	
	@GET
	@Produces({"application/xml", "application/json"})
	public List<UsuarioHasMedalla> findAll(){
		return usuarioHasMedallaEJB.findAll();
	}
	
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public UsuarioHasMedalla find(@PathParam("id") Integer id) {
        return usuarioHasMedallaEJB.find(id);
    }
	
	@POST
    @Consumes({"application/xml", "application/json"})
    public void create(UsuarioHasMedalla entity) {
		usuarioHasMedallaEJB.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, UsuarioHasMedalla entity) {
    	entity.setUsuarioId(id.intValue());
    	usuarioHasMedallaEJB.edit(entity);
    }


}
