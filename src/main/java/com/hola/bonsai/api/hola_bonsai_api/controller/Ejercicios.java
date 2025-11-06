package com.hola.bonsai.api.hola_bonsai_api.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ejercicios")
public class Ejercicios {

    @GetMapping("/hola")
    public String holaMundo(){
        return "hola_mundo";
    }

    @GetMapping("/suma")
    public Integer suma(){
        return (1+1);
    }

    @GetMapping("/calculoHoras")
    public String calculoHoras(){
        int edad = 25;
        double salarioPorHora = 10000.50;
        boolean activo = true;
        String nombre = "Sergio";
        int dias =  30;
        double salario = salarioPorHora * 8.00 *30.00;
        return "Empleado:"+nombre + " Edad:"+edad+" activo:"+activo+" trabaja dias:"+dias+" y gana("+salario+")";
    }

    @GetMapping("/calificacion/{cal}")
    public String calificacion(@PathVariable String cal){
        String resultado = "";
        Float calif =   Float.parseFloat(cal) ;
        if(calif >= 90 ){
            resultado = "Excelente";
        }else if(calif >= 70 ){
            resultado = "Bien";
        }else if(calif >= 60 ){
            resultado = "Regular";
        }else if(calif <= 50 ){
            resultado = "Reprobado";
        }
        return resultado;
    }


    @GetMapping("/ciclo/{inicial}")
    public String ciclo(@PathVariable String inicial){
        String resultado = "";
        Integer calif =   Integer.parseInt(inicial) ;
        Integer count =  0;
        while (count < calif){
            resultado += "Vuelta:"+count;
            count++;
        }
        return resultado;
    }
}
