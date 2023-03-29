package org.fitog4.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.fitog4.model.PlayerBoard;

import java.io.Serializable;
import java.util.concurrent.CopyOnWriteArrayList;

// todo filippo: I am not sure why, but I need a bean.xml for this bean
@ApplicationScoped
public class PlayerBoardBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private CopyOnWriteArrayList<PlayerBoard> playerBoards = new CopyOnWriteArrayList<>();

  public PlayerBoard addNewPlayerBoard() {
    PlayerBoard playerBoard = new PlayerBoard();
    // todo fili improve ID generation mechanism - this is also not thread-safe
    playerBoard.setPlayerBoardId(playerBoards.size());
    playerBoards.add(playerBoard);
    return playerBoard;
  }

  public PlayerBoard getPlayerBoard(int id) {
    return playerBoards.get(id);
  }
}
