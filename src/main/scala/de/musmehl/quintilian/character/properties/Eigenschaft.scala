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

sealed trait Eigenschaft {}

object Eigenschaft {
  case object Mut              extends Eigenschaft
  case object Klugheit         extends Eigenschaft
  case object Intuition        extends Eigenschaft
  case object Charisma         extends Eigenschaft
  case object Fingerfertigkeit extends Eigenschaft
  case object Gewandheit       extends Eigenschaft
  case object Kondition        extends Eigenschaft
  case object Koerperkraft     extends Eigenschaft

  final case class Eigenschaften(
      mut: Eigenschaftswert.Mut,
      klugheit: Eigenschaftswert.Klugheit,
      intuition: Eigenschaftswert.Intuition,
      charisma: Eigenschaftswert.Charisma,
      fingerfertigkeit: Eigenschaftswert.Fingerfertigkeit,
      gewandheit: Eigenschaftswert.Gewandheit,
      kondition: Eigenschaftswert.Kondition,
      koerperkraft: Eigenschaftswert.Koerperkraft
  )

}
