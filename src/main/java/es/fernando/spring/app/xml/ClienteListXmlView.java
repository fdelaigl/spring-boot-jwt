package es.fernando.spring.app.xml;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.xml.MarshallingView;

import es.fernando.spring.app.entity.Cliente;

@Component("listar.xml")
public class ClienteListXmlView extends MarshallingView{

	

	@Autowired
	public ClienteListXmlView(Jaxb2Marshaller marshaller) {
		super(marshaller);
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<Cliente> clientes  = (List<Cliente>) model.get("clientes");
		model.remove("clientes");
		model.put("clientelist", new ClienteList(clientes));
		super.renderMergedOutputModel(model, request, response);
	}

}
