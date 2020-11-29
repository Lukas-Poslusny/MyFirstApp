package cz.educanet.webik;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/users") //localhost:9990/MyFirstApp/api/users
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource {
    private static List<Users> users = new ArrayList<Users>();

    @GET
    public Response getAll() {
       return Response.ok(users).build();
    }


    public Boolean doesUserExist(Users user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(user.getUsername())) {
                return true;
            }
        }
        return false;
    }


    @POST
    public Response createUser(@QueryParam("username") String username, @QueryParam("password") String password) {
        Users tempUser = new Users(username, password);
        if (doesUserExist(tempUser)) {
            return Response.status(Response.Status.valueOf("User already exists!")).build();
        }
        else {
            users.add(tempUser);
            return Response.ok("New user created!").build();
        }
    }


    @DELETE
    public Response removeUser(Users user) {
        if (doesUserExist(user)) {
            users.remove(user);
            return Response.ok("User has been deleted.").build();
        }
        else {
            return Response.status(Response.Status.valueOf("User does not exist.")).build();
        }
    }


    @PUT
    @Path("/{{username}}")
    public Response changeUser(@PathParam("username") String username, @QueryParam("username") String newUsername) {
        Users tempUser = new Users(username, "");
        if (doesUserExist(tempUser)) {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUsername().equals(tempUser.getUsername())) {
                    users.get(i).changeUsername(newUsername);
                    return Response.ok("Username changed.").build();
                }
            }
        }
        else {
            return Response.status(Response.Status.valueOf("User does not exist.")).build();
        }
        return Response.status(Response.Status.valueOf("Internal server error.")).build();
    }
}
