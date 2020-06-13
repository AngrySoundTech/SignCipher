/**
 * Copyright (C) 2020 AngrySoundTech
 * This file is part of SignPad.
 *
 * SignPad is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SignPad is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SignPad.  If not, see <https://www.gnu.org/licenses/>.
 */
package io.github.angarysoundtech

import net.minecraftforge.fml.common.Mod
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

const val MOD_ID = "signpad"

@Mod(MOD_ID)
object SignPad {

    val logger: Logger = LogManager.getLogger(MOD_ID)

    init {
    }

}