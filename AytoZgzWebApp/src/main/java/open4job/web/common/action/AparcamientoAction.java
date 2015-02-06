package open4job.web.common.action;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import open4job.web.common.form.AparcamientoForm;
import open4job.web.modelo.dao.AparcamientoMotoDAO;
import open4job.web.modelo.vo.AparcamientoMotoVO;

public class AparcamientoAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		AparcamientoForm aparcamientoForm = (AparcamientoForm) form;
	
		AparcamientoMotoDAO aparcamientoMotoDAO = new AparcamientoMotoDAO("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:test/test@54.154.192.80:1521:xe", "test", "test");
		
		
		List<AparcamientoMotoVO> motos = aparcamientoMotoDAO
				.getLstAparcamientoMoto();

		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		if (motos != null) {
			Iterator<AparcamientoMotoVO> iterator = motos.iterator();

			while (iterator.hasNext()) {

				AparcamientoMotoVO moto = iterator.next();
				
				aparcamientoForm.setDescripcion(moto.getDescripcion());
				aparcamientoForm.setIcon(moto.getIcon());
				aparcamientoForm.setId(moto.getId());
				aparcamientoForm.setLastUpdated(moto.getLastUpdated());
				aparcamientoForm.setLatitud(moto.getLatitud());
				aparcamientoForm.setLongitud(moto.getLongitud());
				aparcamientoForm.setPlazas(moto.getPlazas());
				aparcamientoForm.setTitle(moto.getTitle());
				
				out.println( "<a href='Consulta?id="+moto.getId()+"'>"+moto.getDescripcion()+"</a>");
				
				out.println("<br>");
			}
		}
		out.println("</body>");
		out.println("</html>");

	
		
		
		// success es una cadena, un alias, que luego lleva al lugar correcto,
		// asi camiando luego cambiando el alias cambia tb la dirección
		return mapping.findForward("success");
	}

}
