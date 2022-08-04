/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.PronosticoDeVentas;
import vista.VentanaVentas;

/**
 *
 * @author Juan
 */
public class ControladorPronosticoDeVentas {
    PronosticoDeVentas pronosticoDeVentas;
    
    VentanaVentas ventanaVentas;
    
    public ControladorPronosticoDeVentas(){
        pronosticoDeVentas = new PronosticoDeVentas();
        ventanaVentas = new VentanaVentas();
    }
    
    //VentanaVentas
    class ManejadorDeEventosVentanaVentas implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
        }
        
    }
}
