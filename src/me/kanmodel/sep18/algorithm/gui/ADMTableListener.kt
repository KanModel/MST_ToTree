package me.kanmodel.sep18.algorithm.gui

import me.kanmodel.sep18.algorithm.gui.PictureADMTable.Companion.syncFlag
import me.kanmodel.sep18.algorithm.util.Log
import javax.swing.event.TableModelEvent
import javax.swing.event.TableModelListener
import javax.swing.table.TableModel

/**
 * ��������
 * ������������ݸı�
 * 1�����������ݷ���Խ������ݸı�
 * 2��������ı��һ�б�ʾ�кŵ�����
 * 3���ı�����ֵ����ķ����Ӧ�Գ�����
 * @author: KanModel
 * Date: 2018-10-13
 * Time: 10:28
 */
class ADMTableListener(private val tableModel: TableModel) : TableModelListener {

    /**
     * description: ��������ݸı�ʱ����
     * @author: KanModel
     */
    override fun tableChanged(e: TableModelEvent?) {
        if (!syncFlag) {
            val firstRow = e!!.firstRow
//            val lastRow = e.lastRow

            val column = e.column

            val type = e.type


            Log.i("$firstRow $column ${tableModel.getValueAt(firstRow, column)} $type")//������к� ���� �¼�����

            if (type == TableModelEvent.UPDATE) {
                syncFlag = true
                Log.i("ͬ���Խ���ֵ")
                tableModel.setValueAt(tableModel.getValueAt(firstRow, column), column - 1, firstRow + 1)
            }
        } else {
            syncFlag = false
        }

    }
}