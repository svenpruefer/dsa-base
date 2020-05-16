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
package de.musmehl.quintilian.serialization

import de.musmehl.quintilian.character.Character
import de.musmehl.quintilian.character.advantages.Vorteil
import de.musmehl.quintilian.character.advantages.Vorteil.GutesGedaechtnis
import de.musmehl.quintilian.character.disadvantages.Nachteil
import de.musmehl.quintilian.character.disadvantages.Nachteil.Arroganz
import de.musmehl.quintilian.character.properties.Eigenschaft.Eigenschaften
import de.musmehl.quintilian.character.properties.Energie.Energien
import de.musmehl.quintilian.character.properties.Energiewert._
import de.musmehl.quintilian.character.properties.Kampfwert._
import de.musmehl.quintilian.character.properties.Kampf.Kampfwerte
import de.musmehl.quintilian.character.properties.Eigenschaftswert._
import de.musmehl.quintilian.character.skills.Sonderfertigkeit
import de.musmehl.quintilian.character.skills.Sonderfertigkeit.Ausweichen1
import de.musmehl.quintilian.character.talents.Talent.Sinnenschaerfe
import de.musmehl.quintilian.character.talents.{Talent, Talentwert}
import de.musmehl.quintilian.character.talents.kampf.Kampftalent.Staebe
import de.musmehl.quintilian.character.talents.kampf.{Kampftalent, Kampftalentwert}
import de.musmehl.quintilian.magic.spell.{Pentagramma, Zauber, Zauberfertigkeitswert}
import de.musmehl.quintilian.serialization.instances._
import io.circe.yaml
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.EitherValues._
import org.scalatest.funsuite.AnyFunSuiteLike

import scala.io.Source

class CharacterParseTest extends AnyFunSuiteLike with TypeCheckedTripleEquals {

  test("A character YAML file with spells should be parsable") {
    val input = Source.fromInputStream(getClass.getResourceAsStream("quintilian.yaml")).mkString

    val expectedResult = Character(
      eigenschaften = Eigenschaften(
        Mut(18),
        Klugheit(18),
        Intuition(16),
        Charisma(15),
        Fingerfertigkeit(13),
        Gewandheit(11),
        Kondition(14),
        Koerperkraft(12)
      ),
      energien = Energien(
        Lebensenergie(32),
        Ausdauer(45),
        Astralenergie(48),
        Karmaenergie(0)
      ),
      kampfwerte = Kampfwerte(
        Attacke(8),
        Parade(8),
        Fernkampf(7),
        Initiative(13)
      ),
      talente = Map(
        (Sinnenschaerfe, Talentwert(9))
      ),
      kampftalente = Map(
        (Staebe, Kampftalentwert(Kampftalentwert.Attacke(9), Kampftalentwert.Parade(5)))
      ),
      zauber = Map(
        (Pentagramma, Zauberfertigkeitswert(14))
      ),
      sonderfertigkeiten = Set(
        Ausweichen1
      ),
      vorteile = Set(
        GutesGedaechtnis
      ),
      nachteile = Set(
        Arroganz
      )
    )

    assert(yaml.parser.parse(input).flatMap(_.as[Character]).right.value === expectedResult)
  }

  test("A character YAML file without spells should be parsable") {
    val input = Source.fromInputStream(getClass.getResourceAsStream("morena.yaml")).mkString

    val expectedResult = Character(
      eigenschaften = Eigenschaften(
        Mut(18),
        Klugheit(12),
        Intuition(15),
        Charisma(11),
        Fingerfertigkeit(9),
        Gewandheit(11),
        Kondition(19),
        Koerperkraft(21)
      ),
      energien = Energien(
        Lebensenergie(43),
        Ausdauer(59),
        Astralenergie(0),
        Karmaenergie(0)
      ),
      kampfwerte = Kampfwerte(
        Attacke(9),
        Parade(9),
        Fernkampf(8),
        Initiative(15)
      ),
      talente = Map[Talent, Talentwert](),
      kampftalente = Map[Kampftalent, Kampftalentwert](),
      zauber = Map[Zauber, Zauberfertigkeitswert](),
      sonderfertigkeiten = Set[Sonderfertigkeit](),
      vorteile = Set[Vorteil](),
      nachteile = Set[Nachteil]()
    )

    assert(yaml.parser.parse(input).flatMap(_.as[Character]).right.value === expectedResult)
  }

}
