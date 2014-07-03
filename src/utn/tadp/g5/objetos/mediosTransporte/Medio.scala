package utn.tadp.g5.objetos.mediosTransporte

import utn.tadp.g5.objetos.Direccion
import utn.tadp.g5.objetos.ModuloExterno
import utn.tadp.g5.objetos.Tramo

trait Medio { 	
  
    def getLinea(): Any = {}    
    def getDescripcion():Any = {}
    def costoCombinacion(medio:Medio,direccionSalida:Direccion, direccionLlegada:Direccion):Double
    def tiempoCombinacion(direccionCombinacion:Direccion, medio:Medio, direccionSalida:Direccion):Double
    def costoPara(direccionSalida:Direccion, direccionLlegada:Direccion):Double
    def tiempoPara(direccionSalida:Direccion, direccionLlegada:Direccion):Double
    
    def costoCombinacionColectivo(direccionSalida:Direccion, direccionLlegada:Direccion) = this.costoPara(direccionSalida, direccionLlegada)
    def costoCombinacionTren(direccionSalida:Direccion, direccionLlegada:Direccion) = this.costoPara(direccionSalida, direccionLlegada)
    def costoCombinacionSubte(direccionSalida:Direccion, direccionLlegada:Direccion) = this.costoPara(direccionSalida, direccionLlegada)
    
    def tiempoCombinacionColectivo(direccionCombinacion : Direccion, direccionSalida:Direccion) = this.tiempoCombinacionBase(direccionCombinacion, direccionSalida)
    def tiempoCombinacionSubte(direccionCombinacion : Direccion, direccionSalida:Direccion) = this.tiempoCombinacionBase(direccionCombinacion, direccionSalida)
    def tiempoCombinacionTren(direccionCombinacion : Direccion, direccionSalida:Direccion) = this.tiempoCombinacionBase(direccionCombinacion, direccionSalida)
    
    def tiempoCombinacionBase(direccionCombinacion:Direccion, direccionLlegada:Direccion) = {
      ModuloExterno.consultarDistanciaPie(direccionCombinacion, direccionLlegada) * 25.0
    }
            
}