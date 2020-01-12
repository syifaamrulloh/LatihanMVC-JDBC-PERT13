/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.syifaamruloh.latihanjdbc.database;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import edu.syifaamruloh.latihanjdbc.impl.PelangganDaoImpl;
import edu.syifaamruloh.latihanjdbc.service.PelangganDao;
import java.sql.SQLException;

/**
 *
 * @author Deek
 * Nama : Muhamad Syifa Amruloh
 * Nim  : 10118910
 * Kelas: IF11K
 */
public class BabershopDatabase {
    
    private static Connection con;
    private static PelangganDao pelangganDao;
    
    public static Connection getKoneksi() throws SQLException{
    
        if (con == null) {
            MysqlDataSource datasource = new MysqlDataSource();
            datasource.setURL("jdbc:mysql://localhost:3306/babershop");
            datasource.setUser("root");
            datasource.setPassword("");
            con = (Connection) datasource.getConnection();
        }
        
        return con;
    }
    
    public static PelangganDao getPelangganDao() throws SQLException{
        if (pelangganDao == null) {
            pelangganDao = new PelangganDaoImpl(getKoneksi());
        }
        return pelangganDao;
    }
}
