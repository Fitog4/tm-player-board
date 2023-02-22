package org.fitog4.view;

import org.fitog4.controller.CardDTO;
import org.fitog4.controller.ResourceChangeDTO;
import org.fitog4.controller.ResourceSimulationDTO;
import org.fitog4.model.Mode;
import org.fitog4.model.PlayerBoard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ShellView {
  public void greetPlayer() {
    System.out.println("Hello TM-player, welcome to your player board app!\n");
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
    System.out.format("+------------+-------------+-------------+-------------+-------------+-------------+-------------+%n");
  }

  public boolean showAndAskIfDone(ResourceSimulationDTO simulation) {
    System.out.println("Allocation result:\n" +
        "\tM\u20ac: " + simulation.getMegaCreditsAmount() + (simulation.getMegaCreditsAmount() < 0 ? " <<< NOT AN OPTION!\n" : "\n") +
        "\tSteel: " + simulation.getSteelAmount() + "\n" +
        "\tTitanium: " + simulation.getTitaniumAmount());
    return !askYesNoQuestion("Would you like to re-allocate?");
  }

  public ShellPlayerAction askPlayerAction(List<ShellPlayerAction> availableActions, Mode mode) {
    System.out.println(mapToPlayerPrompt(availableActions, mode));
    return mapToPlayerAction(availableActions, readLine());
  }

  public ResourceChangeDTO askResourceChange() {
    boolean confirmed = false;
    ResourceChangeDTO resourceChangeDTO = null;
    while (!confirmed) {
      System.out.println("Insert the desired change as a string of text using resource initials M, S, T, P, E and H (e.g. \"-3T +1P +2E\") and hit enter.");
      resourceChangeDTO = new ShellRegexHelper().parseAsResourceChange(readLine());
      System.out.println(resourceChangeDTO.toString());
      confirmed = askYesNoQuestion("Correct?");
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

  public boolean askYesNoQuestion(String question) {
    System.out.println(question + " (hit n/N for no, any key for yes)");
    return !readLine().equalsIgnoreCase("N");
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

  private String mapToPlayerPrompt(List<ShellPlayerAction> availableActions, Mode mode) {
    return "\nChoose your action (type the number and press enter):\n"
        + IntStream.range(1, availableActions.size() + 1)
        .mapToObj(i -> String.format("\t" + i + ") " + availableActions.get(i - 1).getLabel(), mode.getOtherMode().getLabel()))
        .collect(Collectors.joining("\n"));
  }

  public List<CardDTO> askCardAllocation() {
    System.out.println("Insert the card allocation as a string of text using numbers for card cost followed by N for new cards to purchase, S for steel cards and T for titanium cards (e.g. \"8 7N 3S 4TN\") and hit enter.");
    return new ShellRegexHelper().parseAsAllocation(readLine());
  }
}
