//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.salvo.Salvo2version;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@SpringBootApplication
public class Salvo2versionApplication {
    public Salvo2versionApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(Salvo2versionApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(PlayerRepository playerRepository, GameRepository gameRepository, GamePlayerRepository gamePlayerRepository, ShipRepository shipRepository, SalvoRepository salvoRepository, ScoreRepository scoreRepository) {
        return (args) -> {
            Player player1 = new Player("player1@dominio.com", "123");
            Player player2 = new Player("player2@dominio.com", "456");
            Player player3 = new Player("player3@dominio.com", "456");
            Player player4 = new Player("player4@dominio.com", "789");


            playerRepository.save(player1);
            playerRepository.save(player2);
            playerRepository.save(player3);
            playerRepository.save(player4);

            Date creationDate1 = new Date();

            Game game1 = new Game();
            game1.setCreationDate(creationDate1);
            creationDate1 = Date.from(creationDate1.toInstant().plusSeconds(3600L));

            Game game2 = new Game();
            game2.setCreationDate(creationDate1);
            creationDate1 = Date.from(creationDate1.toInstant().plusSeconds(3600L));

            Game game3 = new Game();
            creationDate1 = Date.from(creationDate1.toInstant().plusSeconds(3600L));
            game3.setCreationDate(creationDate1);
            gameRepository.save(game1);
            gameRepository.save(game2);
            gameRepository.save(game3);


            GamePlayer gPlayer1 = new GamePlayer(game1, player1);
            GamePlayer gPlayer2 = new GamePlayer(game1, player2);
            GamePlayer gPlayer3 = new GamePlayer(game2, player3);
            GamePlayer gPlayer4 = new GamePlayer(game2, player4);
            gamePlayerRepository.save(gPlayer1);
            gamePlayerRepository.save(gPlayer2);
            gamePlayerRepository.save(gPlayer3);
            gamePlayerRepository.save(gPlayer4);

            List<String> ubicacion1 = new ArrayList<>(); // creamos una futura ubicacion de un barco
            ubicacion1.add("F4");
            ubicacion1.add("F5");
            ubicacion1.add("F6");
            ubicacion1.add("F7");
            ubicacion1.add("F8");
            ubicacion1.add("F9");

            List<String> ubicacion2 = new ArrayList<>();
            ubicacion2.add("A1");
            ubicacion2.add("B1");
            ubicacion2.add("C1");

            List<String> ubicacion3 = new ArrayList<>();
            ubicacion3.add("G1");
            ubicacion3.add("G2");

            List<String> ubicacion4 = new ArrayList<>();
            ubicacion4.add("H5");
            ubicacion4.add("H6");
            ubicacion4.add("H7");

            List<String> ubicacion5 = new ArrayList<>();
            ubicacion5.add("G1");
            ubicacion5.add("H1");
            ubicacion5.add("I1");

            List<String> ubicacion6 = new ArrayList<>();
            ubicacion6.add("F1");
            ubicacion6.add("F2");

            List<String> ubicacion7 = new ArrayList<>();
            ubicacion7.add("B1");
            ubicacion7.add("B2");

            List<String> ubicacion8 = new ArrayList<>();
            ubicacion8.add("F1");
            ubicacion8.add("F2");
            ubicacion8.add("F3");
            ubicacion8.add("F4");
            ubicacion8.add("F5");

            List<String> ubicacion9 = new ArrayList<>();
            ubicacion9.add("B1");
            ubicacion9.add("B2");

            List<String> ubicacion10 = new ArrayList<>();
            ubicacion10.add("C1");
            ubicacion10.add("C2");
            ubicacion10.add("C3");
            ubicacion10.add("C4");
            ubicacion10.add("C5");

            List<String> ubicacion11 = new ArrayList<>();
            ubicacion11.add("G1");
            ubicacion11.add("G2");

            List<String> ubicacion12 = new ArrayList<>();
            ubicacion12.add("E1");
            ubicacion12.add("E2");
            ubicacion12.add("E3");


            String shipType1 = "Submarine";
            String shipType2 = "Barco";
            String shipType3 = "Barquito";
            String shipType4 = "Fragata";
            String shipType5 = "Bote";
            String shipType6 = "Nave";
            String shipType7 = "T-rex";
            String shipType8 = "Monster";
            String shipType9 = "Shark";
            String shipType10 = "Anguila";
            String shipType11 = "Omega";
            String shipType12 = "Alfa";


            Ship ship1 = new Ship();// instanciamos un barco
            ship1.setShipType(shipType1);
            ship1.setShipsLocation(ubicacion1);//setea la Shiplocation con los valores que llenamos arriba

            gPlayer1.addShip(ship1);


            Ship ship2 = new Ship();
            ship2.setShipType(shipType2);
            ship2.setShipsLocation(ubicacion2);

            gPlayer1.addShip(ship2);

            Ship ship3 = new Ship();
            ship3.setShipType(shipType3);
            ship3.setShipsLocation(ubicacion3);

            gPlayer1.addShip(ship3);

            Ship ship4 = new Ship();
            ship4.setShipType(shipType4);
            ship4.setShipsLocation(ubicacion4);

            gPlayer2.addShip(ship4);


            Ship ship5 = new Ship();
            ship5.setShipType(shipType5);
            ship5.setShipsLocation(ubicacion5);

            gPlayer2.addShip(ship5);

            Ship ship6 = new Ship();
            ship6.setShipType(shipType6);
            ship6.setShipsLocation(ubicacion6);

            gPlayer2.addShip(ship6);

            Ship ship7 = new Ship();
            ship7.setShipType(shipType7);
            ship7.setShipsLocation(ubicacion7);

            gPlayer3.addShip(ship7);

            Ship ship8 = new Ship();
            ship8.setShipType(shipType8);
            ship8.setShipsLocation(ubicacion8);

            gPlayer3.addShip(ship8);

            Ship ship9 = new Ship();
            ship9.setShipType(shipType9);
            ship9.setShipsLocation(ubicacion9);

            gPlayer3.addShip(ship8);

            Ship ship10 = new Ship();
            ship10.setShipType(shipType10);
            ship10.setShipsLocation(ubicacion10);

            gPlayer4.addShip(ship10);

            Ship ship11 = new Ship();
            ship11.setShipType(shipType11);
            ship11.setShipsLocation(ubicacion11);

            gPlayer4.addShip(ship11);

            Ship ship12 = new Ship();
            ship12.setShipType(shipType12);
            ship12.setShipsLocation(ubicacion12);

            gPlayer4.addShip(ship12);


            ship1.setGamePlayer(gPlayer1);
            ship2.setGamePlayer(gPlayer1);
            ship3.setGamePlayer(gPlayer1);
            ship4.setGamePlayer(gPlayer2);
            ship5.setGamePlayer(gPlayer2);
            ship6.setGamePlayer(gPlayer2);
            ship7.setGamePlayer(gPlayer3);
            ship8.setGamePlayer(gPlayer3);
            ship9.setGamePlayer(gPlayer3);
            ship10.setGamePlayer(gPlayer4);
            ship11.setGamePlayer(gPlayer4);
            ship12.setGamePlayer(gPlayer4);


            shipRepository.save(ship1);
            shipRepository.save(ship2);
            shipRepository.save(ship3);
            shipRepository.save(ship4);
            shipRepository.save(ship5);
            shipRepository.save(ship6);
            shipRepository.save(ship7);
            shipRepository.save(ship8);
            shipRepository.save(ship9);
            shipRepository.save(ship10);
            shipRepository.save(ship11);
            shipRepository.save(ship12);


            List<String> salvoLocation1 = new ArrayList<>();
            salvoLocation1.add("A4");
            salvoLocation1.add("A5");
            salvoLocation1.add("A6");

            List<String> salvoLocation2 = new ArrayList<>();
            salvoLocation2.add("C1");
            salvoLocation2.add("C2");
            salvoLocation2.add("D3");
            salvoLocation2.add("E4");

            List<String> salvoLocation3 = new ArrayList<>();
            salvoLocation3.add("H6");
            salvoLocation3.add("H7");

            List<String> salvoLocation4 = new ArrayList<>();
            salvoLocation4.add("J6");
            salvoLocation4.add("J7");
            salvoLocation4.add("J8");

            List<String> salvoLocation5 = new ArrayList<>();
            salvoLocation4.add("B6");
            salvoLocation4.add("B7");

            List<String> salvoLocation6 = new ArrayList<>();
            salvoLocation4.add("F6");
            salvoLocation4.add("F7");

            List<String> salvoLocation7 = new ArrayList<>();
            salvoLocation4.add("J6");
            salvoLocation4.add("J7");
            salvoLocation4.add("J8");

            List<String> salvoLocation8 = new ArrayList<>();
            salvoLocation4.add("G6");
            salvoLocation4.add("G7");


            Salvo salvo1 = new Salvo();
            salvo1.setTurn(1);
            salvo1.setSalvoLocations(salvoLocation1);
            gPlayer1.addSalvo(salvo1);

            Salvo salvo2 = new Salvo();
            salvo2.setTurn(2);
            salvo2.setSalvoLocations(salvoLocation2);
            gPlayer2.addSalvo(salvo2);

            Salvo salvo3 = new Salvo();
            salvo3.setTurn(3);
            salvo3.setSalvoLocations(salvoLocation3);
            gPlayer1.addSalvo(salvo3);

            Salvo salvo4 = new Salvo();
            salvo4.setTurn(4);
            salvo4.setSalvoLocations(salvoLocation4);
            gPlayer2.addSalvo(salvo4);

            Salvo salvo5 = new Salvo();
            salvo5.setTurn(5);
            salvo5.setSalvoLocations(salvoLocation5);
            gPlayer1.addSalvo(salvo4);

            Salvo salvo6 = new Salvo();
            salvo6.setTurn(6);
            salvo6.setSalvoLocations(salvoLocation6);
            gPlayer2.addSalvo(salvo6);

            Salvo salvo7 = new Salvo();
            salvo7.setTurn(7);
            salvo7.setSalvoLocations(salvoLocation7);
            gPlayer1.addSalvo(salvo7);

            Salvo salvo8 = new Salvo();
            salvo8.setTurn(8);
            salvo8.setSalvoLocations(salvoLocation8);
            gPlayer2.addSalvo(salvo8);


            salvoRepository.save(salvo1);
            salvoRepository.save(salvo2);
            salvoRepository.save(salvo3);
            salvoRepository.save(salvo4);
            salvoRepository.save(salvo5);
            salvoRepository.save(salvo6);
            salvoRepository.save(salvo7);
            salvoRepository.save(salvo8);

            Score score1 = new Score();
            score1.setGame(game1);
            score1.setPlayer(player1);
            score1.setScore(1.0);
            score1.setFinishDate(new Date());

            Score score2 = new Score();
            score2.setGame(game1);
            score2.setPlayer(player2);
            score2.setScore(1);
            score2.setFinishDate(new Date());

            Score score3 = new Score();
            score3.setGame(game2);
            score3.setPlayer(player3);
            score3.setScore(0.5);
            score3.setFinishDate(new Date());

            Score score4 = new Score();
            score4.setGame(game2);
            score4.setPlayer(player4);
            score4.setScore(0.5);
            score4.setFinishDate(new Date());

            Score score5 = new Score();
            score5.setGame(game3);
            score5.setPlayer(player1);
            score5.setScore(0.5);
            score5.setFinishDate(new Date());

            Score score6 = new Score();
            score6.setGame(game3);
            score6.setPlayer(player2);
            score6.setScore(0.5);
            score6.setFinishDate(new Date());

            Score score7 = new Score();
            score7.setGame(game2);
            score7.setPlayer(player3);
            score7.setScore(0.5);
            score7.setFinishDate(new Date());

            Score score8 = new Score();
            score8.setGame(game2);
            score8.setPlayer(player4);
            score8.setScore(0.5);
            score8.setFinishDate(new Date());


            scoreRepository.save(score1);
            scoreRepository.save(score2);
            scoreRepository.save(score3);
            scoreRepository.save(score4);
            scoreRepository.save(score5);
            scoreRepository.save(score6);
            scoreRepository.save(score7);
            scoreRepository.save(score8);


        };

    }

}
@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter{

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inputName-> {
            Player player = playerRepository.findByEmail(inputName);
            if (player != null) {
                return new User(player.getEmail(), player.getPassword(),
                        AuthorityUtils.createAuthorityList("user"));
            } else {
                throw new UsernameNotFoundException("Unknown user: " + inputName);
            }
        });
    }
}//end WebSecurityConfiguration

@EnableWebSecurity
@Configuration
class WebSecurityConfiguratio extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/web/games.html").permitAll()
                .antMatchers("/web/css/games.css").permitAll()
                .antMatchers("/api/games").permitAll()
                .antMatchers("/api/players/**").permitAll()
                .antMatchers("/api/game_view/*").hasAuthority("user")
                .antMatchers("/rest/*").denyAll()
                .anyRequest().permitAll();
        http.formLogin()
                .usernameParameter("name")
                .passwordParameter("pwd")
                .loginPage("/api/login");
        http.logout().logoutUrl("/api/logout");
        // turn off checking for CSRF tokens
        http.csrf().disable();
        // if user is not authenticated, just send an authentication failure response
        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));
        // if login is successful, just clear the flags asking for authentication
        http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));
        // if login fails, just send an authentication failure response
        http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));
        // if logout is successful, just send a success response
        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
}

