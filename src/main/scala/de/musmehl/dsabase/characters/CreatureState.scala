// Copyright 2018 Sven Prüfer
//
// This file is part of dsa-base.
//
// dsa-base is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// dsa-base is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with dsa-base.  If not, see <http://www.gnu.org/licenses/>.

package de.musmehl.dsabase.characters

import de.musmehl.dsabase.characters.advantages.Advantage
import de.musmehl.dsabase.characters.disadvantages.Disadvantage
import de.musmehl.dsabase.characters.properties.Property
import de.musmehl.dsabase.characters.specialskills.SpecialSkill

/**
  * describes the state of a creature
  *
  * <p>This class is very general and is supposed to reflect any possible state of any creature in DSA.</p>
  */
case class CreatureState(
                        properties: Map[Property, Int],
                        advantages: Set[Advantage],
                        disadvantages: Set[Disadvantage],
                        specialSkills: Set[SpecialSkill]
                        )
