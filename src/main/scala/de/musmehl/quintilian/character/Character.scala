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
package de.musmehl.quintilian.character

import de.musmehl.quintilian.character.advantages.Vorteil
import de.musmehl.quintilian.character.disadvantages.Nachteil
import de.musmehl.quintilian.character.properties.Eigenschaft._
import de.musmehl.quintilian.character.properties.Energie._
import de.musmehl.quintilian.character.properties.Kampf._
import de.musmehl.quintilian.character.skills.Sonderfertigkeit
import de.musmehl.quintilian.character.talents.Talent
import de.musmehl.quintilian.character.talents.Talentwert
import de.musmehl.quintilian.character.talents.kampf.{Kampftalent, Kampftalentwert}
import de.musmehl.quintilian.liturgies.{Liturgie, Liturgiefertigkeitswert}
import de.musmehl.quintilian.magic.spell.Zauber
import de.musmehl.quintilian.magic.spell.Zauberfertigkeitswert
import de.musmehl.quintilian.rituals.Ritual

/**
  * A stateless DSA character.
  *
  * This class describes a DSA character outside of a game, i.e. for example its maximum life points but not its actual life points
  * at a moment of time, etc.
  */
final case class Character(
    eigenschaften: Eigenschaften,
    energien: Energien,
    kampfwerte: Kampfwerte,
    talente: Map[Talent, Talentwert],
    kampftalente: Map[Kampftalent, Kampftalentwert],
    zauber: Map[Zauber, Zauberfertigkeitswert],
    sonderfertigkeiten: Set[Sonderfertigkeit],
    vorteile: Set[Vorteil],
    nachteile: Set[Nachteil],
    liturgien: Map[Liturgie, Liturgiefertigkeitswert],
    rituale: Set[Ritual]
) {}
