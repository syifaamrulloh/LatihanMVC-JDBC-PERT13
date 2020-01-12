/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.syifaamruloh.latihanjdbc.model;

import edu.syifaamruloh.latihanjdbc.database.BabershopDatabase;
import edu.syifaamruloh.latihanjdbc.entity.Pelanggan;
import edu.syifaamruloh.latihanjdbc.error.PelangganException;
import edu.syifaamruloh.latihanjdbc.event.PelangganListener;
import edu.syifaamruloh.latihanjdbc.service.PelangganDao;
import java.sql.SQLException;

/**
 *
 * @author Deek
 */
public class PelangganModel {
    private int id;
    private String nama;
    private PelangganListener listener;

    public int getId() {
        return id;
    }

    public PelangganListener getListener() {
        return listener;
    }

    public void setListener(PelangganListener listener) {
        this.listener = listener;
    }
    
    protected void fireOnChange(){
        if (listener != null) {
            listener.onChange(this);
        }
    }
    
    protected void fireOnInsert(Pelanggan pelanggan){
        if (listener != null) {
            listener.OnInsert(pelanggan);
        }
    }
    
    protected void fireOnUpdate(Pelanggan pelanggan){
        if (listener != null) {
            listener.OnUpdate(pelanggan);
        }
    }
    
    protected void fireOnDelete(){
        if (listener != null) {
            listener.OnDelete();
        }
    }

    public void setId(int id) {
        this.id = id;
        fireOnChange();
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
        fireOnChange();
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
        fireOnChange();
    }

    public String getTelepon() {
        return telepon;
        
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
        fireOnChange();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        fireOnChange();
    }
    private String alamat;
    private String telepon;
    private String email;
    
    public void insertPelanggan() throws SQLException, PelangganException{
        PelangganDao dao = BabershopDatabase.getPelangganDao();
        
        Pelanggan pelanggan = new Pelanggan();
        pelanggan.setNama(nama);
        pelanggan.setAlamat(alamat);
        pelanggan.setTelepon(telepon);
        pelanggan.setEmail(email);
        
        dao.insertPelanggan(pelanggan);
        fireOnInsert(pelanggan);
    }
    
    public void UpdatePelanggan() throws SQLException, PelangganException{
        PelangganDao dao = BabershopDatabase.getPelangganDao();
        
        Pelanggan pelanggan = new Pelanggan();
        pelanggan.setNama(nama);
        pelanggan.setAlamat(alamat);
        pelanggan.setTelepon(telepon);
        pelanggan.setEmail(email);
        pelanggan.setId(id);
        
        dao.updatePelanggan(pelanggan);
        fireOnUpdate(pelanggan);
    }
    
    public void DeletePelanggan() throws SQLException, PelangganException{
        PelangganDao dao = BabershopDatabase.getPelangganDao();
        
        dao.deletePelanggan(id);
        fireOnDelete();
    }
    
    public void resetPelanggan(){
        setId(0);
        setNama("");
        setAlamat("");
        setTelepon("");
        setEmail("");
    }
}
