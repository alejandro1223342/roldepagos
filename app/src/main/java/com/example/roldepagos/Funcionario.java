package com.example.roldepagos;

public class Funcionario {

    private int id;
    private String nombre;
    private String cargo;
    private String area;
    private int numHijos;
    private String estadoCivil;
    private int subsidio;
    private double descuentoAtrasos;
    private int horasExtras;
    private double sueldoRecibir;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getNumHijos() {
        return numHijos;
    }

    public void setNumHijos(int numHijos) {
        this.numHijos = numHijos;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public int getSubsidio() {
        return subsidio;
    }

    public void setSubsidio(int subsidio) {
        this.subsidio = subsidio;
    }

    public double getDescuentoAtrasos() {
        return descuentoAtrasos;
    }

    public void setDescuentoAtrasos(double descuentoAtrasos) {
        this.descuentoAtrasos = descuentoAtrasos;
    }

    public int getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(int horasExtras) {
        this.horasExtras = horasExtras;
    }

    public double getSueldoRecibir() {
        return sueldoRecibir;
    }

    public void setSueldoRecibir(double sueldoRecibir) {
        this.sueldoRecibir = sueldoRecibir;
    }
}
