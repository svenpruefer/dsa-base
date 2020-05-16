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
package de.musmehl.quintilian.character.talents.kampf

final case class KampftalentwertDiff(attacke: Int = 0, parade: Int = 0) {
  def applyTo(kampftalentwert: Kampftalentwert): Kampftalentwert =
    kampftalentwert.copy(
      attacke = kampftalentwert.attacke.copy(value = kampftalentwert.attacke.value + attacke),
      parade = kampftalentwert.parade.copy(value = kampftalentwert.parade.value + parade)
    )
}

object KampftalentwertDiff {
  val empty: KampftalentwertDiff = KampftalentwertDiff()
}
