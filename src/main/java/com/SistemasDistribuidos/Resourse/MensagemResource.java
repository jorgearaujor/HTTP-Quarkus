package com.SistemasDistribuidos.Resourse;

import com.SistemasDistribuidos.Models.Mensagem;
import com.SistemasDistribuidos.Service.MensagemService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/mensagens")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MensagemResource {

    @Inject
    MensagemService service;

    @GET
    public List<Mensagem> listar() {
        return service.listar();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response buscar(@PathParam("id") Long id) {
        return service.buscarPorId(id)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    public Response adicionar(Mensagem mensagem) {
        Mensagem nova = service.adicionar(mensagem);
        return Response.status(Response.Status.CREATED).entity(nova).build();
    }

    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") Long id) {
        boolean removido = service.remover(id);
        if (removido) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
