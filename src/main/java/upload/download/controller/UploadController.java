package upload.download.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import upload.download.entity.FileEntity;
import upload.download.repository.FileEntityRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UploadController {

    @Autowired
    private FileEntityRepository fileEntityRepository;


    /**
     * Get index page
     *
     * @return index
     */

    @GetMapping("/")
    public String getIndexFilePage() {
        return "index";
    }

    @PostMapping("/")
    public String uploadFiles(@RequestParam("files") MultipartFile[] multipartFiles, Model model) {
        List<String> listFileNames = new ArrayList<>();

        try {
            List<FileEntity> storeFiles = new ArrayList<>();

            for (MultipartFile file : multipartFiles) {
                FileEntity fileEntity = fileEntityRepository.findByFileName(file.getOriginalFilename());

                if (fileEntity != null) {
                    fileEntity.setFileByte(file.getBytes());
                } else {
                    fileEntity = new FileEntity(file.getOriginalFilename(), file.getContentType(), file.getBytes());
                }
                listFileNames.add(file.getOriginalFilename());
                storeFiles.add(fileEntity);
            }

            // save all files
            fileEntityRepository.saveAll(storeFiles);

            // successfully message after uploaded
            model.addAttribute("message", "Files uploaded successfully!");
            model.addAttribute("files", listFileNames);
        } catch (Exception e) {
            // fail message for unsupported file or max size
            model.addAttribute("message", "Fail");
            model.addAttribute("files", listFileNames);
        }
        return "index";
    }
}
