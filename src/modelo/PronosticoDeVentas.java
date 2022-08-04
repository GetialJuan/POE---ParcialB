/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * Parcial B
 * @author Juan Sebastian Getial Getial <202124644>
 * @author Mauricio Muñoz Gutierrez <202123687>
 * @profesor Luis Yovani Romo Portilla
 * Clase que contiene la logica de calcular un pronostico de ventas
 */
public class PronosticoDeVentas {
    //años - {ventas: ..., delta Y :Yn - Yn-1, porcentajeVariacion: (Yn - Yn-1)/Yn-1
    private ArrayList<HashMap<String,Float>> anios;
    private float promedioDeVariaciones;
    private float sumaPromedios;
    
    public PronosticoDeVentas(){
        anios = new ArrayList<>();
    }
    
    public void agregarAnio(float ventas){
        HashMap<String,Float> anio = new HashMap<>();
        if(anios.isEmpty()){
            anio.put("ventas", ventas);
            anio.put("deltaVentas", 0f);
            anio.put("porcentajeVariacion", 0f);
        }
        else{
            //datos del anios anterior
            int indiceAnio = anios.size() -1;
            float ventasAnterior = anios.get(indiceAnio).get("ventas");
            
            //se crean y añaden los datos del anio nuevo
            float deltaVentas = ventas - ventasAnterior;
            float porcentajeVariacion = deltaVentas/ventasAnterior;
            
            anio.put("ventas", ventas);
            anio.put("deltaVentas", deltaVentas);
            anio.put("porcentajeVariacion", porcentajeVariacion);
        }
        anios.add(anio);
        setPromedioDeVariaciones();
    }
    
    private void setPromedioDeVariaciones(){
        sumaPromedios = 0;
        float nPeriodos = anios.size() -1;
        for(HashMap<String,Float> anio : anios){
            sumaPromedios += anio.get("porcentajeVariacion");
        }
        promedioDeVariaciones = sumaPromedios/nPeriodos;
        
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
                else{
                    anio.put("deltaVentas", 0f);
                    anio.put("porcentajeVariacion", 0f);
                }
                indiceAnio++;
            }
        }
        else if(anios.size() == 1){
            anios.get(0).put("deltaVentas", 0f);
            anios.get(0).put("porcentajeVariacion", 0f);
        }
        setPromedioDeVariaciones();
    }
    
    public ArrayList<HashMap<String,Float>> getAnios(){
        return anios;
    }
    
    public void nuevoPronostico(){
        anios.clear();
        promedioDeVariaciones = 0f;
        sumaPromedios = 0f;
    }
    
    public ArrayList<Float> getPronostico(int cuantosAños) {
        if(cuantosAños < 2){
            cuantosAños = 2;
        }
        ArrayList<Float> aniosPronosticados = new ArrayList<>();
        if(anios.size() > 2){
            float ventaUltimoAnio = anios.get(anios.size()-1).get("ventas");
            while(cuantosAños > 0){
                float ventaPronosticada = ventaUltimoAnio*(1+promedioDeVariaciones);
                aniosPronosticados.add(ventaPronosticada);
                ventaUltimoAnio = ventaPronosticada;
                
                cuantosAños--;
            }
            
        }    
        return aniosPronosticados;
    }
    
    public float getSumaPromedios(){
        return sumaPromedios;
    }
}
