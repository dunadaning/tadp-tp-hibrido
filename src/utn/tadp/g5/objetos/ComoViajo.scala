package utn.tadp.g5.objetos

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap
import utn.tadp.g5.objetos.mediosTransporte.Medio
import utn.tadp.g5.objetos.criterios.Criterio
import scala.collection.mutable.ListBuffer

class ComoViajo {

  def consultar(parametrosDeViaje:ParametrosDeViaje, criterio:Criterio) : Viaje = {
    
    var viaje = new Viaje()    
    var transporteCercanosInicio = ArrayBuffer[Cercano]()     
    var transporteCercanosFin = ArrayBuffer[Cercano]()
    
    transporteCercanosInicio = obtenerTransportesCercanosEn(parametrosDeViaje.origen)
    transporteCercanosFin = obtenerTransportesCercanosEn(parametrosDeViaje.destino)

    viaje.recorridos = calcularRecorridos(transporteCercanosInicio, transporteCercanosFin, criterio)
    viaje.calcularDuraciones()
    viaje.calcularCostos()
    
    return  viaje
        
  }
    
  def calcularRecorridos(tInicio:ArrayBuffer[Cercano], tFin:ArrayBuffer[Cercano], criterio:Criterio): List[Recorrido] = {    
    var recorridos = ListBuffer[Recorrido]()
    
    recorridos = fusionarRecorridos(calcularDirecto(tInicio, tFin), calcularCombinado(tInicio, tFin))
       
    recorridos.toList
    
  }

  def fusionarRecorridos(recorridosA: ListBuffer[Recorrido], recorridosB:ListBuffer[Recorrido]):ListBuffer[Recorrido]={
    var recorridosFusionados = ListBuffer[Recorrido]()
    recorridosFusionados = recorridosA
    
    recorridosFusionados.appendAll(recorridosB)
    
    recorridosFusionados
  }
  
  def calcularDirecto(tInicio:ArrayBuffer[Cercano], tFin:ArrayBuffer[Cercano]): ListBuffer[Recorrido] = {    
    var recorrido : Recorrido = null
    val recorridos = ListBuffer[Recorrido]()  
    var ruta = ListBuffer[Transporte]()
    var rutaFiltrada = List[Transporte]()
    
    //CORRECCION: map en lugar de foreach - cambio foreach por foldLeft
    //SE UTILIZA UN MAP
    //tInicio.foldLeft(recorrido)((recorrido: Recorrido, cercano: Cercano) => this.obtenerTransporte(cercano, tFin, recorrido))
    tInicio.map(cercano => ruta += obtenerTransporte(cercano, tFin))
    rutaFiltrada = ruta.toList.filter(e => e!=null)
    
    rutaFiltrada.map{transporte => 
        recorrido = new Recorrido()
        recorrido.ruta = List(transporte)
        recorridos += recorrido}
         
    //recorridos += recorrido 
    //var r = recorridos.toList
    recorridos
  }
  
  def obtenerTransporte(inicio: Cercano, transportesFin: ArrayBuffer[Cercano]): Transporte = {
        
    val cercano = buscarMedioQuePasaPorDestino(inicio.medio, transportesFin)
    var transporte:Transporte = null
    
    if (!cercano.equals(None)){       
       
      transporte = new Transporte(inicio.medio, inicio.direccion, cercano.get.direccion)                
      
      }
    
    transporte
  }

  //Por cada medio cercano del inicio verifico si combina con cada medio cercano del fin, y se agrega la combinacion
  def calcularCombinado(tInicio:ArrayBuffer[Cercano], tFin:ArrayBuffer[Cercano]): ListBuffer[Recorrido] = {     
    var combinados = new ListBuffer[Recorrido]
    var transporteA = new Transporte()
    var transporteB = new Transporte()
    var direccion:Direccion = null
    var ruta : ListBuffer[Transporte] = null
    val ti = tInicio.toArray
    val tf = tFin.toArray

    for (i <- 0 until ti.size){
     for (p <- 0 until tf.size){
       direccion = ModuloExterno.consultarCombinacion(ti(i).medio, tf(p).medio)
       if (direccion!=null){
         var recorridos = new Recorrido()
         //recorridos.ruta = List[Transporte]()
         ruta = ListBuffer[Transporte]()
         transporteA = new Transporte(ti(i).medio, ti(i).direccion, direccion) 
         transporteB = new Transporte(tf(p).medio, direccion, tf(p).direccion) 
         ruta += transporteA
         ruta += transporteB  
         recorridos.ruta = ruta.toList
         combinados += recorridos
       }
     }              
    }
    
    return combinados
  }
  
  def obtenerTransportesCercanosEn(direccion:Direccion): ArrayBuffer[Cercano] = {    
    ModuloExterno.consultarCercanos(direccion)
  }

  //CORRECCION: pasaPorDestino no expresa lo que hace.
  //SE MODIFICA EL NOMBRE
  def buscarMedioQuePasaPorDestino(myTransportIda:Medio, transporteCercanosFin:ArrayBuffer[Cercano]) : Option[Cercano] = {
	  transporteCercanosFin.find(cercano => cercano.elMedioCoinideCon(myTransportIda))
  }
  
  def consultar(viaje:ParametrosDeViaje) : Viaje = {
    this.consultar(viaje, null)
  }
  
}