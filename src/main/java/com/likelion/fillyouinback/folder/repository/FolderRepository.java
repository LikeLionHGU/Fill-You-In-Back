package com.likelion.fillyouinback.folder.repository;

import com.likelion.fillyouinback.folder.domain.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {
  @Query("SELECT f FROM Folder f WHERE f.category.id = :categoryId")
  List<Folder> findByCategoryId(Long categoryId);

  @Modifying
  @Query("DELETE FROM Folder f WHERE f.category.id = :categoryId")
  void deleteByCategoryId(Long categoryId);
}
