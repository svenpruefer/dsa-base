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
package de.musmehl.quintilian.magic

sealed trait SpellAttribute {}

object SpellAttribute {

  case object Antimagie              extends SpellAttribute
  case object Beschwoerung           extends SpellAttribute
  case object DaemonischAllgemein    extends SpellAttribute
  case object DaemonischAgrimoth     extends SpellAttribute
  case object DaemonischAmazeroth    extends SpellAttribute
  case object DaemonischAsfalot      extends SpellAttribute
  case object DaemonischBelhalhar    extends SpellAttribute
  case object DaemonischBlakharaz    extends SpellAttribute
  case object DaemonischLolgramoth   extends SpellAttribute
  case object DaemonischBelzhorash   extends SpellAttribute
  case object DaemonischThargunitoth extends SpellAttribute
  case object Eigenschaften          extends SpellAttribute
  case object Einfluss               extends SpellAttribute
  case object ElementarAllgemein     extends SpellAttribute
  case object ElementarEis           extends SpellAttribute
  case object ElementarErz           extends SpellAttribute
  case object ElementarFeuer         extends SpellAttribute
  case object ElementarHumus         extends SpellAttribute
  case object ElementarLuft          extends SpellAttribute
  case object ElementarWasser        extends SpellAttribute
  case object Form                   extends SpellAttribute
  case object Geisterwesen           extends SpellAttribute
  case object Heilung                extends SpellAttribute
  case object Hellsicht              extends SpellAttribute
  case object Herbeirufung           extends SpellAttribute
  case object Herrschaft             extends SpellAttribute
  case object Illusion               extends SpellAttribute
  case object Kraft                  extends SpellAttribute
  case object Limbus                 extends SpellAttribute
  case object Metamagie              extends SpellAttribute
  case object Objekt                 extends SpellAttribute
  case object Schaden                extends SpellAttribute
  case object Telekinese             extends SpellAttribute
  case object Temporal               extends SpellAttribute
  case object Umwelt                 extends SpellAttribute
  case object Verstaendigung         extends SpellAttribute

}
