package upload.download.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import upload.download.entity.FileEntity;
import upload.download.entity.FileInfo;
import upload.download.repository.FileEntityRepository;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DownloadController {

    @Autowired
    private FileEntityRepository fileEntityRepository;

    /**
     * Get and display file stored in the database.
     *
     * @return download-file page
     */

    @GetMapping("/download/files")
    public String getListOfFiles(Model model) {
        List<FileInfo> fileInfoList = fileEntityRepository.findAll()
                .stream()
                .map(fileEntity -> {
                            String fileInfoName = fileEntity.getFileName();
                            String fileURL = MvcUriComponentsBuilder
                                    .fromMethodName(
                                            DownloadController.class,
                                            "downloadFile",
                                            fileEntity.getFileName())
                                    .build()
                                    .toString();
                            return new FileInfo(fileInfoName, fileURL);
                        }
                ).collect(Collectors.toList());


        model.addAttribute("files", fileInfoList);
        return "download-file";
    }

    /**
     * Get file to download.
     *
     * @return file from database to be downloaded
     */

    @GetMapping("/download/files/{fileInfoName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileInfoName) {
        FileEntity downloadFileEntity = fileEntityRepository.findByFileName(fileInfoName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + downloadFileEntity.getFileName() + "\"")
                .body(downloadFileEntity.getFileByte());
    }
}
