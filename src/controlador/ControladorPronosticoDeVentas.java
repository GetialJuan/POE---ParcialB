
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
                float ventaAnio = Float.
                        parseFloat(ventanaVentas.getTxtCantidadVenta());
                pronosticoDeVentas.agregarAnio(ventaAnio);
                
                //se actualizan las tablas
                float total = pronosticoDeVentas.getSumaPromedios();
                ventanaVentas.generarTablaHistorico(
                pronosticoDeVentas.getAnios(), total);
                try{
                ventanaVentas.
                        generarTablaPronostico(pronosticoDeVentas.getPronostico(
                            Integer.parseInt(ventanaVentas.getTxtCantidad())));
                }catch(Exception ne){
                    ventanaVentas.mensajeErrorCantidad();
                }
                ventanaVentas.setTxtPromedio(
                pronosticoDeVentas.getPromedioDeVariaciones()+"");
            }
            else if(e.getActionCommand().equalsIgnoreCase("borrar año")){
                int anioABorrar = ventanaVentas.getFilaHistorico();
                if(anioABorrar >= pronosticoDeVentas.getAnios().size()){
                    //No hace nada
                }
                else if(anioABorrar != -1){
                    pronosticoDeVentas.borrarAnio(anioABorrar);
                    
                    //se actualizan la tablas
                    float total = pronosticoDeVentas.getSumaPromedios();
                    ventanaVentas.generarTablaHistorico(
                    pronosticoDeVentas.getAnios(), total);
                    try{
                    ventanaVentas.
                            generarTablaPronostico(pronosticoDeVentas.getPronostico(
                                Integer.parseInt(ventanaVentas.getTxtCantidad())));
                    }catch(Exception ne){
                        ventanaVentas.mensajeErrorCantidad();
                    }
                    ventanaVentas.setTxtPromedio(
                        pronosticoDeVentas.getPromedioDeVariaciones()+"");
                }else{
                    ventanaVentas.mensajeBorrar();
                }
            }
            else if(e.getActionCommand().equalsIgnoreCase("modificar año")){
                int anioAModificar = ventanaVentas.getFilaHistorico();
                if(anioAModificar != -1){
                    float dato = ventanaVentas.modificarDato();
                    if(dato != -1){
                        int fila = ventanaVentas.getFilaHistorico();
                        pronosticoDeVentas.modificarAnio(fila, dato);
                        float total = pronosticoDeVentas.getSumaPromedios();
                        ventanaVentas.generarTablaHistorico(
                        pronosticoDeVentas.getAnios(), total);
                        try{
                        ventanaVentas.
                                generarTablaPronostico(pronosticoDeVentas.getPronostico(
                                    Integer.parseInt(ventanaVentas.getTxtCantidad())));
                        }catch(Exception ne){
                            ventanaVentas.mensajeErrorCantidad();
                        }
                        ventanaVentas.setTxtPromedio(
                            pronosticoDeVentas.getPromedioDeVariaciones()+"");
                    }
                    else if(dato == -1){
                        ventanaVentas.mensajeErrorDato();
                    }
                }
            }
            else if(e.getActionCommand().equalsIgnoreCase("nuevo pronostico")){
                System.out.println("btn nuevo pronostico");
                pronosticoDeVentas.nuevoPronostico();
                //se actualizan las tablas
                float total = pronosticoDeVentas.getSumaPromedios();
                ventanaVentas.generarTablaHistorico(
                pronosticoDeVentas.getAnios(), total);
                try{
                ventanaVentas.
                        generarTablaPronostico(pronosticoDeVentas.getPronostico(
                            Integer.parseInt(ventanaVentas.getTxtCantidad())));
                }catch(Exception ne){
                    ventanaVentas.mensajeErrorCantidad();
                }
                ventanaVentas.setTxtPromedio(
                    pronosticoDeVentas.getPromedioDeVariaciones()+"");
            }
        }  
    }
}
