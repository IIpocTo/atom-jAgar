package accountserver.profile;

import accountserver.auth.Authorized;
import entities.token.Token;
import dao.DatabaseAccessLayer;
import entities.user.UserEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("actions")
public class UserActions {

    private static final Logger log = LogManager.getLogger(UserActions.class);

    /* curl -X POST
         -H "Content-Type: application/x-www-form-urlencoded"
         -H "Authorization: Bearer {token}"
         -H "Host: localhost:8080
         -d "score={newScore}"
    "http://localhost:8080/actions/addscore"*/
    @Authorized
    @POST
    @Path("/addscore")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/plain")
    public Response addUserScore(@HeaderParam("Authorization") String rawToken,
                                         @FormParam("score") String score) {
        try {
            if (score == null || "".equals(score)) {
                log.warn("Wrong value - " + score);
                return Response.status(Response.Status.BAD_REQUEST).build();
            }

            Token token = DatabaseAccessLayer.parse(rawToken);
            if (!DatabaseAccessLayer.contains(token)) {
                log.warn(token);
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            UserEntity user = DatabaseAccessLayer.getUser(token);
            Integer oldScore = user.getScore();
            user.updateScore(20);
            Integer newScore = user.getScore();
            log.info("UserEntity increased its score from {} to {}", oldScore, newScore);
            return Response.ok("Your score successfully increased up to " + newScore).build();
        } catch (Exception e) {
            return Response.ok(e.toString()).build();
        }
    }
}
