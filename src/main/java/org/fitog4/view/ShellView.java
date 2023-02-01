package org.fitog4.view;

import org.fitog4.model.PlayerBoard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ShellView {
  public ShellPlayerAction getPlayerAction(List<ShellPlayerAction> availableActions) {
    String playerPrompt = mapToPlayerPrompt(availableActions);
    System.out.println(playerPrompt);
    try {
      return mapToPlayerAction(availableActions, new BufferedReader(new InputStreamReader(System.in)).readLine());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private ShellPlayerAction mapToPlayerAction(List<ShellPlayerAction> availableActions, String playerInput) {
    // todo validation
    return availableActions.get(Integer.parseInt(playerInput.strip()) - 1);
  }


  private String mapToPlayerPrompt(List<ShellPlayerAction> availableActions) {
    return "Choose your action (type the number and press enter):\n"
        + IntStream.range(1, availableActions.size() + 1).mapToObj(i -> i + ") " + availableActions.get(i - 1).getLabel()).collect(Collectors.joining("\n"));
  }

  public void greetPlayer() {
    System.out.println("Hello TM-player, welcome to your player board app!");
  }

  public void sayByeToPlayer() {
    System.out.println("Bye-bye and till next time!");
  }

  public void show(PlayerBoard playerBoard) {
    String format = "| %-10s | %-11d | %-11d | %-11d | %-11d | %-11d | %-11d |%n";

    System.out.format("+------------+-------------+-------------+-------------+-------------+-------------+-------------+%n");
    System.out.format("|            | MegaCredits | Steel       | Titanium    | Plants      | Energy      | Heat        |%n");
    System.out.format("+------------+-------------+-------------+-------------+-------------+-------------+-------------+%n");
    System.out.format(format, "Amount",
        playerBoard.getMegaCredits().getAmount(),
        playerBoard.getSteel().getAmount(),
        playerBoard.getTitanium().getAmount(),
        playerBoard.getPlants().getAmount(),
        playerBoard.getEnergy().getAmount(),
        playerBoard.getHeat().getAmount());
    System.out.format(format, "Production",
        playerBoard.getMegaCredits().getProduction(),
        playerBoard.getSteel().getProduction(),
        playerBoard.getTitanium().getProduction(),
        playerBoard.getPlants().getProduction(),
        playerBoard.getEnergy().getProduction(),
        playerBoard.getHeat().getProduction());
    System.out.format("+------------+-------------+-------------+-------------+-------------+-------------+-------------+%n%n");
  }
}
