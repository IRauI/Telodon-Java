package mpp.telodon.repository;

import mpp.telodon.model.Voluntar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class VoluntarJdbcRepository implements IRepository<Integer, Voluntar>{
    private JdbcUtils dbUtils;

    private static final Logger logger = LogManager.getLogger(VoluntarRepository.class);

    public VoluntarJdbcRepository(Properties props){
        logger.info("Initializing VoluntarJdbcRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public int size() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select count(*) as [SIZE] from voluntar")) {
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
    public void save(Voluntar entity) {
        logger.traceEntry("saving voluntar {} ",entity);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into voluntar values (?,?,?)")){
            preStmt.setInt(1,entity.getId());
            preStmt.setString(2,entity.getUsername());
            preStmt.setString(3,entity.getPassword());
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit();

    }

    @Override
    public void delete(Integer integer) {
        logger.traceEntry("deleting voluntar with id {}",integer);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from voluntar where id=?")){
            preStmt.setInt(1,integer);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit();
    }

    @Override
    public void update(Integer integer, Voluntar entity) {
        logger.traceEntry("updating voluntar {} ", entity);
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement(
                "update voluntar set username=?, password=? where id=?")) {
            preStmt.setString(1, entity.getUsername());
            preStmt.setString(2, entity.getPassword());
            preStmt.setInt(3, integer);
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex);
            System.out.println("Error DB " + ex);
        }
        logger.traceExit();
    }

    @Override
    public Voluntar findOne(Integer integer) {
        logger.traceEntry("finding voluntar with id {} ",integer);
        Connection con=dbUtils.getConnection();

        try(PreparedStatement preStmt=con.prepareStatement("select * from voluntar where id=?")){
            preStmt.setInt(1,integer);
            try(ResultSet result=preStmt.executeQuery()) {
                if (result.next()) {
                    int id = result.getInt("id");
                    String username = result.getString("username");
                    String password = result.getString("password");
                    Voluntar voluntar = new Voluntar(id, username, password);
                    logger.traceExit(voluntar);
                    return voluntar;
                }
            }
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit("No voluntar found with id {}", integer);

        return null;
    }

    @Override
    public Iterable<Voluntar> findAll() {
        Connection con=dbUtils.getConnection();
        List<Voluntar> voluntari=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from voluntar")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    String username = result.getString("username");
                    String password = result.getString("password");
                    Voluntar voluntar = new Voluntar(id, username, password);
                    voluntari.add(voluntar);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB "+e);
        }
        logger.traceExit(voluntari);
        return voluntari;
    }
}
