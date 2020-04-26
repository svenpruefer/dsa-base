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
package de.musmehl.quintilian.gui

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.paint.Color.LightGreen

object MainStage {
  val entrance: JFXApp.PrimaryStage = new JFXApp.PrimaryStage {
    title.value = "Quintilian"
    width = 1600
    height = 900
    scene = new Scene {
      fill = LightGreen
    }
  }
}
