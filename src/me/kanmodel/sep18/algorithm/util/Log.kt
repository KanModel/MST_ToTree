package me.kanmodel.sep18.algorithm.util

import me.kanmodel.sep18.algorithm.Main

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: KanModel
 * Date: 2018-10-13
 * Time: 9:59
 */
object Log {
    fun i(message: Any?) {
        if (Main.isDebug) {
            println(message)
        }
    }

    fun w(message: Any?) {
        println(message)
    }
}