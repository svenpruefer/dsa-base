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
package de.musmehl.quintilian.character.properties.diff

import de.musmehl.quintilian.character.properties.Eigenschaftswert

sealed trait WertDiff[T <: Eigenschaftswert] {
  def change: Int
  def applyTo(value: T): T
}

/*object WertDiff {

  case class MutDiff(change: Int) extends WertDiff[Mut] {
    def applyTo(mut: Mut): Mut = mut.copy(value = mut.value + change)
  }
  case class KlugheitDiff(change: Int) extends WertDiff[Klugheit] {
    def applyTo(klugheit: Klugheit): Klugheit = klugheit.copy(value = klugheit.value + change)
  }
  case class IntuitionDiff(change: Int) extends WertDiff[Intuition] {
    def applyTo(intuition: Intuition): Intuition = intuition.copy(value = intuition.value + change)
  }
  case class CharismaDiff(change: Int) extends WertDiff[Charisma] {
    def applyTo(charisma: Charisma): Charisma = charisma.copy(value = charisma.value + change)
  }
  case class FingerfertigkeitDiff(change: Int) extends WertDiff[Fingerfertigkeit] {
    def applyTo(fingerfertigkeit: Fingerfertigkeit): Fingerfertigkeit =
      fingerfertigkeit.copy(value = fingerfertigkeit.value + change)
  }
  case class GewandheitDiff(change: Int) extends WertDiff[Gewandheit] {
    def applyTo(gewandheit: Gewandheit): Gewandheit = gewandheit.copy(value = gewandheit.value + change)
  }
  case class KonditionDiff(change: Int) extends WertDiff[Kondition] {
    def applyTo(kondition: Kondition): Kondition = kondition.copy(value = kondition.value + change)
  }
  case class KoerperkraftDiff(change: Int) extends WertDiff[Koerperkraft] {
    def applyTo(koerperkraft: Koerperkraft): Koerperkraft = koerperkraft.copy(value = koerperkraft.value + change)
  }

  case class EigenschaftenDiff(
      mutDiff: MutDiff,
      klugheitDiff: KlugheitDiff,
      intuitionDiff: IntuitionDiff,
      charismaDiff: CharismaDiff,
      fingerfertigkeitDiff: FingerfertigkeitDiff,
      gewandheitDiff: GewandheitDiff,
      konditionDiff: KonditionDiff,
      koerperkraftDiff: KoerperkraftDiff
  ) {
    def applyTo(eigenschaften: Eigenschaften): Eigenschaften =
      eigenschaften.copy(
        mut = mutDiff.applyTo(eigenschaften.mut),
        klugheit = klugheitDiff.applyTo(eigenschaften.klugheit),
        intuition = intuitionDiff.applyTo(eigenschaften.intuition),
        charisma = charismaDiff.applyTo(eigenschaften.charisma),
        fingerfertigkeit = fingerfertigkeitDiff.applyTo(eigenschaften.fingerfertigkeit),
        gewandheit = gewandheitDiff.applyTo(eigenschaften.gewandheit),
        kondition = konditionDiff.applyTo(eigenschaften.kondition),
        koerperkraft = koerperkraftDiff.applyTo(eigenschaften.koerperkraft)
      )
  }

  case class LebensenergieDiff(change: Int) extends WertDiff[Lebensenergie] {
    def applyTo(lebensenergie: Lebensenergie): Lebensenergie = lebensenergie.copy(value = lebensenergie.value + change)
  }
  case class AusdauerDiff(change: Int) extends WertDiff[Ausdauer] {
    def applyTo(ausdauer: Ausdauer): Ausdauer = ausdauer.copy(value = ausdauer.value + change)
  }
  case class AstralenergieDiff(change: Int) extends WertDiff[Astralenergie] {
    def applyTo(astralenergie: Astralenergie): Astralenergie = astralenergie.copy(value = astralenergie.value + change)
  }
  case class KarmaenergieDiff(change: Int) extends WertDiff[Karmaenergie] {
    def applyTo(karmaenergie: Karmaenergie): Karmaenergie = karmaenergie.copy(value = karmaenergie.value + change)
  }

  case class EnergienDiff(
      lebensenergieDiff: LebensenergieDiff,
      ausdauerDiff: AusdauerDiff,
      astralenergieDiff: AstralenergieDiff,
      karmaenergieDiff: KarmaenergieDiff
  ) {
    def applyTo(energien: Energien): Energien =
      energien.copy(
        lebensenergie = lebensenergieDiff.applyTo(energien.lebensenergie),
        ausdauer = ausdauerDiff.applyTo(energien.ausdauer),
        astralenergie = astralenergieDiff.applyTo(energien.astralenergie),
        karmaenergie = karmaenergieDiff.applyTo(energien.karmaenergie)
      )
  }

  case class AttackeDiff(change: Int) extends WertDiff[Attacke] {
    override def applyTo(attacke: Attacke): Attacke = attacke.copy(value = attacke.value + change)
  }
  case class ParadeDiff(change: Int) extends WertDiff[Parade] {
    override def applyTo(parade: Parade): Parade = parade.copy(value = parade.value + change)
  }
  case class FernkampfDiff(change: Int) extends WertDiff[Fernkampf] {
    override def applyTo(fernkampf: Fernkampf): Fernkampf = fernkampf.copy(value = fernkampf.value + change)
  }
  case class InitiativeDiff(change: Int) extends WertDiff[Initiative] {
    override def applyTo(initiative: Initiative): Initiative = initiative.copy(value = initiative.value + change)
  }

  case class KampfwerteDiff(
      attackeDiff: AttackeDiff,
      paradeDiff: ParadeDiff,
      fernkampfDiff: FernkampfDiff,
      initiativeDiff: InitiativeDiff
  ) {
    def applyTo(kampfwerte: Kampfwerte): Kampfwerte =
      kampfwerte.copy(
        attacke = attackeDiff.applyTo(kampfwerte.attacke),
        parade = paradeDiff.applyTo(kampfwerte.parade),
        fernkampf = fernkampfDiff.applyTo(kampfwerte.fernkampf),
        initiative = initiativeDiff.applyTo(kampfwerte.initiative)
      )
  }

  case class ZauberfertigkeitspunktDiff(change: Int) extends WertDiff[Zauberfertigkeitswert] {
    override def applyTo(zauberfertigkeitspunkt: Zauberfertigkeitswert): Zauberfertigkeitswert =
      zauberfertigkeitspunkt.copy(value = zauberfertigkeitspunkt.value + change)
  }

  case class TalentpunktDiff(change: Int) extends WertDiff[Talentwert] {
    override def applyTo(talentpunkt: Talentwert): Talentwert = talentpunkt.copy(value = talentpunkt.value + change)
  }

  case class CharacterDiff(
      eigenschaftenDiff: EigenschaftenDiff,
      energienDiff: EnergienDiff,
      kampfwerteDiff: KampfwerteDiff,
      zauberDiff: Map[Zauber, ZauberfertigkeitspunktDiff]
  ) {
    def applyTo(character: Character): Character =
      character.copy(
        eigenschaften = eigenschaftenDiff.applyTo(character.eigenschaften),
        energien = energienDiff.applyTo(character.energien),
        kampfwerte = kampfwerteDiff.applyTo(character.kampfwerte),
        zauber = character.zauber.map {
          case (zauber, zauberfertigkeitspunkt) =>
            zauber -> zauberDiff.getOrElse(zauber, ZauberfertigkeitspunktDiff(0)).applyTo(zauberfertigkeitspunkt)
        }
      )
  }
}*/
