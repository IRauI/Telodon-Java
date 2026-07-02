package mpp.telodon;

import mpp.telodon.repository.*;

import java.util.Properties;

public class Main {
    static void main() {
        Properties props = new Properties();
        props.setProperty("telodon.jdbc.driver", "org.sqlite.JDBC");
        props.setProperty("telodon.jdbc.url", "jdbc:sqlite:telodon.sqlite");
        CaritabilJdbcRepository caritabil_repo = new CaritabilJdbcRepository(props);
        DonatorJdbcRepository donator_repo = new DonatorJdbcRepository(props);
        DonatieJdbcRepository donatie_repo = new DonatieJdbcRepository(props);
        VoluntarJdbcRepository voluntar_repo = new VoluntarJdbcRepository(props);

        caritabil_repo.findAll();
        donator_repo.findAll();
        donatie_repo.findAll();
        voluntar_repo.findAll();

    }
}
