package org.fitog4;

import org.fitog4.controller.CardDTO;
import org.fitog4.controller.PlayerBoardController;
import org.fitog4.controller.ResourceChangeDTO;
import org.fitog4.controller.ResourceSimulationDTO;
import org.fitog4.model.Mode;
import org.fitog4.model.PlayerBoard;
import org.fitog4.view.ShellPlayerAction;
import org.fitog4.view.ShellView;

import java.util.ArrayList;
import java.util.List;

import static org.fitog4.view.ShellPlayerAction.END_GAME;
import static org.fitog4.view.ShellPlayerAction.INCREASE_OR_DECREASE_RESOURCE_PRODUCTION;
import static org.fitog4.view.ShellPlayerAction.PRODUCTION;
import static org.fitog4.view.ShellPlayerAction.SET_MEGA_CREDITS_PER_STEEL;
import static org.fitog4.view.ShellPlayerAction.SET_MEGA_CREDITS_PER_TITANIUM;
import static org.fitog4.view.ShellPlayerAction.SET_TERRAFORMING_RATING;
import static org.fitog4.view.ShellPlayerAction.SIMULATE_ALLOCATION;
import static org.fitog4.view.ShellPlayerAction.SPEND_OR_EARN_RESOURCES;
import static org.fitog4.view.ShellPlayerAction.TOGGLE_MODE;

public class ShellApp {

  private final PlayerBoard playerBoard;
  private Mode mode;
  private final ShellView shellView;
  private final List<ShellPlayerAction> availableActions = new ArrayList<>();
  private boolean endGame;

  private ShellApp(PlayerBoard playerBoard, Mode mode, ShellView shellView) {
    this.playerBoard = playerBoard;
    this.mode = mode;
    this.shellView = shellView;
    refreshAvailableActions();
  }

  public static void main(String[] args) throws Exception {
    ShellApp app = new ShellApp(new PlayerBoard(), Mode.ACTION, new ShellView());
    app.run();
  }

  private void run() {
    shellView.greetPlayer();
    initPlayerBoard();

    shellView.show(playerBoard);

    while (!endGame) {
      ShellPlayerAction playerAction = shellView.askPlayerAction(availableActions, mode);
      if (playerAction != null) {
        process(playerAction);
      }
    }
  }

  private void process(ShellPlayerAction playerAction) {
    switch (playerAction) {
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
      case TOGGLE_MODE:
        toggleMode();
        return;
      case SIMULATE_ALLOCATION:
        boolean done = false;
        while (!done) {
          List<CardDTO> allocation = shellView.askCardAllocation();
          ResourceSimulationDTO simulation = new PlayerBoardController().simulateAllocation(allocation, playerBoard);
          done = shellView.showAndAskIfDone(simulation);
        }
        return;
      case END_GAME:
        boolean sure = shellView.askYesNoQuestion("Sure?");
        if (sure) {
          endGame = true;
          shellView.sayByeToPlayer();
        }
        return;
      default:
        throw new RuntimeException("Player action unknown!");
    }

    shellView.show(playerBoard);
  }

  private void refreshAvailableActions() {
    availableActions.clear();
    availableActions.addAll(getAvailableActions());
  }

  private void toggleMode() {
    mode = mode.getOtherMode();
    refreshAvailableActions();
  }

  private List<ShellPlayerAction> getAvailableActions() {
    switch (mode) {
      case ACTION:
        return List.of(SPEND_OR_EARN_RESOURCES, INCREASE_OR_DECREASE_RESOURCE_PRODUCTION, PRODUCTION, SET_TERRAFORMING_RATING, SET_MEGA_CREDITS_PER_STEEL, SET_MEGA_CREDITS_PER_TITANIUM, TOGGLE_MODE, END_GAME);
      case SIMULATE_ALLOCATION:
        return List.of(SIMULATE_ALLOCATION, TOGGLE_MODE, END_GAME);
      default:
        throw new IllegalStateException("Change the implementation of this method if you add modes.");
    }
  }

  private void initPlayerBoard() {
    playerBoard.setMegaCreditsAmount(42);
    playerBoard.setMegaCreditsProduction(3);
  }
}
