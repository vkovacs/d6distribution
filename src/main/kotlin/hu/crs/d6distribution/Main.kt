package hu.crs.d6distribution

import javafx.application.Application
import javafx.scene.Group
import javafx.stage.Stage
import javafx.scene.Scene
import javafx.scene.canvas.Canvas

import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color


class Main : Application() {
    override fun start(primaryStage: Stage) {

        primaryStage.title = "Drawing Operations Test"
        val root = Group()
        val canvas = Canvas(2000.0, 1000.0)
        val gc: GraphicsContext = canvas.graphicsContext2D
        drawDistribution(gc)
        root.children.add(canvas)
        primaryStage.scene = Scene(root)
        primaryStage.show()
    }
    private fun drawDistribution(gc: GraphicsContext) {
        gc.fill = Color.GREEN
        gc.stroke = Color.BLUE
        gc.lineWidth = 5.0

        for (distribution in distribution()) {
            gc.strokeLine(400.0, 100.0, 100.0, 400.0)
        }

    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }
}