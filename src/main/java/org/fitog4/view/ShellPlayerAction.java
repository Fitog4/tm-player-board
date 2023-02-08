package org.fitog4.view;

public enum ShellPlayerAction {
  VIEW_PLAYER_BOARD("View player board"),
  SPEND_OR_EARN_RESOURCES("Spend or earn resources"),
  INCREASE_OR_DECREASE_RESOURCE_PRODUCTION("Increase or decrease resource production"),
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
