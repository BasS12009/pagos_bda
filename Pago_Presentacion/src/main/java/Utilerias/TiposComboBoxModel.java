/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilerias;

import javax.swing.DefaultComboBoxModel;
import java.util.List;

/**
 *
 * @author PC Gamer
 */

public class TiposComboBoxModel extends DefaultComboBoxModel<String> {

    public TiposComboBoxModel(List<DTOs.TiposDTO> data) {
        for (DTOs.TiposDTO tipo : data) {
            addElement(tipo.getNombre() + " " + tipo.getNumeroParcialidades() + " Pagos");
        }
    }
}