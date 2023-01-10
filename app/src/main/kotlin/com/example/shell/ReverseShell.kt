package com.example.shell

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.InputStream
import java.util.concurrent.TimeUnit

private const val _SHELL_PATH = "system/bin/sh"
suspend fun shell(cmd: String): String {
    val process = Runtime.getRuntime().exec(_SHELL_PATH)
    val result =  process.start(cmd)
    return result
}

private fun Process.start(cmd: String): String {
    write(cmd)
    val input = read()
    destroy()
    return input
}

private fun Process.write(value: String){
    outputStream.bufferedWriter().use { writer ->
        writer.write(value)
        writer.flush()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun Process.read(): String {
    return StringBuffer().let { buf ->
        buf.readStream(errorStream)
        buf.readStream(inputStream)
        //test this with a ping or some other time consuming operation
        waitFor(20, TimeUnit.SECONDS)
        buf.appendln("EXIT VALUE: ${exitValue()}")
    }.toString()
}

private fun StringBuffer.readStream(stream: InputStream) = stream.bufferedReader().useLines { it.forEach { ln -> this.appendln(ln) } }