package com.docChain.Docbackend.Service;

import com.docChain.Docbackend.Repo.DocRepo;
/*import com.docChain.Docbackend.Solidity;*/
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service

public class DocumentService {
    @Autowired
    DocRepo repo;
    private static final String AADHAAR_REGEX = "\\d{4} \\d{4} \\d{4}";
    private static final IPFS ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");
    private static final String INFURA_URL = "https://mainnet.infura.io/v3/YOUR_INFURA_PROJECT_ID";*/
    private static final String PRIVATE_KEY = "your_private_key";
    private static final String CONTRACT_ADDRESS = "your_contract_address";*/
   @Autowired
    Solidity sol;*/
   private Web3j web3j;
    private Credentials credentials;
    public boolean verifyDocument(MultipartFile document) throws IOException, TesseractException, NumberFormatException {
        Path tempDir = Files.createTempDirectory("");
        File tempFile = new File(tempDir.toFile(), Objects.requireNonNull(document.getOriginalFilename()));
        document.transferTo(tempFile);
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:/Program Files/Tesseract-OCR/tessdata");
        tesseract.setLanguage("eng");
        tesseract.setTessVariable("user_defined_dpi", "300");
        tesseract.setPageSegMode(6);
        String extractedText = tesseract.doOCR(tempFile);
        System.out.println("Extracted Text: " + extractedText);

        Pattern pattern = Pattern.compile(AADHAAR_REGEX);
        Matcher matcher = pattern.matcher(extractedText);

        if (matcher.find()) {
            /*DocInfo comp=repo.findById(matcher.group()).orElse(null);
            if(comp!= null)*/
            System.out.println("Aadhaar Number Found: " + matcher.group());
            String str = matcher.group().replaceAll("\\s", "");
            System.out.println(str);
            if(repo.findByAadharNumber(str).isPresent()){
                System.out.println("Present");
                return true;
            }else {
                System.out.println("Aadhaar Number not found.");
                return false;
            }
        } else {
            System.out.println("Aadhaar Number not found.");
            return false;
        }
    }
    public String storeOnIpfs(MultipartFile document) throws IOException {
        NamedStreamable.ByteArrayWrapper file = new NamedStreamable.ByteArrayWrapper(Objects.requireNonNull(document.getOriginalFilename()), document.getBytes());
        MerkleNode response = ipfs.add(file).get(0);
        String hash = response.hash.toString();
        System.out.println(hash);
        return hash;
    }

    public String storeOnBlockhchain(String ipfsHash) {
        web3j = Web3j.build(new HttpService(INFURA_URL));
        credentials = Credentials.create(PRIVATE_KEY);

        IPFSHashStorage contract = IPFSHashStorage.load(CONTRACT_ADDRESS, web3j, credentials, new StaticGasProvider(BigInteger.valueOf(200000), BigInteger.valueOf(1_000_000_000L)));

        TransactionReceipt receipt = contract.storeIPFSHash(ipfsHash).send();

        System.out.println("Transaction Hash: " + receipt.getTransactionHash());
        System.out.println("IPFS Hash Stored: " + ipfsHash);
        return "";
    }
}
