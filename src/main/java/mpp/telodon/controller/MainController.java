package mpp.telodon.controller;

import mpp.telodon.service.CaritabilService;
import mpp.telodon.service.DonatieService;
import mpp.telodon.service.DonatorService;

public class MainController {
    private CaritabilService caritabil_service;
    private DonatorService donator_service;
    private DonatieService donatie_service;

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
