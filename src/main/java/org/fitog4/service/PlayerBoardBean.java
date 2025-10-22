package org.fitog4.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.fitog4.model.PlayerBoard;

import java.io.Serializable;
import java.util.concurrent.CopyOnWriteArrayList;

import static jakarta.transaction.Transactional.TxType.REQUIRES_NEW;

@ApplicationScoped
@Transactional(REQUIRES_NEW)
public class PlayerBoardBean implements Serializable {

    @PersistenceContext(unitName = "defaultPU")
    private EntityManager em;

    private static final long serialVersionUID = 1L;

    private CopyOnWriteArrayList<PlayerBoard> playerBoards = new CopyOnWriteArrayList<>();

    public PlayerBoard addNewPlayerBoard() {
        PlayerBoard playerBoard = new PlayerBoard();
        playerBoards.add(playerBoard);
        em.persist(playerBoard);
        return playerBoard;
    }

    public PlayerBoard getPlayerBoard(int id) {
        return playerBoards.get(id);
    }
}
