package com.proyecto.ecommerce.controlador;

import com.proyecto.ecommerce.servicio.UploadFileService;
import org.springframework.ui.Model;
import com.proyecto.ecommerce.modelo.Producto;
import com.proyecto.ecommerce.modelo.Usuario;
import com.proyecto.ecommerce.servicio.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
    @Autowired
    private ProductoService productoService;

    @Autowired
    private UploadFileService uploadImage;

    @GetMapping("")
    public String show(Model model){
       model.addAttribute("productos", productoService.listaProductos());

        return"/productos/show";
    }

    @GetMapping("/create")
    public String create(){
        return "productos/create";
    }

    @PostMapping("/save")
    public  String save(Producto producto, @RequestParam("img") MultipartFile imagen) throws IOException {
       // LOGGER.info("Este es el objeto producto {}",producto);

        Usuario u =new Usuario(1,"","","","","","","");
        producto.setUsuario(u);

        if(producto.getId()==null){ //antes del save() un producto viene con el id null
            String nombreImagen= uploadImage.saveImage(imagen);
            producto.setImagen(nombreImagen);
        }

        productoService.save(producto);

        return "redirect:/productos";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){

        Producto producto=new Producto();
        Optional<Producto> optionalProduco= productoService.getProducto(id);
        producto=optionalProduco.get();
        LOGGER.info("Producto buscado: {}", producto);
        model.addAttribute("producto", producto);

        return "productos/edit";
    }

    @PostMapping("/update")
    public String update(Producto producto, @RequestParam("img") MultipartFile imagen) throws IOException {

        if(imagen.isEmpty()){ //cuando editamos el producto pero no cambiamos la imagen
            Producto p=new Producto();
            p=productoService.getProducto(producto.getId()).get();
            producto.setImagen(p.getImagen());
        }else{
            Producto p =new Producto();
            p=productoService.getProducto(producto.getId()).get();

            //Eliminar cuando no sea la imagen por defecto
            if(!p.getImagen().equals("default.jpg")) {
                uploadImage.deleteImage(p.getImagen());
            }
            String nombreImagen= uploadImage.saveImage(imagen);
            producto.setImagen(nombreImagen);
        }


        productoService.update(producto);
        return "redirect:/productos";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){

        Producto p =new Producto();
        p=productoService.getProducto(id).get();

        //Eliminar cuando no sea la imagen por defecto
        if(!p.getImagen().equals("default.jpg")) {
        uploadImage.deleteImage(p.getImagen());
        }

        productoService.delete(id);
        return "redirect:/productos";
    }

}
