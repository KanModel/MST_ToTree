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
 * 表格内容数组[dim * (dim + 1)]
 */
var rowData: Array<IntArray> = Array(1) { IntArray(1 + 1) { 0 } }

/**
 * 表头（列名）
 */
var columnNames = Array(1) { "" }

/**
 * 表格类
 */
class PictureTable(private var dim: Int) : AbstractTableModel() {

    /**
     * 从文件加载邻接矩阵数据
     */
    init {
//        val data = DataHolder.cost//加载原始数组
//        dim = data.size - 1
        rowData = Array(dim) { IntArray(dim + 1) { 0 } }//添加一列后的数组
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
            return "∞"
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
     * 表格数据改变时候调用
     * 表格仅能输入符合正则表达式(\d*|∞|max|m)的内容
     * 允许输入m,max来表示无穷大
     */
    override fun setValueAt(aValue: Any?, rowIndex: Int, columnIndex: Int) {
        val regex = "\\d*|∞|max|m"
        val p = Pattern.compile(regex)
        if (!p.matcher(aValue.toString()).matches()) {
            println("不符合格式取消改变值")
            return
        }
        val real: Int =
                if (aValue.toString() == "∞" || aValue.toString() == "max" || aValue.toString() == "m") {
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


        println("$firstRow $column ${tableModel.getValueAt(firstRow, column)} $type")

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

/**
 * 表格第一列样式
 * 保持与表头样式一致
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
        // 获取表头的字体、前景色和背景色，用来将Label伪装成表头的样子
        private val font = UIManager.get("TableHeader.font") as Font
        private val fgc = UIManager.get("TableHeader.foreground") as Color
        private val bgc = UIManager.get("TableHeader.background") as Color
    }
}

/**
 * 标识是否改变表格内容
 * 防止陷入死循环调用事件（修改对应值本身也属于表格更新事件）
 */
var syncFlag = false


fun showPictureTable(dim: Int = DataHolder.cost.size) {
    val jf = JFrame("无向图图-邻接矩阵")
    jf.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    jf.addWindowListener(TableWindowListener(dim))//添加窗口事件监听

    jf.contentPane = getTablePane()
    jf.pack()
    jf.setLocationRelativeTo(null)
    jf.iconImage = Toolkit.getDefaultToolkit().getImage("icon_tree.png")
    jf.isVisible = true
}

fun getTablePane(dim: Int = DataHolder.cost.size): JPanel {
    val panel = JPanel(BorderLayout())

    val table = JTable(PictureTable(dim))

    table.columnModel.getColumn(0).cellRenderer = RowHeaderRenderer()//设置第一列格式
    table.rowHeight = 20//设置行高 列宽
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
