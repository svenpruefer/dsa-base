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

import de.musmehl.quintilian.character.CharacterDiff
import de.musmehl.quintilian.serialization.generators.instances._
import de.musmehl.quintilian.serialization.instances._
import io.circe.syntax._
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.OptionValues._
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks

class CharacterDiffParseSpec extends AnyWordSpec with ScalaCheckDrivenPropertyChecks with TypeCheckedTripleEquals {

  override implicit val generatorDrivenConfig: PropertyCheckConfiguration =
    PropertyCheckConfiguration(minSuccessful = 100, sizeRange = 2)

  "A character diff" should {
    "stay the same" when {
      "it is serialized and deserialized in a round-trip" in {
        forAll(genCharacterDiff) { characterDiff: CharacterDiff =>
          assert(characterDiff.asJson.as[CharacterDiff].toOption.value === characterDiff)
        }
      }
    }
  }
}
