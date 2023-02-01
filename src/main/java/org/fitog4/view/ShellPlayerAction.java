package org.fitog4.view;

public enum ShellPlayerAction {
  VIEW_PLAYER_BOARD("View player board"),
  END_GAME("End the game"),
  DO_SOMETHING_ELSE("Other action") // todo
  ;

  private final String label;

  ShellPlayerAction(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
