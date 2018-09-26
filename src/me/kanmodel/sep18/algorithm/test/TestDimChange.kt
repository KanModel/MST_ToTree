package me.kanmodel.sep18.algorithm.test

import me.kanmodel.sep18.algorithm.print2D
import me.kanmodel.sep18.algorithm.util.DataHolder

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: kgdwhsk
 * Date: 2018-09-26
 * Time: 19:03
 */
fun main(args: Array<String>){
    val data = DataHolder.cost
//    data[0][2] = Int.MAX_VALUE
//    data[2][0] = Int.MAX_VALUE
    print2D(data)
    DataHolder.changeDimension(7)
    print2D(data)
    print2D(DataHolder.cost)

//    saveData(data)
    DataHolder.save()
}