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
 * ��������
 * ������������ݸı�
 * 1�����������ݷ���Խ������ݸı�
 * 2��������ı��һ�б�ʾ�кŵ�����
 * 3���ı�����ֵ����ķ����Ӧ�Գ�����
 */
class TableListener(private val tableModel: TableModel) : TableModelListener {

    override fun tableChanged(e: TableModelEvent?) {
        val firstRow = e!!.firstRow
        val lastRow = e.lastRow

        val column = e.column

        val type = e.type


        println("$firstRow $column ${tableModel.getValueAt(firstRow, column)} $type")

        if (type == TableModelEvent.UPDATE) {
            if ((firstRow + 1) == column || column == 0) {
                return
            }

            if (!syncFlag) {
                syncFlag = true
                println("ͬ���Խ���ֵ")
                tableModel.setValueAt(tableModel.getValueAt(firstRow, column), column - 1, firstRow + 1)
            } else {
                syncFlag = false
            }
        }

    }
}