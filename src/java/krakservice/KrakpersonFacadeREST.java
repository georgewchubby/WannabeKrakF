package krakservice;

import dk.cphbusiness.krak.model.CustomerRequest;
import dk.cphbusiness.krak.model.Krakperson;
import java.util.List;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author joachim
 */
@Stateless
@Path("person")
@DeclareRoles({"krak", "krak-customer"})
@RolesAllowed({"krak"})
public class KrakpersonFacadeREST extends AbstractFacade<Krakperson> {

    @PersistenceContext(unitName = "WannabeKrakFPU")
    private EntityManager em;

    public KrakpersonFacadeREST() {
        super(Krakperson.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Krakperson entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, Krakperson entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @Resource
    SessionContext ctx;

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    @RolesAllowed({"krak", "krak-customer"})
    public Krakperson find(@PathParam("id") String id) {
        String user = ctx.getCallerPrincipal().getName();
        CustomerRequest cr = em.find(CustomerRequest.class, user);
        cr.update();
        em.merge(cr);
        Krakperson person = super.find(id);
        if (person == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return person;
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Krakperson> findAll() {
        return super.findAll();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
