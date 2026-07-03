package mpp.telodon.repository;

import mpp.telodon.model.Caritabil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CaritabilJdbcRepository implements IRepository<Integer, Caritabil> {
    private JdbcUtils dbUtils;

    private static final Logger logger = LogManager.getLogger(CaritabilRepository.class);

    public CaritabilJdbcRepository(Properties props){
        logger.info("Initializing CaritabilJdbcRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public int size() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select count(*) as [SIZE] from caritabil")) {
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
    public void save(Caritabil entity) {
        logger.traceEntry("saving caritabil {} ",entity);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into caritabil values (?,?,?)")){
            preStmt.setInt(1,entity.getId());
            preStmt.setDouble(2,entity.getTotal());
            preStmt.setString(3,entity.getDenumire());
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit();

    }

    @Override
    public void delete(Integer integer) {
        logger.traceEntry("deleting caritabil with id {}",integer);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from caritabil where id=?")){
            preStmt.setInt(1,integer);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit();
    }

    @Override
    public void update(Integer integer, Caritabil entity) {
        logger.traceEntry("updating caritabil {} ", entity);
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement(
                "update caritabil set total=?, denumire=? where id=?")) {
            preStmt.setDouble(1, entity.getTotal());
            preStmt.setString(2, entity.getDenumire());
            preStmt.setInt(3, integer);
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex);
            System.out.println("Error DB " + ex);
        }
        logger.traceExit();
    }

    @Override
    public Caritabil findOne(Integer integer) {
        logger.traceEntry("finding caritabil with id {} ",integer);
        Connection con=dbUtils.getConnection();

        try(PreparedStatement preStmt=con.prepareStatement("select * from caritabil where id=?")){
            preStmt.setInt(1,integer);
            try(ResultSet result=preStmt.executeQuery()) {
                if (result.next()) {
                    int id = result.getInt("id");
                    String denumire = result.getString("denumire");
                    int total = result.getInt("total");
                    Caritabil caritabil = new Caritabil(id, denumire, total);
                    logger.traceExit(caritabil);
                    return caritabil;
                }
            }
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit("No caritabil found with id {}", integer);

        return null;
    }

    @Override
    public Iterable<Caritabil> findAll() {
        Connection con=dbUtils.getConnection();
        List<Caritabil> caritabile=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from caritabil")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    String denumire = result.getString("denumire");
                    int total = result.getInt("total");
                    Caritabil caritabil = new Caritabil(id, denumire, total);
                    caritabile.add(caritabil);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB "+e);
        }
        logger.traceExit(caritabile);
        return caritabile;
    }
}
