package com.salvo.Salvo2version;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(strategy = "native", name="native")
    private long id;

    private String shipType;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="gamePlayer_id")
    private GamePlayer gamePlayer;

    @ElementCollection
    @Column(name="shipLocation")
    private List<String> shipsLocation = new ArrayList<>();

    public Ship() {
    }

    public Ship(String shipType, List<String> shipsLocation, GamePlayer gamePlayer) {
        this.shipType = shipType;
        this.shipsLocation = shipsLocation;
        this.gamePlayer = gamePlayer;
    }

    public long getId() {
        return id;
    }

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType ) {
        this.shipType = shipType;
    }

    public List<String> getShipsLocation() {
        return shipsLocation;
    }

    public void setShipsLocation(List<String> shipsLocation) {
        this.shipsLocation = shipsLocation;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;

    }

    public GamePlayer getShipPlayer(){
        return this.getGamePlayer();
    }
}