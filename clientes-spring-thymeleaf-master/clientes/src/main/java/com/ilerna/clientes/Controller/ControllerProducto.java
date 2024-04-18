package com.ilerna.clientes.Controller;
import com.ilerna.clientes.entity.Cliente;
import com.ilerna.clientes.entity.Producto;
import com.ilerna.clientes.service.GestorClientes;
import com.ilerna.clientes.service.GestorProductos;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/producto")
public class ControllerProducto {
    GestorProductos gp = new GestorProductos ();
    @GetMapping("/")
    public String crud(Model model){
        String valorfinal="./producto/listarproducto";
        try {
            model.addAttribute("productos", gp.listar());
        } catch (SQLException ex) {
            Logger.getLogger(ControllerProducto.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal="error";
        }
        return valorfinal;
    }
    @GetMapping("/alta")
    public String greetingForm(Model model) {
        model.addAttribute("producto", new Producto());
        return "./producto/altaproducto";
    }
    @PostMapping("/alta")
    public String greetingSubmit(@ModelAttribute Producto producto, Model model) {
        String valorfinal="redirect:/producto/";
        try {
            gp.alta(producto);
            try {
                model.addAttribute("productos", gp.listar());
            } catch (SQLException ex) {
                Logger.getLogger(ControllerProducto.class.getName()).log(Level.SEVERE, null, ex);
                valorfinal="error";
            }
        } catch (SQLException ex) {
            valorfinal="error";
        }
        return valorfinal;
    }
    @GetMapping("/eliminar")
    public String SubmitB (@RequestParam("codproducto") int id, Model model){
        String valorfinal="redirect:/producto/";
        try {
            gp.eliminar(id);
            model.addAttribute("productos", gp.listar());
        } catch (SQLException ex) {
            valorfinal="error";
        }
        //return new ModelAndView(valorfinal, model.asMap());
        return valorfinal;
    }
    @GetMapping("/modificar")
    public String modificar(@RequestParam ("codproducto") int id,Model model){
        String valorfinal="./producto/modificarproducto";
        try {
            model.addAttribute("producto", gp.buscar(id));
        } catch (SQLException ex) {
            Logger.getLogger(ControllerProducto.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal="error";
        }
        return valorfinal;
    }
    @PostMapping("/modificar")
    public String modificarBBDD (@ModelAttribute Producto producto, Model model){
        String valorfinal="redirect:/producto/";
        try {
            gp.modificar(producto);
            model.addAttribute("productos",gp.listar());
        } catch (SQLException ex) {
            Logger.getLogger(ControllerProducto.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal="error";
        }
        return valorfinal;
    }
}

