package org.fitog4.service;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import org.fitog4.controller.PlayerBoardController;
import org.fitog4.controller.ResourceChangeDTO;
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

  // curl -X POST http://localhost:8080/tm-player-board/webapi/playerboards/0/delta/resources \
  //    -H 'Content-Type: application/json' \
  //    -d '{"deltaMegaCredits": -30, "deltaSteel": 0, "deltaTitanium": 0, "deltaPlants": 0, "deltaEnergy": 10, "deltaHeat": 0}'
  @POST
  @Path("/{playerBoardId}/delta/resources")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public PlayerBoard earnOrSpendResources(@PathParam("playerBoardId") int playerBoardId, ResourceChangeDTO resourceChangeDTO) {
    PlayerBoard playerBoard = playerBoardBean.getPlayerBoard(playerBoardId);
    new PlayerBoardController().applyResourceAmountChange(resourceChangeDTO, playerBoard);
    return playerBoard;
  }

  // curl -X POST http://localhost:8080/tm-player-board/webapi/playerboards/0/delta/production \
  //    -H 'Content-Type: application/json' \
  //    -d '{"deltaMegaCredits": -30, "deltaSteel": 0, "deltaTitanium": 0, "deltaPlants": 0, "deltaEnergy": 10, "deltaHeat": 0}'
  @POST
  @Path("/{playerBoardId}/delta/production")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public PlayerBoard increaseOrDecreaseResourceProduction(@PathParam("playerBoardId") int playerBoardId, ResourceChangeDTO resourceChangeDTO) {
    PlayerBoard playerBoard = playerBoardBean.getPlayerBoard(playerBoardId);
    new PlayerBoardController().applyResourceProductionChange(resourceChangeDTO, playerBoard);
    return playerBoard;
  }

  // curl -X POST http://localhost:8080/tm-player-board/webapi/playerboards/0/production
  @POST
  @Path("/{playerBoardId}/production")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public PlayerBoard production(@PathParam("playerBoardId") int playerBoardId) {
    PlayerBoard playerBoard = playerBoardBean.getPlayerBoard(playerBoardId);
    new PlayerBoardController().processProduction(playerBoard);
    return playerBoard;
  }

  // curl -X POST http://localhost:8080/tm-player-board/webapi/playerboards/0/trrating/25
  @POST
  @Path("/{playerBoardId}/trrating/{terraformingRating}")
  @Produces(MediaType.APPLICATION_JSON)
  public PlayerBoard setTerraformingRating(@PathParam("playerBoardId") int playerBoardId, @PathParam("terraformingRating") int terraformingRating) {
    PlayerBoard playerBoard = playerBoardBean.getPlayerBoard(playerBoardId);
    playerBoard.setTerraformRating(terraformingRating);
    return playerBoard;
  }

  // curl -X POST http://localhost:8080/tm-player-board/webapi/playerboards/0/mcpersteel/25
  @POST
  @Path("/{playerBoardId}/mcpersteel/{megaCreditsPerUnitOfSteel}")
  @Produces(MediaType.APPLICATION_JSON)
  public PlayerBoard setMegaCreditsPerUnitOfSteel(@PathParam("playerBoardId") int playerBoardId, @PathParam("megaCreditsPerUnitOfSteel") int megaCreditsPerUnitOfSteel) {
    PlayerBoard playerBoard = playerBoardBean.getPlayerBoard(playerBoardId);
    playerBoard.setMegaCreditsPerUnitOfSteel(megaCreditsPerUnitOfSteel);
    return playerBoard;
  }

  // curl -X POST http://localhost:8080/tm-player-board/webapi/playerboards/0/mcpertitanium/25
  @POST
  @Path("/{playerBoardId}/mcpertitanium/{megaCreditsPerUnitOfTitanium}")
  @Produces(MediaType.APPLICATION_JSON)
  public PlayerBoard setMegaCreditsPerUnitOfTitanium(@PathParam("playerBoardId") int playerBoardId, @PathParam("megaCreditsPerUnitOfTitanium") int megaCreditsPerUnitOfTitanium) {
    PlayerBoard playerBoard = playerBoardBean.getPlayerBoard(playerBoardId);
    playerBoard.setMegaCreditsPerUnitOfTitanium(megaCreditsPerUnitOfTitanium);
    return playerBoard;
  }
}
