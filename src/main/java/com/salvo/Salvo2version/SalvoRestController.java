package com.salvo.Salvo2version;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;






@RestController
@RequestMapping("/api")				//raiz de la ruta que solicitar� el front-end
public class SalvoRestController {


    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GamePlayerRepository gamePlayerRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private SalvoRepository salvoRepository;

    @Autowired
    private ShipRepository shipRepository;

    @Autowired
    private ScoreRepository scoreRepository;


    //servicio que puede solicitar el front
    public List<Object> getGames() {
        return gameRepository.findAll()
                .stream()
                .map(game -> getGameDTO(game))
                .collect(Collectors.toList());


    }
    @RequestMapping("/games")
    public Map<String, Object> getLoggedPlayer(Authentication authentication) {
        Map<String, Object> dto = new LinkedHashMap<>();
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Player authenticatedPlayer = getAuthentication(authentication);
        if (authenticatedPlayer == null)
            dto.put("player", "GUEST");
        else
            dto.put("player", loggedPlayerDTO(authenticatedPlayer));
        dto.put("games", getGames()); // Invoca al método que nos devuelve todos los juegos
        return dto;
    }

    private Player getAuthentication(Authentication authentication) {
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return null;
        } else {
            return (playerRepository.findByEmail(authentication.getName()));
        }
    }

    public Map<String, Object> loggedPlayerDTO(Player player) {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", player.getId());
        dto.put("name", player.getEmail());
        return dto;
    }
    /*public List<Object> getAll() {
        return gameRepository.findAll()
                .stream() // Método de String que gestiona el array y nos brinda métodos como .map y .collect
                .map(game -> getGameDTO(game))
                .collect(Collectors.toList());
    }*/

    // Endpoint /api/game_view/nn
    @RequestMapping("/game_view/{gamePlayerId}")
    //segundo servicio que puede solicitar el front, acompa�ado de un parametro pasado por ruta
    public Map<String, Object> getGameView(@PathVariable Long gamePlayerId) {

        GamePlayer gamePlayer = gamePlayerRepository.findOne(gamePlayerId);
        return getGameViewToDTO(gamePlayer.getGame(), gamePlayer);

    }

    // Endpoint /api/ships_view
    @RequestMapping("/ships_view")
    public List<Map<String, Object>> getShips() {
        return shipRepository.findAll()
                .stream()
                .map(ship -> getShipToDTO(ship))
                .collect(Collectors.toList());
    }
    // Endpoint /api/leaderBoard

    @RequestMapping("/leaderBoard")
    public List<Map<String, Object>> makeLeaderBoard() {
        return playerRepository
                .findAll()
                .stream()
                .map(player -> playerLeaderBoardDTO(player))
                .collect(Collectors.toList());
    }

    @RequestMapping(path = "/players", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestParam String username, @RequestParam String password) {
        if (username.isEmpty()) {
            return new ResponseEntity<>("No username given", HttpStatus.FORBIDDEN);
        }
        Player newPlayer = playerRepository.findByEmail(username);
        if (newPlayer != null) {
            return new ResponseEntity<>("Username already exists", HttpStatus.FORBIDDEN);
        }

        playerRepository.save(new Player(username, password));
        return new ResponseEntity<>("Player added", HttpStatus.CREATED);
    }


    /*@RequestMapping(path = "/games", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createGame(Authentication authentication) {
        if (authentication == null) {
            return new ResponseEntity<>(MakeMap("error", "Unauthorized"), HttpStatus.UNAUTHORIZED);
        } else {
            Game game = gameRepository.save(new Game());
            GamePlayer gamePlayer = gamePlayerRepository.save(new GamePlayer(game, getAuthentication(authentication)));
            return new ResponseEntity<>(MakeMap("gpId", gamePlayer.getId()), HttpStatus.CREATED);
        }
    }*/

   /* @RequestMapping("/game_view/{gamePlayerId}")
    public ResponseEntity<Map<String,Object>> getGameView(Authentication authentication, @PathVariable Long gamePlayerId) {
        Map<String, Object> dto = new LinkedHashMap<>();
        Player loggedPlayer = getAuthentication(authentication);
        if (loggedPlayer == null) {
            return new ResponseEntity<>(MakeMap("error","no player logged in"), HttpStatus.FORBIDDEN);
        }
        if(gamePlayerId > numberGamePlayers()){
            return new ResponseEntity<>(MakeMap("error","no such GamePlayer"), HttpStatus.FORBIDDEN);
        }
        GamePlayer gamePlayer = gamePlayerRepository.getOne(gamePlayerId);
        if(gamePlayer.getPlayer().getId() != loggedPlayer.getId()){
            return new ResponseEntity<>(MakeMap("error","Unauthorized"), HttpStatus.UNAUTHORIZED);
        }
        dto = getGameViewToDTO(gamePlayer);
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    public int numberGamePlayers(){
        int i = 0;
        for (GamePlayer gamePlayer: gamePlayerRepository.findAll()) {
            i++;
        }
        return i;
    }*/
    private Map<String, Object> getScoreList (Player player){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("total", player.getScore(player));
        dto.put("won", player.getWins(player.getScores()));
        dto.put("tied", player.getTies(player.getScores()));
        dto.put("lost", player.getLoses(player.getScores()));

        return dto;
    }

    private Map<String, Object> playerLeaderBoardDTO(Player player) {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", player.getId());
        dto.put("email", player.getEmail());
        dto.put("score", getScoreList(player));
        return dto;
    }
    private List<Map<String, Object> > getAllScore(List<Score> scoreList) {
        return scoreList.stream()
                .map(score -> getScoreDTO(score))
                .collect(Collectors.toList());
    }


    private Map<String, Object> getGameDTO(Game game) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", game.getId());
        dto.put("creationDate", game.getCreationDate());
        dto.put("gamePlayer", getGamePlayersLista(game.getGamePlayers()));
        dto.put("scores", getAllScore(game.getScores()));

        return dto;
    }

    private Map<String, Object> gamePlayersDTO(GamePlayer gamePlayer) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", gamePlayer.getId());
        dto.put("player", player2DTO(gamePlayer.getPlayer()));

        return dto;
    }

    private Map<String, Object> player2DTO(Player player) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", player.getId());
        dto.put("userName", player.getEmail());

        return dto;
    }

    private List<Object> getGamePlayersLista(List<GamePlayer> gamePlayers) {
        return gamePlayers
                .stream()
                .map(gamePlayer -> gamePlayersDTO(gamePlayer))
                .collect(Collectors.toList());
    }


    private Map<String, Object> getGameViewToDTO(Game game, GamePlayer gamePlayer) {
        Map<String, Object> dtoGameView = new LinkedHashMap<String, Object>();
        List<Ship> ships = gamePlayer.getShips();
        dtoGameView.put("id", game.getId());
        dtoGameView.put("creationDate", game.getCreationDate());
        dtoGameView.put("gamePlayers", getGamePlayersToListDTO(game.getGamePlayers()));
        dtoGameView.put("ships", getLocationShips(ships));
        dtoGameView.put("salvoes", getSalvosFromAllGamePlayers(game));


        return dtoGameView;


    }

    private List<Map<String, Object>> getGamePlayersToListDTO(List<GamePlayer> gamePlayers) {

        return gamePlayers.stream()
                .map(gamePlayer -> getGamePlayerToDTO(gamePlayer))
                .collect(Collectors.toList());
    }

    private Map<String, Object> getGamePlayerToDTO(GamePlayer gamePlayer) {

        Map<String, Object> dtoGamePlayer = new LinkedHashMap<String, Object>();

        dtoGamePlayer.put("idGamePlayer", gamePlayer.getId());
        dtoGamePlayer.put("player", playerToDTO(gamePlayer.getPlayer()));
        dtoGamePlayer.put("joinDate", gamePlayer.getJoinDate());
        return dtoGamePlayer;
    }

    private Map<String, Object> playerToDTO(Player player) {

        Map<String, Object> dtoPlayer = new LinkedHashMap<String, Object>();
        dtoPlayer.put("idPlayer", player.getId());
        dtoPlayer.put("email", player.getEmail());
        return dtoPlayer;
    }

    private List<Map<String, Object>> getSalvosFromAllGamePlayers(Game game) {
        List<Map<String, Object>> finalList = new ArrayList<>();
        game.getGamePlayers().forEach(gamePlayer -> finalList.addAll(SalvoLocationsList(gamePlayer.getSalvoes())));
        return finalList;
    }

    private List<Map<String, Object>> SalvoLocationsList(List<Salvo> salvoList) {

        return salvoList.stream()
                .map(salvo -> SalvoToDTO(salvo))
                .collect(Collectors.toList());
    }

    private Map<String, Object> SalvoToDTO(Salvo salvo) {

        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("turn", salvo.getTurn());
        dto.put("player", salvo.getGamePlayer().getPlayer().getId());
        dto.put("locations", salvo.getSalvoLocations());

        return dto;
    }

    private List<Map<String, Object>> getLocationShips(List<Ship> ships) {
        return ships.stream()
                .map(ship -> getShipToDTO(ship))
                .collect(Collectors.toList());
    }

    private Map<String, Object> getShipToDTO(Ship ship) {

        Map<String, Object> dtoShip = new LinkedHashMap<String, Object>();
        dtoShip.put("shipType", ship.getShipType());
        dtoShip.put("locations", ship.getShipsLocation());
        dtoShip.put("player", ship.getGamePlayer().getPlayer().getId());
        return dtoShip;

    }

    private List<Map<String, Object>> getShipLocation(List<Ship> ships) {
        return ships.stream()
                .map(ship -> getShipToDTO(ship))
                .collect(Collectors.toList());
    }

    private Map<String, Object> getScoreDTO(Score score) {

        Map<String, Object> dtoScore = new LinkedHashMap<String, Object>();
        dtoScore.put("playerId", score.getPlayer().getId());
        dtoScore.put("finishDate", score.getFinishDate());
        dtoScore.put("score", score.getScore());
        return dtoScore;




    }

}


