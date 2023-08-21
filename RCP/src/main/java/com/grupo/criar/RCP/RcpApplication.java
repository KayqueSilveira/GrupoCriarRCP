package com.grupo.criar.RCP;

import com.grupo.criar.RCP.Models.Pilot;
import com.grupo.criar.RCP.Models.Race;
import com.sun.tools.javac.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class RcpApplication {

	public static void main(String[] args) {
		SpringApplication.run(RcpApplication.class, args);
	}
}

