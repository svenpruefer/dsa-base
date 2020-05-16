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

import de.musmehl.quintilian.character.properties.Eigenschaft.Eigenschaften
import de.musmehl.quintilian.character.properties.Eigenschaftswert._

final case class EigenschaftsDiff(
    mut: Int = 0,
    klugheit: Int = 0,
    intuition: Int = 0,
    charisma: Int = 0,
    fingerfertigkeit: Int = 0,
    gewandheit: Int = 0,
    kondition: Int = 0,
    koerperkraft: Int = 0
) {
  def applyTo(eigenschaften: Eigenschaften): Eigenschaften =
    eigenschaften.copy(
      mut = Mut(eigenschaften.mut.value + mut),
      klugheit = Klugheit(eigenschaften.klugheit.value + klugheit),
      intuition = Intuition(eigenschaften.intuition.value + intuition),
      charisma = Charisma(eigenschaften.charisma.value + charisma),
      fingerfertigkeit = Fingerfertigkeit(eigenschaften.fingerfertigkeit.value + fingerfertigkeit),
      gewandheit = Gewandheit(eigenschaften.gewandheit.value + gewandheit),
      kondition = Kondition(eigenschaften.kondition.value + kondition),
      koerperkraft = Koerperkraft(eigenschaften.koerperkraft.value + koerperkraft)
    )
}

object EigenschaftsDiff {
  val empty: EigenschaftsDiff = EigenschaftsDiff()
}
