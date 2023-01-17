package es.fernando.spring.app.json;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
@Component("listar.json")
public class ClienteListJsonView extends MappingJackson2JsonView{

	@Override
	protected Object filterModel(Map<String, Object> model) {
		
		return super.filterModel(model);
	}

}
