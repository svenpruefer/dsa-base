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

object Property {

  /**
    * A base property of a DSA character.
    */
  sealed trait BaseProperty {}

  case class Mut(value: Int)              extends AnyVal with BaseProperty
  case class Klugheit(value: Int)         extends AnyVal with BaseProperty
  case class Intuition(value: Int)        extends AnyVal with BaseProperty
  case class Charisma(value: Int)         extends AnyVal with BaseProperty
  case class Fingerfertigkeit(value: Int) extends AnyVal with BaseProperty
  case class Gewandheit(value: Int)       extends AnyVal with BaseProperty
  case class Kondition(value: Int)        extends AnyVal with BaseProperty
  case class Koerperkraft(value: Int)     extends AnyVal with BaseProperty

  /**
    * An energy-like property of a DSA character.
    */
  sealed trait Energy {}

  case class Lebensenergie(value: Int) extends AnyVal with Energy
  case class Ausdauer(value: Int)      extends AnyVal with Energy
  case class Astralenergie(value: Int) extends AnyVal with Energy
  case class Karmaenergie(value: Int)  extends AnyVal with Energy

  /**
    * A property relevant for fights.
    */
  sealed trait BattleValue {}

  case class Attacke(value: Int)        extends AnyVal with BattleValue
  case class Parade(value: Int)         extends AnyVal with BattleValue
  case class FernkampfBasis(value: Int) extends AnyVal with BattleValue
  case class Intitiative(value: Int)    extends AnyVal with BattleValue
}
