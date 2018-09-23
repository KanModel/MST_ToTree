package me.KanModel.sep18.algorithm

import me.KanModel.sep18.algorithm.print2D
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Component
import java.awt.Font
import java.awt.event.WindowEvent
import java.awt.event.WindowListener
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
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
private var rowData: Array<IntArray> = Array(1) { IntArray(1 + 1) { 0 } }

class PictureTable(private val dim: Int = 6) : AbstractTableModel() {
    /**
     * 表头（列名）
     */
    private val columnNames = Array(dim + 1) { "" }

    /**
     * 从文件加载邻接矩阵数据
     */
    init {
        val data = loadData()
        rowData = Array(dim) { IntArray(dim + 1) { 0 } }
        for (i in 0 until dim) {
            for (j in 1..dim) {
                rowData[i][j] = data[i][j - 1]
            }
        }

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

    override fun setValueAt(aValue: Any?, rowIndex: Int, columnIndex: Int) {
        rowData[rowIndex][columnIndex] = (aValue.toString().toInt())
        fireTableCellUpdated(rowIndex, columnIndex)
    }

}

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

var syncFlag = false

fun saveData(cost: Array<IntArray> = arrayOf(
        intArrayOf(0, 10, Int.MAX_VALUE, 30, 45, Int.MAX_VALUE),
        intArrayOf(10, 0, 50, Int.MAX_VALUE, 40, 25),
        intArrayOf(Int.MAX_VALUE, 50, 0, Int.MAX_VALUE, 35, 15),
        intArrayOf(30, Int.MAX_VALUE, Int.MAX_VALUE, 0, Int.MAX_VALUE, 20),
        intArrayOf(45, 40, 35, Int.MAX_VALUE, 0, 55),
        intArrayOf(Int.MAX_VALUE, 25, 15, 20, 55, 0))) {


    val file = File("data\\data.txt")
    if (!file.exists()) {
        file.parentFile.mkdirs()
    }

    var data = ""
    for (i in 0..5) {
        for (j in 0..5) {
            data += "${cost[i][j]}\t"
        }
        data += "\n"
    }

    val fos = FileOutputStream(file)
//    fos.write("Te\tst".toByteArray())
    fos.write(data.toByteArray())

    fos.close()
}

fun loadSourceData(): String {
//    val str = ""
    val file = File("data\\data.txt")

    val fileInputStream = FileInputStream(file)
    val size = fileInputStream.available()

    var buffer = ByteArray(size)
    fileInputStream.read(buffer)
    fileInputStream.close()

    return String(buffer)
}

fun loadData(): Array<IntArray> {
    val source = loadSourceData()
    println("load :\n$source")
    val data = Array(6) { IntArray(6) }
    println("分割第一步")
    var s1 = source.split("\n")
    s1.forEach { s: String -> println(s) }
    println(data.size)
    println("分割第二步")
    for (i in 0..5) {
        val s2 = s1[i].split("\t")
        for (j in 0..5) {
            data[i][j] = s2[j].toInt()
        }
    }
    return data
}

fun showPictureTable(dim: Int = 6) {
    val jf = JFrame("无向图图-邻接矩阵")
    jf.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    jf.addWindowListener(object : WindowListener {
        override fun windowDeiconified(e: WindowEvent?) {
        }

        override fun windowClosing(e: WindowEvent?) {
//            println("关闭中？")
/*            for (i in 0 until dim) {
                for (j in 1..dim) {
                    System.out.printf("%-3s ", rowData[i][j])
                }
                println()
            }*/
            val data = Array(dim) { IntArray(dim) { 0 } }
            for (i in 0 until dim) {
                for (j in 0 until dim) {
//                    rowData[i][j] = data[i][j - 1]
                    data[i][j] = rowData[i][j + 1]
                }
            }
            print2D(data)
            saveData(data)
        }

        override fun windowClosed(e: WindowEvent?) {
            println("关闭了")
        }

        override fun windowActivated(e: WindowEvent?) {
        }

        override fun windowDeactivated(e: WindowEvent?) {
        }

        override fun windowOpened(e: WindowEvent?) {
        }

        override fun windowIconified(e: WindowEvent?) {
        }
    })
    val panel = JPanel(BorderLayout())
    val table = JTable(PictureTable(dim))
    table.columnModel.getColumn(0).cellRenderer = RowHeaderRenderer()//设置第一列格式
    table.rowHeight = 20
    for (i in 0..dim) {
        table.columnModel.getColumn(i).preferredWidth = 40
    }
    table.tableHeader.resizingAllowed = true
    val tableModel = table.model
    tableModel.addTableModelListener(TableListener(tableModel))

    panel.add(table.tableHeader, BorderLayout.NORTH)
    panel.add(table, BorderLayout.CENTER)

    jf.contentPane = panel
    jf.pack()
    jf.setLocationRelativeTo(null)
    jf.isVisible = true
}

fun main(args: Array<String>) {
//    saveData()

    val dim = 6

    showPictureTable(dim)
    println("Hello")
}
