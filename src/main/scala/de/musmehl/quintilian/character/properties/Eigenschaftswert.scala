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

sealed trait Eigenschaftswert {
  require(value >= 0, s"Any absolute 'Wert' in DSA is greater than or equal to zero, but ${value.toString} is negative")

  def value: Int
}

object Eigenschaftswert {
  final case class Mut(value: Int)              extends Eigenschaftswert
  final case class Klugheit(value: Int)         extends Eigenschaftswert
  final case class Intuition(value: Int)        extends Eigenschaftswert
  final case class Charisma(value: Int)         extends Eigenschaftswert
  final case class Fingerfertigkeit(value: Int) extends Eigenschaftswert
  final case class Gewandheit(value: Int)       extends Eigenschaftswert
  final case class Kondition(value: Int)        extends Eigenschaftswert
  final case class Koerperkraft(value: Int)     extends Eigenschaftswert
}
