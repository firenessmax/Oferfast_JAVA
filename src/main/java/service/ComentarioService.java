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

import facade.ComentarioFacade;
import model.Comentario;

@Path("/comentarios")
public class ComentarioService {

	@EJB 
	ComentarioFacade comentarioFacadeEJB;
	
	Logger logger = Logger.getLogger(ComentarioService.class.getName());

	@GET
	@Produces({"application/xml", "application/json"})
	public List<Comentario> findAll(){
		return comentarioFacadeEJB.findAll();
	}
	
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Comentario find(@PathParam("id") Integer id) {
        return comentarioFacadeEJB.find(id);
    }
	
	@POST
    @Consumes({"application/xml", "application/json"})
    public void create(Comentario entity) {
		comentarioFacadeEJB.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Comentario entity) {
    	entity.setComentarioId(id.intValue());
    	comentarioFacadeEJB.edit(entity);
    }
}
