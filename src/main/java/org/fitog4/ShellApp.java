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

    while (!endGame) {
      ShellPlayerAction playerAction = shellView.getPlayerAction(availableActions);
      if (playerAction != null) {
        process(playerAction);
      }
    }
  }

  private void process(ShellPlayerAction playerAction) {
    switch (playerAction) {
      case VIEW_PLAYER_BOARD:
        shellView.show(playerBoard);
        break;
      case SPEND_OR_EARN_RESOURCES:
        ResourceChangeDTO resourceAmountChange = shellView.getResourceChange();
        new PlayerBoardController().applyResourceAmountChange(resourceAmountChange, playerBoard);
        shellView.show(playerBoard);
        break;
      case INCREASE_OR_DECREASE_RESOURCE_PRODUCTION:
        ResourceChangeDTO resourceProductionChange = shellView.getResourceChange();
        new PlayerBoardController().applyResourceProductionChange(resourceProductionChange, playerBoard);
        shellView.show(playerBoard);
        break;
      case END_GAME:
        endGame = true;
        shellView.sayByeToPlayer();
        break;
    }
  }

  private List<ShellPlayerAction> getAvailableActions() {
    switch (mode) {
      case ACTION_PHASE:
        return List.of(VIEW_PLAYER_BOARD, SPEND_OR_EARN_RESOURCES, INCREASE_OR_DECREASE_RESOURCE_PRODUCTION, END_GAME);
      default:
        return Collections.singletonList(END_GAME);
    }
  }

  private void initPlayerBoard() {
    playerBoard.setMegaCreditsAmount(42);
    playerBoard.setMegaCreditsProduction(-3);
  }

}
