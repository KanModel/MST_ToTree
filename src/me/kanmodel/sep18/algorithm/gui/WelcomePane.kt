package me.kanmodel.sep18.algorithm.gui

import me.kanmodel.sep18.algorithm.util.DataHolder
import java.util.regex.Pattern
import javax.swing.*

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: kgdwhsk
 * Date: 2018-09-25
 * Time: 14:26
 */
class WelcomePane :JPanel() {
    var pattern = Pattern.compile("^[-\\+]?[\\d]*$")
    init {
        val dimLabel = JLabel("当前邻接矩阵纬度: ${DataHolder.cost.size}")
        add(dimLabel)
//        add(JTextArea("${DataHolder.cost.size}"))

        val btn_changeDim = JButton("更改维度")
        btn_changeDim.addActionListener{
            val result = JOptionPane.showInputDialog(
                    this,
                    "输入新的维度",
                    "6"
            )
            if (result != null) {
                if (pattern.matcher(result).matches()) {
                    println("Result: ${result.toInt()}")
                    if (result.toInt() > 0) {
                        DataHolder.changeDimension(result.toInt())
                        dimLabel.text = "当前邻接矩阵纬度: ${DataHolder.cost.size}"
                    }
                }
            }
        }
        add(btn_changeDim)
    }
}