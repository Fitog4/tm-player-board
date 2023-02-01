package org.fitog4;

import org.fitog4.model.Mode;
import org.fitog4.model.PlayerBoard;
import org.fitog4.model.Resource;
import org.fitog4.view.ShellPlayerAction;
import org.fitog4.view.ShellView;

import java.util.ArrayList;
import java.util.List;

import static org.fitog4.view.ShellPlayerAction.DO_SOMETHING_ELSE;
import static org.fitog4.view.ShellPlayerAction.END_GAME;
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
  }

  public static void main(String[] args) throws Exception {
    ShellApp app = new ShellApp(new PlayerBoard(), Mode.TURN, new ShellView());
    app.run();
  }

  private void run() throws Exception {
    initPlayerBoard();

    availableActions.addAll(List.of(VIEW_PLAYER_BOARD, END_GAME, DO_SOMETHING_ELSE));

    shellView.greetPlayer();

    while (!endGame) {
      ShellPlayerAction playerAction = shellView.getPlayerAction(availableActions);
      process(playerAction);
    }
  }

  private void process(ShellPlayerAction playerAction) throws Exception {
    switch (playerAction) {
      case VIEW_PLAYER_BOARD:
        shellView.show(playerBoard);
        break;
      case END_GAME:
        endGame = true;
        shellView.sayByeToPlayer();
        break;
      default:
        throw new Exception();
    }
  }

  private void initPlayerBoard() {
    Resource megaCredits = new Resource();
    megaCredits.setAmount(42);
    megaCredits.setProduction(-3);
    playerBoard.setMegaCredits(megaCredits);
    playerBoard.setSteel(new Resource());
    playerBoard.setTitanium(new Resource());
    playerBoard.setPlants(new Resource());
    playerBoard.setEnergy(new Resource());
    playerBoard.setHeat(new Resource());
  }

}
