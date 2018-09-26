package me.kanmodel.sep18.algorithm.util

import me.kanmodel.sep18.algorithm.util.FileExecutor.loadData
import me.kanmodel.sep18.algorithm.util.FileExecutor.saveData

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: kgdwhsk
 * Date: 2018-09-25
 * Time: 17:54
 */
object DataHolder {
    var cost = loadData()
    fun reload(){
        cost = loadData()
    }

    fun save(data: Array<IntArray> = cost){
        saveData(data)
    }
}