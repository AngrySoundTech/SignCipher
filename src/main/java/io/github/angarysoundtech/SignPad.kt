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

import io.github.angarysoundtech.pad.PadLibrary
import io.github.angarysoundtech.renderer.EncryptedSignTileEntityRenderer
import net.alexwells.kottle.FMLKotlinModLoadingContext
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
import net.minecraft.tileentity.SignTileEntity
import net.minecraftforge.fml.ModLoadingContext
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.config.ModConfig
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.io.File

const val MOD_ID = "signpad"

@Mod(MOD_ID)
object SignPad {

    val logger: Logger = LogManager.getLogger(MOD_ID)

    val library: PadLibrary

    init {
        TileEntityRendererDispatcher.instance.setSpecialRenderer(
            SignTileEntity::class.java,
            EncryptedSignTileEntityRenderer()
        )
        TileEntityRendererDispatcher.instance.getRenderer<SignTileEntity>(SignTileEntity::class.java)
            .setRendererDispatcher(TileEntityRendererDispatcher.instance)

        library = PadLibrary(File(Minecraft.getInstance().gameDir, "signpad/pads.json"))
    }
}
