/**
 * 
 */
package com.community.controller.account;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.broadleafcommerce.common.web.controller.BroadleafAbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bridgegap.course.Program;
import com.bridgegap.program.data.ProgramDataForm;

/**
 * @author naveen.k.ganachari
 *
 */
@Controller
@RequestMapping("/program")
public class ProgramController extends BroadleafAbstractController {

	@PersistenceContext(unitName = "blPU")
	protected EntityManager em;

	@RequestMapping(method = RequestMethod.GET)
	public String registerBG(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("programData") ProgramDataForm programForm) {
		List<Program> lProgram = findProgram(request.getParameter("programId"));
		if (lProgram != null && !lProgram.isEmpty()) {
			programForm.setProgram(lProgram.get(0));
		}
		String lView = "program/program";
		return lView;
	}

	private List<Program> findProgram(String pProgramId) {
		Query query = em.createQuery("SELECT P FROM com.bridgegap.course.Program P where P.id = ?1").setParameter(1,
				Long.parseLong(pProgramId));
		return query.getResultList();
	}

}
