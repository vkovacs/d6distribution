package hu.crs.d6distribution

import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color
import javafx.stage.Screen
import javafx.stage.Stage
import java.text.DecimalFormat
import java.util.concurrent.ThreadLocalRandom


class Main : Application() {
    val width = 1920
    val height = 1080

    val columnLineWidth = 50.0
    private val textLineWidth = 1.0

    override fun start(primaryStage: Stage) {
        primaryStage.title = "Drawing Operations Test"
        primaryStage.isMaximized = true


        val canvas = Canvas(Screen.getPrimary().bounds.width, Screen.getPrimary().bounds.height)
        drawDistributionOnCanvas(canvas.graphicsContext2D)
        val rootGroup = Group()
        rootGroup.children.add(canvas)
        primaryStage.scene = Scene(rootGroup)
        primaryStage.show()
    }

    private fun drawDistributionOnCanvas(gc: GraphicsContext) {
        gc.lineWidth = columnLineWidth

        var x = width / 5.0 + gc.lineWidth
        val y = (height / 3.0) * 2
        val dec = DecimalFormat()
        for (distribution in distribution()) {

            gc.stroke = randomColor()
            gc.strokeLine(x, y, x, y - distribution.value / 1000)
            gc.lineWidth = textLineWidth
            gc.strokeText(distribution.key.toString(), x - 10, y + 100)
            gc.strokeText("# " + dec.format(distribution.value), x - 10, y + 200)
            gc.lineWidth = columnLineWidth
            x += 100
        }
    }

    private fun randomColor(): Color {
        val r = ThreadLocalRandom.current().nextDouble(0.0, 0.7);
        val g = ThreadLocalRandom.current().nextDouble(0.0, 0.7);
        val b = ThreadLocalRandom.current().nextDouble(0.0, 0.7);

        return Color(r, g, b, 1.0)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }
}