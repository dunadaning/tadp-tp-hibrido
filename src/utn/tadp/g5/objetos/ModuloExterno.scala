package utn.tadp.g5.objetos

import scala.collection.mutable.ArrayBuffer
import utn.tadp.g5.objetos.mediosTransporte.Subte
import utn.tadp.g5.objetos.mediosTransporte.Medio
import utn.tadp.g5.objetos.mediosTransporte.Colectivo
import scala.collection.mutable.ListBuffer
import utn.tadp.g5.objetos.tarjetas.Zona

object ModuloExterno {
  
  //SOLO PARA PRUEBAS, SIMULO MODULO EXTERNO!!!!
  
  def consultarCercanos(direccion:Direccion) : ArrayBuffer[Cercano] = {
    var respuesta = ArrayBuffer[Cercano]()    
    
    if (direccion.calle.equals("Avellaneda")){
      
      var transporteA = new Cercano(new Colectivo(15, ""), new Direccion("Avellaneda",1))    
      respuesta += transporteA
    
      transporteA = new Cercano(new Colectivo(65, ""), new Direccion("Rio de Janeiro",1500))
      respuesta += transporteA

      transporteA = new Cercano(new Colectivo(54,""), new Direccion("Acevedo",350))
      respuesta += transporteA
      
      transporteA = new Cercano(new Colectivo(105,""), new Direccion("Diaz Velez",350))
      respuesta += transporteA
      
      transporteA = new Cercano(new Subte('A', this.getEstacionesSubte('A')))
      transporteA.direccion = new Direccion("Rio de Janeiro")
      
      respuesta += transporteA
    
    }else if (direccion.calle.equals("Rivadavia")){
      var transporteA = new Cercano(new Colectivo(8, ""), new Direccion("Rivadavia",1000))    
      respuesta += transporteA
      
    }else if (direccion.calle.equals("Alsina")){
      var transporteA = new Cercano(new Subte('A', this.getEstacionesSubte('A')))
      transporteA.direccion = new Direccion("Saenz Penia") 
      respuesta += transporteA
      
      transporteA = new Cercano(new Colectivo(105,""), new Direccion("Bartolome Mitre",350))
      respuesta += transporteA   
      
      transporteA = new Cercano(new Colectivo(8,""), new Direccion("Rivadavia",4350))
      respuesta += transporteA 
    
    }else if (direccion.calle.equals("Rosario")){
      var transporteA = new Cercano(new Colectivo(103,""), new Direccion("Rosario",350))
      respuesta += transporteA    
        
    }else if (direccion.calle.equals("Pedernera")){
      var transporteA = new Cercano(new Colectivo(103,""), new Direccion("Primera Junta", 35))
      respuesta += transporteA
      transporteA = new Cercano(new Colectivo(92,""), new Direccion("Cabildo", 355))
      respuesta += transporteA
      
    }else if (direccion.calle.equals("Brandsen")){
      var transporteA = new Cercano(new Colectivo(86,""), new Direccion("Almirante Brown", 378))    
      respuesta += transporteA
      
      transporteA = new Cercano(new Colectivo(96,""), new Direccion("Paseo Colon", 970))    
      respuesta += transporteA
    }
        
    return respuesta
  }
  
  def consultarCombinacion(medioA:Medio, medioB:Medio) : Direccion = {    
    var respuesta: Direccion = null//new Direccion()

    if (medioA.getLinea().equals("103") && medioB.getLinea().equals("86")){  
        respuesta = new Direccion("Rosario", 156) 
    }else if (medioA.getLinea().equals("103") && medioB.getLinea().equals("96")){
      respuesta = new Direccion("Eva Peron", 560)
    }
    
    return respuesta
    
  }
  
  def combinan(medioA :Medio, medioB: Medio)= true
  
  def consultarDistanciaColectivo(colectivo:Colectivo, inicio:Direccion, fin:Direccion) : Double = {        
    
    if(inicio.calle.equals("Primera Junta") && fin.calle.equals("Rosario") ){
      return (13).abs
    }
    
    if(inicio.calle.equals("Rosario") && fin.calle.equals("Brandsen") ){
      return (9).abs
    }
    
    if(inicio.calle.equals("Primera Junta") && fin.calle.equals("Eva Peron") ){
      return (2).abs
    }
    
    if(inicio.calle.equals("Eva Peron") && fin.calle.equals("Paseo Colon") ){
      return (17.5).abs
    }
    
    if(inicio.calle.equals("Rivadavia") && fin.calle.equals("Rivadavia") ){
      return (16.7).abs
    }
    
    if(inicio.calle == fin.calle ){
      return (fin.numero - inicio.numero).abs /1000.0
    }
    
    return 0    
  }
    
  def consultarDistanciaPie(inicio:Direccion, fin:Direccion) : Double = {        
   if(inicio.calle == fin.calle ){
      return (fin.numero - inicio.numero).abs /1000.0
   }
    
    return 0    
  }
  
  def direccionIncluidaEnZona(zona:Zona, direccion:Direccion):Boolean = {
    
    if (direccion.calle.equals("Rivadavia")){
      return true
    }else if (direccion.calle.equals("Bransden")){
      return true
    }else if (direccion.calle.equals("Alsina")){
      return true
    }else if (direccion.calle.equals("Rio de Janeiro")){
      return true
    }else if (direccion.calle.equals("Saenz Penia")){
      return true
    }
    
    false
  }
  
  def getEstacionesSubte(linea:Char):List[Direccion] = {
  
    linea match {
      case 'A' => getEstacionesA()
      case 'B' => getEstacionesB()
      case 'C' => getEstacionesC()
      case 'D' => getEstacionesD()
      case 'E' => getEstacionesE()
      case 'H' => getEstacionesH()
    }
  }
  
  def getEstacionesA():List[Direccion] = {
    var estaciones = List[Direccion](new Direccion("San Pedrito"),
                                     new Direccion("Flores"),
                                     new Direccion("Carabobo"),
                                     new Direccion("Puan"),
                                     new Direccion("Primera Junta"),
                                     new Direccion("Acoyte"),
                                     new Direccion("Rio de Janeiro"),
                                     new Direccion("Castro Barros"),
                                     new Direccion("Loria"),
                                     new Direccion("Miserere"),
                                     new Direccion("Alberti/Pasco"),
                                     new Direccion("Congreso"),
                                     new Direccion("Saenz Penia"),
                                     new Direccion("Lima"),
                                     new Direccion("Piedras"),
                                     new Direccion("Peru"),
                                     new Direccion("Plaza de Mayo"))                                    
                                     
    return estaciones
  }
  
  def getEstacionesB():List[Direccion] = {
    var estaciones = List[Direccion](new Direccion("Juan Manuel de Rosas"))
    estaciones :+ new Direccion("Echeverria")
    estaciones :+ new Direccion("De Los Incas")
    estaciones :+ new Direccion("Tronador")
    estaciones :+ new Direccion("Lacroze")
    estaciones :+ new Direccion("Dorrego")
    estaciones :+ new Direccion("Malabia")
    estaciones :+ new Direccion("Angel Gallardo")
    estaciones :+ new Direccion("Medrano")
    estaciones :+ new Direccion("Carlos Gardel")
    estaciones :+ new Direccion("Pueyrredon")
    estaciones :+ new Direccion("Pasteur")
    estaciones :+ new Direccion("Callao")
    estaciones :+ new Direccion("Uruguay")
    estaciones :+ new Direccion("Carlos Pellegrini")
    estaciones :+ new Direccion("Florida")
    estaciones :+ new Direccion("Alem")
    
    return estaciones
  }
  
  def getEstacionesC():List[Direccion] = {
    var estaciones = List[Direccion](new Direccion("Retiro"))
    estaciones :+ new Direccion("San Martin")
    estaciones :+ new Direccion("Lavalle")
    estaciones :+ new Direccion("Diagonal Norte")
    estaciones :+ new Direccion("Avenida de Mayo")
    estaciones :+ new Direccion("Moreno")
    estaciones :+ new Direccion("Independencia")
    estaciones :+ new Direccion("San Juan")
    estaciones :+ new Direccion("Constitucion")
    
    return estaciones
  }
  
  def getEstacionesD():List[Direccion] = {
    var estaciones = List[Direccion](new Direccion("Congreso de Tucuman"))
    estaciones :+ new Direccion("Juramento")
    estaciones :+ new Direccion("Jose Hernandez")
    estaciones :+ new Direccion("Olleros")
    estaciones :+ new Direccion("Ministro Carranza")
    estaciones :+ new Direccion("Palermo")
    estaciones :+ new Direccion("Plaza Italia")
    estaciones :+ new Direccion("Scalabrini Ortiz")
    estaciones :+ new Direccion("Bulnes")
    estaciones :+ new Direccion("Aguero")
    estaciones :+ new Direccion("Pueyrredon")
    estaciones :+ new Direccion("Facultad de Medicina")
    estaciones :+ new Direccion("Callao")
    estaciones :+ new Direccion("Tribunales")
    estaciones :+ new Direccion("9 de Julio")
    estaciones :+ new Direccion("Diagonal Norte")
    estaciones :+ new Direccion("Catedral")
    
    return estaciones
  }  
  
  def getEstacionesE():List[Direccion] = {
    var estaciones = List[Direccion](new Direccion("Plaza de los Virreyes"))
    estaciones :+ new Direccion("Varela")
    estaciones :+ new Direccion("Medalla Milagrosa")
    estaciones :+ new Direccion("Emilio Mitre")
    estaciones :+ new Direccion("Jose M. Moreno")
    estaciones :+ new Direccion("Avenida la Plata")
    estaciones :+ new Direccion("Boedo")
    estaciones :+ new Direccion("General Urquiza")
    estaciones :+ new Direccion("Jujuy")
    estaciones :+ new Direccion("Pichincha")
    estaciones :+ new Direccion("Entre Rios")
    estaciones :+ new Direccion("San Jose")
    estaciones :+ new Direccion("Independencia")
    estaciones :+ new Direccion("Belgrano")
    
    return estaciones
  }  

  def getEstacionesH():List[Direccion] = {
    var estaciones = List[Direccion](new Direccion("Corrientes"))
    estaciones :+ new Direccion("Once")
    estaciones :+ new Direccion("Venezuela")
    estaciones :+ new Direccion("Humberto Primo")
    estaciones :+ new Direccion("Inclan")
    estaciones :+ new Direccion("Caseros")
    estaciones :+ new Direccion("Parque Patricios")
    estaciones :+ new Direccion("Hospitales")
    
    return estaciones
  }  
}