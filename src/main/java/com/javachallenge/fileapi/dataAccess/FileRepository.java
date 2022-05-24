package com.javachallenge.fileapi.dataAccess;

import com.javachallenge.fileapi.entities.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileData, Long> {


}
