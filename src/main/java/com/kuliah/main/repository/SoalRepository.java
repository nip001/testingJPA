package com.kuliah.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kuliah.main.entity.Soal;

public interface SoalRepository extends CrudRepository<Soal, Long>{

	public Soal findByNamaSoal(String nama);
	@Query(value = "select * from Soal WHERE soal.id_plotmatakuliah is NULL", nativeQuery = true)
	public List<Soal> findAllNull();

}
