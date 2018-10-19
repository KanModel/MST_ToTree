package me.kanmodel.sep18.algorithm.gui

import me.kanmodel.sep18.algorithm.gui.PictureADMTable.Companion.syncFlag
import javax.swing.event.TableModelEvent
import javax.swing.event.TableModelListener
import javax.swing.table.TableModel

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: KanModel
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
class ADMTableListener(private val tableModel: TableModel) : TableModelListener {

    override fun tableChanged(e: TableModelEvent?) {
        if (!syncFlag) {
            val firstRow = e!!.firstRow
//            val lastRow = e.lastRow

            val column = e.column

            val type = e.type


            println("$firstRow $column ${tableModel.getValueAt(firstRow, column)} $type")//������к� ���� �¼�����

            if (type == TableModelEvent.UPDATE) {
                syncFlag = true
                println("ͬ���Խ���ֵ")
                tableModel.setValueAt(tableModel.getValueAt(firstRow, column), column - 1, firstRow + 1)
            }
        } else {
            syncFlag = false
        }

    }
}