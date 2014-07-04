package test

import utn.tadp.g5.funcional.ModuloExterno
import utn.tadp.g5.objetos.Direccion
import scala.collection.mutable.ArrayBuffer
import utn.tadp.g5.funcional.Medio
import utn.tadp.g5.funcional.Tren
import utn.tadp.g5.funcional.Colectivo
import utn.tadp.g5.funcional.Subte

object ModuloExternoImplementado extends ModuloExterno{
  def consultarCercanos(direccion :Direccion):ArrayBuffer[(Medio,Direccion)]={
    val cercanos = new ArrayBuffer[(Medio,Direccion)]()
    if(direccion.calle== "Albariño" && direccion.numero == 291){
      cercanos += ((this.getColectivo1(),new Direccion("Albariño", 300)))
    }
    
    if(direccion.calle == "Rivadavia" && direccion.numero == 12800){
      cercanos += ((this.getSubte(), new Direccion("Rivadavia", 13000)))
    }
    
    if(direccion.calle == "Rivadavia" && direccion.numero == 9000){
    	cercanos += ((this.getColectivo2(), new Direccion("Rivadavia", 8900)))      
    }
    
    if(direccion.calle == "Medrano" && direccion.numero == 900){
      cercanos += ((this.getColectivo1(), new Direccion("Medrano", 890)))
      cercanos += ((this.getTren2(), new Direccion("Medrano", 910)))
    }
    
    cercanos
  }
  
  def combinan(medioA :Medio, medioB: Medio)={
    var combinan = false
    if((medioA.getLinea() == "1" && medioB.getLinea() == "A")){
      combinan = true
    }
        
    if((medioA.getLinea() == "Sarmiento" && medioB.getLinea() == "113")){      
      combinan = true
    }    

    if((medioB.getLinea() == "Sarmiento" && medioA.getLinea() == "113")){
      combinan = true
    }    
    
    combinan    
  }
  
  def consultarCombinacion(medioA:Medio, medioB:Medio)={
    var direccionCombinacion:Direccion = null
    if((medioA.getLinea() == "1" && medioB.getLinea() == "A")){
    	direccionCombinacion = new Direccion("Rivadavia",12000)
    }
    
    if((medioA.getLinea() == "Sarmiento" && medioB.getLinea() == "113")){
       direccionCombinacion = new Direccion("Medrano", 700)
    }    

    if((medioB.getLinea() == "Sarmiento" && medioA.getLinea() == "113")){
       direccionCombinacion = new Direccion("Medrano",910)
    }    
    
    direccionCombinacion
  }
  def consultarDistanciaPie(direccionLlegada:Direccion, direccionSalida:Direccion)={
    (direccionLlegada.numero + direccionSalida.numero) / 10.0
  }
  def consultarDistanciaColectivo(direccionLlegada:Direccion, direccionSalida:Direccion)={
    (direccionLlegada.numero + direccionSalida.numero) / 100.0
  }
  
  def getSubte()={
	  new Subte("A", List(new Direccion("Rivadavia", 12000),
	      new Direccion("Rivadavia", 12500),
	      new Direccion("Rivadavia", 13000),
	      new Direccion("Rivadavia", 14000)))
	}
	
	def getColectivo1()={
	  new Colectivo("113")
	}
	
	def getColectivo2()={
	  new Colectivo("1")
	}
	
	def getTren1()={
	  val tren = new Tren("Roca",List(new Direccion("Rivadavia", 2000), new Direccion("Rivadavia", 3000),
	      new Direccion("Rivadavia", 4000)))
	  tren.addPrecio(1, 2)
	  tren.addPrecio(2, 5)
	  tren
	}
	
	def getTren2()={
	  val tren = new Tren("Sarmiento",List(new Direccion("Medrano", 700), new Direccion("Medrano", 910),
	      new Direccion("Medrano", 1000)))
	  tren.addPrecio(1, 3)
	  tren.addPrecio(2, 7)
	  tren
	}
}