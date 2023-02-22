package org.fitog4;

import org.fitog4.controller.PlayerBoardController;
import org.fitog4.controller.ResourceChangeDTO;
import org.fitog4.model.Mode;
import org.fitog4.model.PlayerBoard;
import org.fitog4.view.ShellPlayerAction;
import org.fitog4.view.ShellView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.fitog4.view.ShellPlayerAction.END_GAME;
import static org.fitog4.view.ShellPlayerAction.INCREASE_OR_DECREASE_RESOURCE_PRODUCTION;
import static org.fitog4.view.ShellPlayerAction.PRODUCTION;
import static org.fitog4.view.ShellPlayerAction.SET_MEGA_CREDITS_PER_STEEL;
import static org.fitog4.view.ShellPlayerAction.SET_MEGA_CREDITS_PER_TITANIUM;
import static org.fitog4.view.ShellPlayerAction.SET_TERRAFORMING_RATING;
import static org.fitog4.view.ShellPlayerAction.SPEND_OR_EARN_RESOURCES;
import static org.fitog4.view.ShellPlayerAction.VIEW_PLAYER_BOARD;

public class ShellApp {

  private final PlayerBoard playerBoard;
  private final Mode mode;
  private final ShellView shellView;
  private final List<ShellPlayerAction> availableActions = new ArrayList<>();
  private boolean endGame;

  private ShellApp(PlayerBoard playerBoard, Mode mode, ShellView shellView) {
    this.playerBoard = playerBoard;
    this.mode = mode;
    this.shellView = shellView;
    availableActions.addAll(getAvailableActions());
  }

  public static void main(String[] args) throws Exception {
    ShellApp app = new ShellApp(new PlayerBoard(), Mode.ACTION_PHASE, new ShellView());
    app.run();
  }

  private void run() {
    shellView.greetPlayer();
    initPlayerBoard();

    shellView.show(playerBoard);

    while (!endGame) {
      ShellPlayerAction playerAction = shellView.askPlayerAction(availableActions);
      if (playerAction != null) {
        process(playerAction);
      }
    }
  }

  private void process(ShellPlayerAction playerAction) {
    switch (playerAction) {
      case VIEW_PLAYER_BOARD:
        break;
      case SPEND_OR_EARN_RESOURCES:
        ResourceChangeDTO resourceAmountChange = shellView.askResourceChange();
        new PlayerBoardController().applyResourceAmountChange(resourceAmountChange, playerBoard);
        break;
      case INCREASE_OR_DECREASE_RESOURCE_PRODUCTION:
        ResourceChangeDTO resourceProductionChange = shellView.askResourceChange();
        new PlayerBoardController().applyResourceProductionChange(resourceProductionChange, playerBoard);
        break;
      case PRODUCTION:
        new PlayerBoardController().processProduction(playerBoard);
        break;
      case SET_TERRAFORMING_RATING:
        int tr = shellView.askNewValue("TR");
        playerBoard.setTerraformRating(tr);
        break;
      case SET_MEGA_CREDITS_PER_STEEL:
        int megaCreditsPerUnitOfSteel = shellView.askNewValue("M\u20ac per unit of Steel");
        playerBoard.setMegaCreditsPerUnitOfSteel(megaCreditsPerUnitOfSteel);
        break;
      case SET_MEGA_CREDITS_PER_TITANIUM:
        int megaCreditsPerUnitOfTitanium = shellView.askNewValue("M\u20ac per unit of Titanium");
        playerBoard.setMegaCreditsPerUnitOfTitanium(megaCreditsPerUnitOfTitanium);
        break;
      case END_GAME:
        endGame = true;
        shellView.sayByeToPlayer();
        return;
      default:
        throw new RuntimeException("Player action unknown!");
    }

    shellView.show(playerBoard);
  }

  private List<ShellPlayerAction> getAvailableActions() {
    switch (mode) {
      case ACTION_PHASE:
        return List.of(VIEW_PLAYER_BOARD, SPEND_OR_EARN_RESOURCES, INCREASE_OR_DECREASE_RESOURCE_PRODUCTION, PRODUCTION, SET_TERRAFORMING_RATING, SET_MEGA_CREDITS_PER_STEEL, SET_MEGA_CREDITS_PER_TITANIUM, END_GAME);
      default:
        return Collections.singletonList(END_GAME);
    }
  }

  private void initPlayerBoard() {
    playerBoard.setMegaCreditsAmount(42);
    playerBoard.setMegaCreditsProduction(3);
  }

}
