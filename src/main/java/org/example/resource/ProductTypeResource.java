package org.example.resource;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.dto.TypeDTO;
import org.example.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Path("/type")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductTypeResource {


    @Autowired
    private ProductTypeService typeService;


    @POST
    @Path("/create")
    public Response createType(@Valid @RequestBody TypeDTO dto) {
        TypeDTO savedType = typeService.create(dto);
        return Response.status(Response.Status.CREATED).entity(savedType).build();
    }

    @GET
    @Path("/getAll")
    public List<TypeDTO> getAllTypes() {
        return typeService.getAll();
    }

    @GET
    @Path("/{id}")
    public Response getTypeById(@PathParam("id") Integer id) {
        TypeDTO dto = typeService.getById(id);
        if (dto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(dto).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateType(@PathParam("id") Integer id, @Valid @RequestBody TypeDTO dto) {
        TypeDTO updatedType = typeService.updateById(id, dto);
        return Response.ok(updatedType).build();
    }

    @DELETE
    @Path("/{id}")
    public Boolean deleteType(@PathParam("id") Integer id) {
        return typeService.deleteById(id);
    }
}
