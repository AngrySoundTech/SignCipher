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
package io.github.angarysoundtech.handler

import io.github.angarysoundtech.MOD_ID
import io.github.angarysoundtech.SignCipher
import io.github.angarysoundtech.util.encrypt
import net.minecraft.client.gui.screen.EditSignScreen
import net.minecraft.client.gui.widget.Widget
import net.minecraft.client.gui.widget.button.Button
import net.minecraft.client.resources.I18n
import net.minecraft.tileentity.SignTileEntity
import net.minecraft.util.text.StringTextComponent
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.client.event.GuiScreenEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import kotlin.math.max

@Mod.EventBusSubscriber(Dist.CLIENT, modid = MOD_ID)
object InitGuiHandler {

    private val regex = Regex("^\\[(.+)]$")

    @SubscribeEvent
    fun onInitGuiEvent(event: GuiScreenEvent.InitGuiEvent) {
        val gui = event.gui

        if (gui is EditSignScreen) {
            var maxY = 0
            for (button: Widget in event.widgetList) {
                maxY = max(button.y, maxY)
            }

            val tileField = EditSignScreen::class.java.getDeclaredField("tileSign")
            tileField.isAccessible = true
            val tile = tileField.get(event.gui) as SignTileEntity

            event.addWidget(Button(gui.width / 2 - 100, maxY + 24, 200, 20, I18n.format("signCipher.gui.encrypt")) { button ->
                val match = regex.find(tile.getText(0).unformattedComponentText)

                // If the first line of the sign matches our expected format
                if (match != null) {
                    val key = match.groupValues[1]

                    // If we have an OTP for the key
                    if (SignCipher.library.keys.containsKey(key)) {
                        // Encrypt each following line
                        for (i in 1..3) {
                            val encrypted = encrypt(tile.getText(i).formattedText, SignCipher.library.keys[key]!!)
                            tile.setText(i, StringTextComponent(encrypted))
                        }
                    }
                }

                tile.markDirty()
                gui.minecraft.displayGuiScreen(null)
            })
        }
    }
}