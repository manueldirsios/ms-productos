package com.gd.productos.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.gd.productos.entities.Producto;
import com.gd.productos.entities.response.GenericResponse;
import com.gd.productos.exceptions.NoFoundException;
import com.gd.productos.service.ProductoService;

@RestController
@RequestMapping("/producto")
@CrossOrigin(origins = "*")
@EnableWebMvc
public class ProductoController {


   private  ProductoService productoService;

    ProductoController (ProductoService productoService){
    	this.productoService=productoService;
    }
    @GetMapping("/lista")
    public ResponseEntity<GenericResponse> lista(){
        return new ResponseEntity<>(productoService.lista(), HttpStatus.OK);
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<GenericResponse> detalle(@PathVariable("id") long id) throws NoFoundException{
        return new ResponseEntity<>( productoService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<GenericResponse> crear(@RequestBody Producto productoDynamo){
        return new ResponseEntity<>(productoService.save(productoDynamo), HttpStatus.CREATED);
    }
    
    @PutMapping("/actualizar")
    public ResponseEntity<GenericResponse> actualizar(@RequestBody Producto productoDynamo){
        return new ResponseEntity<>(productoService.save(productoDynamo), HttpStatus.CREATED);
    }
}