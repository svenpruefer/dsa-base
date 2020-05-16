/* Copyright (C) 2020 Sven Prüfer - All Rights Reserved
 *
 * This file is part of quintilian.
 *
 * quintilian is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * quintilian is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with quintilian.  If not, see <https://www.gnu.org/licenses/>.
 */
package de.musmehl.quintilian.character.properties

sealed trait Energiewert {
  require(value >= 0, s"Any absolute 'Wert' in DSA is greater than or equal to zero, but $value is negative")

  def value: Int
}

object Energiewert {
  final case class Lebensenergie(value: Int) extends Energiewert
  final case class Ausdauer(value: Int)      extends Energiewert
  final case class Astralenergie(value: Int) extends Energiewert
  final case class Karmaenergie(value: Int)  extends Energiewert
}
