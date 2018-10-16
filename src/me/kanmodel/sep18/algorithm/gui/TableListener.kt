package me.kanmodel.sep18.algorithm.gui

import me.kanmodel.sep18.algorithm.gui.PictureTable.Companion.syncFlag
import javax.swing.event.TableModelEvent
import javax.swing.event.TableModelListener
import javax.swing.table.TableModel

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: kgdwhsk
 * Date: 2018-10-13
 * Time: 10:28
 */
/**
 * 表格监听器
 * 监听表格内数据改变
 * 1、不允许数据方阵对角线数据改变
 * 2、不允许改变第一列标示行号的内容
 * 3、改变任意值后更改方阵对应对称数据
 */
class TableListener(private val tableModel: TableModel) : TableModelListener {

    override fun tableChanged(e: TableModelEvent?) {
        val firstRow = e!!.firstRow
        val lastRow = e.lastRow

        val column = e.column

        val type = e.type


        println("$firstRow $column ${tableModel.getValueAt(firstRow, column)} $type")//输出行列号 内容 事件代号

        if (type == TableModelEvent.UPDATE) {
            if ((firstRow + 1) == column || column == 0) {
                return
            }

            if (!syncFlag) {
                syncFlag = true
                println("同步对角数值")
                tableModel.setValueAt(tableModel.getValueAt(firstRow, column), column - 1, firstRow + 1)
            } else {
                syncFlag = false
            }
        }

    }
}