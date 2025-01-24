package com.gd.productos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gd.productos.entities.Producto;
import com.gd.productos.entities.response.GenericResponse;
import com.gd.productos.exceptions.NoFoundException;
import com.gd.productos.repository.ProductoRepository;

@Service
public class ProductoService {

   private  ProductoRepository productoRepositoryDynamo;
	ProductoService( ProductoRepository productoRepositoryDynamo){
		this.productoRepositoryDynamo=productoRepositoryDynamo;
	}


    public GenericResponse  lista(){
    	GenericResponse response=new GenericResponse();
        List<Producto> lista =  productoRepositoryDynamo.findAll();
        response.setTransaccion(lista);
        return response;
    }

    public GenericResponse getById(long id) throws NoFoundException{
    	GenericResponse response=new GenericResponse();
    	Producto producto=productoRepositoryDynamo.findById(id).orElseThrow(() -> new NoFoundException(404,"Producto no encontrado"));
    	response.setTransaccion(producto);
        return response ;
    }

    public Optional<Producto> getByNombre(String nombre){
        return productoRepositoryDynamo.findByNombre(nombre);
    }

    public GenericResponse save(Producto productoDynamo){
       	GenericResponse response=new GenericResponse();
        productoRepositoryDynamo.save(productoDynamo);
        response.setTransaccion(true);
       return response;
    }

    public void delete(long id){
        productoRepositoryDynamo.deleteById(id);
    }

    public boolean existsId(long id){
        return productoRepositoryDynamo.existsById(id);
    }

    public boolean existsNombre(String nombre){
        return productoRepositoryDynamo.existsByNombre(nombre);
    }
    


}