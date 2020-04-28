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

sealed trait Wert {

  require(value >= 0, s"Any absolute value in DSA is greater than or equal to zero, but $value is negative")

  def value: Int
}

object Wert {

  sealed trait Basiseigenschaft extends Wert

  case class Mut(value: Int)              extends Basiseigenschaft
  case class Klugheit(value: Int)         extends Basiseigenschaft
  case class Intuition(value: Int)        extends Basiseigenschaft
  case class Charisma(value: Int)         extends Basiseigenschaft
  case class Fingerfertigkeit(value: Int) extends Basiseigenschaft
  case class Gewandheit(value: Int)       extends Basiseigenschaft
  case class Kondition(value: Int)        extends Basiseigenschaft
  case class Koerperkraft(value: Int)     extends Basiseigenschaft

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

  sealed trait Energie extends Wert

  case class Lebensenergie(value: Int) extends Energie
  case class Ausdauer(value: Int)      extends Energie
  case class Astralenergie(value: Int) extends Energie
  case class Karmaenergie(value: Int)  extends Energie

  case class Energien(
      lebensenergie: Lebensenergie,
      ausdauer: Ausdauer,
      astralenergie: Astralenergie,
      karmaenergie: Karmaenergie
  )

  sealed trait Kampfwert extends Wert

  case class Attacke(value: Int)    extends Kampfwert
  case class Parade(value: Int)     extends Kampfwert
  case class Fernkampf(value: Int)  extends Kampfwert
  case class Initiative(value: Int) extends Kampfwert

  case class Kampfwerte(attacke: Attacke, parade: Parade, fernkampf: Fernkampf, initiative: Initiative)

  case class Zauberfertigkeitspunkt(value: Int) extends Wert

  case class Talentpunkt(value: Int) extends Wert
}
