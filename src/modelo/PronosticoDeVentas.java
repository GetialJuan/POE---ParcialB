/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Juan
 */
public class PronosticoDeVentas {
    //años - {ventas: ..., delta Y :Yn - Yn-1, porcentajeVariacion: (Yn - Yn-1)/Yn-1
    private ArrayList<HashMap<String,Float>> años;
    private float promedioDeVariaciones;
    
    public PronosticoDeVentas(){
        años = new ArrayList<>();
    }
    
    public void agregarAño(float ventas){
        HashMap<String,Float> año = new HashMap<>();
        if(años.isEmpty()){
            año.put("ventas", ventas);
            año.put("deltaVentas", 0f);
            año.put("porcentajeVariacion", 0f);
        }
        else{
            //datos del años anterior
            int indiceAño = años.size() -1;
            float ventasAnterior = años.get(indiceAño).get("ventas");
            
            //se crean y añaden los datos del año nuevo
            float deltaVentas = ventas - ventasAnterior;
            float porcentajeVariacion = deltaVentas/ventasAnterior;
            
            año.put("ventas", ventas);
            año.put("deltaVentas", deltaVentas);
            año.put("porcentajeVariacion", porcentajeVariacion);
        }
        
        años.add(año);
    }
    
    public void setPromedioDeVariaciones(){
        float sumaVariaciones = 0;
        float nPeriodos = años.size() -1;
        for(HashMap<String,Float> año : años){
            sumaVariaciones += año.get("porcentajeVariacion");
        }
        
        promedioDeVariaciones = sumaVariaciones/nPeriodos;
    }
    
    public float promedioDeVariaciones(){
        return promedioDeVariaciones;
    }
    
    public void borrarAño(int cualAño){
        if(!años.isEmpty()){
            años.remove(cualAño);
        }
    }
    
    public void modificarAño(int cualAño, float ventas){
        if(años.size() > 1){
        }
    }
    
    private void calcularDatosDeAños(){
        if(años.size() > 1){
            int indiceAño = 0;
            for(HashMap<String,Float> año : años){
                if(indiceAño > 0){
                    //datos del años anterior
                    int indiceAñoAnterior = indiceAño - 1;
                    float ventasAnterior = años.get(indiceAñoAnterior).get("ventas");

                    //se crean y añaden los datos del año nuevo
                    float deltaVentas = año.get("ventas") - ventasAnterior;
                    float porcentajeVariacion = deltaVentas/ventasAnterior;
                    
                    año.put("deltaVentas", deltaVentas);
                    año.put("porcentajeVariacion", porcentajeVariacion);
                }
            }
        }
    }
}
