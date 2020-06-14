/**
 * Copyright (C) 2020 AngrySoundTech
 * This file is part of SignCipher.
 *
 * SignCipher is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SignCipher is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SignCipher.  If not, see <https://www.gnu.org/licenses/>.
 */
package io.github.angarysoundtech

import io.github.angarysoundtech.renderer.EncryptedSignTileEntityRenderer
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
import net.minecraft.tileentity.SignTileEntity
import net.minecraftforge.fml.common.Mod
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.io.File

const val MOD_ID = "signcipher"

@Mod(MOD_ID)
object SignCipher {

    val logger: Logger = LogManager.getLogger(MOD_ID)

    val library: KeyLibrary

    init {
        TileEntityRendererDispatcher.instance.setSpecialRenderer(
            SignTileEntity::class.java,
            EncryptedSignTileEntityRenderer()
        )
        TileEntityRendererDispatcher.instance.getRenderer<SignTileEntity>(SignTileEntity::class.java)
            .setRendererDispatcher(TileEntityRendererDispatcher.instance)

        library = KeyLibrary(File(Minecraft.getInstance().gameDir, "signcipher/keys.json"))
    }
}
