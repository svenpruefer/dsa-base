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

import de.musmehl.quintilian.character.advantages.VorteilDiff
import de.musmehl.quintilian.character.disadvantages.NachteilDiff
import de.musmehl.quintilian.character.properties.{EigenschaftsDiff, EnergieDiff, KampfDiff}
import de.musmehl.quintilian.character.skills.SonderfertigkeitenDiff
import de.musmehl.quintilian.character.talents.TalentDiff
import de.musmehl.quintilian.character.talents.kampf.KampftalenteDiff
import de.musmehl.quintilian.creatures.daemons.WahrerNameDiff
import de.musmehl.quintilian.liturgies.LiturgieDiff
import de.musmehl.quintilian.magic.spell.ZauberDiff
import de.musmehl.quintilian.rituals.RitualDiff

final case class CharacterDiff(
    eigenschaften: EigenschaftsDiff = EigenschaftsDiff.empty,
    energien: EnergieDiff = EnergieDiff.empty,
    kampfwerte: KampfDiff = KampfDiff.empty,
    talente: TalentDiff = TalentDiff.empty,
    kampftalente: KampftalenteDiff = KampftalenteDiff.empty,
    zauber: ZauberDiff = ZauberDiff.empty,
    sonderfertigkeiten: SonderfertigkeitenDiff = SonderfertigkeitenDiff.empty,
    vorteile: VorteilDiff = VorteilDiff.empty,
    nachteile: NachteilDiff = NachteilDiff.empty,
    liturgien: LiturgieDiff = LiturgieDiff.empty,
    rituale: RitualDiff = RitualDiff.empty,
    wahreNamen: WahrerNameDiff = WahrerNameDiff.empty
) {
  def applyTo(character: Character): Character =
    character.copy(
      eigenschaften = eigenschaften.applyTo(character.eigenschaften),
      energien = energien.applyTo(character.energien),
      kampfwerte = kampfwerte.applyTo(character.kampfwerte),
      talente = talente.applyTo(character.talente),
      kampftalente = kampftalente.applyTo(character.kampftalente),
      zauber = zauber.applyTo(character.zauber),
      sonderfertigkeiten = sonderfertigkeiten.applyTo(character.sonderfertigkeiten),
      vorteile = vorteile.applyTo(character.vorteile),
      nachteile = nachteile.applyTo(character.nachteile),
      liturgien = liturgien.applyTo(character.liturgien),
      rituale = rituale.applyTo(character.rituale),
      wahreNamen = wahreNamen.applyTo(character.wahreNamen)
    )
}

object CharacterDiff {
  val empty: CharacterDiff = CharacterDiff()
}
