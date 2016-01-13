package service;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import facade.UsuarioReportaOfertaFacade;
import model.Oferta;
import model.UsuarioReportaOferta;

@Path("/reportes")
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
	@Path("/newAntiguo")
    @Consumes({"application/xml", "application/json"})
    public void create(UsuarioReportaOferta entity) {
		usuarioReportaOfertaEJB.create(entity);
    }
	
	@POST
    @Consumes({"application/xml", "application/json"})
    public Response crear(JsonObject entrada) {
		UsuarioReportaOferta entity = usuarioReportaOfertaEJB.crear(entrada);
		usuarioReportaOfertaEJB.create(entity);
		
		JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
		jsonObjBuilder.add("INFO", "Reporte creado");
		JsonObject jsonObj = jsonObjBuilder.build();
		return Response.status(Response.Status.OK).entity(jsonObj).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public Response edit(@PathParam("id") Integer id, JsonObject entrada) {
    	UsuarioReportaOferta entity = usuarioReportaOfertaEJB.editar(id.intValue(), entrada);
    	usuarioReportaOfertaEJB.edit(entity);
    	//respuesta
		JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
		jsonObjBuilder.add("INFO", "Datos actualizados");
		JsonObject jsonObj = jsonObjBuilder.build();
		return Response.status(Response.Status.OK).entity(jsonObj).build();
    }

}
