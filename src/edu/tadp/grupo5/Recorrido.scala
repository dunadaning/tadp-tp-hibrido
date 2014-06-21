package edu.tadp.grupo5

class Recorrido {
  var medio:Medio = new Medio(new Tipo())
  var inicio:Direccion = new Direccion()
  var fin:Direccion = new Direccion()
  
  def setMedio(medio:Medio) = {
    this.medio = medio
  }

    def setInicio(direccion:Direccion) = {
    this.inicio = direccion
  }
    
  def setFin(direccion:Direccion) = {
    this.fin = direccion
  }
}