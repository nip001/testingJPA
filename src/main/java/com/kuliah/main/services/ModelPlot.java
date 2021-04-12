package com.kuliah.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuliah.main.entity.PlotMataKuliah;
import com.kuliah.main.repository.PlotMataKuliahRepository;

@Service
public class ModelPlot implements ModelPlotInterface{
	@Autowired
	PlotMataKuliahRepository plotMK;

	@Override
	public List<PlotMataKuliah> getAllPlot() {
		// TODO Auto-generated method stub
		return (List<PlotMataKuliah>) this.plotMK.findAll();
	}
	
	@Override
	public PlotMataKuliah addPlot(PlotMataKuliah plotmatakuliah) {
		// TODO Auto-generated method stub
		return this.plotMK.save(plotmatakuliah);
	}

	@Override
	public List<PlotMataKuliah> getAllPlotByIdPlot(String idPlotmatakuliah) {
		// TODO Auto-generated method stub
		return this.plotMK.findByIdPlotmatakuliah(Long.parseLong(idPlotmatakuliah));
	}
	
}
