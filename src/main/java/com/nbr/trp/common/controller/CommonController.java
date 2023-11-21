package com.nbr.trp.common.controller;

import com.nbr.trp.common.entity.*;
import com.nbr.trp.common.service.CommonService;
import com.nbr.trp.common.service.FileUploadService;
import com.nbr.trp.log.LoggerController;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
import static org.springframework.web.servlet.function.ServerResponse.ok;

@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api/common")
public class CommonController {

    @Autowired
    public CommonService commonService;

    @Autowired
    public FileUploadService fileUploadService;

    @Autowired
    LoggerController loggerController;


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/division")
    public ResponseEntity<?> getDiv(HttpServletRequest request) {
        try{
            String ip = commonService.getIPAddress(request);
            List<Division> dv = commonService.getAllDivision();
            loggerController.ListGeneration("","All Division","",ip);
            // return ResponseEntity.ok(new MessageResponse("Representative registered successfully!"));
            return new ResponseEntity<>(dv, HttpStatus.CREATED);

        }catch(Exception e){
            loggerController.ErrorHandler(e);

            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/district")
    public ResponseEntity<?> getDistr(HttpServletRequest request) {
        try{
            String ip = commonService.getIPAddress(request);
            List<District> ds = commonService.getAllDistrict();
            loggerController.ListGeneration("","All Division","",ip);
            // return ResponseEntity.ok(new MessageResponse("Representative registered successfully!"));
            return new ResponseEntity<>(ds, HttpStatus.CREATED);

        }catch(Exception e){
            loggerController.ErrorHandler(e);

            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/thana")
    public ResponseEntity<?> getThana(HttpServletRequest request) {
        String ip = commonService.getIPAddress(request);

        try{

            List<Thana> ds = commonService.getAllThana();
            loggerController.ListGeneration("","All Thana","",ip);
            // return ResponseEntity.ok(new MessageResponse("Representative registered successfully!"));
            return new ResponseEntity<>(ds, HttpStatus.CREATED);

        }catch(Exception e){
            loggerController.ErrorHandler(e);

            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/bank")
    public ResponseEntity<?> getBank(HttpServletRequest request) {
        String ip = commonService.getIPAddress(request);
        try{
            List<BankName> ds = commonService.getAllBank();
            loggerController.ListGeneration("","All Bank","",ip);
            // return ResponseEntity.ok(new MessageResponse("Representative registered successfully!"));
            return new ResponseEntity<>(ds, HttpStatus.CREATED);

        }catch(Exception e){
            loggerController.ErrorHandler(e);

            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/bankdist")
    public ResponseEntity<?> getBankDist(HttpServletRequest request) {
        String ip = commonService.getIPAddress(request);

        try{
            List<BankDistrict> ds = commonService.getAllBankDist();
            loggerController.ListGeneration("","All Bank District","",ip);
            // return ResponseEntity.ok(new MessageResponse("Representative registered successfully!"));
            return new ResponseEntity<>(ds, HttpStatus.CREATED);

        }catch(Exception e){
            loggerController.ErrorHandler(e);

            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/citycorporation")
    public ResponseEntity<?> getCityCorporation(HttpServletRequest request) {
        String ip = commonService.getIPAddress(request);
        try{
            List<CityCorporation> ds = commonService.getAllCityCorporation();
            loggerController.ListGeneration("","All City Corporation","",ip);
            return new ResponseEntity<>(ds, HttpStatus.CREATED);

        }catch(Exception e){
            loggerController.ErrorHandler(e);

            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/bankbranches/{name}/{district}")
    public ResponseEntity<?> getBankBranches(HttpServletRequest request,@PathVariable String name, @PathVariable String district) {
        String ip = commonService.getIPAddress(request);
        try{
            List<Bank> ds = commonService.getAllBankBranches(name,district);
            loggerController.ListGeneration("","All Branches of "+name+ " in "+district,"",ip);
            return new ResponseEntity<>(ds, HttpStatus.CREATED);

        }catch(Exception e){
            loggerController.ErrorHandler(e);

            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/file")
    public ResponseEntity<FileResponse> filepost(HttpServletRequest request, @RequestPart("file") MultipartFile file) {
        String ip = commonService.getIPAddress(request);
        try {

            File f = new ClassPathResource("").getFile();
            final Path path = Paths.get(f.getAbsolutePath() + File.separator + "static" + File.separator + "files");
            FileResponse fileResponse = fileUploadService.uploadFile(path, file, 1);
            loggerController.IncomingRequest(ip,"File Upload");
            return new ResponseEntity<>(fileResponse, HttpStatus.OK);


        } catch (IOException e) {
            loggerController.ErrorHandler(e);

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/file/{filename}")
    public ResponseEntity<Resource> load(HttpServletRequest request, @PathVariable String filename) {
        String ip = commonService.getIPAddress(request);
        try {
            Path root = Paths.get("target/classes/static/files");
            Resource resource = fileUploadService.retrieve(root, filename,1);
            loggerController.IncomingRequest(ip,"File Retrieval");

            return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.parseMediaType("application/pdf"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);


        } catch (Exception e) {
            loggerController.ErrorHandler(e);

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .contentType(MediaType.parseMediaType("application/pdf"))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +"\"").body(null);

        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/photo")
    public ResponseEntity<FileResponse> photopost(HttpServletRequest request,@RequestPart("file") MultipartFile file) {
        String ip = commonService.getIPAddress(request);
        try {
            File f = new ClassPathResource("").getFile();
            final Path path = Paths.get(f.getAbsolutePath() + File.separator + "static" + File.separator + "photo");
            FileResponse fileResponse = fileUploadService.uploadFile(path, file, 0);
            loggerController.IncomingRequest(ip,"Photo Upload");
            return new ResponseEntity<>(fileResponse, HttpStatus.OK);

        } catch (IOException e) {
            loggerController.ErrorHandler(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value="/photo/{filename}",produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> loadPhoto(HttpServletRequest request,@PathVariable String filename) {
        String ip = commonService.getIPAddress(request);
        try {
            Path root = Paths.get("target/classes/static/photo");
            System.out.println(root);

            Resource resource = fileUploadService.retrieve(root, filename,0);
            loggerController.IncomingRequest(ip,"Photo Retrieval");

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentLength(resource.contentLength())
                    .body(resource);


        } catch (Exception e) {
            loggerController.ErrorHandler(e);
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(null);

        }
    }
}
