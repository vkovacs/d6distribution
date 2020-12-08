package hu.crs.d6distribution

import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color
import javafx.stage.Stage
import java.text.DecimalFormat
import java.util.concurrent.ThreadLocalRandom


class Main : Application() {
    private val columnLineWidth = 50.0
    private val textLineWidth = 1.0

    override fun start(primaryStage: Stage) {

        primaryStage.title = "Drawing Operations Test"
        primaryStage.isMaximized = true
        val root = Group()
        val canvas = Canvas(2000.0, 1000.0)
        val gc: GraphicsContext = canvas.graphicsContext2D
        drawDistribution(gc)
        root.children.add(canvas)
        primaryStage.scene = Scene(root)
        primaryStage.show()
    }

    private fun drawDistribution(gc: GraphicsContext) {
        gc.lineWidth = columnLineWidth

        var x = 400.0 + gc.lineWidth
        val y = 800.0
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