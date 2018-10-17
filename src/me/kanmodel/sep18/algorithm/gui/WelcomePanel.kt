package me.kanmodel.sep18.algorithm.gui

import me.kanmodel.sep18.algorithm.util.DataHolder
import me.kanmodel.sep18.algorithm.util.FileExecutor
import java.awt.Component
import java.awt.Font
import java.io.File
import java.util.regex.Pattern
import javax.swing.*

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: kgdwhsk
 * Date: 2018-09-25
 * Time: 14:26
 */
class WelcomePanel :JPanel() {
    var pattern = Pattern.compile("^[-\\+]?[\\d]*$")
    init {
        val mainBox = Box.createVerticalBox()
        add(mainBox)

        val hBox01 = Box.createHorizontalBox()
        val hBox02 = Box.createHorizontalBox()
        mainBox.add(hBox01)
        mainBox.add(Box.createVerticalGlue())
        mainBox.add(hBox02)

        val dimLabel = JLabel("当前邻接矩阵纬度: ${DataHolder.cost.size}")
        dimLabel.font = Font(null, Font.BOLD, 30)
        val btnChangeDim = JButton("更改维度")
        btnChangeDim.font = Font(null, Font.ITALIC, 30)
        btnChangeDim.addActionListener{
            val result = JOptionPane.showInputDialog(
                    this,
                    "输入新的维度",
                    ""
            )
            if (result != null) {
                if (pattern.matcher(result).matches() && result.isNotEmpty()) {
                    println("Result: ${result.toInt()}")
                    if (result.toInt() > 0) {
                        DataHolder.changeDimension(result.toInt())
                        dimLabel.text = "当前邻接矩阵纬度: ${DataHolder.cost.size}"
                    }
                }
            }
        }
        val welcomeLabel = JLabel("<html><p>欢迎使用</p><p>最小生成树生成器</p></html>")
        welcomeLabel.font = Font(null, Font.BOLD, 64)
        hBox01.add(dimLabel)
        hBox01.add(btnChangeDim)
        hBox02.add(welcomeLabel)

        val btnImportFile = JButton("加载数据")
        btnImportFile.font = Font(null, Font.ITALIC, 30)
        btnImportFile.addActionListener {
            showFileOpenDialog(this)
            dimLabel.text = "当前邻接矩阵纬度: ${DataHolder.cost.size}"
        }

        hBox01.add(Box.createHorizontalStrut(10))
        hBox01.add(btnImportFile)
    }

    companion object {
        fun showFileOpenDialog(parent: Component) {
            val fileChooser = JFileChooser()

            fileChooser.currentDirectory = File(".")

            fileChooser.fileSelectionMode = JFileChooser.FILES_AND_DIRECTORIES
            fileChooser.isMultiSelectionEnabled = false

            val result = fileChooser.showOpenDialog(parent)

            if (result == JFileChooser.APPROVE_OPTION) {
                val file = fileChooser.selectedFile

                println("FILE: ${file.absolutePath}")
                FileExecutor.readFile(file)
            }
        }
    }
}