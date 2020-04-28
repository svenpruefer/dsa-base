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
import de.musmehl.quintilian.character.properties.Wert
import de.musmehl.quintilian.character.properties.Wert.{Eigenschaften, Energien, Kampfwerte, Zauberfertigkeitspunkt}
import de.musmehl.quintilian.magic.spell.{Pentagramma, Zauber}
import io.circe._
import io.circe.syntax._

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
      mut              <- c.downField("Mut").as[Int].map(Wert.Mut)
      klugheit         <- c.downField("Klugheit").as[Int].map(Wert.Klugheit)
      intuition        <- c.downField("Intuition").as[Int].map(Wert.Intuition)
      charisma         <- c.downField("Charisma").as[Int].map(Wert.Charisma)
      fingerfertigkeit <- c.downField("Fingerfertigkeit").as[Int].map(Wert.Fingerfertigkeit)
      gewandheit       <- c.downField("Gewandheit").as[Int].map(Wert.Gewandheit)
      kondition        <- c.downField("Kondition").as[Int].map(Wert.Kondition)
      koerperkraft     <- c.downField("Körperkraft").as[Int].map(Wert.Koerperkraft)
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
      lebensenergie <- c.downField("Lebensenergie").as[Int].map(Wert.Lebensenergie)
      ausdauer      <- c.downField("Ausdauer").as[Int].map(Wert.Ausdauer)
      astralenergie <- c.downField("Astralenergie").as[Int].map(Wert.Astralenergie)
      karmaenergie  <- c.downField("Karmaenergie").as[Int].map(Wert.Karmaenergie)
    } yield Energien(lebensenergie, ausdauer, astralenergie, karmaenergie)

  implicit val encodeKampfwerte: Encoder[Kampfwerte] = (kampfwerte: Kampfwerte) =>
    Json.obj(
      ("Attacke", kampfwerte.attacke.value.asJson),
      ("Parade", kampfwerte.parade.value.asJson),
      ("Fernkampf", kampfwerte.fernkampf.value.asJson),
      ("Initiative", kampfwerte.initiative.value.asJson)
    )

  implicit val decodeKampfwerte: Decoder[Kampfwerte] = (c: HCursor) =>
    for {
      attacke    <- c.downField("Attacke").as[Int].map(Wert.Attacke)
      parade     <- c.downField("Parade").as[Int].map(Wert.Parade)
      fernkampf  <- c.downField("Fernkampf").as[Int].map(Wert.Fernkampf)
      initiative <- c.downField("Initiative").as[Int].map(Wert.Initiative)
    } yield Kampfwerte(attacke, parade, fernkampf, initiative)

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
      zauber        <- c.downField("Zauber").as[Map[Zauber, Zauberfertigkeitspunkt]]
    } yield Character(eigenschaften, energien, kampfwerte, zauber)

}
