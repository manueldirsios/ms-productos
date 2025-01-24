package com.gd.productos.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.gd.productos.entities.Producto;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class ProductoRepository {

    private final DynamoDbTable<Producto> productoTable;

    public ProductoRepository(DynamoDbEnhancedClient enhancedClient) {
        this.productoTable = enhancedClient.table("Producto", TableSchema.fromBean(Producto.class));
    }

    public void save(Producto productoDynamo) {
        productoTable.putItem(productoDynamo);
    }

    public Optional<Producto> findById(long id) {
        return Optional.ofNullable(productoTable.getItem(r -> r.key(k -> k.partitionValue(id))));
    }

    public void deleteById(long id) {
        productoTable.deleteItem(r -> r.key(k -> k.partitionValue(id)));
    }
    

 // Verificar si existe por ID
    public boolean existsById(long id) {
        return findById(id).isPresent();
    }

    // Verificar si existe por Nombre
    public boolean existsByNombre(String nombre) {
        return findByNombre(nombre).isPresent();
    }

    // Encontrar por Nombre
    public Optional<Producto> findByNombre(String nombre) {
        return productoTable.scan().items().stream()
                .filter(producto -> nombre.equals(producto.getNombre()))
                .findFirst();
    }

    // Encontrar todos los productos
    public List<Producto> findAll() {
        List<Producto> productoDynamos = new ArrayList<>();
        productoTable.scan().items().forEach(productoDynamos::add);
        return productoDynamos;
    }
    
    // Guardar múltiples productos
    public void saveAll(List<Producto> productoDynamos) {
        productoDynamos.forEach(productoTable::putItem);
    }
}