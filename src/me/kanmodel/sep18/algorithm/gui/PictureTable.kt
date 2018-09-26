package me.kanmodel.sep18.algorithm.gui

import me.kanmodel.sep18.algorithm.util.DataHolder
import me.kanmodel.sep18.algorithm.print2D
import java.awt.*
import java.util.regex.Pattern
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JTable
import javax.swing.WindowConstants
import javax.swing.event.TableModelEvent
import javax.swing.event.TableModelListener
import javax.swing.table.AbstractTableModel
import javax.swing.table.TableModel
import javax.swing.SwingConstants
import javax.swing.UIManager
import javax.swing.JLabel
import javax.swing.table.TableCellRenderer


/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: kgdwhsk
 * Date: 2018-09-20
 * Time: 15:00
 */


/**
 * ������������[dim * (dim + 1)]
 */
var rowData: Array<IntArray> = Array(1) { IntArray(1 + 1) { 0 } }

/**
 * ��ͷ��������
 */
var columnNames = Array(1) { "" }

/**
 * ������
 */
class PictureTable(private var dim: Int) : AbstractTableModel() {

    /**
     * ���ļ������ڽӾ�������
     */
    init {
//        val data = DataHolder.cost//����ԭʼ����
//        dim = data.size - 1
        rowData = Array(dim) { IntArray(dim + 1) { 0 } }//����һ�к������
        for (i in 0 until dim) {
            for (j in 1..dim) {
                rowData[i][j] = DataHolder.cost[i][j - 1]
            }
        }
        columnNames = Array(dim + 1) { "" }
        for (i in 0 until dim) {
            rowData[i][0] = i + 1
        }

        print2D(rowData)

        for (i in 1..dim) {
            columnNames[i] = i.toString()
        }
    }


    override fun getRowCount(): Int {
        return rowData.size
    }

    override fun getColumnCount(): Int {
        return columnNames.size
    }

    override fun getValueAt(rowIndex: Int, columnIndex: Int): Any {
        if (rowData[rowIndex][columnIndex] == Int.MAX_VALUE)
            return "��"
        return rowData[rowIndex][columnIndex]
    }

    override fun getColumnName(column: Int): String {
        return columnNames[column]
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
        rowData[rowIndex][columnIndex] = real
//        val data = Array(dim) { IntArray(dim) { 0 } }
        for (i in 0 until dim) {
            for (j in 0 until dim) {
                DataHolder.cost[i][j] = rowData[i][j + 1]
            }
        }
//        saveData(data)
        DataHolder.save(DataHolder.cost)
        fireTableCellUpdated(rowIndex, columnIndex)
    }

}

/**
 * ���������
 * �������������ݸı�
 * 1�����������ݷ���Խ������ݸı�
 * 2���������ı��һ�б�ʾ�кŵ�����
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

/**
 * �����һ����ʽ
 * �������ͷ��ʽһ��
 */
internal class RowHeaderRenderer : TableCellRenderer {

    private val label = JLabel()
    override fun getTableCellRendererComponent(table: JTable, value: Any, isSelected: Boolean, hasFocus: Boolean, row: Int, column: Int): Component {
        label.font = font
        label.horizontalAlignment = SwingConstants.CENTER
        label.text = value.toString()
        label.isOpaque = true
        label.foreground = fgc
        label.background = bgc
        return label
    }

    companion object {
        // ��ȡ��ͷ�����塢ǰ��ɫ�ͱ���ɫ��������Labelαװ�ɱ�ͷ������
        private val font = UIManager.get("TableHeader.font") as Font
        private val fgc = UIManager.get("TableHeader.foreground") as Color
        private val bgc = UIManager.get("TableHeader.background") as Color
    }
}

/**
 * ��ʶ�Ƿ�ı��������
 * ��ֹ������ѭ�������¼����޸Ķ�Ӧֵ����Ҳ���ڱ�������¼���
 */
var syncFlag = false


fun showPictureTable(dim: Int = DataHolder.cost.size) {
    val jf = JFrame("����ͼͼ-�ڽӾ���")
    jf.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    jf.addWindowListener(TableWindowListener(dim))//���Ӵ����¼�����

    jf.contentPane = getTablePane()
    jf.pack()
    jf.setLocationRelativeTo(null)
    jf.iconImage = Toolkit.getDefaultToolkit().getImage("icon_tree.png")
    jf.isVisible = true
}

fun getTablePane(dim: Int = DataHolder.cost.size): JPanel {
    val panel = JPanel(BorderLayout())

    val table = JTable(PictureTable(dim))

    table.columnModel.getColumn(0).cellRenderer = RowHeaderRenderer()//���õ�һ�и�ʽ
    table.rowHeight = 20//�����и� �п�
    for (i in 0..dim) {
        table.columnModel.getColumn(i).preferredWidth = 40
    }

    table.tableHeader.resizingAllowed = true
    val tableModel = table.model
    tableModel.addTableModelListener(TableListener(tableModel))

    panel.add(table.tableHeader, BorderLayout.NORTH)
    panel.add(table, BorderLayout.CENTER)

    return panel
}

fun main(args: Array<String>) {
//    saveData()

    showPictureTable()
    println("Hello")
}