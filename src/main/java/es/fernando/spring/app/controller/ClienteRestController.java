package es.fernando.spring.app.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import es.fernando.spring.app.entity.Cliente;
import es.fernando.spring.app.service.ClienteService;
import es.fernando.spring.app.xml.ClienteList;

/**
 * The Class AppController.
 */
@Controller
@SessionAttributes("cliente")
public class ClienteRestController {

	/** The dao. */
	@Autowired
	private ClienteService service;
	
	private Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Listar.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping(value={"/listar","/"})
	public String listar(Model model) {
		model.addAttribute("clientes", service.findAll());
		return "listar";
	}

	@GetMapping(value="/listar-rest")
	public @ResponseBody ClienteList listarrest(Model model) {		
		return new ClienteList(service.findAll());
	}
	/**
	 * Form.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/form")
	public String form(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		return "form";
	}
	
	@GetMapping("/ver//{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Cliente cliente = service.fetchByIdWithFacturas(id);//service.findOne(id);
		if(cliente == null) {
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		return "ver";
	}
	
	@GetMapping(value="/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {
		Path pathFoto = Paths.get("uploads").resolve(filename).toAbsolutePath();
		log.info("pathFoto: " + pathFoto);
		Resource recurso = null;
		try {
			recurso = new UrlResource(pathFoto.toUri());
			if(!recurso.exists() || !recurso.isReadable()) {
				throw new RuntimeException("Error: no se puede cargar la imagen: " + pathFoto.toString());
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +  recurso.getFilename() +"\"")
				.body(recurso);
	}
	/**
	 * Guardar.
	 *
	 * @param cliente the cliente
	 * @param result the result
	 * @return the string
	 */
	@PostMapping(value = "/form")
	public String guardar(@Valid Cliente cliente, BindingResult result, @RequestParam("file") MultipartFile foto, SessionStatus status) {
		if (result.hasErrors()) {
			return "form";
		}
		if (!foto.isEmpty()) {
			if(cliente.getId() != null && cliente.getId() > 0 && cliente.getFoto() != null && cliente.getFoto().length() > 0){
				Path rootPath = Paths.get("uploads").resolve(cliente.getFoto()).toAbsolutePath();
				File  archivo =  rootPath.toFile();
				archivo.delete();
			}
			String uniqueFilename = UUID.randomUUID().toString() + "_" + foto.getOriginalFilename();
			Path rootPath = Paths.get("uploads").resolve(uniqueFilename);

			Path rootAbsolutPath = rootPath.toAbsolutePath();
			
			log.info("rootPath: " + rootPath);
			log.info("rootAbsolutPath: " + rootAbsolutPath);

			try {

				Files.copy(foto.getInputStream(), rootAbsolutPath);
				

				cliente.setFoto(uniqueFilename);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		service.save(cliente);
		status.setComplete();
		return "redirect:/listar";
	}

	/**
	 * Form.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping("/form/{id}")
	public String form(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Cliente cliente = null;

		if (id > 0) {
			cliente = service.findOne(id);
		} else {
			return "redirect:listar";
		}
		model.put("cliente", cliente);
		return "form";
	}

	/**
	 * Eliminar.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		if (id > 0) {
			Cliente cliente = service.findOne(id);
			
			service.delete(id);
			Path rootPath = Paths.get("uploads").resolve(cliente.getFoto()).toAbsolutePath();
			File  archivo =  rootPath.toFile();
			archivo.delete();
		}
		return "redirect:/listar";

	}
}
