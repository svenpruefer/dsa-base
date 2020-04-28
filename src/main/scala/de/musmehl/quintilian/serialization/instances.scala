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
import de.musmehl.quintilian.character.properties.Property.{Eigenschaften, Energien, Kampfwerte, Zauberfertigkeitspunkt}
import de.musmehl.quintilian.magic.Zauber
import de.musmehl.quintilian.magic.Zauber.Pentagramma
import io.circe.syntax._
import io.circe._

object instances {

  implicit val encodeEigenschaften: Encoder[Eigenschaften] = (eigenschaften: Eigenschaften) =>
    Json.obj(
      ("Mut", eigenschaften.mut.value.asJson),
      ("Klugheit", eigenschaften.klugheit.value.asJson),
      ("Intuition", eigenschaften.intuition.value.asJson),
      ("Charisma", eigenschaften.charisma.value.asJson),
      ("Fingerfertigkeit", eigenschaften.fingerfertigkeit.value.asJson),
      ("Gewandheit", eigenschaften.gewandheit.value.asJson),
      ("Kondition", eigenschaften.kondition.value.asJson),
      ("Körperkraft", eigenschaften.koerperkraft.value.asJson)
    )

  implicit val decodeEigenschaften: Decoder[Eigenschaften] = (c: HCursor) =>
    for {
      mut              <- c.downField("Mut").as[Int].map(Property.Mut)
      klugheit         <- c.downField("Klugheit").as[Int].map(Property.Klugheit)
      intuition        <- c.downField("Intuition").as[Int].map(Property.Intuition)
      charisma         <- c.downField("Charisma").as[Int].map(Property.Charisma)
      fingerfertigkeit <- c.downField("Fingerfertigkeit").as[Int].map(Property.Fingerfertigkeit)
      gewandheit       <- c.downField("Gewandheit").as[Int].map(Property.Gewandheit)
      kondition        <- c.downField("Kondition").as[Int].map(Property.Kondition)
      koerperkraft     <- c.downField("Körperkraft").as[Int].map(Property.Koerperkraft)
    } yield Eigenschaften(mut, klugheit, intuition, charisma, fingerfertigkeit, gewandheit, kondition, koerperkraft)

  implicit val encodeEnergien: Encoder[Energien] = (energien: Energien) =>
    Json.obj(
      ("Lebensenergie", energien.lebensenergie.value.asJson),
      ("Astralenergie", energien.astralenergie.value.asJson),
      ("Ausdauer", energien.ausdauer.value.asJson),
      ("Karmaenergie", energien.karmaenergie.value.asJson)
    )

  implicit val decodeEnergien: Decoder[Energien] = (c: HCursor) =>
    for {
      lebensenergie <- c.downField("Lebensenergie").as[Int].map(Property.Lebensenergie)
      ausdauer      <- c.downField("Ausdauer").as[Int].map(Property.Ausdauer)
      astralenergie <- c.downField("Astralenergie").as[Int].map(Property.Astralenergie)
      karmaenergie  <- c.downField("Karmaenergie").as[Int].map(Property.Karmaenergie)
    } yield Energien(lebensenergie, ausdauer, astralenergie, karmaenergie)

  implicit val encodeKampfwerte: Encoder[Kampfwerte] = (kampfwerte: Kampfwerte) =>
    Json.obj(
      ("Attacke", kampfwerte.attacke.value.asJson),
      ("Parade", kampfwerte.parade.value.asJson),
      ("Fernkampfbasis", kampfwerte.fernkampfbasis.value.asJson),
      ("Initiative", kampfwerte.initiative.value.asJson)
    )

  implicit val decodeKampfwerte: Decoder[Kampfwerte] = (c: HCursor) =>
    for {
      attacke        <- c.downField("Attacke").as[Int].map(Property.Attacke)
      parade         <- c.downField("Parade").as[Int].map(Property.Parade)
      fernkampfbasis <- c.downField("Fernkampfbasis").as[Int].map(Property.Fernkampfbasis)
      initiative     <- c.downField("Initiative").as[Int].map(Property.Initiative)
    } yield Kampfwerte(attacke, parade, fernkampfbasis, initiative)

  implicit val encodeSpell: KeyEncoder[Zauber] = {
    case Pentagramma => "Pentagramma"
  }

  implicit val decodeSpell: KeyDecoder[Zauber] = {
    case "Pentagramma" => Some(Pentagramma)
  }

  implicit val encodeSpellWert: Encoder[Zauberfertigkeitspunkt] = Encoder.encodeInt.contramap(_.value)

  implicit val decodeSpellWert: Decoder[Zauberfertigkeitspunkt] = Decoder.decodeInt.map(Zauberfertigkeitspunkt)

  implicit val encodeCharacter: Encoder[Character] = (character: Character) =>
    Json.obj(
      ("Eigenschaften", character.eigenschaften.asJson),
      ("Energien", character.energien.asJson),
      ("Kampfwerte", character.kampfwerte.asJson),
      ("Zauber", character.zauber.asJson)
    )

  implicit val decodeCharacter: Decoder[Character] = (c: HCursor) =>
    for {
      eigenschaften <- c.downField("Eigenschaften").as[Eigenschaften]
      energien      <- c.downField("Energien").as[Energien]
      kampfwerte    <- c.downField("Kampfwerte").as[Kampfwerte]
      zauber        <- c.downField("Zauber").as[Map[Zauber, Zauberfertigkeitspunkt]] // TODO Make this work with an empty map
    } yield Character(eigenschaften, energien, kampfwerte, zauber)

}
