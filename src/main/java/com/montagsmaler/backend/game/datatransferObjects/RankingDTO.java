package com.montagsmaler.backend.game.datatransferObjects;

public class RankingDTO {
    int rank;
    GameUserDTO gameUserDTO;
    int score;

    public RankingDTO(int rank, GameUserDTO gameUserDTO, int score) {
        this.rank = rank;
        this.gameUserDTO = gameUserDTO;
        this.score = score;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public GameUserDTO getGameUserDTO() {
        return gameUserDTO;
    }

    public void setGameUserDTO(GameUserDTO gameUserDTO) {
        this.gameUserDTO = gameUserDTO;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
