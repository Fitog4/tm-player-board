package org.fitog4.model;

public enum Mode {
  ACTION("Action"),
  SIMULATE_ALLOCATION("Simulate Allocation");

  private final String label;

  Mode(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  public Mode getOtherMode() {
    switch(this) {
      case ACTION:
        return SIMULATE_ALLOCATION;
      case SIMULATE_ALLOCATION:
        return ACTION;
      default:
        throw new IllegalStateException("Change the implementation of this method if you add modes.");
    }
  }
}
