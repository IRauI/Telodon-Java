package mpp.telodon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mpp.telodon.controller.LoginController;
import mpp.telodon.controller.MainController;
import mpp.telodon.repository.*;
import mpp.telodon.service.CaritabilService;
import mpp.telodon.service.DonatieService;
import mpp.telodon.service.DonatorService;
import mpp.telodon.service.VoluntarService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Main extends Application {
    public void start(Stage stage) throws IOException {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream("bd.config")) {
            props.load(in);
        }

        VoluntarJdbcRepository voluntarRepo = new VoluntarJdbcRepository(props);
        CaritabilJdbcRepository caritabilRepo = new CaritabilJdbcRepository(props);
        DonatorJdbcRepository donatorRepo = new DonatorJdbcRepository(props);
        DonatieJdbcRepository donatieRepo = new DonatieJdbcRepository(props);

        VoluntarService voluntarService = new VoluntarService(voluntarRepo);
        CaritabilService caritabilService = new CaritabilService(caritabilRepo);
        DonatorService donatorService = new DonatorService(donatorRepo);
        DonatieService donatieService = new DonatieService(donatieRepo, caritabilRepo);

        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/controller/login-view.fxml"));
        Parent loginRoot = loginLoader.load();

        LoginController loginController = loginLoader.getController();
        loginController.setService(voluntarService);
        loginController.setOnLoginSuccess(v -> {
            try {
                showMainView(stage, caritabilService, donatorService, donatieService);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        stage.setTitle("Telodon - Login");
        stage.setScene(new Scene(loginRoot));
        stage.show();
    }

    private void showMainView(Stage stage, CaritabilService caritabilService,
                              DonatorService donatorService, DonatieService donatieService) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/controller/main-view.fxml"));
        Parent root = loader.load();

        MainController controller = loader.getController();
        controller.setServices(caritabilService, donatorService, donatieService);

        stage.setScene(new Scene(root));
        stage.setTitle("Telodon - Main");
    }

    static void main(String[] args) {
        launch(args);
    }
}
