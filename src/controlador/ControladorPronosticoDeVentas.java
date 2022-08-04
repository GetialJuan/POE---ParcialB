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
                
                //se actualizan las tablas
                float total = pronosticoDeVentas.getSumaPromedios();
                ventanaVentas.generarTablaHistorico(
                pronosticoDeVentas.getAnios(), total);
                ventanaVentas.
                        generarTablaPronostico(
                                pronosticoDeVentas.getPronostico(
                                        Integer.parseInt(ventanaVentas.
                                                getTxtCantidad())));
                
            }
            else if(e.getActionCommand().equalsIgnoreCase("borrar año")){
                int añoABorrar = ventanaVentas.getFilaHistorico();
                if(añoABorrar != -1){
                    pronosticoDeVentas.borrarAnio(añoABorrar);
                    
                    //se actualiza la tabla
                    float total = pronosticoDeVentas.getSumaPromedios();
                    ventanaVentas.generarTablaHistorico(
                    pronosticoDeVentas.getAnios(), total);
                }else{
                    ventanaVentas.mensajeBorrar();
                }
            }
            else if(e.getActionCommand().equalsIgnoreCase("modificar año")){
                int añoAModificar = ventanaVentas.getFilaHistorico();
                if(añoAModificar != -1){
                    float dato = ventanaVentas.modificarDato();
                    if(dato != -1){
                        int fila = ventanaVentas.getFilaHistorico();
                        pronosticoDeVentas.modificarAnio(fila, dato);
                        float total = pronosticoDeVentas.getSumaPromedios();
                        ventanaVentas.generarTablaHistorico(
                        pronosticoDeVentas.getAnios(), total);                        
                    }
                    else if(dato == -1){
                        ventanaVentas.mensajeErrorDato();
                    }
                }
            }
            else if(e.getActionCommand().equalsIgnoreCase("nuevo pronostico")){
                System.out.println("btn nuevo pronostico");
                pronosticoDeVentas.nuevoPronostico();
                //se actualiza la tabla
                float total = pronosticoDeVentas.getSumaPromedios();
                ventanaVentas.generarTablaHistorico(
                pronosticoDeVentas.getAnios(), total);
            }
        }
        
    }
}
