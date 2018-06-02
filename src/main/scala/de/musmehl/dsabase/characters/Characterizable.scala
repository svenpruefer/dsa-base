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

/**
  * Encapsulates the API for having a character state and modifying it.
  *
  * <p>Notice that this state is the base of the character, i.e. when learning new skills this state gets modified. This
  * state is, however, not modified by carrying objects or temporary spells. This additional modification is
  * encapsulated by the trait [[de.musmehl.dsabase.characters.Modifiable]].</p>
  */
trait Characterizable {

}
