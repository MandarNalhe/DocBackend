package com.docChain.Docbackend.Controller;

import com.docChain.Docbackend.Service.DocumentService;
import org.apache.pdfbox.jbig2.util.log.Logger;
import org.apache.pdfbox.jbig2.util.log.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class DocumentController {
    @Autowired
    private DocumentService service ;

    @PostMapping("/verify")
    public ResponseEntity<String> verify(@RequestParam("file")MultipartFile document){
        Logger logger = LoggerFactory.getLogger(DocumentController.class);
        logger.info("File uploaded: " + document.getOriginalFilename() + " with size: " + document.getSize());
        try {
            boolean valid = service.verifyDocument(document);
            if(valid){
                return ResponseEntity.ok("Document verified");
            }else{
                return ResponseEntity.status(400).body("Document verification failed");
            }

            /*if (valid) {
                String ipfsHash = service.storeOnIpfs(document);
                String blockchainHash = service.storeOnBlockhchain(ipfsHash);
                return ResponseEntity.ok("Document verified and Shared on BlockChain = "+blockchainHash);
            }else{
                return ResponseEntity.status(400).body("Document verification failed");
            }*/
        }catch (Exception e){
            return ResponseEntity.status(500).body("Error "+e.getMessage());
        }
    }
}
