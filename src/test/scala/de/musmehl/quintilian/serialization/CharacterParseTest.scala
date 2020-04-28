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
import de.musmehl.quintilian.character.properties.Property._
import de.musmehl.quintilian.magic.Zauber.Pentagramma
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
      Eigenschaften(
        Mut(18),
        Klugheit(18),
        Intuition(16),
        Charisma(15),
        Fingerfertigkeit(13),
        Gewandheit(11),
        Kondition(14),
        Koerperkraft(12)
      ),
      Energien(
        Lebensenergie(32),
        Ausdauer(45),
        Astralenergie(48),
        Karmaenergie(0)
      ),
      Kampfwerte(
        Attacke(8),
        Parade(8),
        Fernkampfbasis(7),
        Initiative(13)
      ),
      Map(
        (Pentagramma, Zauberfertigkeitspunkt(14))
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
        Fernkampfbasis(8),
        Initiative(15)
      ),
      zauber = Map()
    )

    assert(yaml.parser.parse(input).flatMap(_.as[Character]).right.value === expectedResult)
  }

}
