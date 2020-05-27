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
package de.musmehl.quintilian.creatures.daemons

final case class WahrerNameDiff(diffs: Map[Daemon, WahrerNameQualitaet] = Map.empty[Daemon, WahrerNameQualitaet]) {
  def applyTo(wahreNamen: Map[Daemon, WahrerNameQualitaet]): Map[Daemon, WahrerNameQualitaet] =
    wahreNamen.map {
      case (daemon: Daemon, wahrerNameQualitaet: WahrerNameQualitaet) if diffs.contains(daemon) =>
        daemon -> WahrerNameQualitaet(wahrerNameQualitaet.value.max(diffs(daemon).value))
      case other => other
    }
}

object WahrerNameDiff {
  val empty: WahrerNameDiff = WahrerNameDiff(Map.empty[Daemon, WahrerNameQualitaet])
}
