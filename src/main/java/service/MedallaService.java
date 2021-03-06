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

import facade.MedallaFacade;
import model.Medalla;

@Path("/medallas")
public class MedallaService {

	@EJB 
	MedallaFacade medallaFacadeEJB;
	
	Logger logger = Logger.getLogger(MedallaService.class.getName());
	
	@GET
	@Produces({"application/xml", "application/json"})
	public List<Medalla> findAll(){
		return medallaFacadeEJB.findAll();
	}
	
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Medalla find(@PathParam("id") Integer id) {
        return medallaFacadeEJB.find(id);
    }
	
	@POST
    @Consumes({"application/xml", "application/json"})
    public void create(Medalla entity) {
		medallaFacadeEJB.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Medalla entity) {
    	entity.setMedallaId(id.intValue());
    	medallaFacadeEJB.edit(entity);
    }

}
