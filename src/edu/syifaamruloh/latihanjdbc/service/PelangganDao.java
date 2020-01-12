/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.syifaamruloh.latihanjdbc.service;

import edu.syifaamruloh.latihanjdbc.entity.Pelanggan;
import edu.syifaamruloh.latihanjdbc.error.PelangganException;
import java.util.List;

/**
 *
 * @author Deek
 * Nama : Muhamad Syifa Amruloh
 * Nim  : 10118910
 * Kelas: IF11K
 */
public interface PelangganDao {

     //Untuk melakukan aktifitas CRUD
    //Get By ID dan Get By Email
    
    public void insertPelanggan(Pelanggan pelanggan) throws PelangganException;
    public void updatePelanggan(Pelanggan pelanggan) throws PelangganException;
    public void deletePelanggan(Integer id) throws PelangganException;
    
    public Pelanggan getPelanggan(Integer id) throws PelangganException;
    
    //Unique
    
    public Pelanggan getPelanggan(String email) throws PelangganException;
    
    //Jika ingin meload semua data pelanggan
    public List<Pelanggan> selectAllPelanggan() throws PelangganException;
}
