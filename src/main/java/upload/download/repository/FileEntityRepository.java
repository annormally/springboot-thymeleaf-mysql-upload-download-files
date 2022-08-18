package upload.download.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import upload.download.entity.FileEntity;

@Repository
@EnableJpaRepositories
public interface FileEntityRepository extends JpaRepository<FileEntity, Long> {
    FileEntity findByFileName(String fileName);
}
