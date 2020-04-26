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

import de.musmehl.quintilian.character.properties.Property._
import de.musmehl.quintilian.magic.Spell

/**
  * A stateless DSA character.
  *
  * This class describes a DSA character outside of a game, i.e. its maximum life points but not its actual life points
  * at a moment of time. For this time-dependent concept, see [[de.musmehl.quintilian.character.CharacterState CharacterState]].
  */
case class Character(
    eigenschaften: Eigenschaften,
    energien: Energien,
    kampfwerte: Kampfwerte,
    zauber: Map[Spell, Spell.Wert]
) {}
