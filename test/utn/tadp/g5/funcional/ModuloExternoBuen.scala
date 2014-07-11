package utn.tadp.g5.funcional

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
    
    if(direccion.calle== "Albarinio" && direccion.numero == 291){
      cercanos += ((this.getColectivo1(),new Direccion("Albarinio", 300)))
    }
    
    if(direccion.calle== "Albarinio" && direccion.numero == 600){
      cercanos += ((this.getColectivo1(),new Direccion("Albarinio", 600)))
    }
    
    if(direccion.calle == "Rivadavia" && direccion.numero == 12800){
      cercanos += ((this.getSubte(), new Direccion("Rivadavia", 13000)))
    }
    
    if(direccion.calle == "Rivadavia" && direccion.numero == 9000){
    	cercanos += ((this.getColectivo2(), new Direccion("Rivadavia", 8900)))      
    }
    
    if(direccion.calle == "Medrano" && direccion.numero == 900){
      cercanos += ((this.getColectivo1(), new Direccion("Medrano", 890)))
    }
    
    if(direccion.calle == "Rivadavia" && direccion.numero == 600){
    	cercanos += ((this.getTren2(), new Direccion("Rivadavia", 700)))      
    }
    
    if(direccion.calle == "Rivadavia" && direccion.numero == 3100){
    	cercanos += ((this.getTren1(), new Direccion("Rivadavia", 3000)))      
    }
    
    if(direccion.calle == "Carabobo" && direccion.numero == 2000){
      cercanos += ((this.getColectivoCarabobo(), new Direccion("Carabobo", 2000)))      
	  cercanos += ((this.getSubteCarabobo(), new Direccion("Carabobo", 2000)))      
    }

    if(direccion.calle == "Carabobo" && direccion.numero == 4600){
      cercanos += ((this.getColectivoCarabobo(), new Direccion("Carabobo", 4600)))      
 	  cercanos += ((this.getSubteCarabobo(), new Direccion("Carabobo", 4500)))      
    }    
    
    cercanos
  }
  
  def combinan(medioA :Medio, medioB: Medio)={
    var combinan = false
    if((medioA.getLinea() == "1" && medioB.getLinea() == "A")){
      combinan = true
    }
        
    if((medioA.getLinea() == "Sarmiento" && medioB.getLinea() == "Roca")){      
      combinan = true
    }    

    if((medioB.getLinea() == "Sarmiento" && medioA.getLinea() == "Roca")){
      combinan = true
    }    
    
    combinan    
  }
  
  def consultarCombinacion(medioA:Medio, medioB:Medio)={
    var direccionCombinacion:Direccion = null
    if((medioA.getLinea() == "1" && medioB.getLinea() == "A")){
    	direccionCombinacion = new Direccion("Rivadavia",12000)
    }
    
    if((medioA.getLinea() == "Sarmiento" && medioB.getLinea() == "Roca")){
       direccionCombinacion = new Direccion("Rivadavia", 2000)
    }    

    if((medioB.getLinea() == "Sarmiento" && medioA.getLinea() == "Roca")){
       direccionCombinacion = new Direccion("Rivadavia", 2000)
    }    
    
    direccionCombinacion
  }
 
  def consultarDistanciaPie(direccionLlegada:Direccion, direccionSalida:Direccion):Double={
   if(direccionLlegada.calle == direccionSalida.calle ){
      return (direccionSalida.numero - direccionLlegada.numero).abs /1000.0
   }
   return 50000.0    
  } 
 
  def consultarDistanciaColectivo(direccionLlegada:Direccion, direccionSalida:Direccion):Double={
   if(direccionLlegada.calle == direccionSalida.calle ){
      return (direccionSalida.numero - direccionLlegada.numero).abs /1000.0
    }
    
    return 5000.0   
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
	  val tren = new Tren("Sarmiento",List(new Direccion("Rivadavia", 700), new Direccion("Rivadavia", 910),
	      new Direccion("Rivadavia", 2000)))
	  tren.addPrecio(1, 3)
	  tren.addPrecio(2, 7)
	  tren
	}
	
	def getColectivoCarabobo()={
	  new Colectivo("8")
	}
	
	def getSubteCarabobo()={
	   new Subte("A", List(new Direccion("Carabobo", 2000),
	     new Direccion("Carabobo", 4500),
	     new Direccion("Carabobo", 7000),
	     new Direccion("Carabobo", 7500))) 
	}
}