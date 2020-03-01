package com.mts.user.service;

import com.mts.user.model.User;
import com.mts.user.model.Users;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users")
public interface UserRestService {

    /**
     * Создает пользователя
     *
     * @param user к сохранению
     * @return статус
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Response create(User user);

    /**
     * Редактироваине пользователя
     *
     * @param id   - идентификатор пользователя
     * @param user - новые данные
     * @return статус
     */
    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    Response update(@PathParam("id") long id, User user);

    /**
     * Просмотр списка
     *
     * @return список
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Users getAll();

    /**
     * Удаляет по идентификатору
     *
     * @return статус
     */
    @DELETE
    @Path("/{id : \\d+}")
    Response delete(@PathParam("id") long id);

    /**
     * Просмотр пользователя
     *
     * @param id - идентификатор требуемого
     * @return найденный пользователь
     */
    @GET
    @Path("/{id : \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getUserById(@PathParam("id") long id);

    /**
     * Детали
     */
    @GET
    @Path("/{id : \\d+}/login")
    Response getUserLogin(@PathParam("id") long id);
}
