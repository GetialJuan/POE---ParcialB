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
    private ArrayList<HashMap<String,Float>> anios;
    private float promedioDeVariaciones;
    private float totalPorcentaje;
    
    public PronosticoDeVentas(){
        anios = new ArrayList<>();
    }
    
    public void agregarAño(float ventas){
        HashMap<String,Float> anio = new HashMap<>();
        if(anios.isEmpty()){
            anio.put("ventas", ventas);
            anio.put("deltaVentas", 0f);
            anio.put("porcentajeVariacion", 0f);
        }
        else{
            //datos del anios anterior
            int indiceAño = anios.size() -1;
            float ventasAnterior = anios.get(indiceAño).get("ventas");
            
            //se crean y añaden los datos del anio nuevo
            float deltaVentas = ventas - ventasAnterior;
            float porcentajeVariacion = deltaVentas/ventasAnterior;
            
            anio.put("ventas", ventas);
            anio.put("deltaVentas", deltaVentas);
            anio.put("porcentajeVariacion", porcentajeVariacion);
        }
        
        anios.add(anio);
    }
    
    private void setPromedioDeVariaciones(){
        float sumaVariaciones = 0;
        float nPeriodos = anios.size() -1;
        calcularTotal();
        promedioDeVariaciones = sumaVariaciones/nPeriodos;
        
    }
    
    public float getPromedioDeVariaciones(){
        return promedioDeVariaciones;
    }
    
    public void borrarAnio(int cualAnio){
        anios.remove(cualAnio);
        calcularDatosDeAnios();
    }
    
    public void modificarAnio(int cualAnio, float ventas){
        anios.get(cualAnio).put("ventas", ventas);
        calcularDatosDeAnios();
    }
    
    private void calcularDatosDeAnios(){
        if(anios.size() > 1){
            int indiceAnio = 0;
            for(HashMap<String,Float> anio : anios){
                if(indiceAnio > 0){
                    //datos del anios anterior
                    int indiceAnioAnterior = indiceAnio - 1;
                    float ventasAnterior = anios.get(indiceAnioAnterior).get("ventas");

                    //se crean y añaden los datos del anio nuevo
                    float deltaVentas = anio.get("ventas") - ventasAnterior;
                    float porcentajeVariacion = deltaVentas/ventasAnterior;
                    
                    anio.put("deltaVentas", deltaVentas);
                    anio.put("porcentajeVariacion", porcentajeVariacion);
                }
                indiceAnio++;
            }
        }
        setPromedioDeVariaciones();
    }
    
    public ArrayList<HashMap<String,Float>> getAnios(){
        return anios;
    }
    
    public void nuevoPronostico(){
        anios.clear();
        promedioDeVariaciones = 0f;
        totalPorcentaje =
    }
    
    public ArrayList<Float> getPronostico(int cuantosAños) {
        if(cuantosAños < 2){
            cuantosAños = 2;
        }
        ArrayList<Float> añosPronosticados = new ArrayList<>();
        if(anios.size() > 2){
            float ventaUltimoAño = anios.get(anios.size()-1).get("ventas");
            while(cuantosAños > 0){
                float ventaPronosticada = ventaUltimoAño*(1+promedioDeVariaciones);
                añosPronosticados.add(ventaPronosticada);
                ventaUltimoAño = ventaPronosticada;
                cuantosAños--;
            }
            
        }
        
        
        return añosPronosticados;
    }
    
    public void calcularTotal(){
        float auxTotal = 0;
        for(HashMap <String,Float> map : anios){
            auxTotal += map.get("porcentajeVariacion");
        }
        totalPorcentaje = auxTotal;
    }
    
    public float getTotalPorcentaje(){
        return totalPorcentaje;
    }
}
