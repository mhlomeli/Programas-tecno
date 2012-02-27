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
import sys
if len(sys.argv) > 1:
	f = file(sys.argv[1])
	
	lineas = f.readlines()

	#edo ='[7, 4, 1, 5, 0, 2, 8, 3, 6]\n'

	edo = '[1, 2, 3, 4, 5, 6, 7, 8, 0]\n'
	edo = '[0, 1, 2, 3, 4]\n'

	print "Buscando, tenemos %i lineas" %len(lineas)

	for linea in lineas:
		if linea == edo:
			print "Lo encontre: %s" % linea
			break
	f.close()
