package org.fitog4.service;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import org.fitog4.model.PlayerBoard;

@Path("playerboards")
public class TerraformingMarsPlayerBoardService extends Application {

  @Inject
  private PlayerBoardBean playerBoardBean;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public PlayerBoard getNewPlayerBoard() {
    return playerBoardBean.addNewPlayerBoard();
  }

  @GET
  @Path("/{playerBoardId}")
  @Produces(MediaType.APPLICATION_JSON)
  public PlayerBoard getPlayerBoard(@PathParam("playerBoardId") int playerBoardId) {
    return playerBoardBean.getPlayerBoard(playerBoardId);
  }
}
