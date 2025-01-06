package com.wixsite.mupbam1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wixsite.mupbam1.models.Pics;

public interface PhotoRepository extends JpaRepository<Pics, Long> {

}
