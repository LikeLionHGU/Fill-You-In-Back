package com.likelion.fillyouinback.folder.repository;

import com.likelion.fillyouinback.folder.domain.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {}
