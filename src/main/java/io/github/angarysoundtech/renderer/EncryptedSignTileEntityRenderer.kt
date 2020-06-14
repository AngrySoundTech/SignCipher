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
package io.github.angarysoundtech.renderer

import io.github.angarysoundtech.util.decryptSign
import io.github.angarysoundtech.util.getKeyForSign
import io.github.angarysoundtech.util.getTagForSign
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer
import net.minecraft.tileentity.SignTileEntity

class EncryptedSignTileEntityRenderer : SignTileEntityRenderer() {

    override fun render(te: SignTileEntity, x: Double, y: Double, z: Double, partialTicks: Float, destroyStage: Int) {
        if (Minecraft.getInstance().currentScreen == null) {
            val tag = getTagForSign(te)

            if (tag != null) {
                val key = getKeyForSign(te)

                if (key != null) {
                    decryptSign(te, key, tag)
                }
            }
        }

        super.render(te, x, y, z, partialTicks, destroyStage)
    }
}
