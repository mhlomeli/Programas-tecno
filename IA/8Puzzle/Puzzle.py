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

from sys import argv
from Util import Utilidades
from Metodos import *
from time import time
import profile

class Puzzle:

	def main(self):

		msg = None

		if len(argv) < 4:
			print "Use %s <Archivo de entrada> <Archivo de salida> <Metodo> " % argv[0]
			print "Los métodos son:"
			print "Para busqueda ancha use ba"
			print "Busqueda profunda use bp"
			print "A Estrella use ae"
		else:
			util = Utilidades(argv[1])
			metodo = argv[3]
			if metodo == 'ba':
				busqueda = BusquedaAncha(util.getN(),util.getInicio(),util.getFinal())
			elif metodo == 'bp':
				busqueda = BusquedaProfunda(util.getN(),util.getInicio(),util.getFinal())
			elif metodo == 'ae':
				busqueda = AEstrella(util.getN(),util.getInicio(),util.getFinal())
			else:
				print "Método incorrecto: %s\nUtilizando busqueda ancha" % metodo
				busqueda = BusquedaAncha(util.getN(),util.getInicio(),util.getFinal())
	
			
			inicio = time()
			msg = busqueda.buscar()
			fin = time()
			#Imprimimos en el archivo
			f = file(argv[2],'w')
			f.write("Se analizó el archivo %s:\n" % argv[1])
			f.write("Con el método de búsqueda: %s\n" % metodo)
			f.write(msg)
			f.write('\n\nLa ejecución tomó %f segundos\n' % (fin - inicio))
			f.flush()
			f.close()

	def main2(self):

		msg = None

		if len(argv) < 2:
			print "Use %s <archivo>" % argv[0]
		else:
			util = Utilidades(argv[1])
			print util.getN(),util.getInicio(),util.getFinal()

			busqueda = AEstrella(util.getN(),util.getInicio(),util.getFinal())
			msg = busqueda.manhattan(util.getInicio(),util.getFinal())

			print "El estado inicial se encuentra a una distancia de %i del final\n\n" % msg
			

if __name__=="__main__":
	p=Puzzle()
	p.main()

#def iniciar():
#	p=Puzzle()
#	p.main()

#profile.run('iniciar()')

