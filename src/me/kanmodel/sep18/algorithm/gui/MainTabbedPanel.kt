package me.kanmodel.sep18.algorithm.gui

import me.kanmodel.sep18.algorithm.Prim.Companion.getTreeStepPanel
import me.kanmodel.sep18.algorithm.gui.CoorTable.Companion.getCoorTablePane
import me.kanmodel.sep18.algorithm.gui.PictureADMTable.Companion.getADMTablePane
import me.kanmodel.sep18.algorithm.gui.TreePanel.Companion.getPicPanel
import me.kanmodel.sep18.algorithm.util.Log
import java.awt.Font
import javax.swing.JTabbedPane

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: kgdwhsk
 * Date: 2018-09-25
 * Time: 14:20
 */
class MainTabbedPanel : JTabbedPane() {

    init {
        font = Font(null, Font.PLAIN, 18)
//        addTab("欢迎", WelcomePanel())
        addTab("欢迎", null, WelcomePanel(), "主界面")
        addTab("矩阵邻接矩阵", null, null, "显示/编辑当前邻接矩阵")
        addTab("结点坐标", null, null, "显示/编辑当前结点显示坐标")
        addTab("原图", null, null, "根据当前邻接矩阵画出带权无向图")
        addTab("最小生成树结果", null, null, "显示当前邻接矩阵下的最小生成树详细步骤")
        selectedIndex = 0
        addChangeListener {
            Log.i("当前选中的选项卡index: $selectedIndex")
            when (selectedIndex) {
                TABLE -> setComponentAt(TABLE, getADMTablePane())//邻接矩阵表格
                COORDINATE_TABLE -> setComponentAt(COORDINATE_TABLE, getCoorTablePane())//邻接矩阵表格
                SOURCE_PICTURE -> setComponentAt(SOURCE_PICTURE, getPicPanel())//原图
                STEP_PICTURE -> setComponentAt(STEP_PICTURE, getTreeStepPanel().scrollPanel)//步骤图
            }
        }
    }

    companion object {
        const val TABLE = 1
        const val COORDINATE_TABLE = 2
        const val SOURCE_PICTURE = 3
        const val STEP_PICTURE = 4
    }
}