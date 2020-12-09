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
    private var width = Screen.getPrimary().bounds.width
    private var height = Screen.getPrimary().bounds.height
    private val throwCount = 1000000

    private val distribution = distribution(throwCount)

    private val yScale = (distribution.values.maxOrNull())!! / ((height / 10) * 4)
    private val xScale = 20.0
    private val barLineScale = width / 30.0
    private val textLineScale = width / 2000

    override fun start(primaryStage: Stage) {
        primaryStage.title = "Drawing Operations Test"
        primaryStage.isMaximized = true

        val canvas = Canvas(width, height)
        drawDistributionOnCanvas(canvas.graphicsContext2D)
        val rootGroup = Group()
        rootGroup.children.add(canvas)
        primaryStage.scene = Scene(rootGroup)
        primaryStage.show()
    }

    private fun drawDistributionOnCanvas(gc: GraphicsContext) {
        gc.lineWidth = barLineScale

        var x = width / 5.0 + gc.lineWidth
        val y = (height / 3.0) * 2
        val dec = DecimalFormat()

        for (distribution in distribution(throwCount)) {
            gc.stroke = randomColor()
            gc.strokeLine(x, y, x, y - distribution.value / yScale)
            gc.lineWidth = textLineScale
            gc.strokeText(distribution.key.toString(), x - width / 1000.0, y + height / 10.0)
            gc.strokeText("# " + dec.format(distribution.value), x - width / 1000.0, y + height / 5.0)
            gc.lineWidth = barLineScale
            x += width / xScale
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