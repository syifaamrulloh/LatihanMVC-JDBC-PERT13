/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.syifaamruloh.latihanjdbc.main;

import edu.syifaamruloh.latihanjdbc.database.BabershopDatabase;
import edu.syifaamruloh.latihanjdbc.entity.Pelanggan;
import edu.syifaamruloh.latihanjdbc.error.PelangganException;
import edu.syifaamruloh.latihanjdbc.service.PelangganDao;
import edu.syifaamruloh.latihanjdbc.view.MainViewPelanggan;
import java.sql.SQLException;
import java.util.List;
import javax.swing.SwingUtilities;

/**
 *
 * @author Deek
 * Nama : Muhamad Syifa Amruloh
 * Nim  : 10118910
 * Kelas: IF11K
 */
public class LatihanMVCJDBC {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException, PelangganException {
        try {
            BabershopDatabase.getKoneksi();
            System.out.println("Koneksi ke database Lancar");
        } catch (Exception e) {
            System.out.println("koneksi gagal" + e);
        }
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    MainViewPelanggan pelanggan = new MainViewPelanggan();
                    pelanggan.loadDatabase();
                    pelanggan.setVisible(true);
                } catch (SQLException | PelangganException e) {
                }
            }
        });
    }
    
}
