//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.salvo.Salvo2version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Player {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private long id;
    private String email;
    private String password;
    @OneToMany(
            mappedBy = "player",
            fetch = FetchType.EAGER
    )
    @Fetch(FetchMode.SUBSELECT)
    private List<GamePlayer> gamePlayers = new ArrayList();

    public Player() {
    }

    public Player(String email, String password) {
        this.email = email;
        this.password = password;

    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public List<GamePlayer> getGamePlayers() {
        return this.gamePlayers;
    }

    public void addGamePlayer(GamePlayer gamePlayer) {
        gamePlayer.setPlayer(this);
        this.gamePlayers.add(gamePlayer);
    }
    @JsonIgnore
    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    List<Score> scores = new ArrayList<>();
    @Fetch(value = FetchMode.SUBSELECT)

    @JsonIgnore
    public List<Score> getScores() { return scores; }

    public void setScores(Game game) {
        this.scores = scores;
    }

    //Para obtener la lista de Scores (Wins, Loses, Draws)
    public double getScore(Player player) {
        return getWins(player.getScores())*1 + getTies(player.getScores())*((double)0.5) + getLoses(player.getScores())*0;
    }


    public double getWins(List<Score> scoreList) {
        return scoreList
                .stream()
                .filter(score -> score.getScore() == 1)
                .count();

    }

    public double getLoses(List<Score>scoreList) {
        return scoreList
                .stream()
                .filter(score -> score.getScore() == 0)
                .count();
    }

    public double getTies(List<Score>scoreList) {
        return scoreList
                .stream()
                .filter(score -> score.getScore() == 0.5)
                .count();
    }


}