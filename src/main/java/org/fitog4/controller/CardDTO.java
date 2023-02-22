package org.fitog4.controller;

public class CardDTO {

  private final int cost;
  private final boolean isNewCard;
  private final boolean isSteelCard;
  private final boolean isTitaniumCard;

  public CardDTO(int cost, boolean isNewCard, boolean isSteelCard, boolean isTitaniumCard) {
    this.cost = cost;
    this.isNewCard = isNewCard;
    this.isSteelCard = isSteelCard;
    this.isTitaniumCard = isTitaniumCard;
  }

  int getCost() {
    return cost;
  }

  boolean isNewCard() {
    return isNewCard;
  }

  boolean isSteelCard() {
    return isSteelCard;
  }

  boolean isTitaniumCard() {
    return isTitaniumCard;
  }

  @Override
  public String toString() {
    return "CardDTO{" +
        "cost=" + cost +
        ", isNewCard=" + isNewCard +
        ", isSteelCard=" + isSteelCard +
        ", isTitaniumCard=" + isTitaniumCard +
        "}\n";
  }
}
