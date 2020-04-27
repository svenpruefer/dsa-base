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
import de.musmehl.quintilian.character.properties.Property
import de.musmehl.quintilian.character.properties.Property.{Eigenschaften, Energien, Kampfwerte}
import de.musmehl.quintilian.magic.Spell
import de.musmehl.quintilian.magic.Spell.Pentagramma
import io.circe.syntax._
import io.circe._

object instances {

  implicit val encodeEigenschaften: Encoder[Eigenschaften] = (eigenschaften: Eigenschaften) =>
    Json.obj(
      ("mut", eigenschaften.mut.value.asJson),
      ("klugheit", eigenschaften.klugheit.value.asJson),
      ("intuition", eigenschaften.intuition.value.asJson),
      ("charisma", eigenschaften.charisma.value.asJson),
      ("fingerfertigkeit", eigenschaften.fingerfertigkeit.value.asJson),
      ("gewandheit", eigenschaften.gewandheit.value.asJson),
      ("kondition", eigenschaften.kondition.value.asJson),
      ("koerperkraft", eigenschaften.koerperkraft.value.asJson)
    )

  implicit val decodeEigenschaften: Decoder[Eigenschaften] = (c: HCursor) =>
    for {
      mut              <- c.downField("mut").as[Int].map(Property.Mut)
      klugheit         <- c.downField("klugheit").as[Int].map(Property.Klugheit)
      intuition        <- c.downField("intuition").as[Int].map(Property.Intuition)
      charisma         <- c.downField("charisma").as[Int].map(Property.Charisma)
      fingerfertigkeit <- c.downField("fingerfertigkeit").as[Int].map(Property.Fingerfertigkeit)
      gewandheit       <- c.downField("gewandheit").as[Int].map(Property.Gewandheit)
      kondition        <- c.downField("kondition").as[Int].map(Property.Kondition)
      koerperkraft     <- c.downField("koerperkraft").as[Int].map(Property.Koerperkraft)
    } yield Eigenschaften(mut, klugheit, intuition, charisma, fingerfertigkeit, gewandheit, kondition, koerperkraft)

  implicit val encodeEnergien: Encoder[Energien] = (energien: Energien) =>
    Json.obj(
      ("lebensenergie", energien.lebensenergie.value.asJson),
      ("astralenergie", energien.astralenergie.value.asJson),
      ("ausdauer", energien.ausdauer.value.asJson),
      ("karmaenergie", energien.karmaenergie.value.asJson)
    )

  implicit val decodeEnergien: Decoder[Energien] = (c: HCursor) =>
    for {
      lebensenergie <- c.downField("lebensenergie").as[Int].map(Property.Lebensenergie)
      ausdauer      <- c.downField("ausdauer").as[Int].map(Property.Ausdauer)
      astralenergie <- c.downField("astralenergie").as[Int].map(Property.Astralenergie)
      karmaenergie  <- c.downField("karmaenergie").as[Int].map(Property.Karmaenergie)
    } yield Energien(lebensenergie, ausdauer, astralenergie, karmaenergie)

  implicit val encodeKampfwerte: Encoder[Kampfwerte] = (kampfwerte: Kampfwerte) =>
    Json.obj(
      ("attacke", kampfwerte.attacke.value.asJson),
      ("parade", kampfwerte.parade.value.asJson),
      ("fernkampfbasis", kampfwerte.fernkampfBasis.value.asJson),
      ("initiative", kampfwerte.initiative.value.asJson)
    )

  implicit val decodeKampfwerte: Decoder[Kampfwerte] = (c: HCursor) =>
    for {
      attacke        <- c.downField("attacke").as[Int].map(Property.Attacke)
      parade         <- c.downField("parade").as[Int].map(Property.Parade)
      fernkampfbasis <- c.downField("fernkampfbasis").as[Int].map(Property.FernkampfBasis)
      initiative     <- c.downField("initiative").as[Int].map(Property.Initiative)
    } yield Kampfwerte(attacke, parade, fernkampfbasis, initiative)

  implicit val encodeSpell: KeyEncoder[Spell] = {
    case Pentagramma => "pentagramma"
  }

  implicit val decodeSpell: KeyDecoder[Spell] = {
    case "pentagramma" => Some(Pentagramma)
  }

  implicit val encodeSpellWert: Encoder[Spell.Wert] = Encoder.encodeInt.contramap(_.value)

  implicit val decodeSpellWert: Decoder[Spell.Wert] = Decoder.decodeInt.map(Spell.Wert)

  implicit val encodeCharacter: Encoder[Character] = (character: Character) =>
    Json.obj(
      ("eigenschaften", character.eigenschaften.asJson),
      ("energien", character.energien.asJson),
      ("kampfwerte", character.kampfwerte.asJson),
      ("zauber", character.zauber.asJson)
    )

  implicit val decodeCharacter: Decoder[Character] = (c: HCursor) =>
    for {
      eigenschaften <- c.downField("eigenschaften").as[Eigenschaften]
      energien      <- c.downField("energien").as[Energien]
      kampfwerte    <- c.downField("kampfwerte").as[Kampfwerte]
      zauber        <- c.downField("zauber").as[Map[Spell, Spell.Wert]] // TODO Make this work with an empty map
    } yield Character(eigenschaften, energien, kampfwerte, zauber)

}
