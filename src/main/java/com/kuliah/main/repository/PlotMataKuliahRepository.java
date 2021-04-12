package com.kuliah.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kuliah.main.entity.PlotMataKuliah;

public interface PlotMataKuliahRepository extends CrudRepository<PlotMataKuliah, Long> {
	@Query(value = "select * from plot_mata_kuliah p JOIN soal on p.id_plot_mata_kuliah = soal.id_plotmatakuliah WHERE soal.id_plotmatakuliah = ?1", nativeQuery = true)
	List<PlotMataKuliah> findByIdPlotmatakuliah(Long idPlotmatakuliah);
}
