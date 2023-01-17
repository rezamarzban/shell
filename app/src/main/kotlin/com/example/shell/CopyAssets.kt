package com.example.shell

import android.content.Context
import java.io.File
import java.io.FileOutputStream

public fun copyAssets(context: Context) {
        val files = arrayListOf<File>()
        with(context) {
            assets?.list("")?.forEach { name ->
                        try {
                            val file: File = File(filesDir.absoluteFile, name)
                            val output = FileOutputStream(file)
                            assets.open(name).copyTo(output, 512)
                            output.close()
                            Runtime.getRuntime().exec("chmod 755 ${file.absolutePath}")
                                .waitFor()
                            files.add(file)
                        } catch (e: Exception) {
                            //error report
                        }
            }
        }
}           