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
package de.musmehl.quintilian.magic.spell

import de.musmehl.quintilian.character.properties.Eigenschaft
import de.musmehl.quintilian.character.properties.Eigenschaft.{Charisma, Mut}
import de.musmehl.quintilian.magic.SpellAttribute
import de.musmehl.quintilian.magic.SpellAttribute.Einfluss

case object BoeserBlick extends Zauber {

  override def displayName: String = "Böser Blick"

  override def eigenschaften: List[Eigenschaft] = List[Eigenschaft](Mut, Charisma, Charisma)

  override def merkmale: Set[SpellAttribute] = Set(Einfluss)
}
