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

import com.google.gson.GsonBuilder
import java.io.File

class KeyLibrary(val file: File) {

    private val gson = GsonBuilder().setPrettyPrinting().create()

    var keys = mutableMapOf("example" to "key")

    init {
        if (!file.exists()) {
            file.parentFile.mkdirs()
            file.createNewFile()
            save()
        }

        load()
    }

    fun save() {
        file.writeText(gson.toJson(keys))
    }

    fun load() {
        keys = gson.fromJson(file.readText(), HashMap::class.java) as MutableMap<String, String>
    }
}
