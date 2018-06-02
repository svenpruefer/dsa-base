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

import de.musmehl.dsabase.characters.profession.Profession
import de.musmehl.dsabase.characters.races.Race
import de.musmehl.dsabase.util.time.Date

/**
  * Represents anything that "lives", i.e. can have a state, is able to somehow interact with the world and has
  * properties, talents etc.
  */
trait Creature {

    /** the name of the creature */
    val name: String

    /** the birthday of the creature as a [[de.musmehl.dsabase.util.time.Date]] */
    val birthday: Date

    val race: Race

    val creature: Creature

    val profession: Profession
}
