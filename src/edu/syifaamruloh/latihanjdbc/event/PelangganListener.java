/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.syifaamruloh.latihanjdbc.event;

import edu.syifaamruloh.latihanjdbc.entity.Pelanggan;
import edu.syifaamruloh.latihanjdbc.model.PelangganModel;

/**
 *
 * @author Deek
 */
public interface PelangganListener {
   
//    Parameter dari model pelanggan
    public void onChange(PelangganModel model);
    
//    parameter dari entity database pelangaan
    public void OnInsert(Pelanggan pelanggan);
    public void OnUpdate(Pelanggan pelanggan);
    
    public void OnDelete();
}
