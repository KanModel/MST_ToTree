package me.kanmodel.sep18.algorithm.gui

import me.kanmodel.sep18.algorithm.util.DataHolder
import java.awt.*
import java.util.regex.Pattern
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JTable
import javax.swing.WindowConstants
import javax.swing.table.AbstractTableModel


/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: kgdwhsk
 * Date: 2018-09-20
 * Time: 15:00
 */


/**
 * �ڽӾ��������
 * ADM Adjacency Matrix
 */
class PictureADMTable(private var dim: Int) : AbstractTableModel() {

    /**
     * ���ļ������ڽӾ�������
     */
    init {
        rowData = Array(dim) { IntArray(dim + 1) { 0 } }//����һ�к������
        for (i in 0 until dim) {
            for (j in 1..dim) {
                rowData[i][j] = DataHolder.cost[i][j - 1]
            }
        }
        for (i in 0 until dim) {
            rowData[i][0] = i + 1
        }

        columnNames = Array(dim + 2) { "" }//������ʼ��
        columnNames[1] = "����"
        for (i in 1..dim) {
            columnNames[i + 1] = i.toString()
        }

        names = DataHolder.names
    }

    //����������ʾ������
    override fun getRowCount(): Int {
        return rowData.size + 1
    }

    //����������ʾ������
    override fun getColumnCount(): Int {
        return columnNames.size
    }

    override fun getValueAt(rowIndex: Int, columnIndex: Int): Any {
        return if (columnIndex in 2 until columnCount && rowIndex in 1 until rowCount) {
            if (rowData[rowIndex - 1][columnIndex - 1] == Int.MAX_VALUE) {
                "��"
            } else {
                rowData[rowIndex - 1][columnIndex - 1]
            }
        } else if (columnIndex == 0) {
            if (rowIndex == 0) {
                "����"
            } else {
                rowIndex
            }
        } else if (columnIndex == 1 || rowIndex == 0) {
            when {
                rowIndex == columnIndex - 1 -> ""
                columnIndex == 1 -> names[rowIndex - 1]
                else -> names[columnIndex - 2]
            }
        } else {
            "gg"
        }
    }

    override fun getColumnName(column: Int): String {
        if (column in 0 until columnNames.size) {
            return columnNames[column]
        } else {
            return "gg"
        }
    }

    override fun isCellEditable(rowIndex: Int, columnIndex: Int): Boolean {
        if ((rowIndex + 1) == columnIndex || columnIndex == 0) {
            return false
        }
        return true
    }

    /**
     * �������ݸı�ʱ�����
     * ���������������������ʽ(\d*|��|max|m)������
     * ��������m,max����ʾ�����
     */
    override fun setValueAt(aValue: Any?, rowIndex: Int, columnIndex: Int) {
        if (rowIndex == 0 || columnIndex == 1) {
            when (columnIndex) {
                1 -> names[rowIndex - 1] = aValue.toString()
                else -> names[columnIndex - 2] = aValue.toString()
            }
            DataHolder.saveNames()
        } else {
            val regex = "\\d*|��|max|m"
            val p = Pattern.compile(regex)
            if (!p.matcher(aValue.toString()).matches()) {
                println("�����ϸ�ʽȡ���ı�ֵ")
                return
            }
            val real: Int =
                    if (aValue.toString() == "��" || aValue.toString() == "max" || aValue.toString() == "m") {
                        Int.MAX_VALUE
                    } else {
                        aValue.toString().toInt()
                    }
            rowData[rowIndex - 1][columnIndex - 1] = real
            for (i in 0 until dim) {
                for (j in 0 until dim) {
                    DataHolder.cost[i][j] = rowData[i][j + 1]
                }
            }
            DataHolder.save()
        }
        fireTableCellUpdated(rowIndex, columnIndex)
    }

    companion object {
        //������������[dim * (dim + 1)]
        var rowData: Array<IntArray> = Array(1) { IntArray(1 + 1) { 0 } }
        //��ͷ��������
        var columnNames = Array(1) { "" }

        var names: Array<String> = Array(1) { "��" }

        /**
         * ��ʶ�Ƿ�ı��������
         * ��ֹ������ѭ�������¼����޸Ķ�Ӧֵ����Ҳ���ڱ�������¼���
         */
        var syncFlag = false

        private fun showADMPictureTable(dim: Int = DataHolder.cost.size) {
            val jf = JFrame("����ͼͼ-�ڽӾ���")
            jf.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
            jf.addWindowListener(TableWindowListener(dim))//���Ӵ����¼�����

            jf.contentPane = getADMTablePane()
            jf.pack()
            jf.setLocationRelativeTo(null)
            jf.iconImage = Toolkit.getDefaultToolkit().getImage("icon_tree.png")
            jf.isVisible = true
        }

        fun getADMTablePane(dim: Int = DataHolder.cost.size): JPanel {
            val panel = JPanel(BorderLayout())

            val table = JTable(PictureADMTable(dim))

            table.columnModel.getColumn(0).cellRenderer = RowHeaderRenderer()//���õ�һ�и�ʽ
            table.rowHeight = 20//�����и� �п�
            for (i in 0..dim + 1) {
                table.columnModel.getColumn(i).preferredWidth = 40
            }

            table.tableHeader.resizingAllowed = true
            val tableModel = table.model
            tableModel.addTableModelListener(ADMTableListener(tableModel))

            panel.add(table.tableHeader, BorderLayout.NORTH)
            panel.add(table, BorderLayout.CENTER)

            return panel
        }

        @JvmStatic
        fun main(args: Array<String>) {
            showADMPictureTable()
            println("Hello")
        }

    }


}