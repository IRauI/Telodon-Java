package mpp.telodon.repository;

import mpp.telodon.model.Donatie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DonatieJdbcRepository implements IRepository<Integer, Donatie>{
    private JdbcUtils dbUtils;

    private static final Logger logger = LogManager.getLogger(DonatieRepository.class);

    public DonatieJdbcRepository(Properties props){
        logger.info("Initializing DonatieJdbcRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public int size() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select count(*) as [SIZE] from donatie")) {
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
    public void save(Donatie entity) {
        logger.traceEntry("saving donatie {} ",entity);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into donatie values (?,?,?,?)")){
            preStmt.setInt(1,entity.getId());
            preStmt.setInt(2,entity.getDonator_id());
            preStmt.setInt(3,entity.getCaritabil_id());
            preStmt.setDouble(4,entity.getSuma());
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit();

    }

    @Override
    public void delete(Integer integer) {
        logger.traceEntry("deleting donatie with id {}",integer);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from donatie where id=?")){
            preStmt.setInt(1,integer);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit();
    }

    @Override
    public void update(Integer integer, Donatie entity) {

    }

    @Override
    public Donatie findOne(Integer integer) {
        logger.traceEntry("finding donatie with id {} ",integer);
        Connection con=dbUtils.getConnection();

        try(PreparedStatement preStmt=con.prepareStatement("select * from donatie where id=?")){
            preStmt.setInt(1,integer);
            try(ResultSet result=preStmt.executeQuery()) {
                if (result.next()) {
                    int id = result.getInt("id");
                    int caritabil_id = result.getInt("caritabil_id");
                    int donator_id = result.getInt("donator_id");
                    double total = result.getDouble("total");
                    Donatie donatie = new Donatie(id, donator_id, caritabil_id, total);
                    logger.traceExit(donatie);
                    return donatie;
                }
            }
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit("No donatie found with id {}", integer);

        return null;
    }

    @Override
    public Iterable<Donatie> findAll() {
        Connection con=dbUtils.getConnection();
        List<Donatie> donatii=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from donatie")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    int caritabil_id = result.getInt("caritabil_id");
                    int donator_id = result.getInt("donator_id");
                    double total = result.getDouble("total");
                    Donatie donatie = new Donatie(id, donator_id, caritabil_id, total);
                    donatii.add(donatie);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB "+e);
        }
        logger.traceExit(donatii);
        return donatii;
    }
}
