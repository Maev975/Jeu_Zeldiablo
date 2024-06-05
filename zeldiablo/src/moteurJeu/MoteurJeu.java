package moteurJeu;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MoteurJeu extends Application {

    private static double FPS = 100;
    private static double dureeFPS = 1000 / (FPS + 1);
    private static double WIDTH = 800;
    private static double HEIGHT = 600;
    private final FrameStats frameStats = new FrameStats();
    private static Jeu jeu = null;
    private static DessinJeu dessin = null;
    Clavier controle = new Clavier();
    private AnimationTimer timer; // Ajout d'une référence à AnimationTimer

    public static void launch(Jeu jeu, DessinJeu dessin) {
        MoteurJeu.jeu = jeu;
        MoteurJeu.dessin = dessin;
        if (jeu != null)
            launch();
        System.out.println("jeu fini");
    }

    public static void setFPS(int FPSSouhaitees) {
        FPS = FPSSouhaitees;
        dureeFPS = 1000 / (FPS + 1);
    }

    public static void setTaille(double width, double height) {
        WIDTH = width;
        HEIGHT = height;
    }

    @Override
    public void start(Stage primaryStage) {
        final Canvas canvas = new Canvas();
        final Pane canvasContainer = new Pane(canvas);
        canvas.widthProperty().bind(canvasContainer.widthProperty());
        canvas.heightProperty().bind(canvasContainer.heightProperty());

        final Label stats = new Label();
        stats.textProperty().bind(frameStats.textProperty());

        final BorderPane root = new BorderPane();
        root.setCenter(canvasContainer);
        root.setBottom(stats);

        final Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                controle.appuyerTouche(event);
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                controle.relacherTouche(event);
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getClickCount() == 2) {
                            jeu.init();
                        }
                    }
                });

        startAnimation(canvas, primaryStage);
    }

    private void startAnimation(final Canvas canvas, Stage stage) { // Ajout du paramètre primaryStage
        final LongProperty lastUpdateTime = new SimpleLongProperty(0);

        timer = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                if (lastUpdateTime.get() == 0) {
                    lastUpdateTime.set(timestamp);
                }

                long elapsedTime = timestamp - lastUpdateTime.get();
                double dureeEnMilliSecondes = elapsedTime / 1_000_000.0;

                if (dureeEnMilliSecondes > dureeFPS) {
                    jeu.update(dureeEnMilliSecondes / 1_000., controle);
                    dessin.dessinerJeu(jeu, canvas);
                    frameStats.addFrame(elapsedTime);
                    lastUpdateTime.set(timestamp);

                    if (jeu.etreFini()) { // on test si le jeu est fini puis on ferme notre Stage
                        timer.stop();
                        stage.close();
                    }
                }
            }
        };

        timer.start();
    }
}
