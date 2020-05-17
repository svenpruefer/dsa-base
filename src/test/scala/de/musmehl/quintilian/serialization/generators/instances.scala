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
import de.musmehl.quintilian.character.properties.Eigenschaftswert
import de.musmehl.quintilian.character.properties.Energie.Energien
import de.musmehl.quintilian.character.properties.Energiewert
import de.musmehl.quintilian.character.properties.Kampf.Kampfwerte
import de.musmehl.quintilian.character.properties.Kampfwert
import de.musmehl.quintilian.character.Character
import de.musmehl.quintilian.character.advantages.Vorteil
import de.musmehl.quintilian.character.disadvantages.Nachteil
import de.musmehl.quintilian.character.skills.Sonderfertigkeit
import de.musmehl.quintilian.character.talents.kampf.{Kampftalent, Kampftalentwert}
import de.musmehl.quintilian.character.talents.{Talent, Talentwert}
import de.musmehl.quintilian.magic.spell.{FlimFlamFunkel, Pentagramma, Zauber, Zauberfertigkeitswert}
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

  val genKampfwerte: Gen[Kampfwerte] = for {
    attacke    <- Gen.chooseNum[Int](0, 12)
    parade     <- Gen.chooseNum[Int](0, 12)
    fernkampf  <- Gen.chooseNum[Int](0, 12)
    initiative <- Gen.chooseNum[Int](0, 20)
  } yield Kampfwerte(
    Kampfwert.Attacke(attacke),
    Kampfwert.Parade(parade),
    Kampfwert.Fernkampf(fernkampf),
    Kampfwert.Initiative(initiative)
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

  val genZauber: Gen[Zauber] = Gen.oneOf(
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

  val genSonderfertigkeit: Gen[Sonderfertigkeit] = Gen.oneOf[Sonderfertigkeit](
    Sonderfertigkeit.Ausweichen1,
    Sonderfertigkeit.Ausweichen2
  )
  val genSonderfertigkeiten: Gen[Set[Sonderfertigkeit]] = Gen.containerOf[Set, Sonderfertigkeit](genSonderfertigkeit)

  val genVorteil: Gen[Vorteil] = Gen.oneOf[Vorteil](
    Vorteil.GutesGedaechtnis,
    Vorteil.EidetischesGedaechtnis
  )
  val genVorteile: Gen[Set[Vorteil]] = Gen.containerOf[Set, Vorteil](genVorteil)

  val genNachteil: Gen[Nachteil] = Gen.oneOf[Nachteil](
    Nachteil.Arroganz,
    Nachteil.Goldgier
  )
  val genNachteile: Gen[Set[Nachteil]] = Gen.containerOf[Set, Nachteil](genNachteil)

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

}
