package org.fitog4.view;

public enum ShellPlayerAction {
  VIEW_PLAYER_BOARD("Player board"),
  SPEND_OR_EARN_RESOURCES("Change resource amounts"),
  INCREASE_OR_DECREASE_RESOURCE_PRODUCTION("Change resource production"),
  PRODUCTION("Production"),
  SET_TERRAFORMING_RATING("Set Terraforming Rating"),
  SET_MEGA_CREDITS_PER_STEEL("Set M\u20ac per unit of Steel"),
  SET_MEGA_CREDITS_PER_TITANIUM("Set M\u20ac per unit of Titanium"),
  END_GAME("End the game")
  ;

  private final String label;

  ShellPlayerAction(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
