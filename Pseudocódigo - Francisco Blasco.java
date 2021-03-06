/// Pseudocódigo - Francisco Blasco

class PrendaNueva {
  int precioFinal (precio) {
      return precio;
  }
}

class PrendaPromo {
  Float valorFijo;

  Float precioFinal (precio) {
      return precio - this.valorFijo;
  }
}

class PrendaLiquidacion {
  Float precioFinal (precio) {
      return (float) precio - precio * 0.5;
  }
}

class Prenda {
    Float precioBase;
    Integer cantidad;
    String tipo;
    var estado;
  
    Float calcularPrecio () {
        return estado.precioFinal(this.precioBase * this.cantidad);
    }
  }


class Venta {
  List<Prenda> prendas = new ArrayList();
  var formaPago;
  Date fechaVenta;
 
  Float calcularImporte () {
    return this.prendas.Stream()
        .reduce(0, (a,b) -> a.calcularPrecio() + b.calcularPrecio()) + this.formaPago.calcularRecargo(this.prendas);
  }
 
}

class VentaTarjeta {
    Integer cantCuotas;
    Float coefFijo;
 
    Float calcularRecargo (List <Prenda> prendas) {
        return this.cantCuotas * this.coefFijo + this.prendas.Stream().reduce(0, (a,b) -> a.calcularPrecio() + b.calcularPrecio()) * 0.01
    }
 }
 
 class VentaEfectivo {
    int calcularRecargo () {
        return 0;
    }
 }

class MacOwins {
  List <Venta> ventas = new ArrayList();   

  Float calcularGanancias (Date fecha) {
        return this.ventas.Stream()
            .filter(v -> v.fechaVenta.equals(fecha))
            .reduce(0, (a,b) -> a.calcularImporte() + b.calcularImporte());
  }
}
