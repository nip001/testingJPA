package com.kuliah.main.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kuliah.main.entity.JawabanTemp;
import com.kuliah.main.entity.Pertanyaan;
import com.kuliah.main.entity.PlotMataKuliah;
import com.kuliah.main.entity.Soal;
import com.kuliah.main.services.ModelDosen;
import com.kuliah.main.services.ModelMahasiswa;
import com.kuliah.main.services.ModelMataKuliah;
import com.kuliah.main.services.ModelPlot;
import com.kuliah.main.services.ModelSoal;
@Controller
public class PlotMKPage {

	@Autowired
	ModelPlot modelPlotMK;

	@Autowired
	ModelMahasiswa modelMahasiswa;
	
	@Autowired
	ModelDosen modelDosen;

	@Autowired
	ModelMataKuliah modelMatakuliah;

	@Autowired
	ModelSoal modelSoal;
	

	@GetMapping("/plotmk/view")
	public String viewPlot(Model model) {
		
		model.addAttribute("listPlot",modelPlotMK.getAllPlot());
		model.addAttribute("active",6);
		return "view_plot";
	}
	
	@GetMapping("/plotmk/add")
	public String viewAddPlot(Model model) {
		
		model.addAttribute("plot_mk",new PlotMataKuliah());
		model.addAttribute("lstMahasiswa",modelMahasiswa.getAllMahasiswa());
		model.addAttribute("lstDosen",modelDosen.getAllDosen());
		model.addAttribute("lstMK",modelMatakuliah.getAllMataKuliah());
		model.addAttribute("soal",modelSoal.getAllSoalNull());
		return "add_plot";
	}
	

	@PostMapping("/plotmk/view")
	  public String addPlot(@ModelAttribute PlotMataKuliah plotmatakuliah, Model model) {
		
		// buat penampung data mahasiswa di halaman htmlnya
//		this.modelMahasiswa.addMahasiswa(mahasiswa);
		this.modelPlotMK.addPlot(plotmatakuliah);
//		model.addAttribute("listMahasisw",modelMahasiswa.getAllMahasiswa());
		model.addAttribute("listPlot",modelPlotMK.getAllPlot());
		
		
		return "redirect:/plotmk/view";
	}

	@GetMapping("/ujian/view/{idMK}")
	public String viewIndexPertanyaan(@PathVariable String idMK,Model model) {
		System.out.println("search===================="+modelPlotMK.getAllPlotByIdPlot(idMK));
		model.addAttribute("plotmk",modelPlotMK.getAllPlotByIdPlot(idMK));
		model.addAttribute("jawaban",new JawabanTemp());
		model.addAttribute("idPlotmata",idMK);
		return "view_ujian";
	}
	


	@PostMapping("/ujian/hasil")
	  public String viewResult(@RequestParam(value="idPlotmata")String idMK, @ModelAttribute("jawaban") JawabanTemp jawaban, Model model) {
		System.out.println(jawaban);
		int counter=0;
		int benar=0;
		int salah=0;
		System.out.println(idMK);
		List<PlotMataKuliah> plotmatakuliah = modelPlotMK.getAllPlotByIdPlot(idMK);
		for (PlotMataKuliah data : plotmatakuliah) {
			for (Soal soal : data.getLstSoal()) {
				for(Pertanyaan pertanyaan : soal.getLstPertanyaan()) {
					String jawabanBenar = pertanyaan.getJawabanBenar();
					if(jawabanBenar.equalsIgnoreCase("1")) {
						jawabanBenar = pertanyaan.getJawaban1();
					}else if(jawabanBenar.equalsIgnoreCase("2")) {
						jawabanBenar = pertanyaan.getJawaban2();
					}else if(jawabanBenar.equalsIgnoreCase("3")) {
						jawabanBenar = pertanyaan.getJawaban3();
					}else {
						jawabanBenar = pertanyaan.getJawaban4();
					}
					if(jawaban.getJawabanUser()[counter].equalsIgnoreCase(jawabanBenar)) {
						benar++;
					}else {
						salah++;
					}
					counter ++;
					
				}
			}
		}
		model.addAttribute("benar",benar);
		model.addAttribute("salah",salah);
		
		
		return "view_hasil";
	}
}
