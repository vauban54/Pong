package fr.arnaud.pong;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.event.ActionEvent;


import java.net.URL;
import java.util.ResourceBundle;

public class PongController implements Initializable {

    @FXML
    private Pane board;

    @FXML
    private Label score;

    @FXML
    private Rectangle player, computer;

    @FXML
    private Circle ball;

    private double playerVely = 0;

    private AnimationTimer timer;

    // Constructeur du controller
    public PongController() {
        timer = new AnimationTimer() {
            // La méthode handle() représente la boucle de jeu
            @Override
            public void handle(long l)  {
                // On récupère les entrées de notre joueur (méthode static)
                PongUtils.handlePlayer(player, playerVely);
                // On met à jour les déplacements des objets (méthode static)
                PongUtils.updateGame(player, computer, ball);
                // On check si la partie est terminée
                checkEndGame();
            }
        };

    }

    @FXML
    public void reset(ActionEvent event) {
        timer.stop();
        // Et on remet les éléments à leur état initial
        ball.setCenterX(450);
        ball.setCenterY(350);
        player.setY(200);
        computer.setY(ball.getCenterY());
        PongUtils.resetGame();
    }

    @FXML
    public void run(ActionEvent event) {
        board.requestFocus();
        // On démarre le timer
        timer.start();
        computer.setY(ball.getCenterY());
    }
    @FXML
    public void onKeyPressed(KeyEvent keyEvent) {

        if (ball.getCenterX() > 15) {
            switch (keyEvent.getCode()) {
                case UP:
                    System.out.println("pressed UP");
                    if (player.getY() >= 0) {
                        playerVely = -10;
                        player.setY(player.getY() + playerVely);

                    }
                    break;
                case DOWN:
                    System.out.println("pressed DOWN");
                    if (player.getY() <= 600) {
                        playerVely = 10;
                        player.setY(player.getY() + playerVely);

                    }
                    break;
                default:
                    break;
            }
        }
    }

    @FXML
    public void onKeyReleased(KeyEvent keyEvent) {
        // On passe la vélocité à 0 quand on lache les touches
        playerVely = 0;
    }

    public void checkEndGame() {
        if (ball.getCenterX() < 15) {
            // La partie est terminée
            timer.stop();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        score.textProperty().bind(Bindings.convert(PongUtils.scoreProperty()));
    }


}