package org.example;

import java.sql.*;

public class testjdbc {
    public static void main(String[] args) throws SQLException {
        Connection cont = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Market", "postgres","1111");

        String topClientesFacturas = 
            "select cl.nro_cedula as cliente, count(*) as CantFacturas " +
            "from cliente cl join factura f on cl.id = f.cliente_id " +
            "group by cliente order by cantfacturas desc";

        String topClientesGastaron = 
            "select cl.nro_cedula as cliente, round(coalesce(sum(fd.cantidad * p.costo ), 0)) as totalGastado " +
            "from cliente cl join factura f on cl.id = f.cliente_id " +
            "join factura_detalle fd on f.id = fd.factura_id " +
            "join producto p on fd.producto_id = p.id " +
            "group by cliente order by totalgastado desc";

        String topMonedasUsadas = 
            "select m.nombre, coalesce(count(*),0) as veces_usada " +
            "from moneda m join factura f on m.id = f.moneda_id " +
            "group by m.nombre order by veces_usada desc";

        String topProveedorProductos = 
            "select p.nombre as Proveedor, coalesce(count(pr.proveedor_id ),0) as veces_proveidas " +
            "from proveedor p join producto pr on p.id = pr.proveedor_id " +
            "group by proveedor order by veces_proveidas desc";

        String productosMasVendidos = 
            "select p.nombre as producto, coalesce(count(*),0) as veces_comprado " +
            "from producto p join factura_detalle fd on p.id = fd.producto_id " +
            "group by producto order by veces_comprado desc";

        String productosMenosVendidos = 
            "select p.nombre as producto, coalesce(count(*),0) as veces_comprado " +
            "from producto p join factura_detalle fd on p.id = fd.producto_id " +
            "group by producto order by veces_comprado asc";

        String detalleFacturaEspecifica = 
            "select fa.fecha_emision, concat(cl.nombre, ' ', cl.apellido ) as cliente, ft.nombre as tipo_de_factura, " +
            "p.nombre as producto, round(fd.cantidad ) as cant_comprada " +
            "from factura fa join cliente cl on fa.cliente_id = cl.id " +
            "join factura_tipo ft on fa.factura_tipo_id = ft.id " +
            "join factura_detalle fd on fa.id = fd.factura_id " +
            "join producto p on fd.producto_id = p.id where fa.id = 1";

        String montosFacturas = 
            "select f.id, round(coalesce(sum(fd.cantidad * p.costo ),0)) as total " +
            "from factura f join factura_detalle fd on f.id = fd.factura_id " +
            "join producto p on fd.producto_id = p.id group by f.id order by total desc";

        String ivaFacturas = 
            "select f.id, round(coalesce(sum(fd.cantidad * p.costo), 0)) as subtotal, " +
            "round(coalesce(sum(fd.cantidad * p.costo), 0)) * 0.10 as iva_10, " +
            "round(coalesce(sum(fd.cantidad * p.costo), 0)) * 1.10 as total_con_iva " +
            "from factura f join factura_detalle fd on f.id = fd.factura_id " +
            "join producto p on fd.producto_id = p.id group by f.id order by subtotal desc";

        }
    }

