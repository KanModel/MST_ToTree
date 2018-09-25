package me.kanmodel.sep18.algorithm

import javax.swing.JLabel
import javax.swing.JPanel

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: kgdwhsk
 * Date: 2018-09-25
 * Time: 14:26
 */
class WelcomePane :JPanel() {
    init {
        add(JLabel("当前邻接矩阵纬度${loadData().size}"))
    }
}