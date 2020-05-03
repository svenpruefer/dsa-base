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

  require(value >= 0, s"Any absolute 'Wert' in DSA is greater than or equal to zero, but $value is negative")

  def value: Int
}

object Eigenschaftswert {

  case class Mut(value: Int)              extends Eigenschaftswert
  case class Klugheit(value: Int)         extends Eigenschaftswert
  case class Intuition(value: Int)        extends Eigenschaftswert
  case class Charisma(value: Int)         extends Eigenschaftswert
  case class Fingerfertigkeit(value: Int) extends Eigenschaftswert
  case class Gewandheit(value: Int)       extends Eigenschaftswert
  case class Kondition(value: Int)        extends Eigenschaftswert
  case class Koerperkraft(value: Int)     extends Eigenschaftswert

  case class Attacke(value: Int)    extends Eigenschaftswert
  case class Parade(value: Int)     extends Eigenschaftswert
  case class Fernkampf(value: Int)  extends Eigenschaftswert
  case class Initiative(value: Int) extends Eigenschaftswert

  case class Lebensenergie(value: Int) extends Eigenschaftswert
  case class Ausdauer(value: Int)      extends Eigenschaftswert
  case class Astralenergie(value: Int) extends Eigenschaftswert
  case class Karmaenergie(value: Int)  extends Eigenschaftswert

  case class Zauberfertigkeitswert(value: Int) extends Eigenschaftswert

  case class Talentwert(value: Int) extends Eigenschaftswert
}
