/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import entidades.Nombre;

/**
 *
 * @author diana
 */
public class NombreDTO {
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;

    public NombreDTO() {
    }

    public NombreDTO(String nombres, String apellidoPaterno, String apellidoMaterno) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    
     public static NombreDTO convertir(Nombre nombre) {
        if (nombre == null) {
            return null;
        }
        
        return new NombreDTO(nombre.getNombres(), nombre.getApellidoPaterno(), nombre.getApellidoMaterno());
    }
     
     public static Nombre convertir(NombreDTO nombreDTO) {
        if (nombreDTO == null) {
            return null;
        }

        Nombre nombre = new Nombre();
        nombre.setNombres(nombreDTO.getNombres());
        nombre.setApellidoPaterno(nombreDTO.getApellidoPaterno());
        nombre.setApellidoMaterno(nombreDTO.getApellidoMaterno());

        return nombre;
    }
}
