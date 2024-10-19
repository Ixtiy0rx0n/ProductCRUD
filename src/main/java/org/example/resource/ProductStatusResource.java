package org.example.resource;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.dto.StatusDTO;
import org.example.service.ProductStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Path("/status")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductStatusResource {

    @Autowired
    private ProductStatusService statusService;


    @POST
    @Path("/create")
    public Response createStatus(@Valid @RequestBody StatusDTO dto) {
        StatusDTO savedStatus = statusService.create(dto);
        return Response.status(Response.Status.CREATED).entity(savedStatus).build();
    }

    @GET
    @Path("/getAll")
    public List<StatusDTO> getAllStatuses() {
        return statusService.getAll();
    }

    @GET
    @Path("/{id}")
    public Response getStatusById(@PathParam("id") Integer id) {
        StatusDTO dto = statusService.getById(id);
        if (dto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(dto).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateStatus(@PathParam("id") Integer id, @Valid @RequestBody StatusDTO dto) {
        StatusDTO updatedStatus = statusService.updateById(id, dto);
        return Response.ok(updatedStatus).build();
    }

    @DELETE
    @Path("/{id}")
    public Boolean deleteStatus(@PathParam("id") Integer id) {
        return statusService.deleteById(id);
    }
}
