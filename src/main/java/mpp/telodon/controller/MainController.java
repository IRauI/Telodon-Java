package mpp.telodon.controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import mpp.telodon.model.Caritabil;
import mpp.telodon.service.CaritabilService;
import mpp.telodon.service.DonatieService;
import mpp.telodon.service.DonatorService;


public class MainController {
    @FXML
    private TableView<Caritabil> caritabilTable;
    @FXML
    private TableColumn<Caritabil, String> denumireColumn;
    @FXML
    private TableColumn<Caritabil, Number> totalColumn;

    private CaritabilService caritabil_service;
    private DonatorService donator_service;
    private DonatieService donatie_service;

    public MainController() {
        denumireColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getDenumire()));
        totalColumn.setCellValueFactory(data ->
                new SimpleDoubleProperty(data.getValue().getTotal()));
        refreshCaritabilTable();
    }

    private void refreshCaritabilTable() {
        ObservableList<Caritabil> items = FXCollections.observableArrayList();
        caritabil_service.getAll().forEach(items::add);
        caritabilTable.setItems(items);
    }

    public void setCaritabil_service(CaritabilService caritabil_service) {
        this.caritabil_service = caritabil_service;
    }

    public void setDonator_service(DonatorService donator_service) {
        this.donator_service = donator_service;
    }

    public void setDonatie_service(DonatieService donatie_service) {
        this.donatie_service = donatie_service;
    }

    public void setServices(CaritabilService caritabilService, DonatorService donatorService, DonatieService donatieService) {
        setCaritabil_service(caritabilService);
        setDonatie_service(donatieService);
        setDonator_service(donatorService);
    }


}
