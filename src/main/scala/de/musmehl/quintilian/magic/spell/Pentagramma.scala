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
import de.musmehl.quintilian.magic.SpellAttribute.{Antimagie, Beschwoerung, DaemonischAllgemein, Geisterwesen}

case object Pentagramma extends Zauber {

  override val displayName: String = "Pentagramma Sphärenbann"

  override val eigenschaften: List[Eigenschaft] = List[Eigenschaft](Mut, Mut, Charisma)

  override val merkmale: Set[SpellAttribute] = Set(DaemonischAllgemein, Antimagie, Beschwoerung, Geisterwesen)
}
