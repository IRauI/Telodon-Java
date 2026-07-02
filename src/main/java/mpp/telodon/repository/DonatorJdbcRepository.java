package mpp.telodon.repository;

import mpp.telodon.model.Donator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DonatorJdbcRepository implements IRepository<Integer, Donator> {

    private JdbcUtils dbUtils;

    private static final Logger logger = LogManager.getLogger(DonatorRepository.class);

    public DonatorJdbcRepository(Properties props){
        logger.info("Initializing DonatorJdbcRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public int size() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select count(*) as [SIZE] from donator")) {
            try(ResultSet result = preStmt.executeQuery()) {
                if (result.next()) {
                    logger.traceExit(result.getInt("SIZE"));
                    return result.getInt("SIZE");
                }
            }
        }catch(SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        return 0;
    }

    @Override
    public void save(Donator entity) {
        logger.traceEntry("saving donator {} ",entity);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into donator values (?,?,?,?,?)")){
            preStmt.setInt(1,entity.getId());
            preStmt.setString(2,entity.getNume());
            preStmt.setString(3,entity.getPrenume());
            preStmt.setString(4,entity.getAdresa());
            preStmt.setString(5,entity.getTelefon());
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit();

    }

    @Override
    public void delete(Integer integer) {
        logger.traceEntry("deleting donator with id {}",integer);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from donator where id=?")){
            preStmt.setInt(1,integer);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit();
    }

    @Override
    public void update(Integer integer, Donator entity) {

    }

    @Override
    public Donator findOne(Integer integer) {
        logger.traceEntry("finding donator with id {} ",integer);
        Connection con=dbUtils.getConnection();

        try(PreparedStatement preStmt=con.prepareStatement("select * from donator where id=?")){
            preStmt.setInt(1,integer);
            try(ResultSet result=preStmt.executeQuery()) {
                if (result.next()) {
                    int id = result.getInt("id");
                    String nume = result.getString("nume");
                    String prenume = result.getString("prenume");
                    String adresa = result.getString("adresa");
                    String telefon = result.getString("telefon");
                    Donator donator = new Donator(id, nume, prenume, adresa, telefon);
                    logger.traceExit(donator);
                    return donator;
                }
            }
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit("No donatro found with id {}", integer);

        return null;
    }

    @Override
    public Iterable<Donator> findAll() {
        Connection con=dbUtils.getConnection();
        List<Donator> donatori=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from donator")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    String nume = result.getString("nume");
                    String prenume = result.getString("prenume");
                    String adresa = result.getString("adresa");
                    String telefon = result.getString("telefon");
                    Donator donator = new Donator(id, nume, prenume, adresa, telefon);
                    donatori.add(donator);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB "+e);
        }
        logger.traceExit(donatori);
        return donatori;
    }
}
