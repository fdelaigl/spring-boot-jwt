package es.fernando.spring.app.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import es.fernando.spring.app.entity.Cliente;
@XmlRootElement(name="clientes")
public class ClienteList {
	
	@XmlElement(name="cliente")
	public List<Cliente> clientes;

	public ClienteList(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public ClienteList() {
	}

	public List<Cliente> getClientes() {
		return clientes;
	}


}
