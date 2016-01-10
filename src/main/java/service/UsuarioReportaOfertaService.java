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

import facade.UsuarioReportaOfertaFacade;
import model.UsuarioReportaOferta;

public class UsuarioReportaOfertaService {

	@EJB 
	UsuarioReportaOfertaFacade usuarioReportaOfertaEJB;
	
	Logger logger = Logger.getLogger(UsuarioReportaOfertaService.class.getName());
	
	@GET
	@Produces({"application/xml", "application/json"})
	public List<UsuarioReportaOferta> findAll(){
		return usuarioReportaOfertaEJB.findAll();
	}
	
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public UsuarioReportaOferta find(@PathParam("id") Integer id) {
        return usuarioReportaOfertaEJB.find(id);
    }
	
	@POST
    @Consumes({"application/xml", "application/json"})
    public void create(UsuarioReportaOferta entity) {
		usuarioReportaOfertaEJB.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, UsuarioReportaOferta entity) {
    	entity.setUsuarioId(id.intValue());
    	usuarioReportaOfertaEJB.edit(entity);
    }

}
