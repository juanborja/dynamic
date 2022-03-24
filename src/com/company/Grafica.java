/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juan
 */
public class Grafica extends JFrame{
    long[]ejex;
    long[]ejey;
    ArrayList<long[]> ys;
    boolean multiple = false;
    String titulo ="";
    String[] st;
    public Grafica(long[] x, List<long[]> y, String graphTitle, String[] seriesTitles){
        ejex = x.clone();
        ys = new ArrayList<>(y);
        titulo =graphTitle;
        st = seriesTitles;
        multiple = true;
        initUI();
    }

    public Grafica(long[]x, long[]y, String t){
        ejex=x.clone();
        ejey=y.clone();
        titulo = t;
     
        initUI();
    }

    private void initUI() {
         XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Line chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private XYDataset createDataset() {


        XYSeriesCollection dataset = new XYSeriesCollection();

        if(multiple){
            for (int i = 0; i < ys.size(); i++){
                XYSeries sr = new XYSeries(st[i]);
                long[] aux = ys.get(i);
                for(int j=0;j<aux.length;j++){
                    sr.add(ejex[j], aux[j]) ;
                }
                dataset.addSeries(sr);
            }

        }
        else{
            XYSeries serie = new XYSeries("Serie");
            dataset.addSeries(serie);
            for(int i=0; i<ejex.length;i++){
                serie.add(ejex[i],ejey[i]);
            }
            dataset.addSeries(serie);
        }
        return dataset;
    }

    private JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Incremento del tiempo en función de la entrada", 
                "Entrada(n)", 
                "Tiempo (en ms)", 
                dataset, 
                PlotOrientation.VERTICAL,
                true, 
                true, 
                false 
        );

        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesStroke(0, new BasicStroke(0.5f));
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.GRAY);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.GRAY);
        chart.getLegend().setFrame(BlockBorder.NONE);
        chart.setTitle(new TextTitle(titulo,
                        new Font("Serif", Font.BOLD, 18)
                )
        );

        return chart;
    }
    
   
    
}
