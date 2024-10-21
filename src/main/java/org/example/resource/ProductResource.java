package org.example.resource;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.dto.ProductDTO;
import org.example.exp.AppBadException;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Path("/product")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@CrossOrigin(origins = "http://localhost:9090")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @POST
    @Path("/create")
    public Response createProduct(@Valid @RequestBody ProductDTO dto) {
        ProductDTO savedProduct = productService.create(dto);
        return Response.status(Response.Status.CREATED).entity(savedProduct).build();
    }

    @GET
    @Path("/getAll")
    public List<ProductDTO> getAllProducts() {
        return productService.getAll();
    }

    @GET
    @Path("/{id}")
    public Response getProductById(@PathParam("id") Integer id) {
        ProductDTO dto = productService.getById(id);
        if (dto == null) {
            throw new AppBadException("Product not found");
        }
        return Response.ok(dto).build();
    }


    @PUT
    @Path("/update/{id}")
    public Response updateProduct(@PathParam("id") Integer id, @Valid @RequestBody ProductDTO dto) {
        ProductDTO updatedProduct = productService.updateById(id, dto);
        return Response.ok(updatedProduct).build();
    }

    @DELETE
    @Path("/{id}")
    public Boolean deleteProduct(@PathParam("id") Integer id) {
        return productService.deleteById(id);
    }

}
