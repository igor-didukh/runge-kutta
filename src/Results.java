
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

class Results {
    private static String solutionTitle;
    private static Function f1, f2;
    private static String argLetter, f1Letter, f2Letter;
    private static String f1_x, f2_x;
    private static JTextArea textInfo;
    
    static void setTitle(ODESystem system, JTextArea textArea) {
        f1 = system.get_f1();
        f2 = system.get_f2();
        
        argLetter = system.getArg().getLetter();
        f1Letter = f1.getLetter();
        f2Letter = f2.getLetter();
        
        f1_x = String.format("%s(%s)", f1Letter, argLetter);
        f2_x = String.format("%s(%s)", f2Letter, argLetter);
        
        solutionTitle = "Solving the ODE system: " + system.getName();
        solutionTitle += "\n(using classical 4th order Runge-Kutta method (RK4) & Ralston's method)";
        solutionTitle += "\ninitial conditions: ";
        solutionTitle += f1Letter + "(0) = " + system.get_f1().getInitValue() + "; ";
        solutionTitle += f2Letter + "(0) = " + system.get_f2().getInitValue();
        
        textInfo = textArea;
    }
    
    static void drawSolution(Result[] res, Function ref) {
        solutionTitle += "\nreference function " + ref.getName();

        XYSeriesCollection dataset = new XYSeriesCollection();
        
        XYSeries seriesRef = new XYSeries("reference"), 
                 seriesF1RK4 = new XYSeries(f1_x + "_RK4"), seriesF2RK4 = new XYSeries(f2_x + "_RK4"),
                 seriesF1RAL = new XYSeries(f1_x + "_RAL"), seriesF2RAL = new XYSeries(f2_x + "_RAL");

        // Draw reference function
        int n = 100;
        double t0 = Solution.t0;
        double h = (Solution.t1 - t0) / (n - 1);
        
        for (int i = 0; i <= n; i++) {
            double t = t0 + i * h;
            seriesRef.add(t, ref.f(t));                              // point ( t, ref(t) ) - reference
        }
        
        // Draw results calculated by both methods
        for (Result r : res) {
            double t = r.t;
            seriesF1RK4.add(t, r.uRK4); seriesF2RK4.add(t, r.vRK4);
            seriesF1RAL.add(t, r.uRAL); seriesF2RAL.add(t, r.vRAL);
        }
        
        dataset.addSeries(seriesRef);
        dataset.addSeries(seriesF1RK4); dataset.addSeries(seriesF2RK4);
        dataset.addSeries(seriesF1RAL); dataset.addSeries(seriesF2RAL);
        
        JFreeChart jFreeChartMain = ChartFactory.createXYLineChart("", argLetter, "Y", dataset, PlotOrientation.VERTICAL, true, false, false);
        
        TextTitle tt = new TextTitle( solutionTitle, new Font("Times", Font.PLAIN, 14) );
        jFreeChartMain.setTitle(tt);
        
        XYPlot xyPlot = (XYPlot) jFreeChartMain.getPlot();
        xyPlot.setDomainCrosshairVisible(true);
        xyPlot.setRangeCrosshairVisible(true);
        
        final XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) xyPlot.getRenderer();

        renderer.setSeriesPaint(0, Color.BLACK);
        renderer.setSeriesStroke(0, new BasicStroke(1.5f) );
        
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesPaint(2, Color.RED);
        renderer.setSeriesPaint(3, Color.BLUE);
        renderer.setSeriesPaint(4, Color.RED);
        
        ChartPanel CPMain = new ChartPanel(jFreeChartMain);
        
        JFrame frame = new JFrame("Solving");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().add(CPMain);
        int frameWidth = 600, frameHeight = 700;
        frame.setPreferredSize(new Dimension(frameWidth, frameHeight));
        frame.pack();
        
        Solution.showFrame(frame);
    }
    
    private static void printOneSolution_arg(int i, Result res) {
        textInfo.append( argLetter + "(" + i + ") = " + res.t + "\n" );
    }
    
    private static void printOneSolution_f1(int i, Result res) {
        textInfo.append( f1Letter + "_RK4(" + i + ") = " + res.uRK4 + "\n" );
        textInfo.append( f1Letter + "_RAL(" + i + ") = " + res.uRAL + "\n" );
    }
    
    private static void printOneSolution_f2(int i, Result res) {
        textInfo.append( f2Letter + "_RK4(" + i + ") = " + res.vRK4 + "\n" );
        textInfo.append( f2Letter + "_RAL(" + i + ") = " + res.vRAL + "\n\n" );
    }
    
    /**
     * If analitic solution of system not set - print only calculated values
     */
    static void printSolution(Result[] res) {
        textInfo.setText(solutionTitle + "\n\n");
        for (int i = 0; i < res.length; i++) {
            printOneSolution_arg(i, res[i]);
            printOneSolution_f1(i, res[i]);
            printOneSolution_f2(i, res[i]);
        }
    }
    
    /**
     * If analitic solution of system set - print analitic and calculated values
     */
    static void printSolutionWithAnalitic(Result[] res) {
        textInfo.setText(solutionTitle + "\n\n");
        
        for (int i = 0; i < res.length; i++) {
            printOneSolution_arg(i, res[i]);
            textInfo.append( f1Letter + "_ANA(" + i + ") = " + f1.f(res[i].t) + "\n" );
            printOneSolution_f1(i, res[i]);
            textInfo.append( f2Letter + "_ANA(" + i + ") = " + f2.f(res[i].t) + "\n" );
            printOneSolution_f2(i, res[i]);
        }
    }
}