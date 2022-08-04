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
        ventanaVentas.
                agregarListenersBtns(new ManejadorDeEventosVentanaVentas());
    }
    
    //VentanaVentas
    class ManejadorDeEventosVentanaVentas implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equalsIgnoreCase("agregar año")){
                //se agrega la venta
                float ventaAño = Float.
                        parseFloat(ventanaVentas.getTxtCantidadVenta());
                pronosticoDeVentas.agregarAño(ventaAño);
                
                //se actualiza la tabla
                
                
            }
            else if(e.getActionCommand().equalsIgnoreCase("borrar año")){
                int añoABorrar = ventanaVentas.getFilaHistorio();
                if(añoABorrar != -1){
                    pronosticoDeVentas.borrarAnio(añoABorrar);
                    
                    //se actualiza la tabla
                }
            }
            else if(e.getActionCommand().equalsIgnoreCase("modifica año")){
                int añoAModificar = ventanaVentas.getFilaHistorio();
                if(añoAModificar != -1){
                    //float nuevaVenta = Float.
                    //    parseFloat(ventanaVentas.getVentaNueva());
                    pronosticoDeVentas.modificarAnio(añoAModificar, añoAModificar);
                    
                    //se actualiza la tabla
                }
            }
            else if(e.getActionCommand().equalsIgnoreCase("nuevo pronostioo")){
                System.out.println("btn nuevo pronostico");
                pronosticoDeVentas.nuevoPronostico();
                //se actualiza las tablas
            }
        }
        
    }
}
