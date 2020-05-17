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
package de.musmehl.quintilian.serialization.generators

import de.musmehl.quintilian.character.properties.Eigenschaft.Eigenschaften
import de.musmehl.quintilian.character.properties.{EigenschaftsDiff, Eigenschaftswert, EnergieDiff, Energiewert, KampfDiff, Kampfwert}
import de.musmehl.quintilian.character.properties.Energie.Energien
import de.musmehl.quintilian.character.properties.Kampf.Kampfwerte
import de.musmehl.quintilian.character.{Character, CharacterDiff}
import de.musmehl.quintilian.character.advantages.{Vorteil, VorteilDiff}
import de.musmehl.quintilian.character.disadvantages.{Nachteil, NachteilDiff}
import de.musmehl.quintilian.character.skills.{Sonderfertigkeit, SonderfertigkeitenDiff}
import de.musmehl.quintilian.character.talents.kampf.{Kampftalent, KampftalenteDiff, Kampftalentwert, KampftalentwertDiff}
import de.musmehl.quintilian.character.talents.{Talent, TalentDiff, Talentwert}
import de.musmehl.quintilian.magic.spell.{FlimFlamFunkel, Pentagramma, Zauber, ZauberDiff, Zauberfertigkeitswert}
import org.scalacheck.Gen

object instances {

  val genEigenschaften: Gen[Eigenschaften] = for {
    mut              <- Gen.chooseNum[Int](0, 25)
    klugheit         <- Gen.chooseNum[Int](0, 25)
    intuition        <- Gen.chooseNum[Int](0, 25)
    charisma         <- Gen.chooseNum[Int](0, 25)
    fingerfertigkeit <- Gen.chooseNum[Int](0, 25)
    gewandheit       <- Gen.chooseNum[Int](0, 25)
    kondition        <- Gen.chooseNum[Int](0, 25)
    koerperkraft     <- Gen.chooseNum[Int](0, 25)
  } yield Eigenschaften(
    Eigenschaftswert.Mut(mut),
    Eigenschaftswert.Klugheit(klugheit),
    Eigenschaftswert.Intuition(intuition),
    Eigenschaftswert.Charisma(charisma),
    Eigenschaftswert.Fingerfertigkeit(fingerfertigkeit),
    Eigenschaftswert.Gewandheit(gewandheit),
    Eigenschaftswert.Kondition(kondition),
    Eigenschaftswert.Koerperkraft(koerperkraft)
  )

  val genEigenschaftsDiff: Gen[EigenschaftsDiff] = for {
    mut              <- Gen.chooseNum[Int](-10, 10)
    klugheit         <- Gen.chooseNum[Int](-10, 10)
    intuition        <- Gen.chooseNum[Int](-10, 10)
    charisma         <- Gen.chooseNum[Int](-10, 10)
    fingerfertigkeit <- Gen.chooseNum[Int](-10, 10)
    gewandheit       <- Gen.chooseNum[Int](-10, 10)
    kondition        <- Gen.chooseNum[Int](-10, 10)
    koerperkraft     <- Gen.chooseNum[Int](-10, 10)
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

  val genEnergien: Gen[Energien] = for {
    lebensenergie <- Gen.chooseNum[Int](1, 60)
    ausdauer      <- Gen.chooseNum[Int](1, 60)
    astralenergie <- Gen.chooseNum[Int](0, 100)
    karmaenergie  <- Gen.chooseNum[Int](0, 100)
  } yield Energien(
    Energiewert.Lebensenergie(lebensenergie),
    Energiewert.Ausdauer(ausdauer),
    Energiewert.Astralenergie(astralenergie),
    Energiewert.Karmaenergie(karmaenergie)
  )

  val genEnergieDiff: Gen[EnergieDiff] = for {
    lebensenergie <- Gen.chooseNum[Int](-10, 10)
    ausdauer      <- Gen.chooseNum[Int](-10, 10)
    astralenergie <- Gen.chooseNum[Int](-10, 10)
    karmaenergie  <- Gen.chooseNum[Int](-10, 10)
  } yield EnergieDiff(
    lebensenergie = lebensenergie,
    ausdauer = ausdauer,
    astralenergie = astralenergie,
    karmaenergie = karmaenergie
  )

  val genKampfwerte: Gen[Kampfwerte] = for {
    attacke    <- Gen.chooseNum[Int](0, 12)
    parade     <- Gen.chooseNum[Int](0, 12)
    fernkampf  <- Gen.chooseNum[Int](0, 12)
    initiative <- Gen.chooseNum[Int](0, 20)
  } yield Kampfwerte(
    attacke = Kampfwert.Attacke(attacke),
    parade = Kampfwert.Parade(parade),
    fernkampf = Kampfwert.Fernkampf(fernkampf),
    initiative = Kampfwert.Initiative(initiative)
  )

  val genKampfDiff: Gen[KampfDiff] = for {
    attacke    <- Gen.chooseNum[Int](-10, 10)
    parade     <- Gen.chooseNum[Int](-10, 10)
    fernkampf  <- Gen.chooseNum[Int](-10, 10)
    initiative <- Gen.chooseNum[Int](-10, 10)
  } yield KampfDiff(
    attacke = attacke,
    parade = parade,
    fernkampf = fernkampf,
    initiative = initiative
  )

  val genTalent: Gen[Talent] = Gen.oneOf[Talent](
    Talent.Sinnenschaerfe,
    Talent.Alchemie
  )
  val genTalentwert: Gen[Talentwert] = Gen.chooseNum[Int](-5, 25).map(Talentwert)
  val genTalente: Gen[Map[Talent, Talentwert]] = Gen.mapOf(
    for {
      talent     <- genTalent
      talentwert <- genTalentwert
    } yield (talent, talentwert)
  )

  val genTalentDiff: Gen[TalentDiff] = Gen
    .mapOf(
      for {
        talent     <- genTalent
        talentwert <- Gen.chooseNum[Int](1, 10) // TODO zero diff is actually allowed
        sign       <- Gen.oneOf[Int](-1, 1)
      } yield (talent, sign * talentwert)
    )
    .map(TalentDiff(_))

  val genKampftalent: Gen[Kampftalent] = Gen.oneOf[Kampftalent](
    Kampftalent.Saebel,
    Kampftalent.Staebe
  )
  val genKampftalentwert: Gen[Kampftalentwert] = for {
    attacke <- Gen.chooseNum[Int](0, 15)
    parade  <- Gen.chooseNum[Int](0, 15)
  } yield Kampftalentwert(Kampftalentwert.Attacke(attacke), Kampftalentwert.Parade(parade))
  val genKampftalente: Gen[Map[Kampftalent, Kampftalentwert]] = Gen.mapOf(
    for {
      kampftalent     <- genKampftalent
      kampftalentwert <- genKampftalentwert
    } yield (kampftalent, kampftalentwert)
  )

  val genKampfwertDiff: Gen[KampftalentwertDiff] = for {
    attacke <- Gen.chooseNum[Int](-10, 10)
    parade  <- Gen.chooseNum[Int](-10, 10)
  } yield KampftalentwertDiff(attacke = attacke, parade = parade)
  val genKampftalenteDiff: Gen[KampftalenteDiff] = Gen
    .mapOf(
      for {
        kampftalent     <- genKampftalent
        kampftalentwert <- genKampfwertDiff
      } yield (kampftalent, kampftalentwert)
    )
    .map(KampftalenteDiff(_))

  val genZauber: Gen[Zauber] = Gen.oneOf[Zauber](
    Pentagramma,
    FlimFlamFunkel
  )
  val genZauberfertigkeitswert: Gen[Zauberfertigkeitswert] = Gen.chooseNum[Int](-5, 25).map(Zauberfertigkeitswert)
  val genZauberfertigkeiten: Gen[Map[Zauber, Zauberfertigkeitswert]] = Gen.mapOf(
    for {
      zauber                <- genZauber
      zauberfertigkeitswert <- genZauberfertigkeitswert
    } yield (zauber, zauberfertigkeitswert)
  )

  val genZauberDiff: Gen[ZauberDiff] = Gen
    .mapOf(
      for {
        zauber                    <- genZauber
        zauberfertigkeitswertDiff <- Gen.chooseNum[Int](1, 10) // TODO zero diff is actually allowed
        sign                      <- Gen.oneOf[Int](-1, 1)
      } yield (zauber, sign * zauberfertigkeitswertDiff)
    )
    .map(ZauberDiff(_))

  val genSonderfertigkeit: Gen[Sonderfertigkeit] = Gen.oneOf[Sonderfertigkeit](
    Sonderfertigkeit.Ausweichen1,
    Sonderfertigkeit.Ausweichen2
  )
  val genSonderfertigkeiten: Gen[Set[Sonderfertigkeit]] = Gen.containerOf[Set, Sonderfertigkeit](genSonderfertigkeit)

  val genSonderfertigkeitenDiff: Gen[SonderfertigkeitenDiff] = for {
    add    <- genSonderfertigkeiten
    remove <- genSonderfertigkeiten
  } yield SonderfertigkeitenDiff(add, remove.diff(add))

  val genVorteil: Gen[Vorteil] = Gen.oneOf[Vorteil](
    Vorteil.GutesGedaechtnis,
    Vorteil.EidetischesGedaechtnis
  )
  val genVorteile: Gen[Set[Vorteil]] = Gen.containerOf[Set, Vorteil](genVorteil)

  val genVorteilDiff: Gen[VorteilDiff] = for {
    add    <- genVorteile
    remove <- genVorteile
  } yield VorteilDiff(add, remove.diff(add))

  val genNachteil: Gen[Nachteil] = Gen.oneOf[Nachteil](
    Nachteil.Arroganz,
    Nachteil.Goldgier
  )
  val genNachteile: Gen[Set[Nachteil]] = Gen.containerOf[Set, Nachteil](genNachteil)

  val genNachteilDiff: Gen[NachteilDiff] = for {
    add    <- genNachteile
    remove <- genNachteile
  } yield NachteilDiff(add, remove.diff(add))

  val genCharacter: Gen[Character] = for {
    eigenschaften      <- genEigenschaften
    energien           <- genEnergien
    kampfwerte         <- genKampfwerte
    talente            <- genTalente
    kampftalente       <- genKampftalente
    zauber             <- genZauberfertigkeiten
    sonderfertigkeiten <- genSonderfertigkeiten
    vorteile           <- genVorteile
    nachteile          <- genNachteile
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

  val genCharacterDiff: Gen[CharacterDiff] = for {
    eigenschaften      <- genEigenschaftsDiff
    energien           <- genEnergieDiff
    kampfwerte         <- genKampfDiff
    talente            <- genTalentDiff
    kampftalente       <- genKampftalenteDiff
    zauber             <- genZauberDiff
    sonderfertigkeiten <- genSonderfertigkeitenDiff
    vorteile           <- genVorteilDiff
    nachteile          <- genNachteilDiff
  } yield CharacterDiff(
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
