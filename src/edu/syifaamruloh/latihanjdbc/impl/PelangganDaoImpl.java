/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.syifaamruloh.latihanjdbc.impl;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import edu.syifaamruloh.latihanjdbc.entity.Pelanggan;
import edu.syifaamruloh.latihanjdbc.error.PelangganException;
import edu.syifaamruloh.latihanjdbc.service.PelangganDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Deek
 * Nama  : Muhamad Syifa Amruloh
 * Nim   : 10118910
 * Kelas : IF11K
 */
public class PelangganDaoImpl implements PelangganDao{
    
    private final Connection con;
    private final String insertPelanggan = "INSERT INTO PELANGGAN" 
                    + " (NAMA, ALAMAT, TELEPON, EMAIL) VALUES"
                    + "(?,?,?,?)";
    private final String updatePelanggan = "UPDATE PELANGGAN SET NAMA=?,ALAMAT=?,TELEPON=?,EMAIL=? WHERE ID=?";
    private final String deletePelanggan = "DELETE FROM PELANGGAN WHERE ID=?";
    private final String getById = "SELECT * FROM PELANGGAN WHERE ID=?";
    private final String getByEmail = "SELECT * FROM PELANGGAN WHERE EMAIL=?";
    private final String selectAll = "SELECT * FROM PELANGGAN";
    
    public PelangganDaoImpl(Connection con){
        this.con = con;
    }
    
    @Override
    public void insertPelanggan(Pelanggan pelanggan) throws PelangganException {
        //Penanganan Insert data
        
        PreparedStatement stat = null;
        
        try {
            con.setAutoCommit(false);
            stat = (PreparedStatement) con.prepareStatement(insertPelanggan,Statement.RETURN_GENERATED_KEYS);
            stat.setString(1, pelanggan.getNama());
            stat.setString(2, pelanggan.getAlamat());
            stat.setString(3, pelanggan.getTelepon());
            stat.setString(4, pelanggan.getEmail());
            stat.executeUpdate();
            
            ResultSet result = stat.getGeneratedKeys();
            if (result.next()) {
                pelanggan.setId(result.getInt(1));
            }
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
            }
            throw new PelangganException(e.getMessage());
        }finally{
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                
                }
            }
        }
    }

    @Override
    public void updatePelanggan(Pelanggan pelanggan) throws PelangganException {
        //Updata pelanggan
        
        PreparedStatement stat = null;
        
        try {
            con.setAutoCommit(false);
            stat = (PreparedStatement) con.prepareStatement(updatePelanggan);
            stat.setString(1, pelanggan.getNama());
            stat.setString(2, pelanggan.getAlamat());
            stat.setString(3, pelanggan.getTelepon());
            stat.setString(4, pelanggan.getEmail());
            
            stat.setInt(5, pelanggan.getId());
            stat.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            try {
               con.rollback();
            } catch (Exception ex) {
            }
            throw new PelangganException(e.getMessage());
        }finally{
            try {
                con.setAutoCommit(true);
            } catch (Exception ex) {
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                
                }
            }
        }
    }


    @Override
    public void deletePelanggan(Integer id) throws PelangganException {
        
        PreparedStatement stat = null;
        
        try {
            con.setAutoCommit(false);
            stat = (PreparedStatement) con.prepareStatement(deletePelanggan);
            stat.setInt(1, id);
            stat.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (Exception ex) {
            }
            throw new PelangganException(e.getMessage());
        }finally{
            try {
                con.setAutoCommit(true);
            } catch (Exception ex) {
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                
                }
            }
        }
    }

    @Override
    public Pelanggan getPelanggan(Integer id) throws PelangganException {
        PreparedStatement stat = null;
        
        try {
            con.setAutoCommit(false);
            stat = (PreparedStatement) con.prepareStatement(getById);
            //mengambil satu data sesuai id
            stat.setInt(1, id);
            
            ResultSet result = stat.executeQuery();
            Pelanggan pelanggan = null;
            if (result.next()) {
                pelanggan = new Pelanggan();
                pelanggan.setId(result.getInt("ID"));
                pelanggan.setNama(result.getString("NAMA"));
                pelanggan.setAlamat(result.getString("ALAMAT"));
                pelanggan.setTelepon(result.getString("TELEPON"));
                pelanggan.setEmail(result.getString("EMAIL"));
                
            }else{
                throw new PelangganException("Pelanggan dengan id "+ id +" tidak di temukan");
            }
            con.commit();
            return pelanggan;
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (Exception ex) {
            }
            throw new PelangganException(e.getMessage());
        }finally{
            try {
                con.setAutoCommit(true);
            } catch (Exception e) {
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                
                }
            }
        }
    }

    @Override
    public Pelanggan getPelanggan(String email) throws PelangganException {
     PreparedStatement stat = null;
        
        try {
            con.setAutoCommit(false);
            stat = (PreparedStatement) con.prepareStatement(getByEmail);
            //mengambil satu data sesuai id
            stat.setString(1, email);
            
            ResultSet result = stat.executeQuery();
            Pelanggan pelanggan = null;
            if (result.next()) {
                pelanggan = new Pelanggan();
                pelanggan.setId(result.getInt("ID"));
                pelanggan.setNama(result.getString("NAMA"));
                pelanggan.setAlamat(result.getString("ALAMAT"));
                pelanggan.setTelepon(result.getString("TELEPON"));
                pelanggan.setEmail(result.getString("EMAIL"));
                
            }else{
                throw new PelangganException("Pelanggan dengan email "+ email +" tidak di temukan");
            }
            con.commit();
            return pelanggan;
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (Exception ex) {
            }
            throw new PelangganException(e.getMessage());
        }finally{
            try {
                con.setAutoCommit(true);
            } catch (Exception e) {
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                
                }
            }
        } 
    }

    @Override
    public List<Pelanggan> selectAllPelanggan() throws PelangganException {
        Statement statement = null;
        List<Pelanggan> list = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            statement = con.createStatement();
            ResultSet result = statement.executeQuery(selectAll);
            while (result.next()) {
                Pelanggan pelanggan = new Pelanggan();
                pelanggan.setId(result.getInt("ID"));
                pelanggan.setNama(result.getString("NAMA"));
                pelanggan.setAlamat(result.getString("ALAMAT"));
                pelanggan.setTelepon(result.getString("TELEPON"));
                pelanggan.setEmail(result.getString("EMAIL"));
                
                list.add(pelanggan);
            }
            con.commit();
            return list;
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (Exception ex) {
            }
            throw new PelangganException(e.getMessage());
        } finally{
            try {
                con.setAutoCommit(true);
            } catch (Exception ex) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
        }
    }
    
}
