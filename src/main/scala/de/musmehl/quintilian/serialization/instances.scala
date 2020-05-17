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

import de.musmehl.quintilian.character.{Character, CharacterDiff}
import de.musmehl.quintilian.character.advantages.{Vorteil, VorteilDiff}
import de.musmehl.quintilian.character.advantages.Vorteil.{EidetischesGedaechtnis, GutesGedaechtnis}
import de.musmehl.quintilian.character.disadvantages.{Nachteil, NachteilDiff}
import de.musmehl.quintilian.character.disadvantages.Nachteil.{Arroganz, Goldgier}
import de.musmehl.quintilian.character.properties.Eigenschaft.Eigenschaften
import de.musmehl.quintilian.character.properties.{EigenschaftsDiff, Eigenschaftswert, EnergieDiff, Energiewert, KampfDiff, Kampfwert}
import de.musmehl.quintilian.character.properties.Energie.Energien
import de.musmehl.quintilian.character.properties.Kampf.Kampfwerte
import de.musmehl.quintilian.character.skills.{Sonderfertigkeit, SonderfertigkeitenDiff}
import de.musmehl.quintilian.character.skills.Sonderfertigkeit.{Ausweichen1, Ausweichen2}
import de.musmehl.quintilian.character.talents.{Talent, TalentDiff, Talentwert}
import de.musmehl.quintilian.character.talents.Talent.{Alchemie, Sinnenschaerfe}
import de.musmehl.quintilian.character.talents.kampf.Kampftalent.{Saebel, Staebe}
import de.musmehl.quintilian.character.talents.kampf.{Kampftalent, KampftalenteDiff, Kampftalentwert, KampftalentwertDiff}
import de.musmehl.quintilian.magic.spell.{FlimFlamFunkel, Pentagramma, Zauber, ZauberDiff, Zauberfertigkeitswert}
import io.circe._
import io.circe.syntax._
import cats.implicits._

object instances {

  private val encodeNonZeroInt: Encoder[Int] = (value: Int) => if (value === 0) Json.Null else Json.fromInt(value)
  private def encodeNonEmptySet[T](implicit elementEncoder: Encoder[T]): Encoder[Set[T]] =
    (set: Set[T]) => if (set.isEmpty) Json.Null else Encoder.encodeSet(elementEncoder)(set)

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
    } yield Eigenschaften(
      mut = mut,
      klugheit = klugheit,
      intuition = intuition,
      charisma = charisma,
      fingerfertigkeit = fingerfertigkeit,
      gewandheit = gewandheit,
      kondition = kondition,
      koerperkraft = koerperkraft
    )

  implicit val encodeEigenschaftsDiff: Encoder[EigenschaftsDiff] = (eigenschaftsDiff: EigenschaftsDiff) =>
    Json
      .obj(
        ("Mut", eigenschaftsDiff.mut.asJson(encodeNonZeroInt)),
        ("Klugheit", eigenschaftsDiff.klugheit.asJson(encodeNonZeroInt)),
        ("Intuition", eigenschaftsDiff.intuition.asJson(encodeNonZeroInt)),
        ("Charisma", eigenschaftsDiff.charisma.asJson(encodeNonZeroInt)),
        ("Fingerfertigkeit", eigenschaftsDiff.fingerfertigkeit.asJson(encodeNonZeroInt)),
        ("Gewandheit", eigenschaftsDiff.gewandheit.asJson(encodeNonZeroInt)),
        ("Kondition", eigenschaftsDiff.kondition.asJson(encodeNonZeroInt)),
        ("Körperkraft", eigenschaftsDiff.koerperkraft.asJson(encodeNonZeroInt))
      )
      .dropNullValues
  implicit val decodeEigenschaftsDiff: Decoder[EigenschaftsDiff] = (c: HCursor) =>
    for {
      mut              <- c.getOrElse[Int]("Mut")(0)
      klugheit         <- c.getOrElse[Int]("Klugheit")(0)
      intuition        <- c.getOrElse[Int]("Intuition")(0)
      charisma         <- c.getOrElse[Int]("Charisma")(0)
      fingerfertigkeit <- c.getOrElse[Int]("Fingerfertigkeit")(0)
      gewandheit       <- c.getOrElse[Int]("Gewandheit")(0)
      kondition        <- c.getOrElse[Int]("Kondition")(0)
      koerperkraft     <- c.getOrElse[Int]("Körperkraft")(0)
    } yield EigenschaftsDiff(
      mut = mut,
      klugheit = klugheit,
      intuition = intuition,
      charisma = charisma,
      fingerfertigkeit = fingerfertigkeit,
      gewandheit = gewandheit,
      kondition = kondition,
      koerperkraft = koerperkraft
    )

  implicit val encodeEnergien: Encoder[Energien] = (energien: Energien) =>
    Json.obj(
      ("Lebensenergie", energien.lebensenergie.value.asJson),
      ("Astralenergie", energien.astralenergie.value.asJson),
      ("Ausdauer", energien.ausdauer.value.asJson),
      ("Karmaenergie", energien.karmaenergie.value.asJson)
    )
  implicit val decodeEnergien: Decoder[Energien] = (c: HCursor) =>
    for {
      lebensenergie <- c.downField("Lebensenergie").as[Int].map(Energiewert.Lebensenergie)
      ausdauer      <- c.downField("Ausdauer").as[Int].map(Energiewert.Ausdauer)
      astralenergie <- c.downField("Astralenergie").as[Int].map(Energiewert.Astralenergie)
      karmaenergie  <- c.downField("Karmaenergie").as[Int].map(Energiewert.Karmaenergie)
    } yield Energien(
      lebensenergie = lebensenergie,
      ausdauer = ausdauer,
      astralenergie = astralenergie,
      karmaenergie = karmaenergie
    )

  implicit val encodeEnergieDiff: Encoder[EnergieDiff] = (energien: EnergieDiff) =>
    Json
      .obj(
        ("Lebensenergie", energien.lebensenergie.asJson(encodeNonZeroInt)),
        ("Astralenergie", energien.astralenergie.asJson(encodeNonZeroInt)),
        ("Ausdauer", energien.ausdauer.asJson(encodeNonZeroInt)),
        ("Karmaenergie", energien.karmaenergie.asJson(encodeNonZeroInt))
      )
      .dropNullValues
  implicit val decodeEnergieDiff: Decoder[EnergieDiff] = (c: HCursor) =>
    for {
      lebensenergie <- c.getOrElse[Int]("Lebensenergie")(0)
      ausdauer      <- c.getOrElse[Int]("Ausdauer")(0)
      astralenergie <- c.getOrElse[Int]("Astralenergie")(0)
      karmaenergie  <- c.getOrElse[Int]("Karmaenergie")(0)
    } yield EnergieDiff(
      lebensenergie = lebensenergie,
      ausdauer = ausdauer,
      astralenergie = astralenergie,
      karmaenergie = karmaenergie
    )

  implicit val encodeKampfwerte: Encoder[Kampfwerte] = (kampfwerte: Kampfwerte) =>
    Json.obj(
      ("Attacke", kampfwerte.attacke.value.asJson),
      ("Parade", kampfwerte.parade.value.asJson),
      ("Fernkampf", kampfwerte.fernkampf.value.asJson),
      ("Initiative", kampfwerte.initiative.value.asJson)
    )
  implicit val decodeKampfwerte: Decoder[Kampfwerte] = (c: HCursor) =>
    for {
      attacke    <- c.downField("Attacke").as[Int].map(Kampfwert.Attacke)
      parade     <- c.downField("Parade").as[Int].map(Kampfwert.Parade)
      fernkampf  <- c.downField("Fernkampf").as[Int].map(Kampfwert.Fernkampf)
      initiative <- c.downField("Initiative").as[Int].map(Kampfwert.Initiative)
    } yield Kampfwerte(attacke = attacke, parade = parade, fernkampf = fernkampf, initiative = initiative)

  implicit val encodeKampfDiff: Encoder[KampfDiff] = (kampfDiff: KampfDiff) =>
    Json
      .obj(
        ("Attacke", kampfDiff.attacke.asJson(encodeNonZeroInt)),
        ("Parade", kampfDiff.parade.asJson(encodeNonZeroInt)),
        ("Fernkampf", kampfDiff.fernkampf.asJson(encodeNonZeroInt)),
        ("Initiative", kampfDiff.initiative.asJson(encodeNonZeroInt))
      )
      .dropNullValues
  implicit val decodeKampfDiff: Decoder[KampfDiff] = (c: HCursor) =>
    for {
      attacke    <- c.getOrElse[Int]("Attacke")(0)
      parade     <- c.getOrElse[Int]("Parade")(0)
      fernkampf  <- c.getOrElse[Int]("Fernkampf")(0)
      initiative <- c.getOrElse[Int]("Initiative")(0)
    } yield KampfDiff(attacke = attacke, parade = parade, fernkampf = fernkampf, initiative = initiative)

  implicit val encodeZauber: KeyEncoder[Zauber] = {
    case Pentagramma    => "Pentagramma"
    case FlimFlamFunkel => "FlimFlamFunkel"
  }
  implicit val decodeZauber: KeyDecoder[Zauber] = {
    case "Pentagramma"    => Some(Pentagramma)
    case "FlimFlamFunkel" => Some(FlimFlamFunkel)
  }

  implicit val encodeZauberfertigkeitswert: Encoder[Zauberfertigkeitswert] = Encoder.encodeInt.contramap(_.value)
  implicit val decodeZauberfertigkeitswert: Decoder[Zauberfertigkeitswert] =
    Decoder.decodeInt.map(Zauberfertigkeitswert)

  implicit val encodeZauberDiff: Encoder[ZauberDiff] = Encoder
    .encodeMap[Zauber, Int](encodeZauber, encodeNonZeroInt)
    .contramap[ZauberDiff](_.diffs)
    .mapJson(_.deepDropNullValues)
  implicit val decodeZauberDiff: Decoder[ZauberDiff] =
    Decoder.decodeMap[Zauber, Int](decodeZauber, Decoder.decodeInt).map(ZauberDiff(_))

  implicit val encodeTalent: KeyEncoder[Talent] = {
    case Sinnenschaerfe => "Sinnenschärfe"
    case Alchemie       => "Alchemie"
  }
  implicit val decodeTalent: KeyDecoder[Talent] = {
    case "Sinnenschärfe" => Some(Sinnenschaerfe)
    case "Alchemie"      => Some(Alchemie)
  }

  implicit val encodeTalentwert: Encoder[Talentwert] = Encoder.encodeInt.contramap(_.value)
  implicit val decodeTalentwert: Decoder[Talentwert] = Decoder.decodeInt.map(Talentwert)

  implicit val encodeTalentDiff: Encoder[TalentDiff] = Encoder
    .encodeMap[Talent, Int](encodeTalent, encodeNonZeroInt)
    .contramap[TalentDiff](_.diffs)
    .mapJson(_.deepDropNullValues)
  implicit val decodeTalentDiff: Decoder[TalentDiff] =
    Decoder.decodeMap[Talent, Int](decodeTalent, Decoder.decodeInt).map(TalentDiff(_))

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

  implicit val encodeKampftalentwertDiff: Encoder[KampftalentwertDiff] = (kampftalentwertDiff: KampftalentwertDiff) =>
    Json.obj(
      ("Attacke", kampftalentwertDiff.attacke.asJson),
      ("Parade", kampftalentwertDiff.parade.asJson)
    )
  implicit val decodeKampftalentwertDiff: Decoder[KampftalentwertDiff] = (c: HCursor) =>
    for {
      attacke <- c.downField("Attacke").as[Int]
      parade  <- c.downField("Parade").as[Int]
    } yield KampftalentwertDiff(attacke = attacke, parade = parade)

  implicit val encodeKampftalenteDiff: Encoder[KampftalenteDiff] = Encoder
    .encodeMap[Kampftalent, KampftalentwertDiff](encodeKampftalent, encodeKampftalentwertDiff)
    .contramap[KampftalenteDiff](_.diffs)
    .mapJson(_.deepDropNullValues)
  implicit val decodeKampftalenteDiff: Decoder[KampftalenteDiff] =
    Decoder
      .decodeMap[Kampftalent, KampftalentwertDiff](decodeKampftalent, decodeKampftalentwertDiff)
      .map(KampftalenteDiff(_))

  implicit val encodeSonderfertigkeit: Encoder[Sonderfertigkeit] = {
    case Ausweichen1 => "Ausweichen 1".asJson
    case Ausweichen2 => "Ausweichen 2".asJson
  }
  implicit val decodeSonderfertigkeit: Decoder[Sonderfertigkeit] = Decoder.decodeString.map {
    case "Ausweichen 1" => Ausweichen1
    case "Ausweichen 2" => Ausweichen2
  }

  implicit val encodeSonderfertigkeitenDiff: Encoder[SonderfertigkeitenDiff] =
    (sonderfertigkeitenDiff: SonderfertigkeitenDiff) =>
      Json
        .obj(
          ("add", sonderfertigkeitenDiff.add.asJson(encodeNonEmptySet[Sonderfertigkeit])),
          ("remove", sonderfertigkeitenDiff.remove.asJson(encodeNonEmptySet[Sonderfertigkeit]))
        )
        .dropNullValues
  implicit val decodeSonderfertigkeitenDiff: Decoder[SonderfertigkeitenDiff] = (c: HCursor) =>
    for {
      add    <- c.getOrElse[Set[Sonderfertigkeit]]("add")(Set.empty[Sonderfertigkeit])
      remove <- c.getOrElse[Set[Sonderfertigkeit]]("remove")(Set.empty[Sonderfertigkeit])
    } yield SonderfertigkeitenDiff(add = add, remove = remove)

  implicit val encodeVorteil: Encoder[Vorteil] = {
    case GutesGedaechtnis       => "Gutes Gedächtnis".asJson
    case EidetischesGedaechtnis => "Eidetisches Gedächtnis".asJson
  }
  implicit val decodeVorteil: Decoder[Vorteil] = Decoder.decodeString.map {
    case "Gutes Gedächtnis"       => GutesGedaechtnis
    case "Eidetisches Gedächtnis" => EidetischesGedaechtnis
  }

  implicit val encodeVorteilDiff: Encoder[VorteilDiff] =
    (vorteilDiff: VorteilDiff) =>
      Json
        .obj(
          ("add", vorteilDiff.add.asJson(encodeNonEmptySet[Vorteil])),
          ("remove", vorteilDiff.remove.asJson(encodeNonEmptySet[Vorteil]))
        )
        .dropNullValues
  implicit val decodeVorteilDiff: Decoder[VorteilDiff] = (c: HCursor) =>
    for {
      add    <- c.getOrElse[Set[Vorteil]]("add")(Set.empty[Vorteil])
      remove <- c.getOrElse[Set[Vorteil]]("remove")(Set.empty[Vorteil])
    } yield VorteilDiff(add = add, remove = remove)

  implicit val encodeNachteil: Encoder[Nachteil] = {
    case Arroganz => "Arroganz".asJson
    case Goldgier => "Goldgier".asJson
  }
  implicit val decodeNachteil: Decoder[Nachteil] = Decoder.decodeString.map {
    case "Arroganz" => Arroganz
    case "Goldgier" => Goldgier
  }

  implicit val encodeNachteilDiff: Encoder[NachteilDiff] =
    (nachteilDiff: NachteilDiff) =>
      Json
        .obj(
          ("add", nachteilDiff.add.asJson(encodeNonEmptySet[Nachteil])),
          ("remove", nachteilDiff.remove.asJson(encodeNonEmptySet[Nachteil]))
        )
        .dropNullValues
  implicit val decodeNachteilDiff: Decoder[NachteilDiff] = (c: HCursor) =>
    for {
      add    <- c.getOrElse[Set[Nachteil]]("add")(Set.empty[Nachteil])
      remove <- c.getOrElse[Set[Nachteil]]("remove")(Set.empty[Nachteil])
    } yield NachteilDiff(add = add, remove = remove)

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

  implicit val encodeCharacterDiff: Encoder[CharacterDiff] = (characterDiff: CharacterDiff) =>
    Json
      .obj(
        ("Eigenschaften", characterDiff.eigenschaften.asJson),
        ("Energien", characterDiff.energien.asJson),
        ("Kampfwerte", characterDiff.kampfwerte.asJson),
        ("Talente", characterDiff.talente.asJson),
        ("Kampftalente", characterDiff.kampftalente.asJson),
        ("Zauber", characterDiff.zauber.asJson),
        ("Sonderfertigkeiten", characterDiff.sonderfertigkeiten.asJson),
        ("Vorteile", characterDiff.vorteile.asJson),
        ("Nachteile", characterDiff.nachteile.asJson)
      )
      .dropNullValues

  implicit val decodeCharacterDiff: Decoder[CharacterDiff] = (c: HCursor) =>
    for {
      eigenschaften      <- c.getOrElse[EigenschaftsDiff]("Eigenschaften")(EigenschaftsDiff.empty)
      energien           <- c.getOrElse[EnergieDiff]("Energien")(EnergieDiff.empty)
      kampfwerte         <- c.getOrElse[KampfDiff]("Kampfwerte")(KampfDiff.empty)
      talente            <- c.getOrElse[TalentDiff]("Talente")(TalentDiff.empty)
      kampftalente       <- c.getOrElse[KampftalenteDiff]("Kampftalente")(KampftalenteDiff.empty)
      zauber             <- c.getOrElse[ZauberDiff]("Zauber")(ZauberDiff.empty)
      sonderfertigkeiten <- c.getOrElse[SonderfertigkeitenDiff]("Sonderfertigkeiten")(SonderfertigkeitenDiff.empty)
      vorteile           <- c.getOrElse[VorteilDiff]("Vorteile")(VorteilDiff.empty)
      nachteile          <- c.getOrElse[NachteilDiff]("Nachteile")(NachteilDiff.empty)
    } yield CharacterDiff(
      eigenschaften = eigenschaften,
      energien = energien,
      kampfwerte = kampfwerte,
      talente = talente,
      kampftalente = kampftalente,
      zauber = zauber,
      sonderfertigkeiten = sonderfertigkeiten,
      vorteile = vorteile,
      nachteile = nachteile
    )
}
