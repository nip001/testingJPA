package com.kuliah.main.services;

import java.util.List;

import com.kuliah.main.entity.PlotMataKuliah;

public interface ModelPlotInterface {
	public List<PlotMataKuliah> getAllPlot();
	public List<PlotMataKuliah> getAllPlotByIdPlot(String idPlotmatakuliah);
	public PlotMataKuliah addPlot(PlotMataKuliah plotmatakuliah);
}
