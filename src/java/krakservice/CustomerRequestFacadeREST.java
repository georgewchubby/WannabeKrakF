package krakservice;

import dk.cphbusiness.krak.model.CustomerRequest;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author joachim
 */
@Stateless
@Path("request")
@DeclareRoles({"krak","krak-customer"})
@RolesAllowed({"krak"})
public class CustomerRequestFacadeREST extends AbstractFacade<CustomerRequest> {
    @PersistenceContext(unitName = "WannabeKrakFPU")
    private EntityManager em;

    public CustomerRequestFacadeREST() {
        super(CustomerRequest.class);
    }
    
    @Resource
    SessionContext ctx;
    @GET
    @Path("thisuser")
    @Produces({"text/plain"})
    @RolesAllowed({"krak","krak-customer"})
    public String find(@PathParam("id") String id) {
        String user = ctx.getCallerPrincipal().getName();
        CustomerRequest cr = em.find(CustomerRequest.class, user);
        return ""+cr.getRequests();
    }
    
    @GET
    @Path("count/{id}")
    @Produces({"text/plain"})
    @RolesAllowed({"krak"})
    public String findGroupCount(@PathParam("id") String id) {
        CustomerRequest cr = em.find(CustomerRequest.class, id);
        return ""+cr.getRequests();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
