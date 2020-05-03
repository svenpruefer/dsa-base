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
import de.musmehl.quintilian.character.properties.Eigenschaft.{Eigenschaften, Energien, Kampfwerte}
import de.musmehl.quintilian.character.properties.Eigenschaftswert
import de.musmehl.quintilian.character.properties.Eigenschaftswert.{Talentwert, Zauberfertigkeitswert}
import de.musmehl.quintilian.character.skills.Sonderfertigkeit
import de.musmehl.quintilian.character.skills.Sonderfertigkeit.Ausweichen1
import de.musmehl.quintilian.character.talents.Talent
import de.musmehl.quintilian.character.talents.Talent.Sinnenschaerfe
import de.musmehl.quintilian.character.talents.kampf.Kampftalent.{Saebel, Staebe}
import de.musmehl.quintilian.character.talents.kampf.{Kampftalent, Kampftalentwert}
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
      mut              <- c.downField("Mut").as[Int].map(Eigenschaftswert.Mut)
      klugheit         <- c.downField("Klugheit").as[Int].map(Eigenschaftswert.Klugheit)
      intuition        <- c.downField("Intuition").as[Int].map(Eigenschaftswert.Intuition)
      charisma         <- c.downField("Charisma").as[Int].map(Eigenschaftswert.Charisma)
      fingerfertigkeit <- c.downField("Fingerfertigkeit").as[Int].map(Eigenschaftswert.Fingerfertigkeit)
      gewandheit       <- c.downField("Gewandheit").as[Int].map(Eigenschaftswert.Gewandheit)
      kondition        <- c.downField("Kondition").as[Int].map(Eigenschaftswert.Kondition)
      koerperkraft     <- c.downField("Körperkraft").as[Int].map(Eigenschaftswert.Koerperkraft)
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
      lebensenergie <- c.downField("Lebensenergie").as[Int].map(Eigenschaftswert.Lebensenergie)
      ausdauer      <- c.downField("Ausdauer").as[Int].map(Eigenschaftswert.Ausdauer)
      astralenergie <- c.downField("Astralenergie").as[Int].map(Eigenschaftswert.Astralenergie)
      karmaenergie  <- c.downField("Karmaenergie").as[Int].map(Eigenschaftswert.Karmaenergie)
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
      attacke    <- c.downField("Attacke").as[Int].map(Eigenschaftswert.Attacke)
      parade     <- c.downField("Parade").as[Int].map(Eigenschaftswert.Parade)
      fernkampf  <- c.downField("Fernkampf").as[Int].map(Eigenschaftswert.Fernkampf)
      initiative <- c.downField("Initiative").as[Int].map(Eigenschaftswert.Initiative)
    } yield Kampfwerte(attacke, parade, fernkampf, initiative)

  implicit val encodeZauber: KeyEncoder[Zauber] = {
    case Pentagramma => "Pentagramma"
  }
  implicit val decodeZauber: KeyDecoder[Zauber] = {
    case "Pentagramma" => Some(Pentagramma)
  }

  implicit val encodeZauberfertigkeitswert: Encoder[Zauberfertigkeitswert] = Encoder.encodeInt.contramap(_.value)
  implicit val decodeZauberfertigkeitswert: Decoder[Zauberfertigkeitswert] =
    Decoder.decodeInt.map(Zauberfertigkeitswert)

  implicit val encodeTalent: KeyEncoder[Talent] = {
    case Sinnenschaerfe => "Sinnenschärfe"
  }
  implicit val decodeTalent: KeyDecoder[Talent] = {
    case "Sinnenschärfe" => Some(Sinnenschaerfe)
  }

  implicit val encodeTalentwert: Encoder[Talentwert] = Encoder.encodeInt.contramap(_.value)
  implicit val decodeTalentwert: Decoder[Talentwert] = Decoder.decodeInt.map(Talentwert)

  implicit val encodeKampftalent: KeyEncoder[Kampftalent] = {
    case Staebe => "Stäbe"
    case Saebel => "Säbel"
  }
  implicit val decodeKampftalent: KeyDecoder[Kampftalent] = {
    case "Stäbe" => Some(Staebe)
    case "Säbel" => Some(Saebel)
  }

  implicit val encodeKampftalentwert: Encoder[Kampftalentwert] = (kampftalentWert: Kampftalentwert) =>
    Json.obj(
      ("Attacke", kampftalentWert.attacke.value.asJson),
      ("Parade", kampftalentWert.parade.value.asJson)
    )
  implicit val decodeKampftalentwert: Decoder[Kampftalentwert] = (c: HCursor) =>
    for {
      attacke <- c.downField("Attacke").as[Int].map(Kampftalentwert.Attacke)
      parade  <- c.downField("Parade").as[Int].map(Kampftalentwert.Parade)
    } yield Kampftalentwert(attacke, parade)

  implicit val encodeSonderfertigkeit: Encoder[Sonderfertigkeit] = {
    case Ausweichen1 => "Ausweichen 1".asJson
  }
  implicit val decodeSonderfertigkeit: Decoder[Sonderfertigkeit] = Decoder.decodeString.map {
    case "Ausweichen 1" => Ausweichen1
  }

  implicit val encodeVorteil: Encoder[Vorteil] = {
    case GutesGedaechtnis => "Gutes Gedächtnis".asJson
  }
  implicit val decodeVorteil: Decoder[Vorteil] = Decoder.decodeString.map {
    case "Gutes Gedächtnis" => GutesGedaechtnis
  }

  implicit val encodeNachteil: Encoder[Nachteil] = {
    case Arroganz => "Arroganz".asJson
  }
  implicit val decodeNachteil: Decoder[Nachteil] = Decoder.decodeString.map {
    case "Arroganz" => Arroganz
  }

  implicit val encodeCharacter: Encoder[Character] = (character: Character) =>
    Json.obj(
      ("Eigenschaften", character.eigenschaften.asJson),
      ("Energien", character.energien.asJson),
      ("Kampfwerte", character.kampfwerte.asJson),
      ("Talente", character.talente.asJson),
      ("Kampftalente", character.kampftalente.asJson),
      ("Zauber", character.zauber.asJson),
      ("Sonderfertigkeiten", character.sonderfertigkeiten.asJson),
      ("Vorteile", character.vorteile.asJson),
      ("Nachteile", character.nachteile.asJson)
    )

  implicit val decodeCharacter: Decoder[Character] = (c: HCursor) =>
    for {
      eigenschaften      <- c.downField("Eigenschaften").as[Eigenschaften]
      energien           <- c.downField("Energien").as[Energien]
      kampfwerte         <- c.downField("Kampfwerte").as[Kampfwerte]
      talente            <- c.downField("Talente").as[Map[Talent, Talentwert]]
      kampftalente       <- c.downField("Kampftalente").as[Map[Kampftalent, Kampftalentwert]]
      zauber             <- c.downField("Zauber").as[Map[Zauber, Zauberfertigkeitswert]]
      sonderfertigkeiten <- c.downField("Sonderfertigkeiten").as[Set[Sonderfertigkeit]]
      vorteile           <- c.downField("Vorteile").as[Set[Vorteil]]
      nachteile          <- c.downField("Nachteile").as[Set[Nachteil]]
    } yield Character(
      eigenschaften,
      energien,
      kampfwerte,
      talente,
      kampftalente,
      zauber,
      sonderfertigkeiten,
      vorteile,
      nachteile
    )

}
