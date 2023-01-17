package es.fernando.spring.app.csv;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import es.fernando.spring.app.entity.Cliente;

@Component("listar.csv")
public class ClienteCsvView extends AbstractView {

	public ClienteCsvView() {
		setContentType("text/csv");
	}

	@Override
	protected boolean generatesDownloadContent() {
		return true;
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Content-Disposition", "attachment; filename=\"clientes.csv\"");
		response.setContentType(getContentType());
		@SuppressWarnings("unchecked")
		List<Cliente> clientes = (List<Cliente>) model.get("clientes");
		CsvBeanWriter beanWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		String[] header = { "id", "nombre", "email", "createAt" };
		beanWriter.writeHeader(header);

		for (Cliente cliente : clientes) {
			beanWriter.write(cliente, header);
		}

		beanWriter.close();
	}

}
