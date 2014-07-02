package test

import org.junit.Test
import org.junit.Assert._
import utn.tadp.g5.objetos.mediosTransporte.Subte
import utn.tadp.g5.objetos.Direccion
import utn.tadp.g5.objetos.TramoSimple
import utn.tadp.g5.objetos.mediosTransporte.Tren
import utn.tadp.g5.objetos.mediosTransporte.Colectivo
import utn.tadp.g5.objetos.ModuloExterno
import utn.tadp.g5.objetos.mediosTransporte.Medio

class TramoTest {
	def getSubte()={
	  new Subte('A', List(new Direccion("Rivadavia", 12000),
	      new Direccion("Rivadavia", 12500),
	      new Direccion("Rivadavia", 13000),
	      new Direccion("Rivadavia", 14000)))
	}
	
	def getColectivo()={
	  new Colectivo(113)
	}
	
	def getTren()={
	  val tren = new Tren("Sarmiento",List(new Direccion("Rivadavia", 2000), new Direccion("Rivadavia", 3000),
	      new Direccion("Rivadavia", 4000)))
	  tren.addPrecio(1, 2)
	  tren.addPrecio(2, 5)
	  tren
	}
	
	def assertCostoTramoSimple(direccionSalida:Direccion, medio:Medio, direccionLlegada:Direccion, costo:Double)={
	  val tramo = new TramoSimple(direccionSalida, medio, direccionLlegada)
	  assertEquals(costo, tramo.costoTramo(), 0.0)
	}
	
	def assertTiempoTramoSimple(direccionSalida:Direccion, medio:Medio, direccionLlegada:Direccion, costo:Double)={
	  val tramo = new TramoSimple(direccionSalida, medio, direccionLlegada)
	  assertEquals(costo, tramo.tiempoTramo(), 0.0)	  
	}
  
	@Test
	def unTramoSimpleDeSubteSale450()={
	  assertCostoTramoSimple(new Direccion("Rivadavia", 14000), this.getSubte(), new Direccion("Rivadavia", 12000), 4.5)
	}
	
	@Test def unTramoSimpleDeTrenSaleLoMismoQueLaTabla()={
	  assertCostoTramoSimple(new Direccion("Rivadavia", 2000), this.getTren(), new Direccion("Rivadavia", 3000), 5)
	}
	
	@Test def unTramoSimpleDeColectivoSaleEnBaseASuKm()={
	  assertCostoTramoSimple(new Direccion("Rivadavia", 2000), this.getColectivo(), new Direccion("Rivadavia", 7000), 2.75)
	}
	
	@Test def unTramoSimpleDeSubteTardaEnBaseALasEstaciones()={
	 assertTiempoTramoSimple(new Direccion("Rivadavia", 14000), this.getSubte(), new Direccion("Rivadavia", 12000), 6)
	}
	
	@Test def unTramoSimpleDeTrenTardaEnBaseALasEstaciones()={
	 assertTiempoTramoSimple(new Direccion("Rivadavia", 3000), this.getTren(), new Direccion("Rivadavia", 2000), 3)
	} 
}