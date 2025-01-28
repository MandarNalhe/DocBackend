package com.docChain.Docbackend.Controller;

import com.docChain.Docbackend.Service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ShareController {
    @Autowired
    private DocumentService service ;
    @PostMapping("/share")
    public ResponseEntity<String> verify(@RequestParam("file") MultipartFile document) throws IOException {
        String ipfsHash = service.storeOnIpfs(document);
        String blockchainHash = service.storeOnBlockhchain(ipfsHash);
        return ResponseEntity.ok("Document uploaded on IPFS with Hash = "+ipfsHash);
    }
}
