package com.nbr.trp.common.controller;

import com.nbr.trp.common.entity.*;
import com.nbr.trp.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/division")
    public ResponseEntity<?> getDiv() {
        try{
            List<Division> dv = commonService.getAllDivision();
            // return ResponseEntity.ok(new MessageResponse("Representative registered successfully!"));
            return new ResponseEntity<>(dv, HttpStatus.CREATED);

        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/district")
    public ResponseEntity<?> getDistr() {
        try{
            List<District> ds = commonService.getAllDistrict();
            // return ResponseEntity.ok(new MessageResponse("Representative registered successfully!"));
            return new ResponseEntity<>(ds, HttpStatus.CREATED);

        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/thana")
    public ResponseEntity<?> getThana() {
        try{
            List<Thana> ds = commonService.getAllThana();
            // return ResponseEntity.ok(new MessageResponse("Representative registered successfully!"));
            return new ResponseEntity<>(ds, HttpStatus.CREATED);

        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/bank")
    public ResponseEntity<?> getBank() {
        try{
            List<BankName> ds = commonService.getAllBank();
            // return ResponseEntity.ok(new MessageResponse("Representative registered successfully!"));
            return new ResponseEntity<>(ds, HttpStatus.CREATED);

        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/bankdist")
    public ResponseEntity<?> getBankDist() {
        try{
            List<BankDistrict> ds = commonService.getAllBankDist();
            // return ResponseEntity.ok(new MessageResponse("Representative registered successfully!"));
            return new ResponseEntity<>(ds, HttpStatus.CREATED);

        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/bankbranches/{name}/{district}")
    public ResponseEntity<?> getBankBranches(@PathVariable String name, @PathVariable String district) {
        System.out.println(name+" "+district);
        try{
            List<Bank> ds = commonService.getAllBankBranches(name,district);
            // return ResponseEntity.ok(new MessageResponse("Representative registered successfully!"));
            return new ResponseEntity<>(ds, HttpStatus.CREATED);

        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/file")
    public ResponseEntity<Map<String, String>> filepost(@RequestPart("file") MultipartFile file) {
        System.out.println("helllllllllllllo");
        System.out.println("handling request parts: {}"+ file);
        try {

            File f = new ClassPathResource("").getFile();
            final Path path = Paths.get(f.getAbsolutePath() + File.separator + "static" + File.separator + "files");
            System.out.println(path);
            if (!Files.exists(path)) {
                System.out.println("doesnt exist");
                try {
                    Files.createDirectories(path);
                } catch (IOException e) {
                    throw new RuntimeException("Could not initialize folder for upload!");
                }
            } else{
                System.out.println("exist");

            }

            Path filePath = path.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/files/")
                    .path(file.getOriginalFilename())
                    .toUriString();

            var result = Map.of(
                    "filename", file.getOriginalFilename(),
                    "fileUri", fileUri
            );
            return new ResponseEntity<>(result, HttpStatus.OK);


        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
