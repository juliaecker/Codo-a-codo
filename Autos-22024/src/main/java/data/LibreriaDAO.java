package data;

import static data.Conexion.close;
import static data.Conexion.getConexion;
import entity.Autos;
import java.sql.*;
import java.util.*;

public class LibreriaDAO {

    private static final String SQL_CREATE = "INSERT INTO autos(marca, modelo, año, precio, stock) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_READ = "SELECT * FROM autos";
    private static final String SQL_UPDATE_PRECIO = "UPDATE autos SET precio = ? WHERE idautos = ?";
    private static final String SQL_UPDATE_STOCK = "UPDATE autos SET stock = ? WHERE idautos = ?";
    private static final String SQL_UPDATE= "UPDATE autos SET marca = ?, modelo = ?, año = ?, precio = ?, stock = ? WHERE idautos = ?";
    private static final String SQL_DELETE = "DELETE FROM autos WHERE idautos = ?";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM autos WHERE idautos = ?";

    public List<Autos> findAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Autos auto = null;
        List<Autos> autos = new ArrayList();

        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_READ);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idAutos = rs.getInt(1);
                String marca = rs.getString(2);
                String modelo = rs.getString(3);
                int año = rs.getInt(4);
                double precio = rs.getDouble(5);
                int stock = rs.getInt(6);

                auto = new Autos(idAutos, marca, modelo, año, precio, stock);

                autos.add(auto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(rs);
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }

        return autos;
    }
    
        public Autos findCarById(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Autos auto = null;
        
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()){
                int idAutos = rs.getInt(1);
                String marca = rs.getString(2);
                String modelo = rs.getString(3);
                int año = rs.getInt(4);
                double precio = rs.getDouble(5);
                int stock = rs.getInt(6);

                auto = new Autos(idAutos, marca, modelo, año, precio, stock);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(rs);
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return auto;
    }

    public int create(Autos auto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_CREATE);
            stmt.setString(1, auto.getMarca());
            stmt.setString(2, auto.getModelo());
            stmt.setInt(3, auto.getAño());
            stmt.setDouble(4, auto.getPrecio());
            stmt.setInt(5, auto.getStock());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }

    public int updatePrecio(Autos auto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE_PRECIO);
            stmt.setDouble(1, auto.getPrecio());
            stmt.setInt(2, auto.getIdAutos());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }

    public int updateStock(Autos auto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE_STOCK);
            stmt.setInt(1, auto.getStock());
            stmt.setInt(2, auto.getIdAutos());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
        public int update(Autos auto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, auto.getMarca());
            stmt.setString(2, auto.getModelo());
            stmt.setInt(3, auto.getAño());
            stmt.setDouble(4, auto.getPrecio());
            stmt.setInt(5, auto.getStock());
            stmt.setInt(6, auto.getIdAutos());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }

    public int delete(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
}
