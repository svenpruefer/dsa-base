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

  case class Mut(value: Int)              extends AnyVal
  case class Klugheit(value: Int)         extends AnyVal
  case class Intuition(value: Int)        extends AnyVal
  case class Charisma(value: Int)         extends AnyVal
  case class Fingerfertigkeit(value: Int) extends AnyVal
  case class Gewandheit(value: Int)       extends AnyVal
  case class Kondition(value: Int)        extends AnyVal
  case class Koerperkraft(value: Int)     extends AnyVal

  case class Eigenschaften(
      mut: Mut,
      klugheit: Klugheit,
      intuition: Intuition,
      charisma: Charisma,
      fingerfertigkeit: Fingerfertigkeit,
      gewandheit: Gewandheit,
      kondition: Kondition,
      koerperkraft: Koerperkraft
  )

  case class Lebensenergie(value: Int) extends AnyVal
  case class Ausdauer(value: Int)      extends AnyVal
  case class Astralenergie(value: Int) extends AnyVal
  case class Karmaenergie(value: Int)  extends AnyVal

  case class Energien(
      lebensenergie: Lebensenergie,
      ausdauer: Ausdauer,
      astralenergie: Astralenergie,
      karmaenergie: Karmaenergie
  )

  case class Attacke(value: Int)        extends AnyVal
  case class Parade(value: Int)         extends AnyVal
  case class FernkampfBasis(value: Int) extends AnyVal
  case class Initiative(value: Int)     extends AnyVal

  case class Kampfwerte(attacke: Attacke, parade: Parade, fernkampfBasis: FernkampfBasis, initiative: Initiative)
}
