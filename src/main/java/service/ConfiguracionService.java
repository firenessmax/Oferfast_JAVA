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

import facade.ConfiguracionFacade;
import model.Configuracion;

@Path("/configuraciones")
public class ConfiguracionService {

	@EJB 
	ConfiguracionFacade configuracionFacadeEJB;
	
	Logger logger = Logger.getLogger(ConfiguracionService.class.getName());
	
	@GET
	@Produces({"application/xml", "application/json"})
	public List<Configuracion> findAll(){
		return configuracionFacadeEJB.findAll();
	}
	
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Configuracion find(@PathParam("id") Integer id) {
        return configuracionFacadeEJB.find(id);
    }
	
	@POST
    @Consumes({"application/xml", "application/json"})
    public void create(Configuracion entity) {
		configuracionFacadeEJB.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Configuracion entity) {
    	entity.setConfiguracionId(id.intValue());
    	configuracionFacadeEJB.edit(entity);
    }

}
