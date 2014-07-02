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
import utn.tadp.g5.objetos.TramoCombinado
import utn.tadp.g5.objetos.TramoSimple
import utn.tadp.g5.objetos.TramoSimple

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
	
	def getTren1()={
	  val tren = new Tren("Sarmiento",List(new Direccion("Rivadavia", 2000), new Direccion("Rivadavia", 3000),
	      new Direccion("Rivadavia", 4000)))
	  tren.addPrecio(1, 2)
	  tren.addPrecio(2, 5)
	  tren
	}
	
	def getTren2()={
	  val tren = new Tren("Sarmiento",List(new Direccion("Rivadavia", 3000), new Direccion("Rivadavia", 4500),
	      new Direccion("Rivadavia", 7000)))
	  tren.addPrecio(1, 3)
	  tren.addPrecio(2, 7)
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
	
	def assertTiempoTramoCompuesto(tramo1:TramoSimple, tramo2:TramoSimple, tiempo:Double)={
	  val tramo = new TramoCombinado(tramo1, tramo2)
	  assertEquals(tiempo, tramo.tiempoTramo(), 0.0)	  	  
	}
  
	def assertCostoTramoCompuesto(tramo1:TramoSimple, tramo2:TramoSimple, costo:Double)={
	  val tramo = new TramoCombinado(tramo1, tramo2)
	  assertEquals(costo, tramo.costoTramo(), 0.0)	  	  
	}
	
	@Test
	def unTramoSimpleDeSubteSale450()={
	  assertCostoTramoSimple(new Direccion("Rivadavia", 14000), this.getSubte(), new Direccion("Rivadavia", 12000), 4.5)
	}
	
	@Test def unTramoSimpleDeTrenSaleLoMismoQueLaTabla()={
	  assertCostoTramoSimple(new Direccion("Rivadavia", 2000), this.getTren1(), new Direccion("Rivadavia", 3000), 2)
	}
	
	@Test def unTramoSimpleDeColectivoSaleEnBaseASuKm()={
	  assertCostoTramoSimple(new Direccion("Rivadavia", 2000), this.getColectivo(), new Direccion("Rivadavia", 7000), 2.75)
	}
	
	@Test def unTramoSimpleDeSubteTardaEnBaseALasEstaciones()={
	 assertTiempoTramoSimple(new Direccion("Rivadavia", 14000), this.getSubte(), new Direccion("Rivadavia", 12000), 6)
	}
	
	@Test def unTramoSimpleDeTrenTardaEnBaseALasEstaciones()={
	 assertTiempoTramoSimple(new Direccion("Rivadavia", 3000), this.getTren1(), new Direccion("Rivadavia", 2000), 3)
	}
	
	@Test def unTramoSimpleDeColectivoTarndaEnBaseASuKm()={
	 assertTiempoTramoSimple(new Direccion("Rivadavia", 2000), this.getColectivo(), new Direccion("Rivadavia", 7000), 20)
	}
	
	@Test def unTramoSubteSubteCuesta450()={
	  val tramo1 = new TramoSimple(new Direccion("Rivadavia", 14000), this.getSubte(), new Direccion("Rivadavia", 12000))
	  val tramo2 = new TramoSimple(new Direccion("Rivadavia", 12500), this.getSubte(), new Direccion("Rivadavia", 13000))
	  assertCostoTramoCompuesto(tramo1, tramo2, 4.5)
	}
	
	@Test def unTramoSubteSubteTardaLaSumaDeAmbosMasElTiempoCombinacion()={
	  val tramo1 = new TramoSimple(new Direccion("Rivadavia", 14000), this.getSubte(), new Direccion("Rivadavia", 12000))
	  val tramo2 = new TramoSimple(new Direccion("Rivadavia", 12500), this.getSubte(), new Direccion("Rivadavia", 13000))
	  assertTiempoTramoCompuesto(tramo1, tramo2, 2+6+4)
	}
	
	@Test def unTramoTrenTrenCuestaLaSumaDeAmbos()={
	  val tramo1 = new TramoSimple(new Direccion("Rivadavia", 3000), this.getTren1(), new Direccion("Rivadavia", 2000))
	  val tramo2 = new TramoSimple(new Direccion("Rivadavia", 4500), this.getTren2(), new Direccion("Rivadavia", 7000))
	  assertCostoTramoCompuesto(tramo1, tramo2, 2 + 3)
	}
	
	@Test def unTramoTrenTrenTardaLaSumaDeAmbosMasElTiempoCombinacion()={
	  val tramo1 = new TramoSimple(new Direccion("Rivadavia", 3000), this.getTren1(), new Direccion("Rivadavia", 2000))
	  val tramo2 = new TramoSimple(new Direccion("Rivadavia", 4500), this.getTren2(), new Direccion("Rivadavia", 7000))
	  val costoCombinado = tramo1.tiempoTramo() + 6 + tramo2.tiempoTramo()
	  assertTiempoTramoCompuesto(tramo1, tramo2, 6+3+3)
	}
	
	@Test def unTramoColectivoColectivoCuestaLaSumaDeAmbos()={
	  val tramo1 = new TramoSimple(new Direccion("Rivadavia", 3000), this.getColectivo(), new Direccion("Rivadavia", 2000))
	  val tramo2 = new TramoSimple(new Direccion("Rivadavia", 1700), this.getColectivo(), new Direccion("Rivadavia", 700))
	  val costoCombinado = tramo1.costoTramo() + tramo2.costoTramo()
	  assertCostoTramoCompuesto(tramo1, tramo2, costoCombinado)
	}
	
	@Test def unTramoColectivoColectivoTardaLaSumaDeAmbosMasElTiempoCombinacion()={
	  val tramo1 = new TramoSimple(new Direccion("Rivadavia", 3000), this.getColectivo(), new Direccion("Rivadavia", 2000))
	  val tramo2 = new TramoSimple(new Direccion("Rivadavia", 1700), this.getColectivo(), new Direccion("Rivadavia", 700))
	  assertTiempoTramoCompuesto(tramo1, tramo2, tramo1.tiempoTramo() +tramo2.tiempoTramo() + 7.5)
	}
}