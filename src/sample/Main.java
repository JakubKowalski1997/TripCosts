package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main extends Application {
    private final TextField field = new TextField();
    private final Label personArray = new Label("");
    private final Label costArray = new Label("");
    private final ComboBox choosePersonToCost = new ComboBox();

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane border = new BorderPane();
        HBox hBox = new HBox();
        border.setTop(hBox);
        VBox paneLeft = new VBox();
        VBox paneCenter = new VBox();
        VBox paneRight = new VBox();
        border.setLeft(paneLeft);
        border.setCenter(paneCenter);
        border.setRight(paneRight);
        field.setPrefWidth(120);

        TextField addPersonCost = new TextField("");
        addPersonCost.setPrefWidth(85);

        Button addCost = new Button("Wpisz koszt i wciśnij ENTER");
        addCost.setPrefWidth(180);
        List listOfCosts = new ArrayList<>();
        final String[] i = new String[1];
        final double[] sum = {0};


        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Button addPerson = new Button("Dodaj osobę");
        addPerson.setPrefWidth(120);
        List<String> listOfPeople = new ArrayList<>();
        final String[] formatedAddPersonString = new String[1];
        addPerson.setOnAction((ActionEvent e) -> {
            if ((field.getText() != null && !field.getText().isEmpty())) {
                listOfPeople.add(field.getText() + "\n");
                formatedAddPersonString[0] = listOfPeople.toString()
                        .replace(",","")
                        .replace("[","")
                        .replace("]","")
                        .replace(" ","")
                        .trim();
                personArray.setText(formatedAddPersonString[0]);
                choosePersonToCost.getItems().addAll(
                        listOfPeople.get(listOfPeople.size() - 1)
                );
                double[] arrayOfCost = new double[listOfPeople.size()];
                System.out.println(Arrays.toString(arrayOfCost));

                double[] arrayOfTotalCost = new double[listOfPeople.size()];
                System.out.println(Arrays.toString(arrayOfTotalCost));

                for(int b = 0;b < listOfPeople.size();b++ ) {
                    arrayOfCost[b] = 0;
                }

                for(int b = 0;b < listOfPeople.size();b++ ) {
                    arrayOfTotalCost[b] = 0;
                }

                addPersonCost.setOnKeyPressed(e1 -> {
                    if(e1.getCode()== KeyCode.ENTER) {
                        listOfCosts.add(choosePersonToCost.getSelectionModel().getSelectedIndex(),addPersonCost.getText() + "\n");
                        Scanner sc = new Scanner(addPersonCost.getText());
                        arrayOfCost[choosePersonToCost.getSelectionModel().getSelectedIndex()] = (arrayOfCost[choosePersonToCost.getSelectionModel().getSelectedIndex()] + sc.nextDouble());
                        double suma = 0;
                        for(int a = 0; a < listOfPeople.size(); a++)
                        {
                            suma+=arrayOfCost[a];
                        }
                        for(int b = 0;b < listOfPeople.size();b++ ) {
                            arrayOfTotalCost[b] = arrayOfCost[b] - (suma) / listOfPeople.size();
                        }
                        String abc = Arrays.toString(arrayOfTotalCost);
                        costArray.setText(abc.replace(",","zł \n")
                                .replace("[","")
                                .replace("]","zł")
                                .replace(" ","")
                                .trim());
                    }});

            } else {
                personArray.setText("Nic nie dodałeś");
                field.setOnKeyPressed(e12 -> {
                    if(e12.getCode()== KeyCode.ENTER) {
                        personArray.setText(formatedAddPersonString[0]);
                    }});
            }

        });

        hBox.getChildren().addAll(addPerson,addCost);
        paneLeft.getChildren().addAll(field,personArray);
        paneCenter.getChildren().addAll(choosePersonToCost,costArray);
        paneRight.getChildren().addAll(addPersonCost);
        primaryStage.getIcons().add(new Image("file:sun.png"));
        primaryStage.setTitle("ShareTripCosts");
        primaryStage.setScene(new Scene(border, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}