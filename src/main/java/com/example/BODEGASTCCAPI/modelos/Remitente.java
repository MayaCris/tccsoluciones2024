package com.example.BODEGASTCCAPI.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "remitentes")
public class Remitente {

    //id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //nombres
    @Column(name = "nombres", nullable = false, length = 50)
    private String nombres;

    //depto
    @Column(name = "departamento", nullable = false, length = 50)
    private String departamento;

    //municipio
    @Column(name = "municipio", nullable = false, length = 50)
    private String municipio;

    //direccion
    @Column(name = "direccion", nullable = false, length = 50)
    private String direccion;

    //metodopago
    @Column(name = "metodo_pago", nullable = false, length = 50)
    private String metodoPago;


    public Remitente() {
    }

    public Remitente(Long id, String nombres, String departamento, String municipio, String direccion, String metodoPago) {
        this.id = id;
        this.nombres = nombres;
        this.departamento = departamento;
        this.municipio = municipio;
        this.direccion = direccion;
        this.metodoPago = metodoPago;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
}
