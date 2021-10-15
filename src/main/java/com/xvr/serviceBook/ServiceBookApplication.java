package com.xvr.serviceBook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@SpringBootApplication
public class ServiceBookApplication {

	public static void main(String[] args) throws IOException {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		String pass = bCryptPasswordEncoder.encode("user");
		Path path = Paths.get("./src/main/resources/data.sql");
		try (RandomAccessFile f = new RandomAccessFile(path.toFile(), "rw")) {
			long aPositionWhereIWantToGo = 165; // 163 ubuntu
			f.seek(aPositionWhereIWantToGo); // this basically reads n bytes in the file
			f.write(pass.getBytes());
			f.seek(247); // 245 ubuntu
			f.write(pass.getBytes());
			f.close();
		}
		/*if (Files.exists(path)){
			try {
				List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
				for (String s: lines){
					System.out.println(s);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
		SpringApplication.run(ServiceBookApplication.class, args);
	}
}
