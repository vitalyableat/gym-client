package com.gym.controller.pages;

import com.gym.Application;
import com.gym.State;
import com.gym.controller.entity.CoachEntityController;
import com.gym.controller.entity.UserEntityController;
import com.gym.entity.User;
import com.gym.utils.SubscriptionUtils;
import com.gym.utils.UserUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CoachesController implements Initializable {
    @FXML
    private VBox wrap_coaches;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        State.subscription = SubscriptionUtils.getUserSubscription(State.user.getId());
        State.coaches = UserUtils.getCoaches();
        setCoaches();
    }

    private void setCoaches() {
        wrap_coaches.getChildren().clear();
        for (User coach : State.coaches) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Application.class.getResource("components/entity/coach-entity.fxml"));
            try {
                AnchorPane coachEntity = fxmlLoader.load();
                CoachEntityController coachEntityController = fxmlLoader.getController();
                coachEntityController.setData(coach);
                wrap_coaches.getChildren().add(coachEntity);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
