package service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ejb.ImagenOfertaFacadeEJB;
import facade.EtiquetaFacade;
import facade.ImagenOfertaFacade;
import facade.OfertaFacade;
import facade.OfertaHasEtiquetaFacade;
import model.Comentario;
import model.Etiqueta;
import model.ImagenOferta;
import model.Oferta;
import model.OfertaHasEtiqueta;
import model.Usuario;
import model.UsuarioReportaOferta;

@Path("/ofertas")
public class OfertaService {

	@EJB 
	OfertaFacade ofertaFacadeEJB;

	@EJB 
	EtiquetaFacade etiquetaFacadeEJB;

	@EJB 
	OfertaHasEtiquetaFacade ofertaHasEtiquetaFacadeEJB;
	
	@EJB 
	ImagenOfertaFacade imagenOfertaFacadeEJB;
	
	
	Logger logger = Logger.getLogger(OfertaService.class.getName());
	
	@GET	//independiente si son visibles o no
	@Path("/all") 
	@Produces({"application/xml", "application/json"})
	public List<Oferta> findAll(){
		logger.setLevel(Level.ALL);
		logger.entering("OfertaService", "findAll");
		List<Oferta> ofertas = ofertaFacadeEJB.findAll();
		if(ofertas.isEmpty()){
			logger.exiting("OfertaService","findAll","sin elementos");
			return ofertas;
		}
		logger.exiting("OfertaService","findAll", ofertas.size());
		return ofertas;
	}
	
	@GET	//solo los visibles
    @Produces({"application/xml", "application/json"})
	public List<Oferta> findAllVisible(){
		List<Oferta> lista = ofertaFacadeEJB.findAllVisible(1);
		return lista;
	}
	
	@GET	//los no visibles
	@Path("/notVisible")
    @Produces({"application/xml", "application/json"})
	public List<Oferta> findAllNotVisible(){
		List<Oferta> lista = ofertaFacadeEJB.findAllVisible(0);
		return lista;
	}
	
	@GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Oferta find(@PathParam("id") Integer id) {
        return ofertaFacadeEJB.find(id);
    }	
	
	@GET
    @Path("{id}/etiquetas")
    @Produces({"application/xml", "application/json"})
    public List<Etiqueta> findEtiquetas(@PathParam("id") Integer id) {
        return ofertaFacadeEJB.findEtiquetaByID(id);
    }	
	
	@GET
    @Path("{id}/comentarios")
    @Produces({"application/xml", "application/json"})
	public List<Comentario> findComentarios(@PathParam("id") Integer id) {
        return ofertaFacadeEJB.findComentarios(id);
    }
	
	@GET
    @Path("{id}/reportes")
    @Produces({"application/xml", "application/json"})
	public List<UsuarioReportaOferta> findReportes(@PathParam("id") Integer id) {
        return ofertaFacadeEJB.findReportes(id);
    }
	
	@GET
    @Path("{id}/imagenes")
    @Produces({"application/xml", "application/json"})
    public List<ImagenOferta> findImagenes(@PathParam("id") Integer id) {
        return ofertaFacadeEJB.findImagenByID(id);
    }
	
	@GET
    @Path("{id}/usuario")//JSON -> reputation, type, urlProfilePicture, urlProfileThumbnail, username, usuarioId
    @Produces({"application/xml", "application/json"})
    public Usuario findUsuario(@PathParam("id") Integer id) {
        int usuarioId = ofertaFacadeEJB.find(id).getUsuarioId();
        return ofertaFacadeEJB.findUsuarioByID(usuarioId);
    }
	
	@POST
    @Path("/newAntiguo")
    @Consumes({"application/xml", "application/json"})
	//aceptar de entrada cualquier JSON, y separarlo, verificar las etiquetas, etc etc etc
    public void create(Oferta entity) {
		ofertaFacadeEJB.create(entity);
    }
	
	@POST
    @Consumes({"application/xml", "application/json"})
	//aceptar de entrada cualquier JSON, y separarlo, verificar las etiquetas, etc etc etc
    public Response createOferta(JsonObject entrada) {
		String respuesta="";
		//se crea la oferta
		Oferta laOferta = ofertaFacadeEJB.crear(entrada);
		//se crea la oferta en la BD
		ofertaFacadeEJB.create(laOferta);
		//se obtiene la clase oferta q esta en BD (ahora tiene el valor de la ID)
		laOferta = ofertaFacadeEJB.findByOferta(laOferta);
		respuesta +="Oferta creada";
		//se ven las etiquetas que estan
		try{
			//existen etiquetas
			if(entrada.getJsonArray("tags").size() > 0){
				//verificar si existe el tag
				List<Etiqueta> listaTag = etiquetaFacadeEJB.addPorOferta(entrada.getJsonArray("tags"));
				//respuesta += listaTag.get(0).getName();
				if(listaTag.isEmpty() == false){
					//respuesta += " listaTag.size(): "+listaTag.size();
					for(int i=0; i<listaTag.size(); i++){
						//respuesta += " "+i+" listaTag(0): "+listaTag.get(i).getName();
						//se guardan las nuevas etiquetas
						etiquetaFacadeEJB.create(listaTag.get(i));
						//respuesta += " "+i+" listaTag(0): "+listaTag.get(i).getName();
					}
				}
				//una vez creadas las etiquetas q no existen, se crean las "conexiones"
				//para eso, se crean las OfertaHasEtiqueta con la Oferta y los nombres de las etiquetas existentes
				List<OfertaHasEtiqueta> listaOHA = ofertaHasEtiquetaFacadeEJB.createByOfertaEtiqueta(laOferta, entrada.getJsonArray("tags"));
				for(int i=0; i<listaOHA.size(); i++){
					//se guardan en BD, todas las OfertaHasEtiqueta
					ofertaHasEtiquetaFacadeEJB.create(listaOHA.get(i));
				}
			}
			respuesta +=" Etiquetas creadas y conectadas ("+entrada.getJsonArray("tags").size()+")";
		} catch(Exception e){
			respuesta +=" No tiene etiquetas ("+entrada.getJsonArray("tags").size()+")";
		} 
		//crear imagenes de oferta
		int imagesNumber = laOferta.getImagesNumber();
		if(imagesNumber>0){
			List<ImagenOferta> listaIO = imagenOfertaFacadeEJB.addPorOferta(imagesNumber, entrada, laOferta.getOfertaId());
			for(int i=0; i<imagesNumber; i++){
				//se guardan en BD, todas las ImagenOferta
				imagenOfertaFacadeEJB.create(listaIO.get(i));
			}
			respuesta +="\nImagenes creadas";
		} else {
			respuesta +="\nNo tiene imagenes";
		}
		JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
		jsonObjBuilder.add("INFO", respuesta);
		JsonObject jsonObj = jsonObjBuilder.build();
		return Response.status(Response.Status.OK).entity(jsonObj).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public Response edit(@PathParam("id") Integer id, JsonObject entrada) {
    	//String respuesta="";
    	Oferta antigua = ofertaFacadeEJB.find(id);
    	antigua = ofertaFacadeEJB.editar(entrada, antigua);
    	ofertaFacadeEJB.edit(antigua);
    	//agregar editar de etiquetas
    	try{
    		//existen etiquetas
			if(entrada.getJsonArray("tags").size() > 0){
				//verificar si existe el tag
				List<Etiqueta> listaTag = etiquetaFacadeEJB.addPorOferta(entrada.getJsonArray("tags"));
				if(listaTag.isEmpty() == false){ //hay tags nuevos	
					//respuesta += " listaTag.size(): "+listaTag.size();
					for(int i=0; i<listaTag.size(); i++){
						//se guardan las nuevas etiquetas
						etiquetaFacadeEJB.create(listaTag.get(i));
					}
				}
				//una vez creadas las etiquetas q no existen, se crean las "conexiones"
				List<OfertaHasEtiqueta> listaOHAnuevas = ofertaHasEtiquetaFacadeEJB.createByOfertaEtiqueta(antigua, entrada.getJsonArray("tags"));
				List<OfertaHasEtiqueta> listaOHAexistentes = ofertaHasEtiquetaFacadeEJB.findByOferta(antigua);
				int buleano=0;
				//respuesta="*nuevas: "+listaOHAnuevas.size()+" *existentes: "+listaOHAexistentes.size();
				//respuesta+="borrar";
				for(int i=0; i<listaOHAexistentes.size(); i++){
					for(int j=0; j<listaOHAnuevas.size(); j++){
						//respuesta+="n: "+listaOHAexistentes.get(i).getEtiquetaId()+" - e: "+listaOHAnuevas.get(j).getEtiquetaId();
						if(listaOHAexistentes.get(i).getEtiquetaId() == listaOHAnuevas.get(j).getEtiquetaId()){
							j=listaOHAnuevas.size()+1;
							buleano=1;
						}
					}
					if(buleano == 0){
						//borrar
						//respuesta+="\nBorrada la relacion: etiqueta("+listaOHAexistentes.get(i).getEtiquetaId()+") - oferta("+listaOHAexistentes.get(i).getOfertaId()+")";
						ofertaHasEtiquetaFacadeEJB.remove(listaOHAexistentes.get(i));
					}
					buleano = 0;
				}
				buleano =0;
				//respuesta+="creacion";
				for(int i=0; i<listaOHAnuevas.size(); i++){
					for(int j=0; j<listaOHAexistentes.size(); j++){
						//respuesta+="n: "+listaOHAnuevas.get(i).getEtiquetaId()+" - e: "+listaOHAexistentes.get(j).getEtiquetaId();
						if(listaOHAnuevas.get(i).getEtiquetaId() == listaOHAexistentes.get(j).getEtiquetaId()){
							j=listaOHAexistentes.size()+1;
							buleano=1;
						}
					}
					if(buleano == 0){
						//crear
						ofertaHasEtiquetaFacadeEJB.create(listaOHAnuevas.get(i));
						//respuesta+="\nCreada la relacion: etiqueta("+listaOHAnuevas.get(i).getEtiquetaId()+") - oferta("+listaOHAnuevas.get(i).getOfertaId()+")";
					}
					buleano =0;
				}
				
			}
    	} catch(Exception e){}
    	//respuesta
		JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
		jsonObjBuilder.add("INFO", "Datos actualizados");
		//jsonObjBuilder.add("String", respuesta);
		JsonObject jsonObj = jsonObjBuilder.build();
		return Response.status(Response.Status.OK).entity(jsonObj).build();
    }

    @PUT
    @Path("{id}/visible")
    @Consumes({"application/xml", "application/json"})
    public Response editDelete(@PathParam("id") Integer id, JsonObject entrada) {
    	int numero = entrada.getInt("visibleOferta");
    	Oferta aux = ofertaFacadeEJB.find(id);
    	aux = ofertaFacadeEJB.editarVisible(numero, aux);
    	ofertaFacadeEJB.edit(aux);
    	//respuesta
		JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
		if(numero == 0){
			jsonObjBuilder.add("INFO", "Oferta eliminada");
		} else {
			jsonObjBuilder.add("INFO", "Oferta visible");
		}
		JsonObject jsonObj = jsonObjBuilder.build();
		return Response.status(Response.Status.OK).entity(jsonObj).build();
    }

}
