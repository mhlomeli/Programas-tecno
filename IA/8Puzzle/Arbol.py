#! /usr/bin/env python
# -*- coding: utf-8 -*-

#    Copyright 2011 Miguel Hernández Lomelí
#    This file is part of NReinas.
#
#    8Puzzle is free software: you can redistribute it and/or modify
#    it under the terms of the GNU General Public License as published by
#    the Free Software Foundation, either version 3 of the License, or
#    (at your option) any later version.
#
#    8Puzzle is distributed in the hope that it will be useful,
#    but WITHOUT ANY WARRANTY; without even the implied warranty of
#    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#    GNU General Public License for more details.
#
#    You should have received a copy of the GNU General Public License
#    along with 8Puzzle.  If not, see <http://www.gnu.org/licenses/>.

from Util import Matriz
#from copy import copy
#from collections import deque
class Nodo:

	def __init__(self,estado, padre, accion):
		self.estado = estado
		self.padre = padre
		self.accion = accion
	
	#Propiedad Estado
	def get_Estado(self):
		return self.__estado

	def set_Estado(self, estado):
		self.__estado = estado
	
	estado = property(get_Estado, set_Estado)

	#Propiedad Padre
	def get_Padre(self):
		return self.__padre

	def set_Padre(self, padre):
		self.__padre = padre
	
	padre = property(get_Padre, set_Padre)

	#Propiedad Hijos
	def get_Hijos(self):
		return self.__hijos

	def set_Hijos(self, hijos):
		self.__hijos = hijos
	
	hijos = property(get_Hijos, set_Hijos)

	#Propiedad Accion
	def get_Accion(self):
		return self.__accion

	def set_Accion(self, accion):
		self.__accion = accion
	
	padre = property(get_Accion, set_Accion)


class Arbol:


	__matriz = None
	__n = 0
	
	acciones = None
	def __init__(self,n):
		self.__n = n
		self.__matriz = Matriz(n)
		self.estadosRevisados = set()
		self.estadosPendientes = set()
		
		#self.estadosRevisados = set()
		#self.estadosPendientes = set()

	#Propiedad Raíz del árbol
	def __get_Raiz(self):
		return self.__Raiz

	def __set_Raiz(self, raiz):
		self.__Raiz = raiz
	
	raiz = property(__get_Raiz, __set_Raiz)

	#Propiedad Estado Inicial
	def __get_EstadoInicial(self):
		return self.__EstadoInicial

	def __set_EstadoInicial(self, estadoInicial):
		self.__EstadoInicial = estadoInicial
	
	estadoInicial = property(__get_EstadoInicial, __set_EstadoInicial)

	#Propiedad Estado Final
	def __get_EstadoFinal(self):
		return self.__EstadoFinal

	def __set_EstadoFinal(self, estadoFinal):
		self.__EstadoFinal = estadoFinal
	
	estadoFinal = property(__get_EstadoFinal, __set_EstadoFinal)

	#Propiedad Estados revisados
	def __get_EstadosRevisados(self):
		return self.__EstadosRevisados

	def __set_EstadosRevisados(self, estadosRevisados):
		self.__EstadosRevisados = estadosRevisados
	
	estadosRevisados = property(__get_EstadosRevisados, __set_EstadosRevisados)

	
	#Propiedad Estados pendientes por revisar
	def __get_EstadosPendientes(self):
		return self.__EstadosPendientes

	def __set_EstadosPendientes(self, estadosPendientes):
		self.__EstadosPendientes = estadosPendientes
	
	estadosPendientes = property(__get_EstadosPendientes, __set_EstadosPendientes)

	def expandirNodos(self,nodo):
		""" Función que regresa una lista de nodos hijos del nodo recivido  """

		hijos = []

		#Agregamos el nodo dado, a la lista de nodos ya revisados
		self.estadosRevisados.add(tuple(nodo.estado))

		#Obtenemos los estados que se crean a partir de las acciones
		estados = self.__matriz.obtenerEstados(nodo.estado)

		estados = filter(self.filtrar,estados)
		for estado in estados:
			hijo = Nodo(estado[0], nodo, estado[1])
			hijos.append(hijo)
		return hijos

	def obtenerAcciones(self):
	
		acc = []
		for i in xrange(len(self.acciones)):
			acc.append(self.acciones.pop())
		return acc

	def obtenerProfundidad(self,nodo):
	
		profundidad = 0
		
		self.acciones =[]	
		while nodo.padre != None:
			self.acciones.append(nodo.accion)
			nodo = nodo.padre
			profundidad += 1
		return profundidad
		
 
	def filtrar(self,x):
		if x[0] is None:
			return False
		else:
			return tuple(x[0]) not in self.estadosRevisados
