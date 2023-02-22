package org.fitog4.view;

import org.fitog4.controller.ResourceChangeDTO;
import org.fitog4.model.PlayerBoard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ShellView {
  public void greetPlayer() {
    System.out.println("Hello TM-player, welcome to your player board app!");
  }

  public void show(PlayerBoard playerBoard) {
    String format = "| %-10s | %-11s | %-11d | %-11d | %-11d | %-11d | %-11d |%n";

    System.out.format("+------------+-------------+-------------+-------------+-------------+-------------+-------------+%n");
    System.out.format("|            | MegaCredits | Steel       | Titanium    | Plants      | Energy      | Heat        |%n");
    System.out.format("|            | (M\u20ac)        | %-11s | %-11s |             |             |             |%n",
        String.format("(=%d M\u20ac)", playerBoard.getMegaCreditsPerUnitOfSteel()),
        String.format("(=%d M\u20ac)", playerBoard.getMegaCreditsPerUnitOfTitanium()));
    System.out.format("+------------+-------------+-------------+-------------+-------------+-------------+-------------+%n");
    System.out.format(format, "Amount",
        playerBoard.getMegaCreditsAmount(),
        playerBoard.getSteelAmount(),
        playerBoard.getTitaniumAmount(),
        playerBoard.getPlantsAmount(),
        playerBoard.getEnergyAmount(),
        playerBoard.getHeatAmount());
    System.out.format(format, "Production",
        playerBoard.getTerraformRating() + "TR" + String.format(" + %d", playerBoard.getMegaCreditsProduction()),
        playerBoard.getSteelProduction(),
        playerBoard.getTitaniumProduction(),
        playerBoard.getPlantsProduction(),
        playerBoard.getEnergyProduction(),
        playerBoard.getHeatProduction());
    System.out.format("+------------+-------------+-------------+-------------+-------------+-------------+-------------+%n%n");
  }

  public ShellPlayerAction askPlayerAction(List<ShellPlayerAction> availableActions) {
    System.out.println(mapToPlayerPrompt(availableActions));
    return mapToPlayerAction(availableActions, readLine());
  }

  public ResourceChangeDTO askResourceChange() {
    boolean confirmed = false;
    ResourceChangeDTO resourceChangeDTO = null;
    while (!confirmed) {
      System.out.println("Insert the desired change as a string of text using resource initials M, S, T, P, E and H (e.g. \"-3T +1P +2E\") and hit enter.");
      resourceChangeDTO = new ShellResourceChangeRegexHelper().parseAsResourceChange(readLine());
      System.out.println(resourceChangeDTO.toString());
      System.out.println("Correct? (hit n/N for no, any key to continue)");
      if (!readLine().equalsIgnoreCase("N")) {
        confirmed = true;
      }
    }
    return resourceChangeDTO;
  }

  public int askNewValue(String label) {
    System.out.println("Insert " + label + ":");
    try {
      return Integer.parseInt(readLine());
    } catch (NumberFormatException ignored) {
      System.out.println("Please insert a number.");
      return askNewValue(label);
    }
  }

  public void sayByeToPlayer() {
    System.out.println("Bye-bye and till next time!");
  }

  private String readLine() {
    try {
      return new BufferedReader(new InputStreamReader(System.in)).readLine().strip();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private ShellPlayerAction mapToPlayerAction(List<ShellPlayerAction> availableActions, String playerInput) {
    try {
      return availableActions.get(Integer.parseInt(playerInput) - 1);
    } catch (NumberFormatException | IndexOutOfBoundsException unused) {
      System.out.println("Please input a number corresponding to an action!");
      return null;
    }
  }

  private String mapToPlayerPrompt(List<ShellPlayerAction> availableActions) {
    return "\nChoose your action (type the number and press enter):\n"
        + IntStream.range(1, availableActions.size() + 1)
        .mapToObj(i -> "\t" + i + ") " + availableActions.get(i - 1).getLabel())
        .collect(Collectors.joining("\n"));
  }
}
