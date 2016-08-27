package com.rameez.monitoring.web;

import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RequestMapping("rest")
@RestController
public class MonitorRestController {
	
	private static final Logger LOG = LogManager.getLogger();
	
	@Value("${env}")
	private String environment;
	
	@RequestMapping("/home")
	public String home() {
		System.out.println(LocalDateTime.now() + ":: Entering into Hello Boot World");
		System.out.println("environment :: " + environment);
		// logs debug message
		if (LOG.isDebugEnabled()) {
			LOG.debug("getWelcome is executed!");
		}
		
		LOG.debug("getWelcome is executed!", ()->{ return "debuggggggggggggg";});
		
		LOG.trace("Tracing:::", getLogMessage());
		LOG.trace("Trace Message!");
		LOG.debug("Debug Message!");
		LOG.info("Info Message!");
		LOG.warn("Warn Message!");
		LOG.error("Error Message!");
		LOG.fatal("Fatal Message!");
		return "Hello Boot World!!";
	}
	
	
	private String getLogMessage() {		
		return "Here! Print this...";
	}


	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> posting(@RequestBody String jsonString) {
		LOG.info(":::::::posting:::::::");
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = null;
		try {
			node = mapper.readTree(jsonString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		LOG.info(node);
		
		if(node.has("event_type_id")) {
			LOG.info(node.get("event_type_id"));
		}
		return new ResponseEntity<JsonNode>(node, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getting() {
		LOG.info(":::::::getting:::::::");
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode1 = mapper.createObjectNode();
        objectNode1.put("framework", "Spring Boot");
        objectNode1.put("company", "SomeCompany");
		return new ResponseEntity<ObjectNode>(objectNode1, HttpStatus.OK);
	}
}
